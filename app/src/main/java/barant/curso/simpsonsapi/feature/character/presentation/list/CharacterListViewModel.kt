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

    private var currentPage = 1
    private var loadedCharacters = mutableListOf<Character>()
    private var isLoadingPage = false
    private val _uiState = MutableLiveData<UiState>()
    val uiState: LiveData<UiState> = _uiState

    fun loadCharacters() {
        loadPage(1)
    }

    fun loadNextCharacters() {
        if (!isLoadingPage) {
            loadPage(currentPage + 1)
        }
    }

    private fun loadPage(page: Int) {
        viewModelScope.launch {
            isLoadingPage = true
            _uiState.value = UiState(isLoading = true, data = loadedCharacters.toList())

            getAll(page).fold(
                { onSuccess(page, it) },
                { onError(it as ErrorApp) }
            )

            isLoadingPage = false
        }
    }

    fun onSuccess(page: Int, list: List<Character>) {
        currentPage = page
        loadedCharacters.addAll(list)
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