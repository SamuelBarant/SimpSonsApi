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
import barant.curso.simpsonsapi.R
import barant.curso.simpsonsapi.core.presentation.ui.components.SimpleSearchBar
import barant.curso.simpsonsapi.core.presentation.ui.components.TopAppBarSimpsons
import barant.curso.simpsonsapi.feature.character.presentation.CharacterUi
import com.example.compose.SimpSonsTheme

class CharacterListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return ComposeView(requireContext()).apply {

            setViewCompositionStrategy(
                ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed
            )

            setContent {

                SimpSonsTheme {

                    val gradient = Brush.linearGradient(
                        listOf(
                            colorResource(R.color.gradientBackgroundStart),
                            colorResource(R.color.gradientBackgroundEnd)
                        )
                    )

                    // Datos mock
                    val sampleCharacters = listOf(
                        CharacterUi(
                            imageRes = R.drawable.person_24px,
                            name = "Homer Simpson",
                            age = "45",
                            gender = "M",
                            occupation = "Nuclear Safety",
                            phrase = "D'oh!"
                        ),
                        CharacterUi(
                            imageRes = R.drawable.person_24px,
                            name = "Marge Simpson",
                            age = "43",
                            gender = "F",
                            occupation = "Homemaker",
                            phrase = "Mmm..."
                        ),
                        CharacterUi(
                            imageRes = R.drawable.person_24px,
                            name = "Bart Simpson",
                            age = "10",
                            gender = "M",
                            occupation = "Student",
                            phrase = "Eat my shorts!"
                        )
                    )

                    var query by remember { mutableStateOf("") }

                    val filteredCharacters = remember(query) {
                        if (query.isBlank()) {
                            sampleCharacters
                        } else {
                            sampleCharacters.filter {
                                it.name.contains(query, ignoreCase = true)
                            }
                        }
                    }

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
                            onTextChanged = { query = it },
                            modifier = Modifier.fillMaxWidth()
                        )

                        LazyColumnCharacter(
                            characters = filteredCharacters
                        )
                    }
                }
            }
        }
    }
}
