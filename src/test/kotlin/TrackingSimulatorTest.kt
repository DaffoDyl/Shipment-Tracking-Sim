import kotlin.test.Test
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.test.assertEquals
import kotlinx.coroutines.test.runTest

class TrackingSimulatorTest {
    @Test
    fun testTrackingSimulator(): Unit = runBlocking {
        runTest {
            val trackingSim = TrackingSimulator()
            val job = launch { trackingSim.runSimulation() }
            job.join()
            val shipment = trackingSim.findShipment("s10000")
            assertEquals("lost", shipment?.getStatus())
            assertEquals("Los Angeles CA", shipment?.getCurrentLocation())
            assertEquals(1652718051403, shipment?.getExpectedDeliveryTimestamp())
            assertEquals("package was damaged slightly during shipping", shipment?.getNotes()?.get(0))
            assertEquals("shipping label reprinted because of damage", shipment?.getNotes()?.get(1))
            assertEquals("inspection completed on exported goods", shipment?.getNotes()?.get(2))
            //Assert Updates
        }
    }
}