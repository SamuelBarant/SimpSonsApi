package barant.curso.simpsonsapi.feature.character.data.remote.api

import barant.curso.simpsonsapi.core.data.remote.api.apiCall
import barant.curso.simpsonsapi.feature.character.domain.Character
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CharactersApiRemoteDataSource(
    private val characterApiService: CharacterApiService,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    suspend fun getCharacters(): Result<List<Character>> = withContext(dispatcher) {
        val resultApi = apiCall { characterApiService.getAllCharacters() }
        resultApi.map { it.results.map { item -> item.toDomain() } }
    }

    suspend fun getByIdCharacter(id: Int): Result<Character>{
        return withContext(dispatcher) {
            val resultApi = apiCall { characterApiService.getByIdCharacter(id) }
            resultApi.map { it.toDomain() }
        }
    }
}