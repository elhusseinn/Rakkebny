import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

public class DiscountExcutor {

public double applyDiscount(double cost, String destination, int numOfPassengers, String customerName){
    DiscountDBManager discountDBManager = new DiscountDBManager();
    ArrayList<String> holidays = new ArrayList<>();

        holidays.add("1-7");
        holidays.add("1-25");
        holidays.add("4-29");
        holidays.add("5-1");
        holidays.add("5-3");
        holidays.add("5-12");
        holidays.add("5-13");
        holidays.add("5-14");
        holidays.add("5-15");
        holidays.add("5-16");
        holidays.add("6-30");
        holidays.add("7-1");
        holidays.add("7-19");
        holidays.add("7-20");
        holidays.add("7-21");
        holidays.add("7-22");
        holidays.add("7-23");
        holidays.add("7-24");
        holidays.add("8-12");
        holidays.add("10-6");
        holidays.add("10-18");
        holidays.add("10-19");



Discount discount = new BasicRide();
discount.setDiscountCost(cost);

if(discountDBManager.checkArea_Discount(destination)){ // destination is offered a discount

    discount = new areaDiscount(discount);

}
if(numOfPassengers > 1){ // user shares the ride

    discount = new PassengersDiscount(discount);

}
if(discountDBManager.checkFirstRide(customerName)){ // customer's first ride

    discount = new FirstRide(discount);

}
if(discountDBManager.checkBirthDay(customerName)){ // ride request day is the birthday of the customer

discount = new BirthdateDiscount(discount);

}

DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd");

if(holidays.contains(LocalDate.now().format(formatter))){ // ride request day is a holiday
discount = new HolidaysDiscount(discount);

}












return discount.getCost();
}



}
