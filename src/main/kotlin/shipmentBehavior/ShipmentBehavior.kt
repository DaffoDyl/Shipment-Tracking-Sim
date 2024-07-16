package shipmentBehavior

import Shipment

interface ShipmentBehavior {
    fun updateShipment(shipment: Shipment?, update: List<String>)
}