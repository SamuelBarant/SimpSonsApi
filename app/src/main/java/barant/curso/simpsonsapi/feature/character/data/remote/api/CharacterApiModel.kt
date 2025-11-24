package barant.curso.simpsonsapi.feature.character.data.remote.api

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonIgnoreUnknownKeys


@Serializable
@JsonIgnoreUnknownKeys
@OptIn(ExperimentalSerializationApi::class)
data class CharacterApiModel(
    val id: Int,
    val age: Int,
    val birthdate: String,
    val gender: String,
    val name: String,
    val occupation: String,
    val portrait_path: String,
    val phrases: List<String>?,
    val status: String
)