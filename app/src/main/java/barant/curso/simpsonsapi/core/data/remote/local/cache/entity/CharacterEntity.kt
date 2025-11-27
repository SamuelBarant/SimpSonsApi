package barant.curso.simpsonsapi.core.data.remote.local.cache.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "character")
data class CharacterEntity (
    @PrimaryKey val id: Int,
    val name: String,
    val age: Int?,
    val gender: String,
    val birthdate: String?,
    val occupation: String,
    val status: String,
    val phrase: List<String>?,
    val img: String
)