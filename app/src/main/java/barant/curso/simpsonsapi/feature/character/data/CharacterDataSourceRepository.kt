package barant.curso.simpsonsapi.feature.character.data

import barant.curso.simpsonsapi.core.data.remote.local.xml.SharedPreferenceStorage
import barant.curso.simpsonsapi.feature.character.data.local.sharedPreference.CharacterSharedPreferenceDataLocalSource
import barant.curso.simpsonsapi.feature.character.data.remote.api.CharactersApiRemoteDataSource
import barant.curso.simpsonsapi.feature.character.domain.Character
import barant.curso.simpsonsapi.feature.character.domain.CharacterRepository

class CharacterDataSourceRepository(
    private val api: CharactersApiRemoteDataSource,
    private val cache: CharacterSharedPreferenceDataLocalSource
) : CharacterRepository {
    override suspend fun getCharacters(page: Int): Result<List<Character>> {
        return api.getCharacterPage(page)
    }

    override suspend fun getByIdCharacter(id: Int): Result<Character> {
        return api.getByIdCharacter(id)
    }
}