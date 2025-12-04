package barant.curso.simpsonsapi.feature.character.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import barant.curso.simpsonsapi.core.data.local.cache.AppCache
import barant.curso.simpsonsapi.feature.character.data.local.room.character.toDomain
import barant.curso.simpsonsapi.feature.character.data.paging.CharacterRemoteMediator
import barant.curso.simpsonsapi.feature.character.data.remote.api.CharacterApiService
import barant.curso.simpsonsapi.feature.character.domain.Character
import barant.curso.simpsonsapi.feature.character.domain.CharacterRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

@OptIn(ExperimentalPagingApi::class)
class CharacterDataSourceRepository(
    private val database: AppCache,
    private val apiService: CharacterApiService
) : CharacterRepository {
    override fun getPageCharacters(): Flow<PagingData<Character>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            remoteMediator = CharacterRemoteMediator(database, apiService),
            pagingSourceFactory = { database.characterDao().pagingSource() }
        ).flow
            .map { pagingData ->
                pagingData.map { it.toDomain() }
            }
    }

    override suspend fun getByIdCharacters(id: Int): Character {
        return database.characterDao().getById(id).toDomain()
    }
}