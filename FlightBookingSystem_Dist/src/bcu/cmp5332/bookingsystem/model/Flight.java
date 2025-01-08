package bcu.cmp5332.bookingsystem.model;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Flight {
    
    private int id;
    private String flightNumber;
    private String origin;
    private String destination;
    private LocalDate departureDate;
    private boolean isHidden;

    private final Set<Customer> passengers;
    
   //50-59%
    private int seatCapacity;
    private float price;

    public Flight(int id, String flightNumber, String origin, String destination, LocalDate departureDate, int seatCapacity, float price, boolean isHidden) {
        this.id = id;
        this.flightNumber = flightNumber;
        this.origin = origin;
        this.destination = destination;
        this.departureDate = departureDate;
        
        passengers = new HashSet<>();
        
        this.seatCapacity = seatCapacity;
        this.price = price;
        
        this.isHidden = isHidden;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }
    
    public String getOrigin() {
        return origin;
    }
    
    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }

    public List<Customer> getPassengers() {
        return new ArrayList<>(passengers);
    }
    /*
	public List<Customer> getPassengersByID(int id){
	    if(this.id == id) {
	    	return
	    }
	}
	*/
    
    public int getSeatCapacity() {
    	return this.seatCapacity;
    }
    
    public void setSeatCapacity(int seatCapacity) {
    	this.seatCapacity = seatCapacity;
    }
    
    public Float getPrice() {
    	return this.price;
    }
    
    public void setPrice(Float price) {
    	this.price = price;
    }
    
    public void setHiddenValue(boolean bool) {
    	this.isHidden = bool;
    }
    
    public boolean getHiddenValue() {
    	return this.isHidden;
    }
    
    
    
    
	
    public String getDetailsShort() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/YYYY");
        return "Flight #" + id + " - " + flightNumber + " - " + origin + " to " 
                + destination + " on " + departureDate.format(dtf);
    }

    public String getDetailsLong() {
        // TODO: implementation here
    	
    	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/YYYY");
    	String out = "Flight #" + this.id 
    			+"\n"+"Flight No: " + this.flightNumber
    			+"\n"+"Origin: " + this.origin
    			+"\n"+"Destination: "+ this.destination
    			+"\n"+"Departure Date: "+ this.departureDate.format(dtf)
    			+"\n"+"---------------------------"
    			+"\n"+"Passengers: "+ " Capacity: "+ this.seatCapacity +"\n";
    	String customerList = "";
    	for(Customer customer : this.getPassengers()) {
    		out = out + customerList.concat(customer.getDetailsShort()).concat("\n");
    	}
    	
        return out;
    }
    
    public void addPassenger(Customer passenger) throws FlightBookingSystemException{
    	//this.passengers.add(passenger);
    	
    	if(this.getPassengers().contains(passenger)){
			throw new FlightBookingSystemException("Passenger " + passenger.getName() + " is already present in the flight's list of passengers");
		}
		else {
			if(this.passengers.size() > this.seatCapacity) {
				throw new FlightBookingSystemException("Flights capacity reached");
			}
			else {
				this.passengers.add(passenger);
				
			}
			
		}
    	
    }
    
    public void removePassengers(Customer passenger) throws FlightBookingSystemException {
    	//this.passengers.remove(customer);
    	
    	if(this.getPassengers().contains(passenger)) {
    		this.passengers.remove(passenger);
    		
    	}
    	else {
    		throw new FlightBookingSystemException("Passenger " + passenger.getName() + " is not in the flights list of passengers");
    	}
    }
}
