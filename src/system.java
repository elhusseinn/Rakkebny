import java.util.ArrayList;
import java.util.List;

public class system extends DataBase implements rideFeatures {

    private static system instance = null;

    private system() {
    }

    public static system GetInstance() {
        if (instance == null) {
            instance = new system();
        }
        return instance;

    }

    @Override
    public Driver searchDriver(String place) {
        Driver chosenDriver = null;
        for (int i = 0; i < GetInstance().users.size(); i++) {
            if (GetInstance().getUsers().get(i) instanceof Driver) {
                chosenDriver = (Driver) GetInstance().getUsers().get(i);
                for (int v = 0; v < chosenDriver.getFavouritePlaces().size(); v++) {
                    if (chosenDriver.getFavouritePlaces().get(v).equals(place)) {

                        return chosenDriver;
                    }
                }
            }
        }
        return chosenDriver;
    }


    public User Authenticate(User user) {
        if (user instanceof Admin) {
            for (int i = 0; i < admins.size(); i++) {
                if (admins.get(i).getUserName().equalsIgnoreCase(user.getUserName()) &&
                        admins.get(i).getPassword().equals(user.getPassword())) {
                    return admins.get(i);

                }
            }

        } else {

            for (int i = 0; i < users.size(); i++) {
                if (users.get(i).getUserName().equalsIgnoreCase(user.getUserName()) &&
                        users.get(i).getPassword().equals(user.getPassword())) {
                    return users.get(i);

                }
            }

        }
        System.out.println("Credentials doesn't match!\n");
        return null;
    }


    public String accountCheck(User user) {
       /* if (user.getStatus().equalsIgnoreCase("suspended")){
            return "you are suspended";
        }*/

        for (int i = 0; i < users.size(); i++) {
            if (user.getUserName().equalsIgnoreCase(users.get(i).getUserName())) {
                return "Error User name already exists, user name must be unique";
            }
        }
        return "";
    }

    public void addDriver(Driver driver) {
        pendingDrivers.add(driver);
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void addCompletedRides(Ride ride) {
        completedRides.add(ride);
    }

    public void addAdmin(Admin admin) {
        admins.add(admin);
    }


}
