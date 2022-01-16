package ObserverProject;

import java.util.ArrayList;
import java.util.List;

public class KUPA {
    protected User currentUser;
    protected List<User> userList;
    protected CSI csi;

    public KUPA() {
        userList = new ArrayList<>();

        csi = new CSI();
        new Thread(() -> {
            try {
                while (true) {
                    Thread.sleep(10000);
                    csi.run();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public User logIn(String name) {
        for (User user : userList) {
            if (user.name.equals(name)) {
                return user;
            }
        }
        return null;
    }


}
