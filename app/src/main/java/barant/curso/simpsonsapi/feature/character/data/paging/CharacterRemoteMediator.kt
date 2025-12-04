package barant.curso.simpsonsapi.feature.character.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadState.Loading.endOfPaginationReached
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import barant.curso.simpsonsapi.core.data.local.cache.AppCache
import barant.curso.simpsonsapi.feature.character.data.local.room.character.CharacterEntity
import barant.curso.simpsonsapi.feature.character.data.local.room.character.toEntity
import barant.curso.simpsonsapi.feature.character.data.local.room.remoteKeys.RemoteKeysEntity
import barant.curso.simpsonsapi.feature.character.data.remote.api.CharacterApiService
import barant.curso.simpsonsapi.feature.character.data.remote.api.toDomain
import barant.curso.simpsonsapi.feature.character.domain.Character

@OptIn(ExperimentalPagingApi::class)
class CharacterRemoteMediator(
    private val database: AppCache,
    private val apiService: CharacterApiService
): RemoteMediator<Int, CharacterEntity>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, CharacterEntity>
    ): MediatorResult {
        try {
            val page = when (loadType) {
                LoadType.REFRESH -> 1
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull() ?: return MediatorResult.Success(endOfPaginationReached = true)
                    val remoteKeys = database.remoteKeysDao().remoteKeysCharacterId(lastItem.id)
                    remoteKeys?.nextKey ?: return MediatorResult.Success(endOfPaginationReached = true)
                }
            }

            val responseApi = apiService.getPagesItems(page)
            val result = responseApi.body()?.results?.map { it.toDomain() } ?: emptyList()
            val endOfPaginationReached = result.isEmpty()

            val keys = result.map { character ->
                RemoteKeysEntity(
                    characterId = character.id,
                    prevKey = if (page == 1) null else page - 1,
                    nextKey = if (endOfPaginationReached) null else page + 1
                )
            }

            database.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    database.remoteKeysDao().clearRemoteKeys()
                    database.characterDao().clearAll()
                }
                database.characterDao().insertAll(result.map { it.toEntity() })
                database.remoteKeysDao().insertAll(keys)
            }

            return MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (e: Exception){
            return MediatorResult.Error(e)
        }
    }

    override suspend fun initialize(): InitializeAction {
        val lastFetch = database.remoteKeysDao().getLastFetchTime() ?: 0L
        val currentTime = System.currentTimeMillis()
        val refreshInterval = 24 * 60 * 60 * 1000 // 24 horas

        return if (currentTime - lastFetch > refreshInterval) {
            InitializeAction.LAUNCH_INITIAL_REFRESH
        } else {
            InitializeAction.SKIP_INITIAL_REFRESH
        }
    }

}