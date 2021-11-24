
public class Ride {
    private String source;
    private String destination;
    private Driver driver;
    private Customer customer;
    private RideReceipt rideReceipt;
    private double cost;


    public RideReceipt getRideReceipt() { return rideReceipt; }

    public void setRideReceipt(RideReceipt rideReceipt) { this.rideReceipt = rideReceipt; }

    public Driver getDriver() { return driver; }

    public void setDriver(Driver driver) { this.driver = driver; }

    public Customer getCustomer() { return customer; }

    public void setCustomer(Customer customer) { this.customer = customer; }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public void notifyDrivers(Ride ride) {
        ride.setDriver(system.GetInstance().searchDriver(ride.source));
        ride.driver.addNotification(ride);
    }
}
