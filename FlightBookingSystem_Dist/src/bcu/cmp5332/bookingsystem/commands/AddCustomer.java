package bcu.cmp5332.bookingsystem.commands;

import java.io.IOException;
import java.util.List;

import bcu.cmp5332.bookingsystem.data.FlightBookingSystemData;
import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Customer;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

public class AddCustomer implements Command {

    private final String name;
    private final String phone;
    private final String email;
    private List<FlightBookingSystem> fbsList;
    
    private boolean isHidden = false;

    public AddCustomer(String name, String phone, String email) {
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    @Override
    public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException, IOException {
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
        
    	
    	int customerID = 0;
    	if(fbs.getCustomers().size() > 0) {
    		customerID = fbs.getCustomers().size() +1;
    		Customer customer = new Customer(customerID, name, phone,email,isHidden);
    		if(fbs.getCustomers().contains(customer)) {
    			throw new FlightBookingSystemException(customer.getName() + " Already exists");
    		}
        	fbs.addCustomer(customer);
        	System.out.println("Customer # "+ "ID" + customerID + customer.getName() + " added.");
        	fbsList.set(0, fbs);
        	try {
        		FlightBookingSystemData.store(fbsList);
        	}
        	catch(IOException ex){
        		System.out.println("File is either missing or in use");
        	}
    	}
    
    }
}
