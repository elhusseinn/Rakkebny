import java.util.ArrayList;

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
    public void register(String name , String email,String password,String phoneNumber) {
        this.setEmailAddress(email);
        this.setUserName(name);
        this.setPassword(password);
        this.setPhoneNumber(phoneNumber);
    }

}
