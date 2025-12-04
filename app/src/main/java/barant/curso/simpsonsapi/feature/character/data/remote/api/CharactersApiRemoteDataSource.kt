package barant.curso.simpsonsapi.feature.character.data.remote.api

import android.util.Log
import barant.curso.simpsonsapi.core.data.remote.api.apiCall
import barant.curso.simpsonsapi.feature.character.domain.Character
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CharactersApiRemoteDataSource(
    private val characterApiService: CharacterApiService,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    suspend fun getCharactersPage(page: Int): List<Character> =
        withContext(dispatcher) {
            val resultApi = apiCall { characterApiService.getPagesItems(page) }
            Log.d("page", resultApi.toString())

            resultApi.getOrElse { throw it }.results.map { it.toDomain() }
        }

    suspend fun getByIdCharacter(id: Int): Character =
        withContext(dispatcher) {
            val resultApi = apiCall { characterApiService.getByIdCharacter(id) }
            resultApi.getOrElse { throw it }.toDomain()
        }
}