import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import java.text.SimpleDateFormat
import java.util.*

class TrackerViewHelper(shipment: Shipment): ShipmentObserver {

    var shipmentId by mutableStateOf(shipment.id)
        private set

    var shipmentStatus by mutableStateOf(shipment.status)
        private set

    var shipmentLocation by mutableStateOf(shipment.currentLocation)
        private set

    var expectedShipmentDeliveryDate by mutableStateOf(toDateString(shipment.expectedDeliveryTimestamp))
        private set

    var shipmentNotes = mutableStateListOf<String>()
        private set

    var shipmentUpdateHistory = mutableStateListOf<String>()
        private set

    init {
        shipmentNotes = setNotes(shipment)
        shipmentUpdateHistory = setHistory(shipment)
    }

    override fun notify(shipment: Shipment) {
        this.shipmentId = shipment.id
        this.shipmentStatus = shipment.status
        this.shipmentLocation = shipment.currentLocation
        this.expectedShipmentDeliveryDate = toDateString(shipment.expectedDeliveryTimestamp)
        this.shipmentNotes = setNotes(shipment)
        this.shipmentUpdateHistory = setHistory(shipment)
    }

    fun trackShipment(shipment: Shipment) {
        shipment.subscribe(this)
    }

    fun stopTracking(shipment: Shipment) {
        shipment.unsubscribe(this)
    }

    private fun toDateString(stamp: Long): String {
        if (stamp == 0L) {return "unknown"}
        val date = Date(stamp)
        val format = SimpleDateFormat("yyyy.MM.dd HH:mm")
        return format.format(date)
    }

    private fun setNotes(shipment: Shipment): SnapshotStateList<String> {
        val notes = mutableStateListOf<String>()
        for (note in shipment.notes) {
            notes.add(note)
        }
        return notes
    }

    private fun setHistory(shipment: Shipment): SnapshotStateList<String> {
        val updates = mutableStateListOf<String>()
        for (update in shipment.updateHistory) {
            updates.add(
                "Shipment went from ${update.previousStatus} to ${update.newStatus} at ${toDateString(update.timestamp)}"
            )
        }
        return updates
    }

}