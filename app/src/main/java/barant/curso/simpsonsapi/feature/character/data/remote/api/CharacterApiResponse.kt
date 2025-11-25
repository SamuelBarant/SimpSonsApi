package barant.curso.simpsonsapi.feature.character.data.remote.api

data class CharacterApiResponse(
    val count: Int,
    val next: String?,
    val prev: String?,
    val pages: Int,
    val results: List<CharacterApiModel>
)
