class Shipment(
    private var id: String,
    private var status: String,
) {
    private var currentLocation: String = ""
    private var expectedDeliveryDateTimestamp: Long = 0
    private val notes: MutableList<String> = mutableListOf()
    private val updateHistory: MutableList<ShippingUpdate> = mutableListOf()


    fun getId(): String { return id }
    fun setId(id: String) { this.id = id }
    fun getStatus(): String { return status }
    fun setStatus(status: String) { this.status = status }
    fun getCurrentLocation(): String { return currentLocation }
    fun setCurrentLocation(location: String) { currentLocation = location }
    fun getExpectedDeliveryDateTimestamp(): Long { return expectedDeliveryDateTimestamp }
    fun setExpectedDeliveryDateTimestamp(timestamp: Long) {expectedDeliveryDateTimestamp = timestamp}
    fun getNotes() : List<String> { return notes }
    fun getHistory() : List<ShippingUpdate> { return updateHistory }

    fun addNote(note: String) { notes.add(note) }
    fun addUpdate(update: ShippingUpdate) { updateHistory.add(update) }
}