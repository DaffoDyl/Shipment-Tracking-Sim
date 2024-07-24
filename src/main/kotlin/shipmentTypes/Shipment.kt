package shipmentTypes

import ShipmentObserver
import ShipmentSubject
import ShippingUpdate

open class Shipment(id: String, status: String): ShipmentSubject {

    var id: String = id
        set(value){
            field = value
            notifyObservers()
        }
    var status: String = status
        set(value){
            field = value
            notifyObservers()
        }
    var currentLocation: String = "unknown"
        set(value){
            field = value
            notifyObservers()
        }

    var expectedDeliveryTimestamp: Long = 0
        set(value){
            field = value
            notifyObservers()
        }

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