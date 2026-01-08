package barant.curso.simpsonsapi.feature.character.presentation

import androidx.annotation.DrawableRes

data class CharacterUi(
    @DrawableRes val imageRes: Int,
    val name: String,
    val age: String,
    val gender: String,
    val occupation: String,
    val phrase: String
)
