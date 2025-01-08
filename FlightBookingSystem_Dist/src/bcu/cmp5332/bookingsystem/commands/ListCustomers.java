package bcu.cmp5332.bookingsystem.commands;

import java.util.List;

import bcu.cmp5332.bookingsystem.model.Customer;
import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

public class ListCustomers implements Command {

	@Override
	public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException {
		// TODO Auto-generated method stub
		//for each customer print out long customer details 
		
		
		List<Customer> customers = flightBookingSystem.getCustomers();
        for (Customer customer : customers) {
            if(customer.getHiddenValue() == true) {
            	// do not display customer details, customer is hidden
            }
            else {
            	System.out.println(customer.getDetailsLong());
            }
        }
        //System.out.println(flights.size() + " flight(s)");
	}

}
