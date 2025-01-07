package bcu.cmp5332.bookingsystem.gui;

import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.TableColumnModel;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Booking;
import bcu.cmp5332.bookingsystem.model.Customer;
import bcu.cmp5332.bookingsystem.model.Flight;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;



public class DisplayCustomerBookingDetails extends JFrame{
	private MainWindow mw;
    private int ID;
    private FlightBookingSystem fbs;
    public DisplayCustomerBookingDetails(MainWindow mw, FlightBookingSystem fbs, int ID) throws FlightBookingSystemException {
    	
    	
    	this.mw = mw;
        this.ID = ID;
        this.fbs = fbs;
        initialize();
    }
    
    private void initialize() throws FlightBookingSystemException {
    	try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {

        }
    	
    	setTitle("Customer Bookings Made");
    	
    	setSize(600, 300);
    	
    	
        // headers for the table
    	
    	//
        String[] columns = new String[]{"Booking ID", "Customer ID", "Flight ID", "Flight Departure"};
        Customer customer = fbs.getCustomerByID(ID);
        Object[][] data = new Object[customer.getBookings().size()][4];
		for(int i = 0; i < customer.getBookings().size();i++) {
			List<Booking> bookings = customer.getBookings();
			Booking booking = bookings.get(i);
			Flight flight = booking.getFlight();
			data[i][0] = booking.getBookingID();
        	data[i][1] = customer.getID();
        	data[i][2] = flight.getId();
        	data[i][3] = flight.getDetailsShort();
			
		}
        
        
        
        JTable table = new JTable(data, columns);
        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(100);
        columnModel.getColumn(1).setPreferredWidth(100);
        columnModel.getColumn(2).setPreferredWidth(100);
        columnModel.getColumn(3).setPreferredWidth(500);
        
        
        
        this.getContentPane().removeAll();
        setLocationRelativeTo(mw);
        
        
        setAutoRequestFocus(true);
        toFront();

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);  
        this.getContentPane().add(new JScrollPane(table));
        setVisible(true);
        this.revalidate();
    }
}
