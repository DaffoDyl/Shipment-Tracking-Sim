package shipmentTypesTest

import shipmentTypes.BulkShipment
import kotlin.test.Test
import kotlin.test.assertEquals

class BulkShipmentTest {
    @Test
    fun testBulkShipmentConstruction() {
        val shipment = BulkShipment("s100", "created", 100)
        assertEquals("s100", shipment.id)
        assertEquals("created", shipment.status)
    }
}