package updateBehavior

import Shipment

interface UpdateBehavior {
    fun updateShipment(shipment: Shipment?, update: List<String>)
}