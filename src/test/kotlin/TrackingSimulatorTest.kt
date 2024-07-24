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
            assertEquals("lost", shipment?.status)
            assertEquals("Los Angeles CA", shipment?.currentLocation)
            assertEquals(1652718051403, shipment?.expectedDeliveryTimestamp)
        }
    }
}