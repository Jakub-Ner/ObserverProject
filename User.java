package ObserverProject;

import java.util.ArrayList;
import java.util.List;

public class User {
    final String name;
    private List<Location> locationList;

    public User(String name){
        this.name = name;
        locationList = new ArrayList<>();
    }


    public void unsubscribeLocation(String location){
        Location unsubscribeLocation = Location.findLocation(locationList, location);
        if (unsubscribeLocation == null){
            System.out.println("Couldn't find the "+ location);
        }
        else{
            locationList.remove(unsubscribeLocation);
        }
    }
    public boolean subscribeLocation(String location){
        Location subscribedLocation = Location.findLocation(locationList, location);

        if (subscribedLocation != null){
            System.out.println(location + " is already subscribed");
            return false;
        }
        subscribedLocation = new Location(location);
        locationList.add(subscribedLocation);
        return true;
    }
    public void displayLocations(){
        locationList.forEach(location -> System.out.println(location.getLocationName()));
    }
}
