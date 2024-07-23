import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
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
    coroutineScope.launch { trackingServer.runServer() }

    MaterialTheme {
        Column {
            Row {
                TextField(
                    value = inputText,
                    onValueChange = { inputText = it },
                    placeholder = { Text("Shipment ID") },
                    modifier = Modifier.weight(1f),
                )
                Button(
                    onClick = { viewHelper.add(inputText) },
                    modifier = Modifier.height(height = 56.dp)
                ) {
                    Text("Track")
                }
            }
            for (id in viewHelper) {
                val shipment = trackingServer.findShipment(id)
                Column {
                    if (shipment != null) {
                        val view = TrackerViewHelper(shipment)
                        view.trackShipment(shipment)
                        Row {
                            Text("Tracking shipment: ")
                            Text(
                                text = view.shipmentId,
                                modifier = Modifier.weight(1f))
                            Button(
                                onClick = {
                                    viewHelper.remove(id)
                                    view.stopTracking(shipment) },
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
                    else {
                        Row {
                            Text("Id does not exist: $id")
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
val trackingServer = TrackingServer()
fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        App()
    }
}
