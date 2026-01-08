package barant.curso.simpsonsapi.core.presentation.ui.components

import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun ActionButton(
    icon: ImageVector,
    modifier: Modifier = Modifier
){
    FloatingActionButton(
        modifier = modifier,
        onClick = {/*TODO*/},
        content = {
            Icon(
                imageVector = icon,
                contentDescription = null,
            )
        }
    )
}