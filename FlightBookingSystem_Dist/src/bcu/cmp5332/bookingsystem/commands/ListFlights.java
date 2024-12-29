package bcu.cmp5332.bookingsystem.commands;


import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Flight;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

import java.util.List;

public class ListFlights implements Command {

    @Override
    public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException {
        //for each flight print long flight details.
    	List<Flight> flights = flightBookingSystem.getFlights();
        for (Flight flight : flights) {
            System.out.println(flight.getDetailsLong());
        }
        System.out.println(flights.size() + " flight(s)");
    }
}
