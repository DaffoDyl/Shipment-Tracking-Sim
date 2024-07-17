import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList

class TrackerViewHelper(shipment: Shipment): ShipmentObserver {
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
        shipmentNotes = setNotes(shipment)
        shipmentUpdateHistory = setHistory(shipment)
        shipment.subscribe(this)
    }

    override fun notify(shipment: Shipment) {
        this.shipmentId = shipment.getId()
        this.shipmentStatus = shipment.getStatus()
        this.shipmentLocation = shipment.getCurrentLocation()
        this.expectedShipmentDeliveryDate = toDateString(shipment.getExpectedDeliveryTimestamp())
        this.shipmentNotes = setNotes(shipment)
        this.shipmentUpdateHistory = setHistory(shipment)
    }
    fun trackShipment(id: String) {}
    fun stopTracking() {}
    private fun toDateString(stamp: Long): String {
        if (stamp == 0L) {return "unknown"}
        return java.time.format.DateTimeFormatter.ISO_INSTANT
            .format(java.time.Instant.ofEpochSecond(stamp))
    }
    private fun setNotes(shipment: Shipment): SnapshotStateList<String> {
        val notes = mutableStateListOf<String>()
        for (note in shipment.getNotes()) {
            notes.add(note)
        }
        return notes
    }
    private fun setHistory(shipment: Shipment): SnapshotStateList<String> {
        val updates = mutableStateListOf<String>()
        for (update in shipment.getHistory()) {
            updates.add(
                "Shipment went from ${update.previousStatus} to ${update.newStatus} at ${toDateString(update.timestamp)}"
            )
        }
        return updates
    }
}