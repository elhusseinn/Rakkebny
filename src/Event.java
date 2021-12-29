public interface Event {
    boolean is_done=false;
    EventDBManager eventManager = new EventDBManager();
    public void action(int RideID);

}
