package barant.curso.simpsonsapi.feature.character.presentation.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import barant.curso.simpsonsapi.core.domain.error.ErrorApp
import barant.curso.simpsonsapi.feature.character.domain.Character
import barant.curso.simpsonsapi.feature.character.domain.GetPageCharacterUseCase
import kotlinx.coroutines.launch

class CharacterListViewModel(private val getPages: GetPageCharacterUseCase) : ViewModel() {
    val characters = getPages().cachedIn(viewModelScope)

    
}