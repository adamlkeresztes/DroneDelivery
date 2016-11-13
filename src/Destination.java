/**
 * This is the destination for a package. It contains the package name and weight.
 *
 * Created by Adam on 2016-11-12.
 */
public class Destination {

    private String locationName;
    private double packageWeight; // Assumed to be in kilograms

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public double getPackageWeight() {
        return packageWeight;
    }

    public void setPackageWeight(double packageWeight) {
        this.packageWeight = packageWeight;
    }
}
