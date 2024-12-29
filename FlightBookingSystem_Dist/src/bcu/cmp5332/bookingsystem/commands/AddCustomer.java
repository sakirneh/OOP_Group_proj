package bcu.cmp5332.bookingsystem.commands;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Customer;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

public class AddCustomer implements Command {

    private final String name;
    private final String phone;

    public AddCustomer(String name, String phone) {
        this.name = name;
        this.phone = phone;
        
    }

    @Override
    public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException {
        // TODO: implementation here
    	/*
    	 * 
    	 * check if customer list is not empty
    	 * create new customer id
    	 * create new customer obj
    	 * if customer exists in customer list throw exception
    	 * otherwise
    	 * add customer to fbs.
    	 * 
    	*/
    	int customerID = 0;
    	if(flightBookingSystem.getCustomers().size() > 0) {
    		customerID = flightBookingSystem.getCustomers().size() +1;
    		Customer customer = new Customer(customerID, name, phone);
    		if(flightBookingSystem.getCustomers().contains(customer)) {
    			throw new FlightBookingSystemException(customer.getName() + " Already exists");
    		}
        	flightBookingSystem.addCustomer(customer);
        	System.out.println("Customer # "+ "ID" + customerID + customer.getName() + " added.");
    	}
    
    	
    	
    }
}
