class TrackingSimulator {
    private val shipments: MutableList<Shipment> = mutableListOf()

    fun findShipment(id: String) : Shipment? {
        for (shipment in shipments) {
            if (shipment.id == id) {
                return shipment
            }
        }
        return null
    }
    fun addShipments() {}
    fun runSimulation() {}
}