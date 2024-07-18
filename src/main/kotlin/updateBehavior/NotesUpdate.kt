package updateBehavior

import Shipment

open class NotesUpdate: UpdateBehavior {
    override fun updateShipment(shipment: Shipment?, update: List<String>) {
        val note = update[3]
        shipment?.addNote(note)
    }
}