package m.farzan.jettipapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.Remove
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import m.farzan.jettipapp.ui.components.CircleIconButton
import m.farzan.jettipapp.ui.components.OutlinedInput
import m.farzan.jettipapp.ui.theme.JetTipAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetTipAppTheme(window = window) {
                window.statusBarColor = MaterialTheme.colors.surface.toArgb()
                Surface(
                    modifier = Modifier
                        .fillMaxSize(),
                ) {

                    Column {
                        HeaderContact()
                    }

                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun HeaderContact() {
    val totalPerPersonState = remember {
        mutableStateOf(0f)
    }
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .padding(top = 10.dp),

        ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp, 20.dp),
            shape = RoundedCornerShape(5),
            elevation = 2.dp,
            backgroundColor = MaterialTheme.colors.background,
        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {


                Text(
                    text = String.format("%.2f$", totalPerPersonState.value),
                    style = MaterialTheme.typography.h3
                )
                Text(text = "Total Per Person", style = MaterialTheme.typography.subtitle2)


            }
        }
    }

    MainContact { bill, split, tip ->
        if (bill.isNotEmpty())
            totalPerPersonState.value = (bill.toFloat() + tip) / split
        else
            totalPerPersonState.value = 0f
    }


}


@Composable
fun MainContact(onChangeState: (bill: String, split: Int, tip: Float) -> Unit) {
    val totalBillState = remember {
        mutableStateOf("")
    }
    val splitValue = remember {
        mutableStateOf(1)
    }
    val sliderPositionState = remember {
        mutableStateOf(0f)
    }
    val tipValue = remember {
        mutableStateOf(0f)
    }


    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp, 0.dp)
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp, 20.dp),
            shape = RoundedCornerShape(5),
            elevation = 4.dp, backgroundColor = MaterialTheme.colors.background,
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp, 10.dp),
            ) {

                OutlinedInput(
                    modifier = Modifier
                        .fillMaxWidth(),
                    valueState = totalBillState,
                    labelID = "Enter Bill",
                    singleLine = true,
                    maxLines = 1,
                    leadingIcon = Icons.Filled.AttachMoney,
                    onValueChange = {

                        if (totalBillState.value.length <= 7) {
                            totalBillState.value = it
                            if (totalBillState.value.isNotEmpty())
                                tipValue.value =
                                    calculateTip(
                                        totalBillState.value.toFloat(),
                                        sliderPositionState.value
                                    )

                            onChangeState(
                                totalBillState.value,
                                splitValue.value,
                                tipValue.value
                            )

                        }
                    },
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Number
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(0.dp, 20.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "Split", style = MaterialTheme.typography.body1)
                    Spacer(modifier = Modifier.width(80.dp))
                    CircleIconButton(
                        modifier = Modifier
                            .size(50.dp),
                        elevation = 3.5.dp,
                        onClick = {
                            if (splitValue.value > 1) {
                                splitValue.value--
                                onChangeState(
                                    totalBillState.value,
                                    splitValue.value,
                                    tipValue.value
                                )
                            }
                        },
                        icon = Icons.Default.Remove
                    )
                    Text(
                        modifier = Modifier.width(40.dp),
                        textAlign = TextAlign.Center,
                        text = splitValue.value.toString(),
                        style = MaterialTheme.typography.h6
                    )
                    CircleIconButton(
                        modifier = Modifier
                            .size(50.dp),
                        elevation = 3.dp,
                        onClick = {
                            if (splitValue.value < 10) {
                                splitValue.value++
                                onChangeState(
                                    totalBillState.value,
                                    splitValue.value,
                                    tipValue.value
                                )
                            }
                        },
                        icon = Icons.Default.Add
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(0.dp, 20.dp)
                ) {
                    Text(text = "Tip", style = MaterialTheme.typography.body1)
                    Spacer(modifier = Modifier.width(150.dp))
                    Text(
                        text = String.format("%.2f$", tipValue.value),
                        style = MaterialTheme.typography.h6
                    )
                }

                Column(
                    modifier = Modifier.padding(0.dp, 10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = String.format("%.0f%%", sliderPositionState.value * 100),
                        style = MaterialTheme.typography.h6
                    )
                    Slider(
                        value = sliderPositionState.value, onValueChange = {
                            sliderPositionState.value = it
                            if (totalBillState.value.isNotEmpty())
                                tipValue.value = calculateTip(totalBillState.value.toFloat(), it)
                            else
                                tipValue.value = 0f

                            onChangeState(
                                totalBillState.value,
                                splitValue.value,
                                tipValue.value
                            )

                        },
                        modifier = Modifier.padding(16.dp, 0.dp),
                        steps = 9
                    )
                }
            }
        }
    }
}


fun String.toFloat(): Float {
    return try {
        java.lang.Float.parseFloat(this);
    }catch (e: NumberFormatException){
        0f;
    }
}

fun calculateTip(bill: Float, percentage: Float): Float {
    return bill * percentage
}