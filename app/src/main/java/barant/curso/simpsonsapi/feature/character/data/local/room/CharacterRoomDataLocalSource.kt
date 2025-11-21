package barant.curso.simpsonsapi.feature.character.data.local.room

import barant.curso.simpsonsapi.core.error.ErrorApp
import barant.curso.simpsonsapi.feature.character.domain.Character

class CharacterRoomDataLocalSource {
    val phrases: List<String> = listOf(
        "Doh!",
        "Why you little...!",
        "Woo-hoo!",
        "Mmm... (food)... *drooling*",
        "Stupid Flanders!",
        "Shut up Flanders!",
        "AAAAGHH!",
        "Lisa, knock off that racket!",
        "Uh oh, the boss.",
        "Lets all go out for frosty chocolate milkshakes!",
        "Whatever, Ill be at Moes.",
        "I am evil Ho-mer! I am evil Ho-mer! I am evil Ho-mer!",
        "Better them than me.",
        "Better them than me... Oh wait, that was me.",
        "Marge, my face hurts again!"
    )
    val list: MutableList<Character> = mutableListOf(
        Character(
            1,
            "homer",
            25,
            "Hombre",
            "15/5/2006",
            "Nuclear",
            "alive",
            phrases,
            "lkj"
        )
    )

    suspend fun getCharacter(): Result<List<Character>> {
        if (list.isEmpty()) {
            return Result.failure(ErrorApp.DataEmptyErrorApp)
        }
        return Result.success(list)
    }

    suspend fun getByIdCharacter(id: Int): Result<Character> {
        if (list.isEmpty()) {
            return Result.failure(ErrorApp.DataEmptyErrorApp)
        }
        return Result.success(list.find { it.id == id }!!)
    }
}