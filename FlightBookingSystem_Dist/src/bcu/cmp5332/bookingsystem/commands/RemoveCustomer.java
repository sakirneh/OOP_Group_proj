package bcu.cmp5332.bookingsystem.commands;

import java.io.IOException;
import java.util.List;

import bcu.cmp5332.bookingsystem.data.FlightBookingSystemData;
import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Customer;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

public class RemoveCustomer implements Command{

	private Customer customer;
	private int customerID;
	private List<FlightBookingSystem> fbsList;
	
	public RemoveCustomer(int customerID) {
		this.customerID = customerID;
	}
	
	@Override
	public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException, IOException {
		// TODO Auto-generated method stub
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
        
		if(customerID <= fbs.getCustomers().size()) {
			customer = fbs.getCustomerByID(customerID);
			if(customer.getHiddenValue() == false) {
				customer.setHiddenValue(true);
				System.out.println("Customer with ID: " + customer.getID() + " has been removed.");
				fbsList.set(0, fbs);
	        	try {
	        		FlightBookingSystemData.store(fbsList);
	        	}
	        	catch(IOException ex){
	        		System.out.println("File is either missing or in use");
	        	}
			}
		}
		else {
			throw new FlightBookingSystemException("Customer ID does not exist!");
		}
		
	}

}
