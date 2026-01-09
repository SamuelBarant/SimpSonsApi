package barant.curso.simpsonsapi.feature.character.presentation.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.res.colorResource
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.paging.compose.collectAsLazyPagingItems
import barant.curso.simpsonsapi.R
import barant.curso.simpsonsapi.core.presentation.ui.components.SimpleSearchBar
import barant.curso.simpsonsapi.core.presentation.ui.components.TopAppBarSimpsons
import barant.curso.simpsonsapi.core.presentation.ui.theme.SimpSonsTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class CharacterListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)

            setContent {
                SimpSonsTheme {

                    val navController = findNavController()
                    val viewModel: CharacterListViewModel by viewModel()
                    val lazyItemsList = viewModel.characterFlow.collectAsLazyPagingItems()

                    val gradient = Brush.linearGradient(
                        listOf(
                            colorResource(R.color.gradientBackgroundStart),
                            colorResource(R.color.gradientBackgroundEnd)
                        )
                    )

                    var query by remember { mutableStateOf("") }

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(gradient)
                    ) {

                        TopAppBarSimpsons(
                            modifier = Modifier.fillMaxWidth(),
                            label = getString(R.string.subtitleList)
                        )

                        SimpleSearchBar(
                            query = query,
                            onTextChanged = {
                                query = it
                                viewModel.onSearchQueryChanged(query)
                            },
                            modifier = Modifier.fillMaxWidth()
                        )

                        LazyColumnCharacter(
                            characters = lazyItemsList,
                            onCharacterClick = { id ->
                                val action = CharacterListFragmentDirections
                                    .actionCharacterListToCharacterDetail(id)
                                navController.navigate(action)
                            }
                        )
                    }
                }
            }
        }
    }
}
