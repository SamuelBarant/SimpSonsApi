package barant.curso.simpsonsapi.feature.character.presentation.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import barant.curso.simpsonsapi.core.domain.error.ErrorApp
import barant.curso.simpsonsapi.feature.character.domain.Character
import barant.curso.simpsonsapi.feature.character.domain.GetAllCharacterUseCase
import kotlinx.coroutines.launch

class CharacterListViewModel(private val getAll: GetAllCharacterUseCase) : ViewModel() {

    private val _uiState = MutableLiveData<UiState>()
    val uiState: LiveData<UiState> = _uiState

    fun loadCharacters() {
        viewModelScope.launch {
            _uiState.value = UiState(isLoading = true)
            getAll().fold({ onSuccess(it) }, { onError(it as ErrorApp) })
        }
    }

    fun onSuccess(list: List<Character>) {
        _uiState.value = UiState(data = list)
    }

    fun onError(error: ErrorApp) {
        _uiState.value = UiState(error = error)
    }

    data class UiState(
        val isLoading: Boolean = false,
        val data: List<Character>? = null,
        val error: ErrorApp? = null
    )
}