import java.util.ArrayList;
import java.util.Scanner;

public class Admin extends User{
    private ArrayList <Driver> pendingDriver = new ArrayList <Driver>();
    private ArrayList <User> Suspended = new ArrayList <User>();


    public void addDriver (Driver driver){
        pendingDriver.add(driver);
    }
    public ArrayList<Driver> getPendingDriver() {
        return pendingDriver;
    }
    public void SuspendedUser(User user){
        if (user instanceof Admin){}
        else{
            Suspended.add(user);
        }
    }
    public void verifyRegisteration (Driver driver){
        if (pendingDriver.isEmpty()){
            System.out.println("there's no pending drivers");
        }else{
            for (int i =0 ; i<pendingDriver.size();i++){
                System.out.println(pendingDriver.get(i).getUserName());
            }
        }
    }

    @Override
    public User login() {
        String username;
        String password;
        Admin admin = new Admin();
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Please enter a user name: ");
            username = scanner.nextLine();
            admin.setUserName(username);
            System.out.println("Please enter a password: ");
            password = scanner.nextLine();
            admin.setPassword(password);
        } catch (Exception e) {
            System.out.println("Please try again! ");
        }
        return admin;
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
            System.out.println("Please enter an email: ");
            email = scanner.nextLine();
            admin.setEmailAddress(email);
            System.out.println("Please enter a phone number: ");
            phoneNo = scanner.nextLine();
            admin.setPhoneNumber(phoneNo);
            System.out.println("Please enter a password: ");
            password = scanner.nextLine();
            admin.setPassword(password);
        } catch (Exception e) {
            System.out.println("Please try again!");
        }
        return admin;
    }
/*
    @Override
    public void register(String name , String email,String password,String phoneNumber) {
        this.setEmailAddress(email);
        this.setUserName(name);
        this.setPassword(password);
        this.setPhoneNumber(phoneNumber);
    }*/

}
