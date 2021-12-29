public class acceptRideEvent implements Event{

    @Override
    public void action(int RideID) {
        eventManager.insertEvent(RideID, "Accepted Ride"); // customer accepts captain's price

    }
}
