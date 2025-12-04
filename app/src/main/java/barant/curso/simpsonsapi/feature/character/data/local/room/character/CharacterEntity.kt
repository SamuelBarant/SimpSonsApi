package barant.curso.simpsonsapi.feature.character.data.local.room.character

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "character")
data class CharacterEntity (
    @PrimaryKey val id: Int,
    val name: String,
    val age: Int?,
    val birthdate: String?,
    val gender: String,
    val occupation: String,
    val phrases: List<String>?,
    val status: String,
    val portrait_path: String
)