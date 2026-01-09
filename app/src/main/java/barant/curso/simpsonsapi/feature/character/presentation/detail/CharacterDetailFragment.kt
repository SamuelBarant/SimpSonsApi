package barant.curso.simpsonsapi.feature.character.presentation.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import barant.curso.simpsonsapi.core.presentation.ui.components.ErrorView
import barant.curso.simpsonsapi.core.presentation.ui.components.LoadingView
import barant.curso.simpsonsapi.core.presentation.ui.theme.SimpSonsTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class CharacterDetailFragment : Fragment() {
    private val viewModel: CharacterDetailViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val characterId = arguments?.getInt("id") ?: -1

        return ComposeView(requireContext()).apply {
            val navController = findNavController()
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)

            setContent {
                SimpSonsTheme {
                    val uiState by viewModel.uiState.observeAsState(
                        CharacterDetailViewModel.UiState(isLoading = true)
                    )

                    LaunchedEffect(characterId) {
                        viewModel.loadCharacter(characterId)
                    }

                    when {
                        uiState.isLoading -> LoadingView()
                        uiState.error != null -> ErrorView(uiState.error?.message ?: "Unknown error")
                        uiState.data != null -> CharacterDetailContent(character = uiState.data!!, navController = navController)
                    }
                }
            }
        }
    }
}
