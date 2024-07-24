package updateBehavior

import shipmentTypes.Shipment

interface UpdateBehavior {

    fun updateShipment(shipment: Shipment?, update: List<String>)

}