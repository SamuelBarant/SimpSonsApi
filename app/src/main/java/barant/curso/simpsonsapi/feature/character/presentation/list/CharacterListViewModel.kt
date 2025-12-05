package barant.curso.simpsonsapi.feature.character.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import barant.curso.simpsonsapi.feature.character.domain.GetPageCharacterUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapLatest

class CharacterListViewModel(private val getPages: GetPageCharacterUseCase) : ViewModel() {
    private val _query = MutableStateFlow("")

    fun onSearchQueryChanged(text: String) {
        _query.value = text
    }

    val characterFlow =
        _query
            .debounce(300)
            .flatMapLatest { query ->
                getPages(query)
            }
            .cachedIn(viewModelScope)
}