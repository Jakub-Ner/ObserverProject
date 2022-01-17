package ObserverProject;

import java.util.ArrayList;
import java.util.List;

public class User {
    final String name;
    private List<Location> locationList;
    private List<Measurement> measurementList;

    public User(String name) {
        this.name = name;
        locationList = new ArrayList<>();
        measurementList = new ArrayList<>();
    }
    public void addMeasurements(Measurement measurement){
        measurementList.add(measurement);
    }

    public void displayLocations() {
        locationList.forEach(location -> System.out.println(location.getLocationName()));
    }

    public boolean unsubscribeLocation(String location) {
        Location unsubscribeLocation = Location.findLocation(locationList, location);
        if (unsubscribeLocation == null) {
            System.out.println("Couldn't find the " + location);
            return false;
        } else {
            locationList.remove(unsubscribeLocation);
        }
        return true;
    }

    public boolean subscribeLocation(String location) {
        Location subscribedLocation = Location.findLocation(locationList, location);

        if (subscribedLocation != null) {
            System.out.println(location + " is already subscribed");
            return false;
        }
        subscribedLocation = new Location(location);
        locationList.add(subscribedLocation);
        return true;
    }
}
