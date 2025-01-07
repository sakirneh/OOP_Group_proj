package bcu.cmp5332.bookingsystem.model;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import java.util.List;

public class Customer {
    
    private int id;
    private String name;
    private String phone;
    private String email;
    
    
    private final List<Booking> bookings = new ArrayList<>();
    
    // TODO: implement constructor here
    public Customer(int id, String name, String phoneNum, String email) {
    	this.id = id;
    	this.name = name;
    	this.phone = phoneNum;
    	this.email = email;
    	
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
    
    public String getEmail() {
    	return this.email;
    }
    
    public void setEmail(String email) {
    	this.email = email;
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
    
    
    

    public void addBooking(Booking booking) throws FlightBookingSystemException {

        // TODO: implementation here
    	
    	
    	// if booking exists throw exception that customer already has a booking for the flight
    	if(this.bookings.contains(booking)) {
    		throw new FlightBookingSystemException(booking.getCustomer().getName() + " Already has a booking for flight ID: " + booking.getFlight().getId());
    	}
    	else {
    		this.bookings.add(booking);
    	}
    }
    
    public List<Booking> getBookings() {
    	
    	return bookings;
    	
    }
    
    public Booking getBookingByID(int id) throws FlightBookingSystemException{
    	if(!this.getBookings().contains(this.getBookings().get(id))) {
    		throw new FlightBookingSystemException("Booking does not exist");
    	}
    	else {
    		return this.getBookings().get(id);
    	}
    	
    }
    
    public void cancelBookingForFlight(Flight flight) throws FlightBookingSystemException {
    	/* 
    	 * iterates over each booking and checks if any booking has the flight 
    	 * that was passed to cancel.
    	 * 
    	 * then if the customer has a booking for that flight,
    	 * remove passenger from the flight list and remove booking from bookings
    	 */
    	
    	
    	
    	Booking booking = null;
		for(Booking tempBook : this.getBookings()) {
			if(flight == tempBook.getFlight()) {
				booking = tempBook;
				break;
			}
		}
		
		if(this.getBookings().contains(booking)) {
			flight.removePassengers(this);
	    	bookings.remove(booking);
		}
		else {
			throw new FlightBookingSystemException(this.getName() + " Does not have a booking for flight ID: " + flight.getId());
		}
    	
    }
}
