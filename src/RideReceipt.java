
public class RideReceipt {
    Ride ride;

    public RideReceipt(Ride ride) {
        this.ride = ride;
    }

    public String printRideReceipt_() {
        return "The driver is" + this.ride.getDriver() + "\n" +
                "The Customer is" + this.ride.getCustomer() + "\n" +
                "The Source is" + this.ride.getSource() + "\n" +
                "The Destination is" + this.ride.getDestination() + "\n" +
                "The Cost is" + this.ride.getCost() + "\n";
    }
}
