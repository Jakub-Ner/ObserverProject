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
        String measurements = location.getMeasurements();
        System.out.println(measurements);
        end_thread();
    }

    public synchronized void end_thread() {
        counter -= 1;
    }

    public void addLocation(String location) {
        synchronized (locationsSemaphore) {
            if (Location.findLocation(locationList, location) == null) {
                locationList.add(new Location(location));
            }
        }
    }
}
