package barant.curso.simpsonsapi.feature.character.presentation.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import barant.curso.simpsonsapi.core.domain.error.ErrorApp
import barant.curso.simpsonsapi.feature.character.domain.Character
import barant.curso.simpsonsapi.feature.character.domain.GetByIdCharacterUseCase
import kotlinx.coroutines.launch

class CharacterDetailViewModel(val getByID: GetByIdCharacterUseCase) : ViewModel() {
    private val _uiState = MutableLiveData<UiSate>()
    val uiState: LiveData<UiSate> = _uiState

    fun loadCharacter(id: Int) {
        viewModelScope.launch {
            _uiState.value = UiSate(isLoading = true)
            getByID(id).fold(
                { onSuccess(it) },
                { onFailure(it as ErrorApp) }
            )
        }
    }

    fun onSuccess(character: Character) {
        _uiState.value = UiSate(data = character)
    }

    fun onFailure(error: ErrorApp) {
        _uiState.value = UiSate(error = error)
    }

    data class UiSate(
        val isLoading: Boolean = false,
        val error: ErrorApp? = null,
        val data: Character? = null,
    )
}