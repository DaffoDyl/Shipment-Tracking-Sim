import shipmentTypes.*

class ShipmentPacker {

    fun packShipment(update: List<String>): Shipment {
        val status = update[0]
        val id = update[1]
        val type = update[3]
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