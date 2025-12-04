package barant.curso.simpsonsapi.feature.character.domain

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

class GetPageCharacterUseCase(private val repository: CharacterRepository) {
    operator fun invoke(): Flow<PagingData<Character>> {
        return repository.getPageCharacters()
    }
}