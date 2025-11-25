package barant.curso.simpsonsapi.feature.character.domain

interface CharacterRepository {
    suspend fun getCharacters(page: Int): Result<List<Character>>
    suspend fun getByIdCharacter(id: Int): Result<Character>
}