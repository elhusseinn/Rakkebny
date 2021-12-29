public class arrivedLocationEvent implements Event{


    @Override
    public void action(int RideID) {
        eventManager.insertEvent(RideID, "Arrived At Location");
    }
}
