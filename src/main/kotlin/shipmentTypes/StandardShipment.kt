package shipmentTypes

class StandardShipment(id: String, status: String): Shipment(id, status) {
    override fun handleShipmentType(updateTimestamp: Long) {} //No conditions
}