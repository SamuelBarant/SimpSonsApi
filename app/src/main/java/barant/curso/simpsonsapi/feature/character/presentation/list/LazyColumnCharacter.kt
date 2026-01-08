package barant.curso.simpsonsapi.feature.character.presentation.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import barant.curso.simpsonsapi.feature.character.presentation.CharacterUi

@Composable
fun LazyColumnCharacter(
    characters: List<CharacterUi>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        contentPadding = PaddingValues(vertical = 16.dp, horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(characters, key = { it.name }) { character ->
            CardSimpSons(
                imageRes = character.imageRes,
                name = character.name,
                age = character.age,
                gender = character.gender,
                occupation = character.occupation,
                phrase = character.phrase,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}