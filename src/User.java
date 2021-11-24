import java.util.Scanner;

public abstract class User {
    private String userName;
    private String emailAddress;
    private String Password;
    private String phoneNumber;

    protected enum status {
        Registered, Pending, Suspended, NONE
    }

    private status stat = status.Registered;

    public status getStatus() {
        return stat;
    }

    public void setStatus(status st) {
        this.stat = st;
    }

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

    public User login(User user) {
        if (user instanceof Admin) {
            return system.GetInstance().Authenticate(user);
        } else {
            User currUser = system.GetInstance().Authenticate(user);

            if(currUser == null){
                System.out.println("You are either not registered or you're still pending");
                return currUser;
            }
            return currUser;
        }
    }
}