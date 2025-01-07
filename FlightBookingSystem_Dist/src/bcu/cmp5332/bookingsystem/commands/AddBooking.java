package bcu.cmp5332.bookingsystem.commands;



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
		/*
		
		check if the customer list is not empty
		get customer and flight obj by id
		create new booking object
		
		try to add the booking to customer booking list
		and passenger to flight passenger list
		
		*/
		
		try{
			if(flightBookingSystem.getCustomers().size() >0) {
				customer = flightBookingSystem.getCustomerByID(customerID);
				flight = flightBookingSystem.getFlightByID(flightID);
				
				int ID = customer.getBookings().size() +1;
	            
				Booking booking = new Booking(ID,customer, flight, flightBookingSystem.getSystemDate());
				
				try {
					customer.addBooking(booking);
					flight.addPassenger(customer);
					System.out.println("A new Booking has been made for "+ customer.getName());
					System.out.println(customer.getName() + " was added to flight " + flight.getId());
					
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
