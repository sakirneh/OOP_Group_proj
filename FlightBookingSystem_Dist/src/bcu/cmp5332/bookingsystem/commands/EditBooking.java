package bcu.cmp5332.bookingsystem.commands;

import java.io.IOException;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;
import bcu.cmp5332.bookingsystem.model.Booking;
import bcu.cmp5332.bookingsystem.model.Customer;
import bcu.cmp5332.bookingsystem.model.Flight;

public class EditBooking implements Command{

	private int bookingID;
	private int customerID;
	private int flightID;
	private Booking booking;
	private Customer customer;
	private Flight flight;
	
	public EditBooking(int bookingID, int flightID){
		this.bookingID = bookingID;
		this.flightID = flightID;
	}
	
	@Override
	public void execute(FlightBookingSystem fbs) throws FlightBookingSystemException, IOException {
		// TODO Auto-generated method stub
		
		try {
			for(Customer customer : fbs.getCustomers()) {
				if(bookingID > customer.getBookings().size()) {
					throw new FlightBookingSystemException("Customer has no bookings with given ID");
				}
				else if(flightID > fbs.getFlights().size()) {
					throw new IndexOutOfBoundsException("Flight ID does not exist");
				}
				else {
					if(customer.getBookings().contains(customer.getBookingByID(bookingID -1))) {
						this.customer = customer;
						break;
					}
				}
			}
			
			booking = this.customer.getBookingByID(bookingID -1);
			flight = fbs.getFlightByID(flightID);
			Flight oldFlight = booking.getFlight();
			String oldBookingFlightNum = oldFlight.getFlightNumber();
			booking.setFlight(flight);
			System.out.println("Booking flight successfully changed from : " + oldBookingFlightNum + " to " + flight.getFlightNumber());
		}
		catch(FlightBookingSystemException ex){
			System.out.println(ex.getMessage());
		}
		catch(IndexOutOfBoundsException e) {
			System.out.println(e.getMessage());
		}
		
	}

}
