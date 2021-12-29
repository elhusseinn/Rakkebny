public class arrivedDestinationEvent implements Event {


    @Override
    public void action(int RideID) {
        eventManager.insertEvent(RideID, "Arrived At destination");
    }
}
