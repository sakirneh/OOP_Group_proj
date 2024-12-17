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
    	int customerID = 0;
    	if(flightBookingSystem.getCustomers().size() >0) {
    		customerID = flightBookingSystem.getCustomers().size() +1;
    	}
    	Customer customer = new Customer(customerID, name, phone);
    	flightBookingSystem.addCustomer(customer);
    	System.out.println("Customer #" + customer.getName() + " added.");
    	
    }
}
