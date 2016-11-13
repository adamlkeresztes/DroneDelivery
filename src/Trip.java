import java.util.List;

/**
 * This is a single trip made by a drone. It can contain multiple destinations.
 *
 * Created by Adam on 2016-11-12.
 */
public class Trip {

    private int tripID;
    private Drone drone;
    private double weightRemaining; // Assumed to be in kilograms
    private boolean isFull = false; // If there is no room left on this trip
    private List<Destination> destinationList;

    public Trip (Drone drone) {
        this.drone = drone;
        this.setWeightRemaining(drone.getMaxWeight());
    }

    public int getTripID() {
        return tripID;
    }

    public void setTripID(int tripID) {
        this.tripID = tripID;
    }

    public Drone getDrone() {
        return drone;
    }

    public void setDrone(Drone drone) {
        this.drone = drone;
    }

    public double getWeightRemaining() {
        return weightRemaining;
    }

    public void setWeightRemaining(double weightRemaining) {
        this.weightRemaining = weightRemaining;
    }

    public boolean isFull() {
        return isFull;
    }

    public void setFull(boolean full) {
        isFull = full;
    }

    public List<Destination> getDestinationList() {
        return destinationList;
    }

    public void setDestinationList(List<Destination> destinationList) {
        this.destinationList = destinationList;
    }
}
