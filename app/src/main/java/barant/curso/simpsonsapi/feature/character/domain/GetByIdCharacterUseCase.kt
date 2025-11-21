package barant.curso.simpsonsapi.feature.character.domain

class GetByIdCharacterUseCase(private val repository: CharacterRepository) {
    suspend operator fun invoke(id: Int): Result<Character> {
        return repository.getByIdCharacter(id)
    }
}