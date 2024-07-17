package updateBehavior

import Shipment
import ShippingUpdate

class InitialUpdate: UpdateBehavior {
    override fun updateShipment(shipment: Shipment?, update: List<String>) {
        val status: String = update[0]
        val timestamp: Long = update[2].toLong()
        shipment?.addUpdate(ShippingUpdate("not created", status, timestamp))
    }
}