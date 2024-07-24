import shipmentTypes.*

class ShipmentPacker {

    fun packShipment(shipmentInfo: List<String>): Shipment { //Each line in shipmentInfo contains in order: {status, id, timestamp, shipment type}
        val status = shipmentInfo[0]
        val id = shipmentInfo[1]
        val timestamp = shipmentInfo[2].toLong()
        val type = shipmentInfo[3]
        val shipment = when(type) {
            "standard" -> StandardShipment(id, status)
            "express" -> ExpressShipment(id, status, timestamp)
            "overnight" -> OvernightShipment(id, status, timestamp)
            "bulk" -> BulkShipment(id, status, timestamp)
            else -> throw Exception("Invalid shipment")
        }
        return shipment
    }
}