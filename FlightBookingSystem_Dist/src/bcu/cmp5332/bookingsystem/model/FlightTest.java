package bcu.cmp5332.bookingsystem.model;

import static org.junit.Assert.*;
import  org.junit.Test;

import java.io.IOException;
import java.time.LocalDate;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.data.*;

public class FlightTest {

	@Test
	public void testGetDetailShort() throws FlightBookingSystemException, IOException {
		LocalDate departureDate = LocalDate.parse("2022-11-25");
		float price = (float) 100.0;
		Flight flight = new Flight(1,"LH2560", "Birmingham","Munich",departureDate,25,price);
		
		assertEquals("Flight #1 - LH2560 - Birmingham to Munich on 25/11/2022",flight.getDetailsShort());
	}
	@Test
	public void testGetDetailsLong() throws FlightBookingSystemException, IOException {
		Customer customer = new Customer(1,"Abdel-Rahman Tawil","07555555555","AbdelRahmanTawil@gmail.com");
		
		float price = (float) 100.0;
		
		LocalDate departureDate = LocalDate.parse("2022-11-25");
		Flight flight = new Flight(1,"LH2560", "Birmingham","Munich",departureDate,25,price);
		
		
		
		flight.addPassenger(customer);
		String testData = 
				"Flight #1\n"
				+ "Flight No: LH2560\n"
				+ "Origin: Birmingham\n"
				+ "Destination: Munich\n"
				+ "Departure Date: 25/11/2022\n"
				+ "---------------------------\n"
				+ "Passengers:  Capacity: 25\n"
				+ "Customer #1 -  Name: Abdel-Rahman Tawil - Phone: 07555555555\n"
				;
		assertEquals(testData, flight.getDetailsLong());
	}
	
	@Test
	public void testAddPassengers() throws FlightBookingSystemException {
		Customer customer = new Customer(1,"Abdel-Rahman Tawil","07555555555","AbdelRahmanTawil@gmail.com");
		
		float price = (float) 100.0;
		
		LocalDate departureDate = LocalDate.parse("2022-11-25");
		Flight flight = new Flight(1,"LH2560", "Birmingham","Munich",departureDate,25,price);
		
		
		
		flight.addPassenger(customer);
		
		assertEquals(1,flight.getPassengers().size());
	}
	
	@Test
	public void removePassengers() throws FlightBookingSystemException {
		Customer customer = new Customer(1,"Abdel-Rahman Tawil","07555555555","AbdelRahmanTawil@gmail.com");
		
		float price = (float) 100.0;
		
		LocalDate departureDate = LocalDate.parse("2022-11-25");
		Flight flight = new Flight(1,"LH2560", "Birmingham","Munich",departureDate,25,price);
		
		
		
		flight.addPassenger(customer);
		flight.removePassengers(customer);
		assertEquals(0,flight.getPassengers().size());
	}
	
	@Test(expected = FlightBookingSystemException.class)
	public void removeFromEmptyPassengerList() throws FlightBookingSystemException {
		Customer customer = new Customer(1,"Abdel-Rahman Tawil","07555555555","AbdelRahmanTawil@gmail.com");
		
		float price = (float) 100.0;
		
		LocalDate departureDate = LocalDate.parse("2022-11-25");
		Flight flight = new Flight(1,"LH2560", "Birmingham","Munich",departureDate,25,price);
		
		
		
		
		flight.removePassengers(customer);
		
	}
	
	@Test
	public void testStoreFlight() throws FlightBookingSystemException, IOException {
		LocalDate departureDate = LocalDate.parse("2022-11-25");
		float price = (float) 100.0;
		Flight flight = new Flight(1, "LH2560","Birmingham", "Munich",departureDate,25,price);
		
		FlightBookingSystem fbs = new FlightBookingSystem();
		fbs.addFlight(flight);
		
		FlightBookingSystemData.store(fbs);
	}
	
	@Test
	public void testLoadFlight() throws FlightBookingSystemException, IOException {
		FlightBookingSystem fbs = FlightBookingSystemData.load();
		
		Flight flight = fbs.getFlightByID(1);
		
		String testString = "1LH2560BirminghamMunich2022-11-2525100.0";
		String flightString = flight.getId() +flight.getFlightNumber() + flight.getOrigin() + flight.getDestination() +flight.getDepartureDate() + flight.getSeatCapacity() + flight.getPrice();
		assertEquals(testString, flightString);
	}
}
