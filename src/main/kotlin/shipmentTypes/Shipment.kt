package shipmentTypes

import ShipmentObserver
import ShipmentSubject
import ShippingUpdate

open class Shipment(

    var id: String,

    var status: String

): ShipmentSubject {

    var currentLocation: String = "unknown"

    var expectedDeliveryTimestamp: Long = 0

    var notes: MutableList<String> = mutableListOf()
        private set
    var updateHistory: MutableList<ShippingUpdate> = mutableListOf()
        private set

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

    fun addNote(note: String) {
        notes.add(note)
        notifyObservers()
    }

    fun addUpdate(update: ShippingUpdate) {
        updateHistory.add(update)
        notifyObservers()
    }
}