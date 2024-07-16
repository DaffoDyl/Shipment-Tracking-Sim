package updateBehavior

import Shipment
import ShippingUpdate

open class StatusUpdate: UpdateBehavior {
    override fun updateShipment(shipment: Shipment?, update: List<String>) {
        val status: String = update[0]
        val timestamp = update[2].toLong()
        shipment?.addUpdate(ShippingUpdate(shipment.getStatus(), status, timestamp))
        shipment?.setStatus(status)
    }
}