package barant.curso.simpsonsapi.feature.character.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import barant.curso.simpsonsapi.feature.character.data.remote.api.CharactersApiRemoteDataSource
import barant.curso.simpsonsapi.feature.character.data.remote.paging.CharacterPagingSource
import barant.curso.simpsonsapi.feature.character.domain.Character
import barant.curso.simpsonsapi.feature.character.domain.CharacterRepository
import kotlinx.coroutines.flow.Flow

class CharacterDataSourceRepository(
    private val api: CharactersApiRemoteDataSource,
) : CharacterRepository {
    override fun getPageCharacters(): Flow<PagingData<Character>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { CharacterPagingSource(api) }
        ).flow
    }
}