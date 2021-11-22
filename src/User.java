import java.util.Scanner;

public abstract class User {
    private String userName;
    private String emailAddress;
    private String Password;
    private String phoneNumber;

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void setPassword(String password) {
        this.Password = password;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getPassword() {
        return Password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getUserName() {
        return userName;
    }

    public  User login(String email,String password){
        User user = null;
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Please enter a user name: ");
            email = scanner.nextLine();
            user.setUserName(email);
            System.out.println("Please enter a password: ");
            password = scanner.nextLine();
            user.setPassword(password);
        } catch (Exception e) {
            System.out.println("Please try again! ");
        }
        return user;
    }

    public abstract User register();
}
