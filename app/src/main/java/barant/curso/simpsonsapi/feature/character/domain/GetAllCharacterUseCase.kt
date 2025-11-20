package barant.curso.simpsonsapi.feature.character.domain

class GetAllCharacterUseCase(private val repository: CharacterRepository) {
    suspend operator fun invoke(): Result<List<Character>> {
        return repository.getAllCharacter()
    }
}