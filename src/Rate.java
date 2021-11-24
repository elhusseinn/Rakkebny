import java.util.ArrayList;

public class Rate {
    private int value = 0;
    private double averageRating = 0;
    private ArrayList<Integer> ratings = new ArrayList<>();
    public Rate(){

    }


    public void setValue(int value) {
        CalculateAverageRating(value);
    }

    public int getValue() {
        return value;
    }
    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double rate) {
        this.averageRating = rate;
    }

    public ArrayList<Integer> getRating() {
        return ratings;
    }

    public double displayRating() {
        return getAverageRating();
    }

    public void addRating(int value){
        ratings.add(value);
    }

    public void CalculateAverageRating(double value) {
          value=0;
        for (int i = 0; i < ratings.size(); i++) {  //2, 5,6,8,9
            value = (ratings.get(i) + value);// 0+
        }
        value/=ratings.size();
        value +=averageRating;
    }
}
