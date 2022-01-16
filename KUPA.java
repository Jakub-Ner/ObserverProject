package ObserverProject;

import java.util.ArrayList;
import java.util.List;

public class KUPA {
    protected User currentUser;
    protected List<User> userList;
    protected CSI csi;

    public KUPA() {
        userList = new ArrayList<>();
//        userList.add(new User("admin"));
        csi = new CSI();
        new Thread(() -> {
            try {
                csi.run();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
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
