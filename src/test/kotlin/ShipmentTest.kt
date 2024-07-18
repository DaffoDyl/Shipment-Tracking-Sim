import kotlin.test.Test
import kotlin.test.assertEquals

class ShipmentTest {
    @Test
    fun testShipmentConstruction() {
        val shipment = Shipment("id", "status")
        assertEquals("id", shipment.getId())
        assertEquals("status", shipment.getStatus())
    }
}