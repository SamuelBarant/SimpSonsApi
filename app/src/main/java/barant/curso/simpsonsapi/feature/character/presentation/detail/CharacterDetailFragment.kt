//package barant.curso.simpsonsapi.feature.character.presentation.detail
//
//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.absoluteOffset
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.size
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.shape.CircleShape
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.ArrowBackIosNew
//import androidx.compose.material.icons.filled.CalendarToday
//import androidx.compose.material.icons.outlined.CalendarToday
//import androidx.compose.material.icons.outlined.Favorite
//import androidx.compose.material.icons.outlined.FavoriteBorder
//import androidx.compose.material.icons.outlined.Person
//import androidx.compose.material3.Icon
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Text
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.graphics.Brush
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.graphics.RectangleShape
//import androidx.compose.ui.graphics.Shape
//import androidx.compose.ui.layout.ContentScale
//import androidx.compose.ui.platform.ComposeView
//import androidx.compose.ui.platform.ViewCompositionStrategy
//import androidx.compose.ui.res.colorResource
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.res.stringResource
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.unit.dp
//import androidx.fragment.app.Fragment
//import barant.curso.simpsonsapi.R
//import barant.curso.simpsonsapi.core.presentation.ui.components.ActionButton
//import com.example.compose.SimpSonsTheme
//
//
//class CharacterDetailFragment : Fragment() {
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        return ComposeView(requireContext()).apply {
//            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
//
//            setContent {
//                SimpSonsTheme {
//                    val gradient = Brush.linearGradient(
//                        listOf(
//                            colorResource(R.color.gradientBackgroundStart),
//                            colorResource(R.color.gradientBackgroundEnd)
//                        )
//                    )
//
//                    Box(
//                        modifier = Modifier.fillMaxSize()
//                    ){
//                        Column(
//                            modifier = Modifier.fillMaxSize().background(gradient).padding(10.dp)
//                        ) {
//                            Text(
//                                text = sampleCharacter.name,
//                                style = MaterialTheme.typography.titleMedium,
//                                modifier = Modifier.padding(top = 20.dp)
//                            )
//                            Box (
//                                modifier = Modifier.padding(top = 20.dp)
//                            ){
//                                Row {
//                                    Image(
//                                        painter = painterResource(id = sampleCharacter.imageRes),
//                                        contentDescription = sampleCharacter.name,
//                                        contentScale = ContentScale.Crop,
//                                        modifier = Modifier
//                                            .size(150.dp)
//                                            .clip(RoundedCornerShape(10.dp))
//                                    )
//                                    Column (
//                                        modifier = Modifier.padding(start = 10.dp)
//                                    ){
//                                        Column {
//                                            Row (
//                                                verticalAlignment = Alignment.CenterVertically
//                                            ){
//                                                Icon(
//                                                    imageVector = Icons.Outlined.CalendarToday,
//                                                    contentDescription = null,
//                                                    modifier = Modifier.size(18.dp)
//                                                )
//                                                Text(
//                                                    text = stringResource(id = R.string.labelAgeCharacter),
//                                                    style = MaterialTheme.typography.bodyLarge,
//                                                    fontWeight = FontWeight.W500
//                                                )
//                                            }
//                                            Text(
//                                                modifier = Modifier.padding(start = 10.dp),
//                                                text = sampleCharacter.age
//                                            )
//                                        }
//                                        Column {
//                                            Row (
//                                                verticalAlignment = Alignment.CenterVertically
//                                            ){
//                                                Icon(
//                                                    imageVector = Icons.Outlined.Person,
//                                                    contentDescription = null,
//                                                    modifier = Modifier.size(18.dp)
//                                                )
//                                                Text(
//                                                    text = stringResource(id = R.string.labelAgeCharacter),
//                                                    style = MaterialTheme.typography.bodyLarge,
//                                                    fontWeight = FontWeight.W500
//                                                )
//                                            }
//                                            Text(
//                                                modifier = Modifier.padding(start = 10.dp),
//                                                text = sampleCharacter.gender
//                                            )
//                                        }
//                                        Column {
//                                            Row (
//                                                verticalAlignment = Alignment.CenterVertically
//                                            ){
//                                                Icon(
//                                                    imageVector = Icons.Outlined.Person,
//                                                    contentDescription = null,
//                                                    modifier = Modifier.size(18.dp)
//                                                )
//                                                Text(
//                                                    text = stringResource(id = R.string.labelOccupationCharacter),
//                                                    style = MaterialTheme.typography.bodyLarge,
//                                                    fontWeight = FontWeight.W500
//                                                )
//                                            }
//                                            Text(
//                                                modifier = Modifier.padding(start = 10.dp),
//                                                text = sampleCharacter.occupation
//                                            )
//                                        }
//                                        Column {
//                                            Row (
//                                                verticalAlignment = Alignment.CenterVertically
//                                            ){
//                                                Icon(
//                                                    imageVector = Icons.Outlined.FavoriteBorder,
//                                                    contentDescription = null,
//                                                    modifier = Modifier.size(18.dp)
//                                                )
//                                                Text(
//                                                    text = stringResource(id = R.string.labelOccupationCharacter),
//                                                    style = MaterialTheme.typography.bodyLarge,
//                                                    fontWeight = FontWeight.W500
//                                                )
//                                            }
//                                            Text(
//                                                modifier = Modifier.padding(start = 10.dp),
//                                                text = sampleCharacter.occupation
//                                            )
//                                        }
//                                        Column {
//                                            Row (
//                                                verticalAlignment = Alignment.CenterVertically
//                                            ){
//                                                Icon(
//                                                    imageVector = Icons.Outlined.Person,
//                                                    contentDescription = null,
//                                                    modifier = Modifier.size(18.dp)
//                                                )
//                                                Text(
//                                                    text = stringResource(id = R.string.labelBirthdateCharacter),
//                                                    style = MaterialTheme.typography.bodyLarge,
//                                                    fontWeight = FontWeight.W500
//                                                )
//                                            }
//                                            Text(
//                                                modifier = Modifier.padding(start = 10.dp),
//                                                text = sampleCharacter.birthdate
//                                            )
//                                        }
//                                    }
//                                }
//                            }
//                            Text(
//                                text = stringResource(id = R.string.labelPhrasesCharacter),
//                                style = MaterialTheme.typography.titleSmall
//                            )
//                            LazyColumnPhrase(sampleCharacterPhrases)
//                        }
//                        ActionButton(
//                            icon = Icons.Default.ArrowBackIosNew,
//                            modifier = Modifier
//                                .align(Alignment.BottomEnd)
//                                .padding(20.dp)
//                        )
//                    }
//
//                }
//            }
//        }
//    }
//}
