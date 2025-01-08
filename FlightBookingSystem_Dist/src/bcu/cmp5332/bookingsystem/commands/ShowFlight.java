package bcu.cmp5332.bookingsystem.commands;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Flight;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

public class ShowFlight implements Command{

	int flightID= 0;
	private Flight flight = null;
	public ShowFlight(int flightID){
		this.flightID = flightID;
	}
	@Override
	public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException {
		// TODO Auto-generated method stub
		//prints short details about flights
		if(flightBookingSystem.getFlights().size() >0 ) {
			if(flightBookingSystem.getFlightByID(flightID).getHiddenValue() == false) {
				flight = flightBookingSystem.getFlightByID(flightID);
				System.out.println(flight.getDetailsShort());
			}
			else {
				System.out.println("Flight is hidden");
			}
			
		}
		else {
			throw new FlightBookingSystemException("There are no flights available with " + this.flightID + " id");
		}
		
		
		
	}

}
