import java.util.ArrayList;
import java.util.Scanner;

public class Customer extends User {
    private enum status {
        Pending, Registered, Suspended
    }

/*
    @Override
    public Customer login() {
        String name;
        String password;
        Customer dummy = new Customer();
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Please enter a user name: ");
            name = scanner.nextLine();
            dummy.setUserName(name);
            System.out.println("Please enter a password: ");
            password = scanner.nextLine();
            dummy.setPassword(password);
        } catch (Exception e) {
            System.out.println("Please try again! ");
        }
        return dummy;
    }
*/
    @Override
    public Customer register() {
        Customer newCustomer = new Customer();
        Scanner scanner = new Scanner(System.in);
        String name;
        String email;
        String password;
        String phoneNo;
        try {
            System.out.println("Please enter a user name: ");
            name = scanner.nextLine();
            newCustomer.setUserName(name);
            System.out.println("Please enter an email: ");
            email = scanner.nextLine();
            newCustomer.setEmailAddress(email);
            System.out.println("Please enter a phone number: ");
            phoneNo = scanner.nextLine();
            newCustomer.setPhoneNumber(phoneNo);
            System.out.println("Please enter a password: ");
            password = scanner.nextLine();
            newCustomer.setPassword(password);
        } catch (Exception e) {
            System.out.println("Please try again!");
        }
        return newCustomer;
    }

    public Ride requestRide() {
        Ride ride = new Ride();
        String source;
        String destination;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter source for the ride: ");
        source = scanner.nextLine();
        ride.setSource(source);
        System.out.println("Please enter destination for the ride: ");
        destination = scanner.nextLine();
        ride.setDestination(destination);
        return ride;
    }
}
