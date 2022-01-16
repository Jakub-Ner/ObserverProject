package ObserverProject;

import java.util.ArrayList;
import java.util.List;

public class CSI {
    protected List<Location> locationList;
    private static int counter;

    CSI() {
        locationList = new ArrayList<>();
    }

    public void run() throws InterruptedException {
        counter = locationList.size();
        List<Thread> threads = new ArrayList<>();
        for (Location location : locationList) {
            threads.add(new Thread(() -> getSendMeasurements(location)));
            threads.get(threads.size()-1).start();
        }
        while (counter > 0) {
            threads.get(threads.size() - 1).join();
        }
    }

//    public updateLocationList(){
//
//    }
    public void getSendMeasurements(Location location) {
        location.getMeasurements();

        end_thread();
    }
    public synchronized void end_thread(){
        counter -=1;
    }
    public void addLocation(String location) {
        if (Location.findLocation(locationList, location) == null){
            locationList.add(new Location(location));

        }
    }
}
