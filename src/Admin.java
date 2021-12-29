import java.util.ArrayList;
import java.util.Scanner;

public class Admin extends User {

AdminDBManager db = new AdminDBManager();
    public void verifyRegistration(Driver driver) {
        db.changeStatus(driver, "Registered");
        System.out.println("Verified successfully Driver now can login into his account");
    }
    public void SuspendedUser(User user) {
        db.changeStatus(user, "Suspended");
    }





}
