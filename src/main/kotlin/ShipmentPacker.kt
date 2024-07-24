import shipmentTypes.*

class ShipmentPacker {

    fun packShipment(shipmentInfo: List<String>): Shipment {
        val status = shipmentInfo[0]
        val id = shipmentInfo[1]
        val type = shipmentInfo[3]
        val shipment = when(type) {
            "standard" -> Shipment(id, status)
            "express" -> ExpressShipment(id, status)
            "overnight" -> OvernightShipment(id, status)
            "bulk" -> BulkShipment(id, status)
            else -> throw Exception("Invalid shipment")
        }
        return shipment
    }
}