import java.util.ArrayList;
import java.util.Scanner;

public class Admin extends User {


    public void verifyRegistration(Driver driver) {
        driver.setStatus(status.Registered);
        system.GetInstance().pendingDrivers.remove(driver);
    }
    public void SuspendedUser(User user) {
        if (user instanceof Admin) {
        } else {
            system.GetInstance().Suspended.add(user);
            user.setStatus(status.Suspended);
        }
    }
    public ArrayList<User> getPendingDrivers() {
        return system.GetInstance().pendingDrivers;
    }

    /*public void verifyRegisteration(Driver driver) {
        if (pendingDriver.isEmpty()) {
            System.out.println("there's no pending drivers");
        } else {
            driver.setStatus(status.Registered);



        }
    }*/




}
