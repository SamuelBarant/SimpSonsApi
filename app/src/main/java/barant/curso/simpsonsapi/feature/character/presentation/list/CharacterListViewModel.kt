package barant.curso.simpsonsapi.feature.character.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import barant.curso.simpsonsapi.feature.character.domain.GetPageCharacterUseCase

class CharacterListViewModel(private val getPages: GetPageCharacterUseCase) : ViewModel() {
    val characters = getPages().cachedIn(viewModelScope)
}