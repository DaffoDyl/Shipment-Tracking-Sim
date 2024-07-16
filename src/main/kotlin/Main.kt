import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
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
                modifier = Modifier.weight(1f)
            )
            Button(
                onClick = { buttonText = "Tracked" },
                modifier = Modifier.height(height = 56.dp)
            ) {
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
