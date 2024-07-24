package updateBehavior

import Shipment

open class LocationUpdate: UpdateBehavior {

    override fun updateShipment(shipment: Shipment?, update: List<String>) {
        val location = update[3]
        shipment?.currentLocation = location
    }

}