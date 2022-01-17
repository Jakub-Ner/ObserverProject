package ObserverProject;

import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

public class Menu extends KUPA {
    public Menu() throws InterruptedException {
        menu();
    }

    private void menu() {
        welcome();
        while (true) {
            mainMenu();
        }
    }

    private void welcome() {
        System.out.println("Hi!\n" +
                "[1]-create an acount\n" +
                "[2]-log-in");

        int input = 0;
        while (input < 1 || input > 2) {
            input = takeIntInput();
        }

        if (input == 1) createUser();
        if (input == 2) logIn();
    }

    public void createUser() {
        String name = takeStringInput();
        if (super.logIn(name) != null) {
            System.out.println("\nthis name is already used");
            menu();
        } else {
            super.currentUser = new User(name);
            super.userList.add(super.currentUser);
        }
    }

    public void logIn() {
        String name = takeStringInput();
        if (name.equals("admin")) {
            adminLog();
        } else {
            User prolyUser = super.logIn(name);
            if (prolyUser == null) {
                System.out.println("Couldn't find the user");
                welcome();
            }
            super.currentUser = prolyUser;
        }
    }

    private void mainMenu() {
        System.out.println("\noptions:\n" +
                "[1]-display your list of subscribed locations\n" +
                "[2]-subscribe new location\n" +
                "[3]-unsubscribe location\n" +
                "[4]-Log out");

        int input = 0;
        while (input < 1 || input > 4) {
            input = takeIntInput();
        }
        if (input == 1) super.currentUser.displayLocations();
        if (input == 2) subscribeLocation();
        if (input == 3) unsubscribeLocation();
        if (input == 4) menu();
    }

    public void subscribeLocation() {
        System.out.println("Which location would you subscribe?");
        String location = takeStringInput();
        boolean addedLocation = super.currentUser.subscribeLocation(location);
        if (addedLocation) {
            super.csi.addLocation(location, super.currentUser);
        }
    }

    public void unsubscribeLocation() {
        System.out.println("Which location would you unsubscribe?");
        String location = takeStringInput();
        boolean removedLocation = super.currentUser.unsubscribeLocation(location);
        if(removedLocation){
            super.csi.removeLocation(location, super.currentUser);

        }
    }

    public String takeStringInput() {
//        boolean properInput = false;
        String input = null;
//        while (!properInput) {
//            try {
        input = new Scanner(System.in).nextLine();
//                properInput = true;
//            } catch (InputMismatchException e) {
//                e.printStackTrace();
//            }
//        }
        if (input.toUpperCase(Locale.ROOT).equals("LOG OUT")) {
            menu();
        }
        return input;
    }

    public int takeIntInput() {
        boolean properInput = false;
        int input = 0;
        while (!properInput) {
            try {
                input = new Scanner(System.in).nextInt();
                properInput = true;
            } catch (InputMismatchException e) {
                System.out.println("invalid input");
            }
        }
        return input;
    }

    private void adminLog() {
        System.out.println("\nusers:\n");
        userList.forEach(user -> System.out.println(user.name));

        System.out.println("\nusing locations:\n");
        csi.locationList.forEach(location -> System.out.println(location.getLocationName()));

        while (true) {
            takeStringInput();
        }
    }
}

