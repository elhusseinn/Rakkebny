import java.util.ArrayList;
import java.util.Scanner;

public class Admin extends User {


    public void verifyRegistration(Driver driver) {
        driver.setStatus(status.Registered);
        system.GetInstance().users.add(driver);
        system.GetInstance().pendingDrivers.remove(driver);
        System.out.println("Verified successfully Driver now can login into his account");
    }
    public void SuspendedUser(User user) {
        if (user instanceof Admin) {
            System.out.println("Cannot suspend an Admin!!");
        } else {
            user.setStatus(status.Suspended);
            system.GetInstance().Suspended.add(user);
            system.GetInstance().users.remove(user);
            System.out.println("User suspended!!");
        }
    }
    public Driver getPendingDriver(int i) {

        return system.GetInstance().pendingDrivers.get(i);
    }





}
