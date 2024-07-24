package updateBehavior

import shipmentTypes.Shipment
import ShippingUpdate

class TimeUpdate: UpdateBehavior {

    override fun updateShipment(shipment: Shipment?, update: List<String>) {
        val status: String = update[0]
        val timestamp: Long = update[2].toLong()
        val expectedTimeStamp: Long = update[3].toLong()
        shipment?.addUpdate(ShippingUpdate(shipment.status, status, timestamp))
        shipment?.status = status
        shipment?.expectedDeliveryTimestamp = expectedTimeStamp
        shipment?.handleShipmentType(expectedTimeStamp)
    }

}