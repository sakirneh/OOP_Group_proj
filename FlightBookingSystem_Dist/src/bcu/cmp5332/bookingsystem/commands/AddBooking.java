package bcu.cmp5332.bookingsystem.commands;



import java.io.IOException;
import java.util.List;

import bcu.cmp5332.bookingsystem.data.FlightBookingSystemData;
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
	
	private List<FlightBookingSystem> fbsList;
	
	public AddBooking(int customerID, int flightID) {
		this.customerID = customerID;
		this.flightID = flightID;
	}
	
	@Override
	public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException {
		// TODO Auto-generated method stub
		/*
		
		check if the customer list is not empty
		get customer and flight obj by id
		create new booking object
		
		try to add the booking to customer booking list
		and passenger to flight passenger list
		
		*/
		
		try{
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
			if(fbs.getCustomers().size() >0) {
				customer = fbs.getCustomerByID(customerID);
				flight = fbs.getFlightByID(flightID);
				
				int ID = customer.getBookings().size() +1;
	            
				Booking booking = new Booking(ID,customer, flight, fbs.getSystemDate());
				
				try {
					customer.addBooking(booking);
					flight.addPassenger(customer);
					System.out.println("A new Booking has been made for "+ customer.getName());
					System.out.println(customer.getName() + " was added to flight " + flight.getId());
					
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

			}
			
		}
		catch(IllegalArgumentException ex) {
			System.out.println("The customer list is empty");
		}
	}

}
