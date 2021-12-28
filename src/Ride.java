
public class Ride {
    private String source;
    private String destination;
    private Driver driver;
    private Customer customer;
    private double cost;

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    private int rate;

    SQLiteJDBC db = new SQLiteJDBC();

    public Ride(String source, String destination, Customer customer) {
        this.source = source;
        this.destination = destination;
        this.customer = customer;
    }

    public Ride() {

    }

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
        db.insertDriverNotification(ride);
    }

}
