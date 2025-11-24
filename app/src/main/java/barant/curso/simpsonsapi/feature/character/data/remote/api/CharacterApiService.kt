package barant.curso.simpsonsapi.feature.character.data.remote.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path


interface CharacterApiService {
    @GET("api/characters")
    suspend fun getAllCharacters(): Response<CharacterApiResponse>
    @GET("api/characters/{id}")
    suspend fun getByIdCharacter(@Path("id") id: Int): Response<CharacterApiModel>
}