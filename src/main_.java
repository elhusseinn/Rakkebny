import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class main_ {

    SQLiteJDBC s = new SQLiteJDBC();
    controllerDBManager controllerDBManager = new controllerDBManager();
    DriverDBManager driverDBManager = new DriverDBManager();
    AdminDBManager adminDBManager = new AdminDBManager();

    public void logIn() {
        String mess;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the login page\n" + "Login as\n1-Customer\n2-Driver\n3-Admin");
        mess = scanner.nextLine();
        switch (mess) {
            case "1":
                System.out.println("Welcome to the customer login page\nPlease enter you username");
                Customer dummyCustomer = new Customer();
                mess = scanner.nextLine();
                dummyCustomer.setUserName(mess);
                System.out.println("Please enter your password");
                mess = scanner.nextLine();
                dummyCustomer.setPassword(mess);
                dummyCustomer = (Customer) dummyCustomer.login(dummyCustomer);
                if (dummyCustomer != null) {
                    System.out.println("Successfully logged in!");
                    customerMenu(dummyCustomer);
                }
                break;

            case "2":
                System.out.println("Welcome to the driver login page\nPlease enter you username");
                Driver dummyDriver = new Driver();
                mess = scanner.nextLine();
                dummyDriver.setUserName(mess);
                System.out.println("Please enter your password");
                mess = scanner.nextLine();
                dummyDriver.setPassword(mess);
                dummyDriver = (Driver) dummyDriver.login(dummyDriver);
                if (dummyDriver != null) {
                    System.out.println("Successfully logged in!");
                    driverMenu(dummyDriver);
                }
                break;
            case "3":
                System.out.println("Welcome to the admin login page\nPlease enter you username");
                Admin dummyAdmin = new Admin();
                mess = scanner.nextLine();
                dummyAdmin.setUserName(mess);
                System.out.println("Please enter your password");
                mess = scanner.nextLine();
                dummyAdmin.setPassword(mess);
                dummyAdmin = (Admin) dummyAdmin.login(dummyAdmin);
                if (dummyAdmin != null) {
                    System.out.println("Successfully logged in!");
                    adminMenu(dummyAdmin);
                }
                break;
            default:
                System.out.println("Something wrong happened!!");
                break;
        }
    }

    public void driverMenu(Driver driver) {
        String mess;
        Scanner scanner = new Scanner(System.in);

        if (driverDBManager.getRideStatus(driver.getUserName()).equalsIgnoreCase("Available")) {
            System.out.println("1-List all notifications available.\n2- Set a favourite place.\n3- View average rating.\n4- View all previous rides.");
            mess = scanner.nextLine().toLowerCase();
            switch (mess) {
                case "1":
                    if (driver.getNotifications().isEmpty()) {
                        System.out.println("You don't have any notifications!\n");
                    } else {
                        int counter = 1;
                        for (int i = 0; i < driver.getNotifications().size(); i++) {
                            System.out.print(counter + " customerName-> " + driver.getNotifications().get(i).getCustomer().getUserName() + " ,source-> " + driver.getNotifications().get(i).getSource() + " ,destination-> " + driver.getNotifications().get(i).getDestination());
                            counter++;
                            System.out.println();
                        }

                        System.out.println("Do you want to make an offer(y/n)?\n");
                        mess = scanner.nextLine().toLowerCase();
                        switch (mess) {
                            case "y":
                                System.out.println("Enter the number of the ride\n");
                                int rideNumber = 0;
                                rideNumber = scanner.nextByte();
                                System.out.println("Enter the cost that you want: ");
                                double cost = scanner.nextDouble();
                                driver.makeOffer(driver.getNotifications().get(rideNumber - 1), cost); // customer, source, dest, rideID
                                System.out.println("Your cost is set successfully and user notified!");
                                break;
                            case "n":
                                break;
                            default:
                                System.out.println("Something is wrong!");
                                break;
                        }
                    }
                    break;
                case "2":
                    System.out.println("Enter a favourite place\n");
                    mess = scanner.nextLine().toLowerCase();
                    driver.setFavouritePlaces(mess);
                    break;
                case "3":
                    System.out.println(driver.getAverageRating());
                    break;

                case "4":
                    driver.listPreviousRides();
                    break;
            }
        } else if (driverDBManager.getRideStatus(driver.getUserName()).equalsIgnoreCase("onRide")) {
            int message;
            System.out.println("1-Arrived on Location.\n2- Arrived on Destination.\n");
            message = scanner.nextInt();
            switch (message) {
                case 1: {
                    driver.arrived(s.getRideNumber(driver.getUserName()), "Location");
                }
                break;
                case 2: {
                    driver.arrived(s.getRideNumber(driver.getUserName()), "Destination");
                    s.changeStatus(driver, "Available");
                }
                break;
            }
        }
    }

    public void customerMenu(Customer customer) {
        String mess;

        Scanner scanner = new Scanner(System.in);
        System.out.println("1-Request Ride\n2-show ride notifications\n3-logout");
        mess = scanner.nextLine().toLowerCase();
        switch (mess) {
            case "1" -> {
                System.out.println("Please enter source for the ride: ");
                String source = scanner.nextLine().toLowerCase();
                System.out.println("Please enter destination for the ride: ");
                String destination = scanner.nextLine().toLowerCase();
                System.out.println("Please enter number of passengers taking the ride: ");
                int noOfPassengers=scanner.nextInt();
                if(noOfPassengers>3){
                    System.err.println("sorry, number of passengers out of limit!");
                    break;
                }
                Ride ride = new Ride(source, destination, customer,noOfPassengers);
                customer.requestRide(ride);
            }

            case "2" -> {
                if (s.getCustomerNotification(customer).size() == 0) {
                    System.out.println("you don't have any notifications!");
                    break;
                }
                for (int i = 0; i < s.getCustomerNotification(customer).size(); i++) {
                    System.out.println((i + 1) + " Driver Name -> " + s.getCustomerNotification(customer).get(i).getDriver().getUserName() + ", cost -> " + s.getCustomerNotification(customer).get(i).getCost() + "\n");
                }

                System.out.println("Enter the number of the ride you want to accept\n");

                int rideNumber = 0;
                rideNumber = scanner.nextByte();

                int jk = 0;
                while (jk == 0) {
                    System.out.println("Would you like to accept the Ride(y/n)");
                    String inp;
                    inp = scanner.nextLine();
                    switch (inp) {
                        case "y" -> {
                            customer.acceptRide(s.getRide(customer.getUserName()));
                            s.changeStatus(s.getCustomerNotification(customer).get(rideNumber - 1).getDriver(), "onRide");
                            System.out.println("you have accepted the ride successfully!\n\n");


                            System.out.println("would you like to rate the driver(y/n)");
                            String inp2 = scanner.nextLine();

                            DiscountExcutor discountExcutor = new DiscountExcutor();
                            double cost = discountExcutor.applyDiscount(s.getCustomerNotification(customer).get(rideNumber - 1).getCost(), s.getRideDestination(s.getRide(customer.getUserName())), s.getRideNoOfPassengers(s.getRide(customer.getUserName())), customer.getUserName());

                            switch (inp2) {
                                case "y" -> {
                                    System.out.println("please Enter the number of rating 1-5");
                                    int rate = scanner.nextInt();

                                    s.updateRide(s.getCustomerNotification(customer).get(rideNumber - 1).getDriver().getUserName(), cost, s.getRide(customer.getUserName()), rate);
                                    s.deleteNotification(customer.getUserName());
                                    s.deleteCustomerNotification(s.getCustomerNotification(customer).get(rideNumber - 1).getCustomer().getUserName());
                                    System.out.println("Driver has been rated successfully!");
                                    jk++;


                                }
                                case "n" -> {
                                    s.updateRide(s.getCustomerNotification(customer).get(rideNumber - 1).getDriver().getUserName(), cost, s.getRide(customer.getUserName()), -1);
                                    s.deleteNotification(customer.getUserName());
                                    s.deleteCustomerNotification(s.getCustomerNotification(customer).get(rideNumber - 1).getCustomer().getUserName());
                                    jk++;
                                }

                            }


                        }
                        case "n" -> {
                            System.out.println("NVM\n");
                        }
                    }


                }
            }
            case "3" -> {
                System.out.println("NVM\n");
            }
        }

    }

    public void adminMenu(Admin admin)  {
        String mess;
        Scanner scanner = new Scanner(System.in);
        System.out.println("1-Show pending verification list\n2-Suspend user\n3-Show Events\n4-Add area to offered discounts");
        mess = scanner.nextLine();
        switch (mess) {
            case "1": {
                if (s.getPendingDrivers().isEmpty()) {
                    System.out.println("There's no current pending requests");
                    break;
                }
                for (int i = 0; i < s.getPendingDrivers().size(); i++) {
                    System.out.println((i + 1) + "-" + s.getPendingDrivers().get(i).getUserName());
                }
                System.out.println("Enter the number of the driver you want to verify");
                int driverNumber = scanner.nextInt();
                admin.verifyRegistration(controllerDBManager.getDriver(s.getPendingDrivers().get(driverNumber - 1).getUserName()));
            }
                break;
            case "2": {
                for (int i = 0; i < s.getRegisteredUsers().size(); i++) {
                    System.out.println((i + 1) + "-" + s.getRegisteredUsers().get(i).getUserName());
                }

                System.out.println("Enter the number of the user you want to suspend");
                int userNumber = scanner.nextInt();
                if (s.getRegisteredUsers().get(userNumber - 1) instanceof Customer) {
                    admin.SuspendedUser(controllerDBManager.getCustomer(s.getRegisteredUsers().get(userNumber - 1).getUserName()));
                } else if (s.getRegisteredUsers().get(userNumber - 1) instanceof Driver) {
                    admin.SuspendedUser(controllerDBManager.getDriver(s.getRegisteredUsers().get(userNumber - 1).getUserName()));
                } else {
                    System.out.println("Can't suspend this user");
                }
            }
            break;
            case "3":{

                for(int RideID : adminDBManager.getDistinctRideNumbers()){
                    System.out.println(RideID);
                }
                System.out.println("Choose the number of Ride you want to show: ");
                int RideID;
                RideID = scanner.nextInt();

                admin.showEvents(RideID);


            }
            break;
            case "4":{

                    System.out.println("Enter the Name of the Area : ");
                    String area;
                    area=scanner.nextLine();
                    admin.addAreaToDiscountOffers(area);


            }

        }
    }

    public void driverRegister() {
        String mess;
        Driver driver = new Driver();
        Scanner scanner = new Scanner(System.in);
        System.out.println("welcome to driver registration, please enter your information" + "\n" + "Username: ");
        mess = scanner.nextLine();
        driver.setUserName(mess);

        System.out.println("phone number: ");
        mess = scanner.nextLine();
        driver.setPhoneNumber(mess);

        System.out.println("Email-Address: ");
        mess = scanner.nextLine();
        driver.setEmailAddress(mess);

        System.out.println("password: ");
        mess = scanner.nextLine();
        driver.setPassword(mess);

        System.out.println("driving liscence: ");
        mess = scanner.nextLine();
        driver.setDrivingLiscence(mess);

        System.out.println("national ID: ");
        mess = scanner.nextLine();
        driver.setNationalID(mess);
        driver.register(driver);


    }

    public void customerRegister() throws ParseException {
        String mess;
        Customer newCustomer = new Customer();
        Scanner scanner = new Scanner(System.in);
        System.out.println("welcome to customer registration, please enter your information" + "\n" + "Username: ");
        mess = scanner.nextLine();
        newCustomer.setUserName(mess);

        System.out.println("Password: ");
        mess = scanner.nextLine();
        newCustomer.setPassword(mess);

        System.out.println("Email-Address: ");
        mess = scanner.nextLine();
        newCustomer.setEmailAddress(mess);

        System.out.println("phone number: ");
        mess = scanner.nextLine();
        newCustomer.setPhoneNumber(mess);

        System.out.println("birthday date (dd/MM/yyy)");
        mess = scanner.nextLine();
        String sDate1 = mess;

        Date date1 = new SimpleDateFormat("dd/MM/yyy").parse(sDate1);
        java.sql.Date sqlDate = new java.sql.Date(date1.getTime());

        newCustomer.setBirthDate(sqlDate);

        newCustomer.register(newCustomer);
    }

    public void Menu() throws ParseException {
        String mess;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to registration" + "\n" + "1-Register 2-login");
        mess = scanner.nextLine();
        switch (mess) {
            case "1" -> {
                System.out.println("Welcome to registration" + "\n" + "1-Driver 2-Customer");
                mess = scanner.nextLine();
                switch (mess) {
                    case "1" -> driverRegister();
                    case "2" -> customerRegister();
                }
            }
            case "2" -> logIn();
        }

    }


    public static void main(String[] args) throws ClassNotFoundException, ParseException {

        main_ m = new main_();
        while (true) {
            m.Menu();

        }
    }
}




