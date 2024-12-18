package bcu.cmp5332.bookingsystem.commands;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Booking;
import bcu.cmp5332.bookingsystem.model.Customer;
import bcu.cmp5332.bookingsystem.model.Flight;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

public class CancelBooking implements Command{
	
	private final int customerID;
	private final int flightID;
	
	public CancelBooking(int customerID, int flightID) {
		this.customerID = customerID;
		this.flightID = flightID;
	}
	
	@Override
	public void execute(FlightBookingSystem fbs) throws FlightBookingSystemException {
		// TODO Auto-generated method stub
		
		if(fbs.getCustomerByID(this.customerID) != null && fbs.getFlightByID(this.flightID) != null) {
			Customer customer = fbs.getCustomerByID(this.customerID);
			Flight flight = fbs.getFlightByID(this.flightID);
			customer.cancelBookingForFlight(customer, flight);
			System.out.println("Booking successfully cancelled for " + customer.getName());
			
		}
		else {
			throw new FlightBookingSystemException("No existing booking for customer ID " + this.customerID + " and flight ID " + this.flightID);
		}
	}
	

}
