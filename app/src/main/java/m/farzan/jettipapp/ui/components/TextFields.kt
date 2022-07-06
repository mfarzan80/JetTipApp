package m.farzan.jettipapp.ui.components

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType

@Composable
fun OutlinedInput(
    modifier: Modifier = Modifier,
    valueState: MutableState<String>,
    labelID: String,
    enabled: Boolean = true,
    maxLines: Int = 10,
    singleLine: Boolean = false,
    onValueChange: (String) -> Unit = { valueState.value = it },
    imeAction: ImeAction = ImeAction.Next,
    keyboardType: KeyboardType = KeyboardType.Text,
    onAction: KeyboardActions = KeyboardActions.Default,
    leadingIcon: ImageVector
) {
    OutlinedTextField(
        modifier = modifier,
        value = valueState.value,
        singleLine = singleLine,
        onValueChange = onValueChange,
        label = { Text(text = labelID) },
        maxLines = maxLines,
        enabled = enabled,
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
            imeAction = imeAction
        ),
        keyboardActions = onAction,
        leadingIcon = { Icon(imageVector = leadingIcon, contentDescription = "icon") }


    )
}