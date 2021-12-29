public class makeOfferEvent implements Event{
    @Override
    public void action(int RideID) {
        eventManager.insertEvent(RideID, "MakeOffer");



    }



}
