package bcu.cmp5332.bookingsystem.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Flight;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;
import bcu.cmp5332.bookingsystem.model.Customer;

public class DisplayFlightPassengers extends JFrame {

	private MainWindow mw;
    //private JTextField nameText = new JTextField();
    
    //private JLabel flightLabel = new JLabel();

    private FlightBookingSystem fbs;
    
    //private JButton cancelBtn = new JButton("Exit");
    
    private int ID;

    public DisplayFlightPassengers(MainWindow mw, FlightBookingSystem fbs, int ID) throws FlightBookingSystemException, IOException {
        
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
    	
    	setTitle("Flight Passengers");
    	
    	setSize(600, 300);
    	
    	List<Flight> flightsList = fbs.getFlights();
        // headers for the table
    	Flight flight = fbs.getFlightByID(ID);
    	List<Customer> passengerList = flight.getPassengers();
    	
        String[] columns = new String[]{"Customer Name", "Phone Number", "Email"};
        Object[][] data = new Object[flight.getPassengers().size()][3];
        
        
        try {
        	for(int i = 0; i < passengerList.size();i ++) {
            	Customer customer = passengerList.get(i);
            	data[i][0] = customer.getName();
            	data[i][1] = customer.getPhone();
            	data[i][2] = customer.getEmail();
            }
        }
        catch(IndexOutOfBoundsException ex){
        	
        }
        
        JTable table = new JTable(data, columns);
        
        
        
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


	