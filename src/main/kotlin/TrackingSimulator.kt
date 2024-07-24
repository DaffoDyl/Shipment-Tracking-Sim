import kotlinx.coroutines.*
import updateBehavior.*
import java.io.File

class TrackingSimulator() {

    private val shipments: MutableList<Shipment> = mutableListOf()

    private val shipmentUpdates = mapOf(
        Pair("canceled",  StatusUpdate()),
        Pair("delivered", StatusUpdate()),
        Pair("lost",      StatusUpdate()),
        Pair("delayed",   TimeUpdate()),
        Pair("shipped",   TimeUpdate()),
        Pair("location",  LocationUpdate()),
        Pair("noteadded", NotesUpdate()),
    )

    fun addShipment(shipment: Shipment) {
        shipments.add(shipment)
    }

    fun findShipment(id: String) : Shipment? {
        return shipments.find {
            it.id == id
        }
    }

    suspend fun runSimulation()  {
        val updates = mutableListOf<List<String>>()

        File("src/data/test.txt").forEachLine { //Each line contains in order: {status, id, timestamp, conditional info}
            updates.add(it.split(","))
        }
        for (update in updates) {
            val status = update[0]
            val id = update[1]
            if(status == "created") { addShipment(Shipment(id, status)) }
            shipmentUpdates[status]?.updateShipment(findShipment(id), update)
            delay(1000L)
        }
    }

}