import androidx.compose.runtime.*

class TrackerViewHelper(shipment: Shipment) {
        var shipmentId by mutableStateOf(shipment.getId())
            private set
        var shipmentStatus by mutableStateOf(shipment.getStatus())
            private set
        var shipmentLocation by mutableStateOf(shipment.getCurrentLocation())
            private set
        var expectedShipmentDeliveryDate by mutableStateOf(toDateString(shipment.getExpectedDeliveryTimestamp()))
            private set
        var shipmentNotes = mutableStateListOf<String>()
            private set
        var shipmentUpdateHistory = mutableStateListOf<String>()
            private set

        init {
            for (note in shipment.getNotes()) {
                shipmentNotes.add(note)
            }
            for (update in shipment.getHistory()) {
                shipmentUpdateHistory.add(
                    "Shipment went from ${update.previousStatus} to ${update.newStatus} at ${toDateString(update.timestamp)}"
                )
            }
        }

        fun trackShipment(id: String) {}
        fun stopTracking() {}
        private fun toDateString(stamp: Long): String {
            if (stamp == 0L) {return "unknown"}
            return java.time.format.DateTimeFormatter.ISO_INSTANT
                .format(java.time.Instant.ofEpochSecond(stamp))
        }
}