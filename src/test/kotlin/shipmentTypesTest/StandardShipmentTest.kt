package shipmentTypesTest

import shipmentTypes.StandardShipment
import kotlin.test.Test
import kotlin.test.assertEquals

class StandardShipmentTest {
    @Test
    fun testStandardShipmentConstruction() {
        val shipment = StandardShipment("s100", "created")
        assertEquals("s100", shipment.id)
        assertEquals("created", shipment.status)
    }
}