class Shipment(val id: String, timeStamp : Long) {
    private val notes: MutableList<String> = mutableListOf()
    private val updateHistory: MutableList<ShippingUpdate> = mutableListOf()
    private var status: String = "created"
    private var expectedDeliveryDateTimestamp: Long = 0
    private var currentLocation: String = ""

    init {
        addUpdate(ShippingUpdate("", status, timeStamp))
    }
    fun addNote(note: String) { notes.add(note) }
    fun addUpdate(update: ShippingUpdate) { updateHistory.add(update) }
}