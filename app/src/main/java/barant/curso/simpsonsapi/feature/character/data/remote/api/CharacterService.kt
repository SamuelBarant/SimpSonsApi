package barant.curso.simpsonsapi.feature.character.data.remote.api

import barant.curso.simpsonsapi.feature.character.domain.Character
import okhttp3.Response
import retrofit2.http.GET


interface CharacterService {
    @GET("characters")
    suspend fun getAllCharacters(): Response<List<Character>>
}