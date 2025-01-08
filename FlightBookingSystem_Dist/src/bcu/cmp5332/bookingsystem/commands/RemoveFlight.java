package bcu.cmp5332.bookingsystem.commands;

import java.io.IOException;
import java.util.List;

import bcu.cmp5332.bookingsystem.data.FlightBookingSystemData;
import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;
import bcu.cmp5332.bookingsystem.model.Customer;
import bcu.cmp5332.bookingsystem.model.Flight;

public class RemoveFlight implements Command{
	
	private Flight flight;
	private int flightID;
	
	private List<FlightBookingSystem> fbsList;
	public RemoveFlight(int flightID) {
		this.flightID = flightID;
	}
	
	@Override
	public void execute(FlightBookingSystem fbs) throws FlightBookingSystemException, IOException {
		// 
		try {
			fbsList = FlightBookingSystemData.load();
		} catch (FlightBookingSystemException e) {
			// 
			System.out.println("Error while loading file " + e.getMessage());
		} catch (IOException e) {
			//  
			System.out.println("The file is either missing or in use");
		}
        //FlightBookingSystem fbs = flightBookingSystem;
        
		if(flightID <= fbs.getFlights().size()) {
			flight = fbs.getFlightByID(flightID);
			
			if(flight.getHiddenValue() == false) {
				
				List<Customer> customers = fbs.getCustomers();
				for(Customer customer : customers) {
					if(flight.getPassengers().contains(customer)) {
						customer.cancelBookingForFlight(flight);
					}
					else {
						
					}
				}
				
				flight.setHiddenValue(true);
				System.out.println("Flight with ID: " +flight.getId() + " has been removed");
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
			throw new FlightBookingSystemException("Flight ID does not exist!");
		}
	}

}
