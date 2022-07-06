package m.farzan.jettipapp.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CircleIconButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    icon: ImageVector,
    elevation: Dp = 0.dp,
    ) {
    Card(
        modifier = modifier,
        elevation = elevation,
        shape = CircleShape,
        onClick = onClick
    ) {
        Icon(modifier = Modifier.padding(10.dp), imageVector = icon, contentDescription = "icon")
    }

}