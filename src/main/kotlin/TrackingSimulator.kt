import kotlinx.coroutines.*
import shipmentBehavior.*
import java.io.File

class TrackingSimulator {
    private val shipments: MutableList<Shipment> = mutableListOf()
    private val shipmentUpdates = mapOf(
        Pair("canceled",  CancelShipment()),
        Pair("created",   CreateShipment()),
        Pair("delayed",   DelayShipment()),
        Pair("delivered", DeliverShipment()),
        Pair("location",  LocateShipment()),
        Pair("lost",      LoseShipment()),
        Pair("noteadded", NoteShipment()),
        Pair("shipped",   ShipShipment()),
    )

    fun findShipment(id: String) : Shipment? {
        for (shipment in shipments) {
            if (shipment.getId() == id) {
                return shipment
            }
        }
        return null
    }
    fun addShipments() = runBlocking {
        File("src/data/test.txt").forEachLine { //Each line contains in order: {status, id, timestamp, conditional info}
            launch {
                delay(1000L)
                val update = it.split(",")
                val status = update[0]
                val shipment = findShipment(update[1])
                shipmentUpdates[status]?.updateShipment(shipment, update)
            }
        }
    }
    fun runSimulation() {}
}