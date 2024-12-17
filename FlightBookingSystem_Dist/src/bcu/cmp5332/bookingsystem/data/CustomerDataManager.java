package bcu.cmp5332.bookingsystem.data;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Customer;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class CustomerDataManager implements DataManager {

    private final String RESOURCE = "./resources/data/customers.txt";
    
    @Override
    public void loadData(FlightBookingSystem fbs) throws IOException, FlightBookingSystemException {
        // TODO: implementation here
    	File customerFile = new File(RESOURCE);
    	BufferedReader in = new BufferedReader(new FileReader(customerFile));
    	
    	List<String> lines = in.lines().toList();
		
		try {
			for(String line : lines) {
				
				String[] values = line.split(this.SEPARATOR);
				Customer customer = new Customer(Integer.valueOf(values[0]), values[1], values[2]);
				fbs.addCustomer(customer);
			}
			in.close();
		}
		catch(IOException ex) {
			System.out.println("File does not exist");
		}
    }

    @Override
    public void storeData(FlightBookingSystem fbs) throws IOException {
        // TODO: implementation here
    }
}
