import java.sql.SQLOutput;
import java.util.Scanner;

public class Customer extends User {
    private enum status{
        Pending,Registered,Suspended
    }

    @Override
    public void login(User user) {
        String name;
        String password;
        Customer dummy=new Customer();
        Scanner scanner=new Scanner(System.in);
        System.out.println("Please enter a user name: ");
        name=scanner.nextLine();
        dummy.setUserName(name);
        System.out.println("Please enter a password: ");
        password=scanner.nextLine();
        dummy.setPassword(password);

    }
    @Override
    public Customer register(){
        Customer newCustomer=new Customer();
        Scanner scanner=new Scanner(System.in);
        String name;
        String email;
        String password;
        String phoneNo;
        System.out.println("Please enter a user name: ");
        name=scanner.nextLine();
        newCustomer.setUserName(name);
        System.out.println("Please enter an email: ");
        email=scanner.nextLine();
        newCustomer.setEmailAddress(email);
        System.out.println("Please enter a phone number: ");
        phoneNo=scanner.nextLine();
        newCustomer.setPhoneNumber(phoneNo);
        System.out.println("Please enter a password: ");
        password=scanner.nextLine();
        newCustomer.setPassword(password);
        return newCustomer;
    }
}
