package ObserverProject;

import java.util.List;

public class Location {
    private String URL = "https://api.openweathermap.org/data/2.5/weather?q=";
    private String locationName;
    private String key = Key.key;
    public List<User> userList;

    public Location(String location){
        this.locationName = location;
    }

    public String getMeasurements(){
        String pogoda =GetHttpData.HttpGet(URL + locationName + key);
        return pogoda;
    }
    public static Location findLocation(List<Location> locationList, String wantedLocation){
        if (locationList.size() > 0) {
            for (Location location : locationList) {
                if (location.locationName.equals(wantedLocation)) {
                    return location;
                }
            }
        }
        return null;
    }

    public String getLocationName(){return locationName;}
}
