package barant.curso.simpsonsapi.feature.character.domain

interface CharacterRepository {
    suspend fun getAllCharacter(): Result<List<Character>>
    suspend fun getByIdCharacter(id: Int): Result<Character>
}