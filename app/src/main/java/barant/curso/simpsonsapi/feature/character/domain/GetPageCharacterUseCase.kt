package barant.curso.simpsonsapi.feature.character.domain

import androidx.paging.PagingData
import androidx.paging.filter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetPageCharacterUseCase(private val repository: CharacterRepository) {
    operator fun invoke(query: String): Flow<PagingData<Character>> {
        return repository.getPageCharacters().map { pagingData ->
            if (query.isBlank()) {
                pagingData
            } else {
                pagingData.filter { character ->
                    character.name.contains(query, ignoreCase = true)
                }
            }
        }
    }
}