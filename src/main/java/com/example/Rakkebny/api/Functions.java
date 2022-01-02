package com.example.Rakkebny.api;
import com.example.Rakkebny.controller.*;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@RequestMapping("api")
@RestController
public class Functions {

    @PostMapping("admin/login")
    public User login(@RequestBody AccountData user1) {
        User admin = new Admin();
        admin.setUserName(user1.getUsername());
        admin.setPassword(user1.getPassword());
      if (Controller.GetInstance().Authenticate(admin).logincheck(admin)) {
            return Controller.GetInstance().Authenticate(admin).login(admin);
        } else {
            return null;
        }
    }

    @PostMapping("driver/login")
    public User logindrv(@RequestBody AccountData user1) {
        User driver = new Driver();
        driver.setUserName(user1.getUsername());
        driver.setPassword(user1.getPassword());
        if (Controller.GetInstance().Authenticate(driver).logincheck(driver)) {
            return Controller.GetInstance().Authenticate(driver).login(driver);
        } else {
            return null;
        }
    }

    @PostMapping("customer/login")
    public User logincu(@RequestBody AccountData user1) {
        User customer = new Customer();
        customer.setUserName(user1.getUsername());
        customer.setPassword(user1.getPassword());
        if (Controller.GetInstance().Authenticate(customer).logincheck(customer)) {
            return Controller.GetInstance().Authenticate(customer).login(customer);
        } else {
            return null;
        }
    }

    @PostMapping("customer/register")
    public boolean register(@RequestBody Customer customer) {
        customer.register(customer);
        return true;
    }

    @PutMapping("driver/register")
    public boolean register(@RequestBody Driver driver) {
        driver.register(driver);
        return  true;
    }

    @GetMapping("/admin/logs/{id}")
    public List<String> ShowEvent(@PathVariable int id) {
        Admin admin = new Admin();
        List<String> TMP= admin.showEvents(id);
        return TMP;
    }

    @PutMapping("admin/discount/add/{area}")
    public boolean addDiscountArea(@PathVariable String area) {
        Admin admin = new Admin();
        admin.addAreaToDiscountOffers(area);
    return true;
    }

    @PostMapping("admin/verify")
    public boolean verifyRegistration(@RequestBody Driver driver) {
        Admin admin = new Admin();
        admin.verifyRegistration(driver);
        return true;
    }

    @PostMapping("admin/suspend")
    public boolean SuspendedUser(@RequestBody User user) {
        Admin admin = new Admin();
        admin.SuspendedUser(user);
        return true;
    }

    @PostMapping("driver/addFvaoruiteplaces/{place}")
    public boolean Favouriteplace(@PathVariable  String place,@RequestBody Driver driver) {
        driver.setFavouritePlaces(place);
        return true;
    }

    @GetMapping ("driver/ListNotfication")
    public ArrayList<Ride> getNotfication(@RequestBody Driver driver) {
        ArrayList<Ride> TMP= driver.getNotifications();
        return TMP;
    }

    @PostMapping("driver/makeOffer/{Cost}")
    public boolean makeOffer(@PathVariable  double Cost,@RequestBody Ride ride) {

        Driver driver = new Driver();
        driver.makeOffer(ride,Cost);
        return true;
    }

    @GetMapping ("driver/ListPreviousRide")
    public ArrayList<Ride> ListPreviousRide(@RequestBody Driver driver) {
        ArrayList<Ride> rides = driver.listPreviousRides();
         return rides;
    }

    @GetMapping ("driver/ShowavrgRating")
    public double ShowavgRating(@RequestBody Driver driver) {
        double offer = driver.getAverageRating();
        return offer;
    }

    @PostMapping("driver/Arrived/{status}/{id}")
    public boolean arrived(@PathVariable  String status, @PathVariable int id) {
        Driver driver = new Driver();
        driver.arrived(id,status);
        return true;
    }

    @PostMapping("customer/requestRide/{source}/{destination}/{noOfPassengers}")
    public boolean requestride(@PathVariable  String source, @PathVariable String destination,@RequestBody Customer customer, @PathVariable int noOfPassengers) {
        Customer customer1 = new Customer();
        Ride ride = new Ride(source,destination,customer, noOfPassengers);
        customer1.requestRide(ride);
        return true;
    }

 /*   @GetMapping ("customer/getCustomerNotfication")
    public ArrayList<Ride> showoffers(@RequestBody Customer customer) {
        ArrayList<Ride> tmp = customer.getRideNotifications();
        return tmp;
    }*/

    @PostMapping("customer/acceptRide/{id}")
    public boolean acceptride(@PathVariable int id) {
        Customer customer = new Customer();
        customer.acceptRide(id);
        return true;
    }
}

