import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.*;

/**
 * This class will create a shipment schedule for drones based on the specifications of the drones and deliveries.
 *
 * Created by Adam on 2016-11-12.
 */
public class ShipmentSchedule {
    List<Drone> masterDroneList = new ArrayList<>();
    List<Destination> masterDestinationList = new ArrayList<>();
    List<Trip> masterTripList = new ArrayList<>();


    public void run() throws IOException {
        readFile("shippingInfo.txt");

        for (Destination destination : masterDestinationList) {
            boolean addedToTrip = false;
            for (Trip trip : masterTripList) {
                if (trip.isFull()) {
                    continue;
                }
                if (destination.getPackageWeight() <= trip.getWeightRemaining()) {
                    List<Destination> destinationList = trip.getDestinationList();
                    destinationList.add(destination);
                    trip.setDestinationList(destinationList);
                    trip.setWeightRemaining(trip.getWeightRemaining() - destination.getPackageWeight());
                    if (trip.getWeightRemaining() == 0) {
                        trip.setFull(true);
                    }
                    addedToTrip = true;
                }
            }

            if (!addedToTrip) {
                for (Drone drone : masterDroneList) {
                    if (drone.getMaxWeight() >= destination.getPackageWeight()) {
                        Trip trip = new Trip(drone);
                        List<Destination> destinationList = new ArrayList<>();
                        destinationList.add(destination);
                        trip.setWeightRemaining(trip.getWeightRemaining() - destination.getPackageWeight());
                        trip.setDestinationList(destinationList);
                        if (trip.getWeightRemaining() == 0) {
                            trip.setFull(true);
                        }
                        masterTripList.add(trip);
                        break;
                    }
                }
            }
        }

        printList();

    }

    /**
     * This will read in a shipping info file and populate the masterDroneList and the masterDestinationList.
     *
     * @param fileName The name of the input file with the drone and destination information
     * @throws IOException
     */
    public boolean readFile(String fileName) throws IOException{
        URL path = ShipmentSchedule.class.getResource(fileName);
        File file = new File(path.getFile());
        Scanner in = new Scanner(new FileReader(file));

        try {

            String line = removeLinePrefix(in.nextLine());

            if (line == null) {
                System.out.println("The input file isn't formatted correctly. Each line must start with 'Line #x:'");
                return false;
            }


            String[] droneArray = line.split(",");

            if (droneArray.length < 1) {
                System.out.println("There are no drones specified in the input file. No deliveries can be made.");
                return false;
            }

            int idCount = 0;
            for (int i=0; i<droneArray.length; i++) {
                Drone drone = new Drone();
                drone.setDroneId(idCount);
                drone.setDroneName(removeBrackets(droneArray[i]).trim());
                i++;
                drone.setMaxWeight(Double.parseDouble(removeBrackets(droneArray[i])));
                masterDroneList.add(drone);
                idCount ++;
            }

            while (in.hasNext()) {
                line = removeLinePrefix(in.nextLine());
                String[] destinationArray = line.split(",");
                // Check if there are only 2 items in list
                Destination destination = new Destination();
                destination.setLocationName(removeBrackets(destinationArray[0]));
                destination.setPackageWeight(Double.valueOf(removeBrackets(destinationArray[1])));
                masterDestinationList.add(destination);
            }

        } catch (Exception e){
            //print error and where it occurred
            System.out.println(e);
        } finally {
            in.close();
            return true;
        }
    }

    /**
     * Removes the braces ("[]") surrounding each entry
     * @param item - the name of the item
     * @return - the name of the item with the brackets removed
     */
    public String removeBrackets (String item) {
        return item.replace("[", "").replace("]", "");
    }

    /**
     * Each line starts with "Line #x:". This must be removed before continuing.
     * @param line - The line read in from the input file
     * @return - The same line with the "Line #x:" prefix removed.
     */
    public String removeLinePrefix (String line) {
        String[] lineArray = line.split(":");
        if (lineArray.length < 2) {
            return null;
        }
        return lineArray[1].trim();
    }

    /**
     * This will sort the master trip list based on the Id of the drone. This is done for display purposes.
     */
    public void sortMasterTripList() {
        Collections.sort(masterTripList, new Comparator<Trip>() {
            @Override
            public int compare(Trip c2, Trip c1) {
                return Integer.compare(c2.getDrone().getDroneId(), c1.getDrone().getDroneId());
            }
        });
    }

    /**
     * This will print the list of all trips to be made by each drone.
     */
    public void printList() {
        sortMasterTripList();

        int droneId = -1;
        int tripId = 1;
        for (Trip trip : masterTripList) {
            Drone drone = trip.getDrone();
            tripId ++;
            if (drone.getDroneId() != droneId) {
                tripId = 1;
                System.out.println("");
                System.out.println("[" + drone.getDroneName() + "]");
                droneId ++;
            }

            System.out.println("Trip #" + tripId);

            StringBuilder locations = new StringBuilder();
            for (Destination destination : trip.getDestinationList()) {
               locations.append(", [" + destination.getLocationName() + "]");
            }
           String locationsToPrint = locations.substring(2, locations.length());

            System.out.println(locationsToPrint);


        }
    }

}
