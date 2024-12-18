package bcu.cmp5332.bookingsystem.commands;

import java.util.List;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Booking;
import bcu.cmp5332.bookingsystem.model.Customer;
import bcu.cmp5332.bookingsystem.model.Flight;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

public class AddBooking implements Command{

	private final int customerID;
	private final int flightID;
	private Customer customer = null;
	private Flight flight = null;
	
	public AddBooking(int customerID, int flightID) {
		this.customerID = customerID;
		this.flightID = flightID;
	}
	
	@Override
	public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException {
		// TODO Auto-generated method stub
		
		if(flightBookingSystem.getCustomers().size() >0) {
			customer = flightBookingSystem.getCustomerByID(customerID);
			flight = flightBookingSystem.getFlightByID(flightID);
		}
		Booking booking = new Booking(customer, flight, flightBookingSystem.getSystemDate());
		customer.addBooking(booking);
		flight.addPassenger(customer);
		System.out.println("A new Booking has been made for "+ customer.getName());
	}

}
