package barant.curso.simpsonsapi.feature.character.domain

class GetByIdCharacterUseCase(private val repository: CharacterRepository) {
    suspend operator fun invoke(id:Int): Result<Character> {
        return try {
            repository.getByIdCharacter(id)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}