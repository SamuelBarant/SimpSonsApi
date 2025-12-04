package barant.curso.simpsonsapi.feature.character.presentation.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import barant.curso.simpsonsapi.core.domain.error.ErrorApp
import barant.curso.simpsonsapi.feature.character.domain.Character
import barant.curso.simpsonsapi.feature.character.domain.GetByIdCharacterUseCase
import kotlinx.coroutines.launch

class CharacterDetailViewModel(private val getById: GetByIdCharacterUseCase) : ViewModel() {
    private val _uiState = MutableLiveData<UiState>()
    val uiState: LiveData<UiState> = _uiState

    fun loadCharacter(id: Int) {
        viewModelScope.launch {
            _uiState.value = UiState(isLoading = true)

            try {
                val character = getById(id)
                _uiState.value = UiState(data = character)
            } catch (e: ErrorApp) {
                _uiState.value = UiState(error = e)
            }
        }
    }

    data class UiState(
        val isLoading: Boolean = false,
        val data: Character? = null,
        val error: ErrorApp? = null
    )

}