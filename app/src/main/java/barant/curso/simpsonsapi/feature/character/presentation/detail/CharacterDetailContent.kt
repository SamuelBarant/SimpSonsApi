package barant.curso.simpsonsapi.feature.character.presentation.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.outlined.CalendarToday
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Work
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import barant.curso.simpsonsapi.R
import barant.curso.simpsonsapi.core.presentation.ui.components.ActionButton
import barant.curso.simpsonsapi.feature.character.domain.Character
import coil3.compose.AsyncImage

@Composable
fun CharacterDetailContent(
    character: Character,
    navController: NavController
) {
    val gradient = Brush.linearGradient(
        listOf(
            colorResource(R.color.gradientBackgroundStart),
            colorResource(R.color.gradientBackgroundEnd)
        )
    )

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(gradient)
                .padding(10.dp)
        ) {

            Text(
                text = character.name,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(top = 20.dp)
            )

            Box(
                modifier = Modifier.padding(top = 20.dp)
            ) {
                Row {
                    AsyncImage(
                        model = stringResource(id = R.string.imgUrl).plus(character.img),
                        contentDescription = character.name,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(150.dp)
                            .clip(RoundedCornerShape(10.dp))
                    )

                    Column(
                        modifier = Modifier.padding(start = 10.dp)
                    ) {

                        // AGE
                        Column {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.Outlined.CalendarToday,
                                    contentDescription = null,
                                    modifier = Modifier.size(18.dp)
                                )
                                Text(
                                    text = stringResource(id = R.string.labelAgeCharacter),
                                    style = MaterialTheme.typography.bodyLarge,
                                    fontWeight = FontWeight.W500
                                )
                            }
                            Text(
                                text = "${character.age.toString()} ${stringResource(id = R.string.ageSuffix)}",
                                modifier = Modifier.padding(start = 10.dp)
                            )
                        }

                        // GENDER
                        Column {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.Outlined.Person,
                                    contentDescription = null,
                                    modifier = Modifier.size(18.dp)
                                )
                                Text(
                                    text = stringResource(id = R.string.labelGenderCharacter),
                                    style = MaterialTheme.typography.bodyLarge,
                                    fontWeight = FontWeight.W500
                                )
                            }
                            Text(
                                modifier = Modifier.padding(start = 10.dp),
                                text = character.gender
                            )
                        }

                        // OCCUPATION
                        Column {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.Outlined.Work,
                                    contentDescription = null,
                                    modifier = Modifier.size(18.dp)
                                )
                                Text(
                                    text = stringResource(id = R.string.labelOccupationCharacter),
                                    style = MaterialTheme.typography.bodyLarge,
                                    fontWeight = FontWeight.W500
                                )
                            }
                            Text(
                                modifier = Modifier.padding(start = 10.dp),
                                text = character.occupation
                            )
                        }

                        // STATUS
                        Column {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.Outlined.FavoriteBorder,
                                    contentDescription = null,
                                    modifier = Modifier.size(18.dp)
                                )
                                Text(
                                    text = stringResource(id = R.string.labelStateCharacter),
                                    style = MaterialTheme.typography.bodyLarge,
                                    fontWeight = FontWeight.W500
                                )
                            }
                            Text(
                                modifier = Modifier.padding(start = 10.dp),
                                text = character.status
                            )
                        }

                        // BIRTHDATE
                        Column {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.Outlined.CalendarToday,
                                    contentDescription = null,
                                    modifier = Modifier.size(18.dp)
                                )
                                Text(
                                    text = stringResource(id = R.string.labelBirthdateCharacter),
                                    style = MaterialTheme.typography.bodyLarge,
                                    fontWeight = FontWeight.W500
                                )
                            }
                            Text(
                                modifier = Modifier.padding(start = 10.dp),
                                text = character.birthdate ?: ""
                            )
                        }
                    }
                }
            }

            Text(
                text = stringResource(id = R.string.labelPhrasesCharacter),
                style = MaterialTheme.typography.titleSmall
            )

            LazyColumnPhrase(character.phrase ?: emptyList())
        }

        ActionButton(
            icon = Icons.Default.ArrowBackIosNew,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(20.dp),
            onClick = {
                val action = CharacterDetailFragmentDirections.backToList()
                navController.navigate(action)
            }
        )
    }
}
