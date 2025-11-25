package barant.curso.simpsonsapi.feature.character.domain

class GetAllCharacterUseCase(private val repository: CharacterRepository) {
    suspend operator fun invoke(page: Int): Result<List<Character>> {
        return repository.getCharacters(page)
    }
}