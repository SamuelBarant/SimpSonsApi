package barant.curso.simpsonsapi.feature.character.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Character(
    val id: Int,
    val name: String,
    val age: Int,
    val gender: String,
    val birthdate: String?,
    val occupation: String,
    val status: String,
    val phrase: List<String>?,
    val img: String
) : Parcelable
