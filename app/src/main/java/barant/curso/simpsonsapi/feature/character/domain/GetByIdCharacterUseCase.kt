package barant.curso.simpsonsapi.feature.character.domain

import barant.curso.simpsonsapi.feature.character.data.CharacterDataSourceRepository

class GetByIdCharacterUseCase(private val repository: CharacterDataSourceRepository) {
    suspend operator fun invoke(id:Int): Character{
        return repository.getByIdCharacters(id)
    }
}