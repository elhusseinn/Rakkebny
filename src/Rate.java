import java.util.ArrayList;

public class Rate {
    private int value;
    private double averageRating;
    private ArrayList<Integer> rating = new ArrayList<Integer>();

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    public ArrayList<Integer> getRating() {
        return rating;
    }

    public void setRating(ArrayList<Integer> rating) {
        this.rating = rating;
    }

    public void displayRating(Driver driver) {

    }

    public void showAverageRating(Driver driver) {
        double rate = 0;
        double avg;
        for (int i = 0; i < rating.size(); i++) {
            rate = (rating.get(i) + rate);
        }
        avg = rate / rating.size();
    }
}
