import java.util.List;

/**
 * Created by Adam on 2016-11-12.
 */
public class DroneDelivery {

    public Drone droneDelivering;
    public List<Destination> destinationList;

    public Drone getDroneDelivering() {
        return droneDelivering;
    }

    public void setDroneDelivering(Drone droneDelivering) {
        this.droneDelivering = droneDelivering;
    }

    public List<Destination> getDestinationList() {
        return destinationList;
    }

    public void setDestinationList(List<Destination> destinationList) {
        this.destinationList = destinationList;
    }
}
