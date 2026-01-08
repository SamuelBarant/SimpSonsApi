package barant.curso.simpsonsapi.core.presentation.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import barant.curso.simpsonsapi.R
import com.example.compose.extendedLight

// Default Material 3 typography values
val baseline = Typography()

val h1FontFamily = FontFamily(
    Font(R.font.bangers_regular, weight = FontWeight.Bold)
)
val AppTypography = Typography(
    titleLarge = baseline.titleLarge.copy(
        fontFamily = h1FontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 38.sp,
        color = extendedLight.customColor1.colorContainer
    )
)

