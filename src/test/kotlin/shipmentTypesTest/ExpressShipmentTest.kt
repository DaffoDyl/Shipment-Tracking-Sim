package shipmentTypesTest

import shipmentTypes.ExpressShipment
import kotlin.test.Test
import kotlin.test.assertEquals

class ExpressShipmentTest {
    @Test
    fun testExpressShipmentConstruction() {
        val shipment = ExpressShipment("s100", "created", 100)
        assertEquals("s100", shipment.id)
        assertEquals("created", shipment.status)
    }
}