package bcu.cmp5332.bookingsystem.commands;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Customer;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

public class ShowCustomer implements Command{

	private final int customerID;
	private Customer customer;
	public ShowCustomer(int customerID) {
		this.customerID = customerID;
	}
	
	@Override
	public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException {
		// TODO Auto-generated method stub
		
		if(flightBookingSystem.getCustomers().size() >1) {
			customer = flightBookingSystem.getCustomerByID(customerID);
		}
		else {
			System.out.println("There are no customers with Id " + this.customerID);
		}
		System.out.println(customer.getDetailsShort());
	}

}
