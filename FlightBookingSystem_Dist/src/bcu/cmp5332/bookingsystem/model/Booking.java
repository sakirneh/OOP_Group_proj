package bcu.cmp5332.bookingsystem.model;

import java.time.LocalDate;

public class Booking {
    
    private Customer customer;
    private Flight flight;
    private LocalDate bookingDate;
    

    public Booking(Customer customer, Flight flight, LocalDate bookingDate) {
        // TODO: implementation here
    	this.customer = customer;
    	this.flight = flight;
    	this.bookingDate = bookingDate;
        
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
    
    public void setLocalBookingDate(LocalDate bookingDate) {
    	this.bookingDate = bookingDate;
    }
    
}
