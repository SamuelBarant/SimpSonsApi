package barant.curso.simpsonsapi.feature.character.presentation.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import barant.curso.simpsonsapi.feature.character.domain.Character
import kotlinx.coroutines.flow.Flow

@Composable
fun LazyColumnCharacter(
    characters: LazyPagingItems<Character>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        contentPadding = PaddingValues(vertical = 16.dp, horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        if (characters.itemCount == 0) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        CircularProgressIndicator()
                        Spacer(modifier = Modifier.height(8.dp))
                        Text("Loading...")
                    }
                }
            }
        } else {
            items(characters.itemCount) { index ->
                if (index in 0 until characters.itemCount) {
                    val item = characters[index]
                    item?.let {
                        CardSimpSons(
                            img = "https://cdn.thesimpsonsapi.com/500".plus(it.img),
                            name = it.name,
                            age = it.age.toString(),
                            gender = it.gender,
                            occupation = it.occupation,
                            phrase = it.phrase?.firstOrNull() ?: ""
                        )
                    }
                }
            }
        }
    }
}
