import androidx.compose.runtime.*

class TrackerViewHelper(shipment: Shipment) {
        var shipmentId by mutableStateOf(shipment.getId())
            private set
        var shipmentNotes = mutableStateListOf<String>()
}