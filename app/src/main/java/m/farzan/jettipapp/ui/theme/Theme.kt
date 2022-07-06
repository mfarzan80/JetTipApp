package m.farzan.jettipapp.ui.theme

import android.view.View
import android.view.Window
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb

private val DarkColorPalette = darkColors(
    primary = Pink500,
    primaryVariant = Color.Black,
    secondary = Blue200,
    background = Gray1000,
    surface = Gray900,
    onPrimary = Gray900,
    onSecondary = Gray10,
    onBackground = Gray10,
    onSurface = Gray10,
)

private val LightColorPalette = lightColors(
    primary = Pink700,
    primaryVariant = Gray200,
    secondary = Blue200,
    background = Color.White,
    surface = Gray10,
    onPrimary = Gray10,
    onSecondary = Gray1000,
    onBackground = Gray1000,
    onSurface = Gray1000,

    )

@Composable
fun JetTipAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    window: Window,
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        LightColorPalette
    }




    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}