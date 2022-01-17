package ObserverProject;

import java.util.ArrayList;
import java.util.List;

public class CSI {
    protected List<Location> locationList;
    private static int counter;
    private Object locationsSemaphore = new Object();

    CSI() {
        locationList = new ArrayList<>();
    }

    public void run() throws InterruptedException {
        synchronized (locationsSemaphore) {
            counter = locationList.size();
            for (Location location : locationList) {
                new Thread(() -> getSendMeasurements(location)).run();
            }
            while (counter > 0) {
                Thread.sleep(500);
            }
        }
    }

    public void getSendMeasurements(Location location) {
        String measurement = location.getMeasurements();
        Measurement serializedMeasurement = Measurement.serializeMeasurement(measurement);
        sendMeasurements(location, serializedMeasurement);
        end_thread();
    }
    public void sendMeasurements(Location location, Measurement measurement) {
        location.userList.forEach(user -> user.addMeasurements(measurement));
    }

    public synchronized void end_thread() {
        counter -= 1;
    }

    public void removeLocation(String location, User user) {
        synchronized (locationsSemaphore) {
            Location removalLocation = Location.findLocation(locationList, location);
            removalLocation.userList.remove(user);
            if(removalLocation.userList.size() == 0){
                locationList.remove(removalLocation);
            }
        }
    }

    public void addLocation(String location, User user) {
        synchronized (locationsSemaphore) {
            Location newLocation = new Location(location);
            if (Location.findLocation(locationList, location) == null) {
                locationList.add(newLocation);
            }
            newLocation.userList.add(user);

        }
    }
}
