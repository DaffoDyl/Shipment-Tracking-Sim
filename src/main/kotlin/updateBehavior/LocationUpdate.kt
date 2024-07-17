package updateBehavior

import Shipment
import ShippingUpdate

open class LocationUpdate: UpdateBehavior {
    override fun updateShipment(shipment: Shipment?, update: List<String>) {
        val location = update[3]
        shipment?.setCurrentLocation(location)
    }
}