class Shipment(
    private var id: String,
    private var status: String,
): ShipmentSubject {
    private var currentLocation: String = "unknown"
    private var expectedDeliveryTimestamp: Long = 0
    private val notes: MutableList<String> = mutableListOf()
    private val updateHistory: MutableList<ShippingUpdate> = mutableListOf()
    private var subscribers = mutableListOf<ShipmentObserver>()

    override fun subscribe(observer: ShipmentObserver) {
        subscribers.add(observer)
    }
    override fun unsubscribe(observer: ShipmentObserver) {
        subscribers.remove(observer)
    }
    override fun notifyObservers() {
        subscribers.forEach {
            it.notify(this)
        }
    }

    fun getId(): String { return id }
    fun getStatus(): String { return status }
    fun getCurrentLocation(): String { return currentLocation }
    fun getExpectedDeliveryTimestamp(): Long { return expectedDeliveryTimestamp }
    fun getNotes() : List<String> { return notes }
    fun getHistory() : List<ShippingUpdate> { return updateHistory }
    fun setId(id: String) {
        this.id = id
        notifyObservers()
    }
    fun setStatus(status: String) {
        this.status = status
        notifyObservers()
    }
    fun setCurrentLocation(location: String) {
        currentLocation = location
        notifyObservers()
    }
    fun setExpectedDeliveryTimestamp(timestamp: Long) {
        expectedDeliveryTimestamp = timestamp
        notifyObservers()
    }
    fun addNote(note: String) {
        notes.add(note)
        notifyObservers()
    }
    fun addUpdate(update: ShippingUpdate) {
        updateHistory.add(update)
        notifyObservers()
    }
}