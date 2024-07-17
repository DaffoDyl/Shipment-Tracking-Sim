import kotlinx.coroutines.*
import updateBehavior.*
import java.io.File

class TrackingSimulator() {
    private val shipments: MutableList<Shipment> = mutableListOf()
    private val shipmentUpdates = mapOf(
        Pair("created",   InitialUpdate()),
        Pair("canceled",  StatusUpdate()),
        Pair("delivered", StatusUpdate()),
        Pair("lost",      StatusUpdate()),
        Pair("delayed",   TimeUpdate()),
        Pair("shipped",   TimeUpdate()),
        Pair("location",  LocationUpdate()),
        Pair("noteadded", NotesUpdate()),
    )

    fun addShipment(shipment: Shipment) { shipments.add(shipment) }
    fun findShipment(id: String) : Shipment? {
        for (shipment in shipments) {
            if (shipment.getId() == id) {
                return shipment
            }
        }
        return null
    }
    fun runSimulation() = runBlocking {
        File("src/data/test.txt").forEachLine { //Each line contains in order: {status, id, timestamp, conditional info}
            launch {
                delay(1000L)
                val update = it.split(",")
                val status = update[0]
                val id = update[1]
                if(status == "created") { addShipment(Shipment(id, status)) }
                shipmentUpdates[status]?.updateShipment(findShipment(id), update)
            }
        }
    }
}