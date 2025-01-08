package bcu.cmp5332.bookingsystem.gui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.List;

import javax.swing.JFrame;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import bcu.cmp5332.bookingsystem.commands.Command;
import bcu.cmp5332.bookingsystem.commands.RemoveFlight;
import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Flight;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

public class DeleteFlightWindow extends JFrame implements MouseListener{

	private MainWindow mw;
	private FlightBookingSystem fbs;
	
	 public DeleteFlightWindow(MainWindow mw,FlightBookingSystem fbs) {
		 
	        this.mw = mw;
	        this.fbs = fbs;
	        initialize();
	    }
	
	 private void initialize() {

	        try {
	            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	        } catch (Exception ex) {

	        }

	        setTitle("Add a New Flight");

	        setSize(350, 220);
	        

	        List<Flight> flightsList = fbs.getFlights();
	        // headers for the table
	        String[] columns = new String[]{"Flight No", "Origin", "Destination", "Departure Date"};
	        int size = flightsList.size();
	        Object[][] data = new Object[size][6];
	        
	        
	        JTable table = new JTable();
	        /*
	        for (int i = 0; i < flightsList.size(); i++) {
	        	Flight flight = flightsList.get(i);
	        	data[i][0] = flight.getFlightNumber();
	            data[i][1] = flight.getOrigin();
	            data[i][2] = flight.getDestination();
	            data[i][3] = flight.getDepartureDate();
	            
	        }
	        */
	        
	        table.setModel( new DefaultTableModel(data,columns));;
	        
	        table.addMouseListener(new MouseListener() {
	        
				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub
					int row = table.rowAtPoint(e.getPoint());
					System.out.println("This is the flights");
					try {
						
						Command removeFlight = new RemoveFlight(row+1);
						Object[][] temp = new Object[data.length -1][6];
						
						removeFlight.execute(fbs);
						//dtmModel.removeRow(row );
						
						for(int i  = 0; i < row; i ++) {
				        	temp[i][0] = data[i][0];
				        	temp[i][1] = data[i][1];
				        	temp[i][2] = data[i][2];
				        	temp[i][3] = data[i][3];
						}
						
						for(int i = row + 1; i < data.length; i ++) {
							temp[i-1][0] = data[i][0];
							temp[i-1][1] = data[i][1];
							temp[i-1][2] = data[i][2];
							temp[i-1][3] = data[i][3];
						}
						
						
						Object[][] data1 = new Object[][]{data};
						data1 = temp;
						table.setModel(new DefaultTableModel(data1,columns));
						//mw.displayFlights();
						//DefaultTableModel tblModel = (DefaultTableModel) table.getModel();
						//tblModel.removeRow(row);
					} catch (FlightBookingSystemException | IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}

				@Override
				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseReleased(MouseEvent e) {
					// TODO Auto-generated method stub
					//deleteFlight();
				}

				@Override
				public void mouseEntered(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseExited(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
	        	
	        });
	        
	        
	        setVisible(true);
	        
	        this.getContentPane().removeAll();
	        this.getContentPane().add(new JScrollPane(table));
	        this.revalidate();
	    

	        
	        setLocationRelativeTo(mw);

	        setVisible(true);

	    }
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
