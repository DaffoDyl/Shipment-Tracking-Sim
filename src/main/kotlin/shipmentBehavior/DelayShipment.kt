package shipmentBehavior

import Shipment
import ShippingUpdate

class DelayShipment: ShipmentBehavior {
    override fun updateShipment(shipment: Shipment?, update: List<String>) {
        val status: String = update[0]
        val timestamp: Long = update[2].toLong()
        val expectedTimestamp: Long = update[3].toLong()
        shipment?.addUpdate(ShippingUpdate(shipment.getStatus(), status, timestamp))
        shipment?.setStatus(status)
        shipment?.setExpectedDeliveryTimestamp(expectedTimestamp)
    }
}