package barant.curso.simpsonsapi.feature.character.data

import barant.curso.simpsonsapi.feature.character.data.remote.api.CharactersApiRemoteDataSource
import barant.curso.simpsonsapi.feature.character.domain.Character
import barant.curso.simpsonsapi.feature.character.domain.CharacterRepository

class CharacterDataSourceRepository(
    private val api: CharactersApiRemoteDataSource,
) : CharacterRepository {
    override suspend fun getAllCharacter(): Result<List<Character>> {
        return api.getCharacters()
    }

    override suspend fun getByIdCharacter(id: Int): Result<Character> {
        return api.getByIdCharacter(id)
    }

}