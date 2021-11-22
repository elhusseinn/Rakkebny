public class RideReceipt {
    Ride ride;

    public RideReceipt(Ride ride) {
        this.ride = ride;
    }

    public void printRideReceipt_(Ride ride) {
        System.out.println("The driver is" + ride.getDriver() + "\n" +
                "The Customer is" + ride.getCustomer() + "\n" +
                "The Source is" + ride.getSource() + "\n" +
                "The Destination is" + ride.getDestination() + "\n" +
                "The Cost is" + ride.getCost() + "\n");
    }
}
