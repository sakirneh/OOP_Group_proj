package bcu.cmp5332.bookingsystem.data;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Flight;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.Scanner;

public class FlightDataManager implements DataManager {
    
	public String RESOURCE;
	public FlightDataManager(String RESOURCE) {
		this.RESOURCE = RESOURCE;
	}
    
    @Override
    public void loadData(FlightBookingSystem fbs) throws IOException, FlightBookingSystemException {
        try (Scanner sc = new Scanner(new File(RESOURCE))) {
            int line_idx = 1;
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] properties = line.split(SEPARATOR, -1);
                
                try {
                	
                	
                    int id = Integer.parseInt(properties[0]);
                    String flightNumber = properties[1];
                    String origin = properties[2];
                    String destination = properties[3];
                    LocalDate departureDate = LocalDate.parse(properties[4]);
                    String seatCapacity = properties[5];
                    String price = properties[6];
                    boolean isHidden = Boolean.valueOf(properties[7]);
                    Flight flight = new Flight(id, flightNumber, origin, destination, departureDate,Integer.parseInt(seatCapacity),Float.parseFloat(price),isHidden);
                    fbs.addFlight(flight);
                } catch (NumberFormatException ex) {
                    throw new FlightBookingSystemException("Unable to parse Flight id " + properties[0] + " on line " + line_idx
                        + "\nError: " + ex);
                }
                catch(FlightBookingSystemException ex) {
                	System.out.println(ex.getMessage());
                }
                line_idx++;
            }
        }
        
        
    }
    
    @Override
    public void storeData(FlightBookingSystem fbs) throws IOException {
        try (PrintWriter out = new PrintWriter(new FileWriter(RESOURCE))) {
            for (Flight flight : fbs.getFlights()) {
                out.print(flight.getId() + SEPARATOR);
                out.print(flight.getFlightNumber() + SEPARATOR);
                out.print(flight.getOrigin() + SEPARATOR);
                out.print(flight.getDestination() + SEPARATOR);
                out.print(flight.getDepartureDate() + SEPARATOR);
                out.print(flight.getSeatCapacity() + SEPARATOR);
                out.print(flight.getPrice() + SEPARATOR);
                out.print(flight.getHiddenValue() + SEPARATOR);
                out.println();
            }
        }
    }
}
