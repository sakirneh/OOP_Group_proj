package bcu.cmp5332.bookingsystem.model;

import static org.junit.Assert.assertEquals;


import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import org.junit.Test;
import bcu.cmp5332.bookingsystem.data.*;
import bcu.cmp5332.bookingsystem.main.*;

public class CustomerTest {
	
	
	
	@Test
	public void testGetDetailShort() throws FlightBookingSystemException, IOException {
		Customer customer = new Customer(1,"Abdel-Rahman Tawil","07555555555","AbdelRahmanTawil@gmail.com");
		
		assertEquals("Customer #1 -  Name: Abdel-Rahman Tawil - Phone: 07555555555",customer.getDetailsShort());
	}
	@Test
	public void testGetDetailsLong() throws FlightBookingSystemException, IOException {
		Customer customer = new Customer(1,"Abdel-Rahman Tawil","07555555555","AbdelRahmanTawil@gmail.com");
		FlightBookingSystem fbs = new FlightBookingSystem();
		float price = (float) 100.0;
		
		LocalDate departureDate = LocalDate.parse("2022-11-25");
		Flight flight = new Flight(1,"LH2560", "Birmingham","Munich",departureDate,25,price);
		
		fbs.addCustomer(customer);
		fbs.addFlight(flight);
		
		
		Booking booking = new Booking(customer, flight, fbs.getSystemDate());
		customer.addBooking(booking);
		flight.addPassenger(customer);
		String testData = "Customer #1\n"
				+ "Name: Abdel-Rahman Tawil\n"
				+ "Phone: 07555555555\n"
				+ "---------------------------\n"
				+ "Bookings:\n"
				+ "Booking date: 11/11/2024 for Flight #1 - LH2560 - Birmingham to Munich on 25/11/2022\n";
		assertEquals(testData, customer.getDetailsLong());
	}
	@Test
	public void testAddBooking() throws FlightBookingSystemException, IOException {
		Customer customer = new Customer(1,"Abdel-Rahman Tawil","07555555555","AbdelRahmanTawil@gmail.com");
		FlightBookingSystem fbs = new FlightBookingSystem();
		float price = (float) 100.0;
		LocalDate departureDate = LocalDate.parse("2022-11-25");
		Flight flight = new Flight(1,"LH2560", "Birmingham","Munich",departureDate,25,price);
		
		fbs.addCustomer(customer);
		fbs.addFlight(flight);
		
		
		Booking booking = new Booking(customer, flight, fbs.getSystemDate());
		customer.addBooking(booking);
		flight.addPassenger(customer);
		assertEquals(1,customer.getBookings().size());
	}
	
	
	@Test
	public void testCancelBookingForFlight() throws FlightBookingSystemException, IOException {
		Customer customer = new Customer(1,"Abdel-Rahman Tawil","07555555555","AbdelRahmanTawil@gmail.com");
		FlightBookingSystem fbs = new FlightBookingSystem();
		float price = (float) 100.0;
		LocalDate departureDate = LocalDate.parse("2022-11-25");
		Flight flight = new Flight(1,"LH2560", "Birmingham","Munich",departureDate,25,price);
		
		fbs.addCustomer(customer);
		fbs.addFlight(flight);
		
		
		Booking booking = new Booking(customer, flight, fbs.getSystemDate());
		customer.addBooking(booking);
		flight.addPassenger(customer);
		customer.cancelBookingForFlight(flight);
		assertEquals(0, customer.getBookings().size());
		
	}
	@Test
	public void testCustomerStoreData() throws IOException, FlightBookingSystemException {
		Customer customer = new Customer(1,"Abdel-Rahman Tawil","07555555555","AbdelRahmanTawil@gmail.com");
		List<FlightBookingSystem> fbsList = FlightBookingSystemData.load();
		FlightBookingSystem fbs = fbsList.getLast();
		
		fbs.addCustomer(customer);
		
		FlightBookingSystemData.store(fbsList);
		
		List<FlightBookingSystem> testFbsList = FlightBookingSystemData.load();
		FlightBookingSystem testFbs = testFbsList.getLast();
		
		Customer customerLoaded = testFbs.getCustomerByID(1);
		
		String testString = "1Abdel-Rahman Tawil07555555555AbdelRahmanTawil@gmail.com";
		String customerString = customerLoaded.getID() + customerLoaded.getName() + customerLoaded.getPhone() + customerLoaded.getEmail();
		
		assertEquals(testString, customerString);
	}
	
	@Test
	public void testCustomerLoadData() throws FlightBookingSystemException, IOException {
		List<FlightBookingSystem> fbsList = FlightBookingSystemData.load();
		FlightBookingSystem fbs = fbsList.getLast();
		
		Customer customer = fbs.getCustomerByID(1);
		//Customer testCustomer = new Customer(1,"Abdel-Rahman Tawil","07555555555","AbdelRahmanTawil@gmail.com");
		
		String testString = "1Abdel-Rahman Tawil07555555555AbdelRahmanTawil@gmail.com";
		String customerString = customer.getID() + customer.getName() + customer.getPhone() + customer.getEmail();
		assertEquals(testString, customerString);
	}
	
	
}
