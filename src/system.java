import java.util.ArrayList;
import java.util.List;

public class system extends DataBase implements rideFeatures{

    private static system instance = null;
    private system(){}
    public static system GetInstance(){
        if (instance == null){instance = new system();}
        return instance;

    }

    @Override
    public Driver searchDriver(String place) {
        Driver choosenDriver=null;
        for (int i = 0 ;i<GetInstance().users.size();i++){
            if (GetInstance().getUsers().get(i) instanceof Driver){
                choosenDriver = (Driver) GetInstance().getUsers().get(i);
                for (int v = 0 ;v<choosenDriver.getFavouritePlaces().size();v++) {
                    if (choosenDriver.getFavouritePlaces().get(v).equals(place)) {
                        return choosenDriver;
                    }
                }
            }
        }
        return choosenDriver;
    }


    /*  public boolean Authenticate(User user){
       String type = user.getClass().getName();

       if(type.equalsIgnoreCase("customer")){
           for(int i = 0; i < customers.size();i++){
               if((customers.get(i).getUserName().equals(user.getUserName())) &&
                 (customers.get(i).getPassword().equals(user.getPassword()))){
                   return true;
               }
           }
       }
       else if (type.equalsIgnoreCase("driver")){

           for(int i = 0; i < drivers.size();i++){
               if((drivers.get(i).getUserName().equals(user.getUserName())) &&
                       (drivers.get(i).getPassword().equals(user.getPassword()))){
                   return true;
               }
           }

       }
       else if (type.equalsIgnoreCase("admin")){
           for(int i = 0; i < admins.size();i++){
               if((admins.get(i).getUserName().equals(user.getUserName())) &&
                       (admins.get(i).getPassword().equals(user.getPassword()))){
                   return true;
               }
           }

       }

        return false;
    }

   */

    public boolean Authenticate(User user){

        for(int i = 0; i < users.size(); i++){
            if(users.get(i).getUserName().equalsIgnoreCase(user.getUserName()) &&
                    users.get(i).getPassword().equals(user.getPassword())){
                return true;

            }
        }
        System.out.println("Credentials doesn't match!\n");
        return false;
    }

    public String accountCheck(User user){
        for(int i = 0; i < users.size(); i++){
            if(user.getUserName().equalsIgnoreCase(users.get(i).getUserName())){
                return "Error User name already exists, user name must be unique";
            }
        }
        return "";
    }
    public void addDriver(User user) {
        pendingDrivers.add(user);
    }
    public ArrayList<User> getUsers() {
        return users;
    }



}