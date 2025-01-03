package bcu.cmp5332.bookingsystem.commands;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Flight;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;
import java.time.LocalDate;

public class AddFlight implements  Command {

    private final String flightNumber;
    private final String origin;
    private final String destination;
    private final LocalDate departureDate;
    
    //50-59%
    private int seatCapacity;
    private Float price;
    
    public AddFlight(String flightNumber, String origin, String destination, LocalDate departureDate, int seatCapacity, Float price) {
        this.flightNumber = flightNumber;
        this.origin = origin;
        this.destination = destination;
        this.departureDate = departureDate;
        
        this.seatCapacity = seatCapacity;
        this.price = price;
    }
    
    @Override
    public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException {
        /*
         * 
         * check if flight list is not empty
         * create new flight id
         * create new flight object and add to fbs
         * 
    	*/
    	int maxId = 0;
        if (flightBookingSystem.getFlights().size() > 0) {
            int lastIndex = flightBookingSystem.getFlights().size() - 1;
            maxId = flightBookingSystem.getFlights().get(lastIndex).getId();
            
            Flight flight = new Flight(++maxId, this.flightNumber, this.origin, this.destination, this.departureDate,this.seatCapacity,this.price);
            flightBookingSystem.addFlight(flight);
            System.out.println("Flight #" + flight.getId() + " added.");
        }
        
        
    }
}
