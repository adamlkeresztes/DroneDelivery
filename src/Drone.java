import java.util.List;

/**
 * This is the drone object. It contains the name and max weight it can carry.
 * Created by Adam on 2016-11-12.
 */
public class Drone {

    private int droneId;
    private String droneName;
    private double maxWeight; // Assumed to be in Kilograms

    public int getDroneId() {
        return droneId;
    }

    public void setDroneId(int droneId) {
        this.droneId = droneId;
    }

    public String getDroneName() {
        return droneName;
    }

    public void setDroneName(String droneName) {
        this.droneName = droneName;
    }

    public double getMaxWeight() {
        return maxWeight;
    }

    public void setMaxWeight(double maxWeight) {
        this.maxWeight = maxWeight;
    }

}
