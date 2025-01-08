package bcu.cmp5332.bookingsystem.commands;


import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Flight;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

import java.util.List;

public class ListFlights implements Command {

    @Override
    public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException {
        //for each flight print long flight details.
    	int flightCounter = 0;
    	
    	List<Flight> flights = flightBookingSystem.getFlights();
        for (Flight flight : flights) {
            if(flight.getHiddenValue() == true) {
            	//do not display flight details if it is hidden.
            }
            else {
            	System.out.println(flight.getDetailsLong());
            	flightCounter++;
            }
        }
        System.out.println(flightCounter + " flight(s)");
    }
}
