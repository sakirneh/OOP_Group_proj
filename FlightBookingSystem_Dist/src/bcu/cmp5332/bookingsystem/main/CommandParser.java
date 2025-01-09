package bcu.cmp5332.bookingsystem.main;

import bcu.cmp5332.bookingsystem.commands.LoadGUI;
import bcu.cmp5332.bookingsystem.commands.RemoveCustomer;
import bcu.cmp5332.bookingsystem.commands.RemoveFlight;
import bcu.cmp5332.bookingsystem.commands.ShowCustomer;
import bcu.cmp5332.bookingsystem.commands.ShowFlight;
import bcu.cmp5332.bookingsystem.commands.ListFlights;
import bcu.cmp5332.bookingsystem.commands.AddBooking;
import bcu.cmp5332.bookingsystem.commands.AddCustomer;
import bcu.cmp5332.bookingsystem.commands.AddFlight;
import bcu.cmp5332.bookingsystem.commands.CancelBooking;
import bcu.cmp5332.bookingsystem.commands.Command;
import bcu.cmp5332.bookingsystem.commands.EditBooking;
import bcu.cmp5332.bookingsystem.commands.Help;
import bcu.cmp5332.bookingsystem.commands.ListCustomers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;


public class CommandParser {
    
    public static Command parse(String line) throws IOException, FlightBookingSystemException {
        try {
        	
            String[] parts = line.split(" ", 3);
            String cmd = parts[0];

            
            if (cmd.equals("addflight")) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                
             // Flight Number
                String flighNumber;
                try {
                    System.out.print("Flight Number: ");
                    flighNumber = reader.readLine();
                    if (flighNumber.isEmpty()) {
                        throw new IllegalArgumentException("Flight Number cannot be empty.");
                    }
                } catch (IOException e) {
                    throw new FlightBookingSystemException("Error reading Flight Number.", e);
                }

                // Origin
                String origin;
                try {
                    System.out.print("Origin: ");
                    origin = reader.readLine();
                    if (origin.isEmpty()) {
                        throw new IllegalArgumentException("Origin cannot be empty.");
                    }
                } catch (IOException e) {
                    throw new FlightBookingSystemException("Error reading Origin.", e);
                }

                // Destination
                String destination;
                try {
                    System.out.print("Destination: ");
                    destination = reader.readLine();
                    if (destination.isEmpty()) {
                        throw new IllegalArgumentException("Destination cannot be empty.");
                    }
                } catch (IOException e) {
                    throw new FlightBookingSystemException("Error reading Destination.", e);
                }

                // Seat Capacity
                int seatCapacity;
                try {
                    System.out.print("Seat Capacity: ");
                    String seatCapacityInput = reader.readLine();
                    seatCapacity = Integer.parseInt(seatCapacityInput);
                    if (seatCapacity <= 0) {
                        throw new IllegalArgumentException("Seat Capacity must be greater than zero.");
                    }
                } catch (IOException e) {
                    throw new FlightBookingSystemException("Error reading Seat Capacity.", e);
                } catch (NumberFormatException e) {
                    throw new FlightBookingSystemException("Seat Capacity must be a valid integer.", e);
                }

                // Price
                float price;
                try {
                    System.out.print("Price: ");
                    String priceInput = reader.readLine();
                    price = Float.parseFloat(priceInput);
                    if (price <= 0) {
                        throw new IllegalArgumentException("Price must be greater than zero.");
                    }
                } catch (IOException e) {
                    throw new FlightBookingSystemException("Error reading Price.", e);
                } catch (NumberFormatException e) {
                    throw new FlightBookingSystemException("Price must be a valid number.", e);
                }

                LocalDate departureDate = parseDateWithAttempts(reader);

                return new AddFlight(flighNumber, origin, destination, departureDate,Integer.valueOf(seatCapacity), Float.valueOf(price));
                
            } else if(cmd.equals("removeflight")) {
            	return new RemoveFlight(Integer.valueOf(parts[1]));
            }
            else if (cmd.equals("addcustomer")) {
            	BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                System.out.print("Name: ");
                String name = reader.readLine();
                
                System.out.print("Phone: ");
                String phoneNum = reader.readLine();
                
                System.out.print("Email: ");
                String email = reader.readLine();

                return new AddCustomer(name, phoneNum,email);
                
            } else if(cmd.equals("removecustomer")) {
            	return new RemoveCustomer(Integer.valueOf(parts[1]));
            }
            else if (cmd.equals("loadgui")) {
                return new LoadGUI();
            } else if (parts.length == 1) {
                if (line.equals("listflights")) {
                    return new ListFlights();
                } else if (line.equals("listcustomers")) {
                    return new ListCustomers();
                } else if (line.equals("help")) {
                    return new Help();
                }
            } else if (parts.length == 2) {
                int id = Integer.parseInt(parts[1]);

                if (cmd.equals("showflight")) {
                    return new ShowFlight(id);
                } else if (cmd.equals("showcustomer")) {
                    return new ShowCustomer(id);
                }
            } else if (parts.length == 3) {
                

                if (cmd.equals("addbooking")) {
                	return new AddBooking(Integer.valueOf(parts[1]),Integer.valueOf(parts[2]));
                    
                } else if (cmd.equals("editbooking")) {
                	 
                	 return new EditBooking(Integer.valueOf(parts[1]),Integer.valueOf(parts[2]));
                    
                } else if (cmd.equals("cancelbooking")) {
                    return new CancelBooking(Integer.valueOf(parts[1]),Integer.valueOf(parts[2]));
                }
            }
        } catch (NumberFormatException ex) {

        }

        throw new FlightBookingSystemException("Invalid command.");
    }
    
    private static LocalDate parseDateWithAttempts(BufferedReader br, int attempts) throws IOException, FlightBookingSystemException {
        if (attempts < 1) {
            throw new IllegalArgumentException("Number of attempts should be higher that 0");
        }
        while (attempts > 0) {
            attempts--;
            System.out.print("Departure Date (\"YYYY-MM-DD\" format): ");
            try {
                LocalDate departureDate = LocalDate.parse(br.readLine());
                return departureDate;
            } catch (DateTimeParseException dtpe) {
                System.out.println("Date must be in YYYY-MM-DD format. " + attempts + " attempts remaining...");
            }
        }
        
        throw new FlightBookingSystemException("Incorrect departure date provided. Cannot create flight.");
    }
    
    private static LocalDate parseDateWithAttempts(BufferedReader br) throws IOException, FlightBookingSystemException {
        return parseDateWithAttempts(br, 3);
    }
}
