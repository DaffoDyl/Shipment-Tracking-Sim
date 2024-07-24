package shipmentTypesTest

import shipmentTypes.OvernightShipment
import kotlin.test.Test
import kotlin.test.assertEquals

class OvernightShipmentTest {
    @Test
    fun testOvernightShipmentConstruction() {
        val shipment = OvernightShipment("s100", "created", 100)
        assertEquals("s100", shipment.id)
        assertEquals("created", shipment.status)
    }
}