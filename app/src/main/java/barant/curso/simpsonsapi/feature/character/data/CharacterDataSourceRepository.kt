package barant.curso.simpsonsapi.feature.character.data

import barant.curso.simpsonsapi.feature.character.data.local.room.CharacterRoomDataLocalSource
import barant.curso.simpsonsapi.feature.character.data.local.sharedPreference.CharacterSharedPreferenceDataLocalSource
import barant.curso.simpsonsapi.feature.character.data.remote.api.CharacterApiDataRemoteSource
import barant.curso.simpsonsapi.feature.character.domain.Character
import barant.curso.simpsonsapi.feature.character.domain.CharacterRepository

class CharacterDataSourceRepository(
    private val api: CharacterApiDataRemoteSource,
    private val sp: CharacterSharedPreferenceDataLocalSource,
    private val room: CharacterRoomDataLocalSource
) : CharacterRepository {
    override suspend fun getAllCharacter(): Result<List<Character>> {
        return api.getCharacter()
    }

    override suspend fun getByIdCharacter(id: Int): Result<Character> {
        return api.getByIdCharacter(id)
    }

}