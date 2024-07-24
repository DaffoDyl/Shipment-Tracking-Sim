import kotlin.test.Test
import kotlin.test.assertEquals

class ShippingUpdateTest {
    @Test
    fun testShippingUpdateConstruction() {
        val shippingUpdate = ShippingUpdate("created", "shipped", 1000)
        assertEquals("created", shippingUpdate.previousStatus)
        assertEquals("shipped", shippingUpdate.newStatus)
        assertEquals(1000, shippingUpdate.timestamp)
    }
}