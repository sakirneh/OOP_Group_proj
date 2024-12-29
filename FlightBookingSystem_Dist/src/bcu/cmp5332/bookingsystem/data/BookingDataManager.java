package bcu.cmp5332.bookingsystem.data;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Booking;
import bcu.cmp5332.bookingsystem.model.Customer;
import bcu.cmp5332.bookingsystem.model.Flight;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class BookingDataManager implements DataManager {
    
    public final String RESOURCE = "./resources/data/bookings.txt";

    @Override
    public void loadData(FlightBookingSystem fbs) throws IOException, FlightBookingSystemException {
        // TODO: implementation here
    	
    	try (Scanner sc = new Scanner(new File(RESOURCE))) {
            int line_idx = 1;
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] properties = line.split(SEPARATOR, -1);
                try {
                    int customerID = Integer.parseInt(properties[0]);
                    String customerName = properties[1];
                    String customerPhone = properties[2];
                     
                    Customer customer = fbs.getCustomerByID(customerID);
                    
                    
                    
                    int id = Integer.parseInt(properties[3]);
                    String flightNumber = properties[4];
                    String origin = properties[5];
                    String destination = properties[6];
                    LocalDate departureDate = LocalDate.parse(properties[7]);
                    //Flight flight = new Flight(id, flightNumber, origin, destination, departureDate);
                    Flight flight = fbs.getFlightByID(id);
                    
                    Booking booking = new Booking(customer,flight,departureDate);
                    flight.addPassenger(customer);
                    customer.addBooking(booking);
                    
                } catch (NumberFormatException ex) {
                    throw new FlightBookingSystemException("Unable to parse book id " + properties[0] + " on line " + line_idx
                        + "\nError: " + ex);
                }
                
                line_idx++;
            }
            
        }
    	catch(IOException ex) {
        	throw new IOException("The file does not exist or is corrupted");
        }
    }

    @Override
    public void storeData(FlightBookingSystem fbs) throws IOException {
        // TODO: implementation here
    	try (PrintWriter out = new PrintWriter(new FileWriter(RESOURCE))) {
    		
            for(Customer customer : fbs.getCustomers()) {
            	
            	if(customer.getBookings().size() > 0) {
            		for(Booking booking : customer.getBookings()) {
            			out.print(booking.getCustomer().getID() + SEPARATOR);
                        out.print(booking.getCustomer().getName() + SEPARATOR);
                        out.print(booking.getCustomer().getPhone() + SEPARATOR);
                        
                        out.print(booking.getFlight().getId() + SEPARATOR);
                        out.print(booking.getFlight().getFlightNumber() + SEPARATOR);
                        out.print(booking.getFlight().getOrigin() + SEPARATOR);
                        out.print(booking.getFlight().getDestination() + SEPARATOR);
                        out.print(booking.getFlight().getDepartureDate() + SEPARATOR);
                        out.println();
                	}
            	}
            	
            	
            	
            }
        }
    	catch(IOException ex) {
        	throw new IOException("The file does not exist or is corrupted");
        }
    }
    
}
