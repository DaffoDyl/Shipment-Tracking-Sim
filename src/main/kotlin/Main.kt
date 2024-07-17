import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import kotlinx.coroutines.launch

@Composable
@Preview
fun App() {
    var inputText by remember { mutableStateOf("") }
    val viewHelper = remember { mutableStateListOf<String>() }
    val coroutineScope = rememberCoroutineScope()
    coroutineScope.launch { trackingSim.runSimulation() }

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
                    onClick = { viewHelper.add(inputText) },
                    modifier = Modifier.height(height = 56.dp)
                ) {
                    Text("Track")
                }
            }
            for (id in viewHelper) {
                val ship = trackingSim.findShipment(id)
                Column {
                    if (ship != null) {
                        val view = TrackerViewHelper(ship)
                        Row {
                            Text("Tracking shipment: ${view.shipmentId}")
                            Button(
                                onClick = { viewHelper.remove(id) },
                                modifier = Modifier.padding(4.dp)
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
                    } else {
                        Row {
                            Text("Tracking shipment: $id does not exist")
                            Button(
                                onClick = { viewHelper.remove(id) },
                                modifier = Modifier.padding(4.dp)
                            ) {
                                Text("X")
                            }
                        }
                    }
                }

            }
        }


    }
}
val trackingSim = TrackingSimulator()

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        App()
    }
}
