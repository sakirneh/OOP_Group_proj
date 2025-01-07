package bcu.cmp5332.bookingsystem.commands;

import java.io.IOException;
import java.util.List;

import bcu.cmp5332.bookingsystem.data.FlightBookingSystemData;
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
	private List<FlightBookingSystem> fbsList;
	
	public EditBooking(int bookingID, int flightID){
		this.bookingID = bookingID;
		this.flightID = flightID;
	}
	
	@Override
	public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException, IOException {
		// TODO Auto-generated method stub
		/*
		 * check if the bookingID and flighID are in the system
		 * then if the current customer of the customers in the system has the booking, set the customer to the current customer and break
		 * then get booking from the current customer with bookingID.
		 * get flight by ID
		 * 
		 * set the booking flight to new flight
		 */
		try {
			try {
				fbsList = FlightBookingSystemData.load();
			} catch (FlightBookingSystemException e) {
				// TODO Auto-generated catch block
				System.out.println("Error while loading file " + e.getMessage());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("The file is either missing or in use");
			}
	        FlightBookingSystem fbs = flightBookingSystem;
	        
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
			fbsList.set(0, fbs);
        	try {
        		FlightBookingSystemData.store(fbsList);
        	}
        	catch(IOException ex){
        		System.out.println("File is either missing or in use");
        	}
		}
		catch(FlightBookingSystemException ex){
			System.out.println(ex.getMessage());
		}
		catch(IndexOutOfBoundsException e) {
			System.out.println(e.getMessage());
		}
		
	}

}
