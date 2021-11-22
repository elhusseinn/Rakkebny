import java.util.ArrayList;
import java.util.Scanner;

public class Admin extends User {
    protected ArrayList<Driver> pendingDriver = new ArrayList<Driver>();
    protected ArrayList<User> Suspended = new ArrayList<User>();


    public void addDriver(Driver driver) {
        pendingDriver.add(driver);
    }

    public ArrayList<Driver> getPendingDriver() {
        return pendingDriver;
    }

    @Override
    public User register() {
        Admin admin = new Admin();
        Scanner scanner = new Scanner(System.in);
        String name;
        String email;
        String password;
        String phoneNo;

        try {
            System.out.println("Please enter a user name: ");
            name = scanner.nextLine();
            admin.setUserName(name);
            System.out.println("Please enter a password: ");
            password = scanner.nextLine();
            admin.setPassword(password);
        } catch (Exception e) {
            System.out.println("Please try again!");
        }
        return admin;
    }
    public void SuspendedUser(User user) {
        if (user instanceof Admin) {
        } else {
            Suspended.add(user);
        }
    }

    public void verifyRegisteration(Driver driver) {
        if (pendingDriver.isEmpty()) {
            System.out.println("there's no pending drivers");
        } else {
            for (int i = 0; i < pendingDriver.size(); i++) {
                System.out.println(pendingDriver.get(i).getUserName());
            }
        }
    }


}
