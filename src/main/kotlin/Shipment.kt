class Shipment(
    private var id: String,
    private var status: String,
) {
    private var currentLocation: String = "unknown"
    private var expectedDeliveryTimestamp: Long = 0
    private val notes: MutableList<String> = mutableListOf()
    private val updateHistory: MutableList<ShippingUpdate> = mutableListOf()

    fun getId(): String { return id }
    fun setId(id: String) { this.id = id }
    fun getStatus(): String { return status }
    fun setStatus(status: String) { this.status = status }
    fun getCurrentLocation(): String { return currentLocation }
    fun setCurrentLocation(location: String) { currentLocation = location }
    fun getExpectedDeliveryTimestamp(): Long { return expectedDeliveryTimestamp }
    fun setExpectedDeliveryTimestamp(timestamp: Long) {expectedDeliveryTimestamp = timestamp}
    fun getNotes() : List<String> { return notes }
    fun getHistory() : List<ShippingUpdate> { return updateHistory }

    fun addNote(note: String) { notes.add(note) }
    fun addUpdate(update: ShippingUpdate) { updateHistory.add(update) }
}