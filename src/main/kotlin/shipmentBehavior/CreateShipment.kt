package shipmentBehavior

import Shipment
import ShippingUpdate

class CreateShipment: ShipmentBehavior {
    override fun updateShipment(shipment: Shipment?, update: List<String>) {
        val status: String = update[0]
        val timestamp: Long = update[2].toLong()
        shipment?.addUpdate(ShippingUpdate("", status, timestamp))
    }
}