package bcu.cmp5332.bookingsystem.commands;

import bcu.cmp5332.bookingsystem.data.FlightBookingSystemData;
import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Flight;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class AddFlight implements  Command {

    private final String flightNumber;
    private final String origin;
    private final String destination;
    private final LocalDate departureDate;
    
    //50-59%
    private int seatCapacity;
    private Float price;
    private List<FlightBookingSystem> fbsList;
    
    private boolean isHidden = false;
    
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
    	try {
			fbsList = FlightBookingSystemData.load();
		} catch (FlightBookingSystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        FlightBookingSystem fbs = flightBookingSystem;
    	int maxId = 0;
        if (fbs.getFlights().size() > 0) {
            int lastIndex = fbs.getFlights().size() - 1;
            maxId = fbs.getFlights().get(lastIndex).getId();
            
            Flight flight = new Flight(++maxId, this.flightNumber, this.origin, this.destination, this.departureDate,this.seatCapacity,this.price,isHidden);
            
            fbs.addFlight(flight);
            System.out.println("Flight #" + flight.getId() + " added.");
            fbsList.set(0, fbs);
        	try {
				FlightBookingSystemData.store(fbsList);
			} catch (IOException e) {
				
				System.out.println("File is either missing or in use");
			}
        }
        
        
    }
}
