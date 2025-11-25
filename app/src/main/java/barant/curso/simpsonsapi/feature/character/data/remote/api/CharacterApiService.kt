package barant.curso.simpsonsapi.feature.character.data.remote.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface CharacterApiService {
    @GET("api/characters/{id}")
    suspend fun getByIdCharacter(@Path("id") id: Int): Response<CharacterApiModel>

    @GET("/api/characters")
    suspend fun getPagesItems(@Query("page") page: Int): Response<CharacterApiResponse>
}