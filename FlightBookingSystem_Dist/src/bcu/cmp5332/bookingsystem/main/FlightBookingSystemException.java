package bcu.cmp5332.bookingsystem.main;

import java.io.IOException;

/**
 * FlightBookingSystemException extends {@link Exception} class and is a custom exception
 * that is used to notify the user about errors or invalid commands.
 * 
 */
public class FlightBookingSystemException extends Exception {

    	public FlightBookingSystemException(String message) {
        	super(message);
    	}

	public FlightBookingSystemException(String string, IOException e) {
		
	}

	public FlightBookingSystemException(String string, NumberFormatException e) {
	
	}
}
