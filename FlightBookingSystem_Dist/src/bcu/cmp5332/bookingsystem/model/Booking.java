package bcu.cmp5332.bookingsystem.model;

import java.time.LocalDate;

public class Booking {
    
    private Customer customer;
    private Flight flight;
    private LocalDate bookingDate;
    private int bookingID;
    

    public Booking(int bookingID,Customer customer, Flight flight, LocalDate bookingDate) {
        // TODO: implementation here
    	this.customer = customer;
    	this.flight = flight;
    	this.bookingDate = bookingDate;
    	
    	this.bookingID = bookingID;
        
    }
    
    // TODO: implementation of Getter and Setter methods
    
    public Customer getCustomer() {
    	return this.customer;
    }
    
    public void setCustomer(Customer customer) {
    	this.customer = customer;
    }
    
    public Flight getFlight() {
    	return this.flight;
    }
    
    public void setFlight(Flight flight) {
    	this.flight = flight;
    }
    
    public LocalDate getDate() {
    	return this.bookingDate;
    }
    
    public int getBookingID() {
    	return this.bookingID;
    }
    
    public void setBookingID(int bookingID) {
    	this.bookingID = bookingID;
    }
    
    public void setLocalBookingDate(LocalDate bookingDate) {
    	this.bookingDate = bookingDate;
    }
    
}
