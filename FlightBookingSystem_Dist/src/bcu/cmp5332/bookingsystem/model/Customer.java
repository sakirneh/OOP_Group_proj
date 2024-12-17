package bcu.cmp5332.bookingsystem.model;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Customer {
    
    private int id;
    private String name;
    private String phone;
    private final List<Booking> bookings = new ArrayList<>();
    
    // TODO: implement constructor here
    public Customer(int id, String name, String phoneNum) {
    	this.id = id;
    	this.name = name;
    	this.phone = phoneNum;
    	
    }
    
    // TODO: implementation of Getter and Setter methods
    public void setID(int id) {
    	this.id = id;
    }
    public int getID() {
    	return this.id;
    }
    
    public void setName(String name) {
    	this.name = name;
    }
    
    public String getName() {
    	return this.name;
    }
    
    public void setPhone(String phone) {
    	this.phone = phone;
    }
    
    public String getPhone() {
    	return this.phone;
    }
    
    public List getBookings(){
    	return this.bookings;
    }
    
    public String getDetailsShort() {
        //DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/YYYY");
        return "Customer #" + this.id + " - " +" Name: " 
                + this.name + "-"+ " Phone: " + this.phone;
    }
    
    public void addBooking(Booking booking) {
        // TODO: implementation here
    	bookings.add(booking);
    }
}
