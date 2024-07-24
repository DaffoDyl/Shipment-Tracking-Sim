import shipmentTypes.Shipment

interface ShipmentObserver {

    fun notify(shipment: Shipment)

}