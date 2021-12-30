
public class Ride implements Discount {
    private String source;
    private String destination;
    private Driver driver;
    private Customer customer;
    private double cost;
    private int rate;
    private int RideID;
    private int noOfPassengers;
    private RideDBManager RM = new RideDBManager();

    public Ride() {

    }
    public Ride(String source, String destination, Customer customer,int noOfPassengers) {
        this.source = source;
        this.destination = destination;
        this.customer = customer;
        this.noOfPassengers=noOfPassengers;
    }

    public void setRideID(int rideID) {
        RideID = rideID;
    }
    public void setDriver(Driver driver) { this.driver = driver; }
    public void setRate(int rate) {
        this.rate = rate;
    }
    public void setCustomer(Customer customer) { this.customer = customer; }
    public void setNoOfPassengers(int noOfPassengers) {
        this.noOfPassengers = noOfPassengers;
    }
    public void setSource(String source) {
        this.source = source;
    }
    public void setCost(double cost) {
        this.cost = cost;
    }
    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int getNoOfPassengers() {
        return noOfPassengers;
    }
    public int getRideID() {
        return RideID;
    }
    public int getRate() {
        return rate;
    }
    public Driver getDriver() { return driver; }
    public Customer getCustomer() { return customer; }
    public String getSource() {
        return source;
    }
    public String getDestination() {
        return destination;
    }
    public double getCost() {
        return cost;
    }

    public void notifyDrivers(Ride ride, int ID) {
       RM.insertDriverNotification(ride, ID);
    }

}
