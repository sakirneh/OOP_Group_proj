package bcu.cmp5332.bookingsystem.model;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
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
    
    
    public String getDetailsShort() {
        //DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/YYYY");
    	String out = "Customer #" + this.id + " - " +" Name: " 
                + this.name + " - "+ "Phone: " + this.phone;
        return out;
    }
    
    public String getDetailsLong() {
    	
    	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/YYYY");
    	String out ="Customer #" +this.id
    			+"\n"+"Name: " + this.name
    			+"\n"+"Phone: " + this.phone
    			+"\n"+"---------------------------"
    			+"\n"+"Bookings:"+"\n";
    	
    	String bookingOut = "";
    	for(Booking booking : this.getBookings()) {
    		out = out + "Booking date: " +bookingOut.concat(booking.getDate().format(dtf) + " for ")
    		.concat(booking.getFlight().getDetailsShort() + "\n");
    	}
    	return out;
    }
    
    
    
    public void addBooking(Booking booking) {
        // TODO: implementation here
    	bookings.add(booking);
    }
    
    public List<Booking> getBookings() {
    	
    	return bookings;
    	
    }
    
    public void removeBooking(Customer customer, Flight flight) {
    	Booking booking = null;
    	for(Booking tempBook : customer.getBookings()) {
    		if(customer == tempBook.getCustomer()) {
    			booking = tempBook;
    		}
    	}
    	bookings.remove(booking);
		flight.removePassengers(customer);
    	
    }
}
