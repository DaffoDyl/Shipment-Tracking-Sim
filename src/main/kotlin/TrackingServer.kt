import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import updateBehavior.*
import java.io.File

object TrackingServer {
    private val shipments: MutableList<Shipment> = mutableListOf()
    private val shipmentUpdates = mapOf(
        Pair("canceled",  StatusUpdate()),
        Pair("delivered", StatusUpdate()),
        Pair("lost",      StatusUpdate()),
        Pair("delayed",   TimeUpdate()),
        Pair("shipped",   TimeUpdate()),
        Pair("location",  LocationUpdate()),
        Pair("noteadded", NotesUpdate())
    )

    fun addShipment(shipment: Shipment) {
        shipments.add(shipment)
    }
    fun findShipment(id: String) : Shipment? {
        return shipments.find {
            it.getId() == id
        }
    }
    fun runServer() {
        embeddedServer(Netty, 8080) {
            routing {
                get("/") {
                    call.respondText(File("index.html").readText(), ContentType.Text.Html)
                }

                post("/data") {
                    val data = call.receiveText()
                    call.respondText { "Received data: $data" }
                    val update = data.split(",")
                    val status = update[0]
                    val id = update[1]
                    if(status == "created") { addShipment(Shipment(id, status)) }
                    shipmentUpdates[status]?.updateShipment(findShipment(id), update)
                }
            }
        }.start(wait = false)
    }
}