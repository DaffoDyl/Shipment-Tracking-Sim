import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
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
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking

@Composable
@Preview
fun App() {
    var inputText by remember { mutableStateOf("") }
    val viewHelper = remember { mutableStateListOf<TrackerViewHelper>() }

    MaterialTheme {
        Column {
            Row {
                TextField(
                    value = inputText,
                    onValueChange = { inputText = it },
                    placeholder = { Text("Shipment ID") },
                    modifier = Modifier.weight(1f)
                )
                Button(
                    onClick = {

                        viewHelper.add(TrackerViewHelper(Shipment(inputText, "dummy"))) },
                    modifier = Modifier.height(height = 56.dp)
                ) {
                    Text("Track")
                }
            }
            for (view in viewHelper) {
                Column {
                    Row {
                        Text("Tracking shipment: ${view.shipmentId}")
                        Button(
                            onClick = {},
                        ) {
                            Text("X")
                        }
                    }
                    Text("Status: ${view.shipmentStatus}")
                    Text("Location: ${view.shipmentLocation}")
                    Text("Expected Delivery: ${view.expectedShipmentDeliveryDate}")
                    Text("Status Updates:")
                    for (update in view.shipmentUpdateHistory) {
                        Text(update)
                    }
                    Text("Notes:")
                    for (note in view.shipmentNotes) {
                        Text(note)
                    }
                }
            }
        }


    }
}
val trackingSim = TrackingSimulator()

fun main() = runBlocking {
    async { trackingSim.runSimulation() }
    application {
        Window(onCloseRequest = ::exitApplication) {
            App()
        }
    }
}
