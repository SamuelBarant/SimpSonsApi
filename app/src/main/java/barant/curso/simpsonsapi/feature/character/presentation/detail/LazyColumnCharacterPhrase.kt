package barant.curso.simpsonsapi.feature.character.presentation.detail

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun LazyColumnPhrase(
    phrases: List<String>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        contentPadding = PaddingValues(vertical = 16.dp, horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(phrases) { phrases ->
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                ),
                border = BorderStroke(1.dp, color = Color.LightGray),
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            ){
                Box(
                    modifier = Modifier.fillMaxSize().padding(10.dp),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Text(
                        text = phrases,
                        style = MaterialTheme.typography.bodySmall,
                    )
                }
            }
        }
    }
}