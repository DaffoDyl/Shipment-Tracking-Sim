package shipmentTypes

class OvernightShipment(id: String, status: String, timestamp: Long): Shipment(id, status) {
    private val expectedTimestamp = timestamp + 86400000 //24 hours after creation of shipment
    override fun handleShipmentType(updateTimestamp: Long) {
        if (expectedTimestamp < updateTimestamp) {
            this.addNote("Shipment expected later than the required 24 hours waiting time for overnight shipments")
        } else if (expectedTimestamp > updateTimestamp) {
            this.addNote("Shipment expected sooner than the required 24 hours waiting time for overnight shipments")
        }
    }
}