package shipmentTypes


class BulkShipment(id: String, status: String, timestamp: Long): Shipment(id, status) {
    private val expectedTimestamp = timestamp + (86400000 * 3) //3 days after creation of shipment
    override fun handleShipmentType(updateTimestamp: Long) {
        if (expectedTimestamp > updateTimestamp) {
            this.addNote("Shipment expected sooner than the required greater than 3 days waiting time for bulk shipments")
        }
    }
}