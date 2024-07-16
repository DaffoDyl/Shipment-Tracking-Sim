import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

@Composable
@Preview
fun App() {
    var inputText by remember { mutableStateOf("") }
    var buttonText by remember { mutableStateOf("Track") }

    MaterialTheme {
        Row {
            TextField(
                value = inputText,
                onValueChange = { inputText = it },
                placeholder = { Text("Shipment ID") },
            )
            Button(onClick = {
                buttonText = "Tracked"
            }) {
                Text(buttonText)
            }
        }

    }
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        App()
    }
}
