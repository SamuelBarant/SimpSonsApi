package barant.curso.simpsonsapi.feature.character.domain

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {
    fun getPageCharacters(): Flow<PagingData<Character>>
    suspend fun getByIdCharacters(id: Int): Character
}