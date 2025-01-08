package bcu.cmp5332.bookingsystem.gui;

import bcu.cmp5332.bookingsystem.commands.Command;
import bcu.cmp5332.bookingsystem.commands.RemoveFlight;
import bcu.cmp5332.bookingsystem.data.FlightBookingSystemData;
import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Customer;
import bcu.cmp5332.bookingsystem.model.Flight;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;



public class MainWindow extends JFrame implements ActionListener, MouseListener {

    private JMenuBar menuBar;
    private JMenu adminMenu;
    private JMenu flightsMenu;
    private JMenu bookingsMenu;
    private JMenu customersMenu;

    private JMenuItem adminExit;

    private JMenuItem flightsView;
    private JMenuItem flightsAdd;
    private JMenuItem flightsDel;
    
    private JMenuItem bookingsIssue;
    private JMenuItem bookingsUpdate;
    private JMenuItem bookingsCancel;

    private JMenuItem custView;
    private JMenuItem custAdd;
    private JMenuItem custDel;

    private FlightBookingSystem fbs;
    private List<FlightBookingSystem> fbsList;
    
    private JTable table;
    DefaultTableModel dtmModel;
    
    private MainWindow mw;
    public Object[][] data;
    
    private List<Flight> flightListModifiable;
    
    int in = 0;
    
    

    public MainWindow(FlightBookingSystem fbs) throws FlightBookingSystemException, IOException {
    	
        initialize();
        this.fbs = fbs;
        this.fbsList = FlightBookingSystemData.load();
        this.flightListModifiable = fbs.getFlightsModifiable();
    }
    
    public FlightBookingSystem getFlightBookingSystem() {
        return fbs;
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {

        }
        
        setTitle("Flight Booking Management System");
        this.setLocation(500, 200);
        menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        //adding adminMenu menu and menu items
        adminMenu = new JMenu("Admin");
        menuBar.add(adminMenu);

        adminExit = new JMenuItem("Exit");
        adminMenu.add(adminExit);
        adminExit.addActionListener(this);

        // adding Flights menu and menu items
        flightsMenu = new JMenu("Flights");
        menuBar.add(flightsMenu);

        flightsView = new JMenuItem("View");
        flightsAdd = new JMenuItem("Add");
        flightsDel = new JMenuItem("Delete");
        flightsMenu.add(flightsView);
        flightsMenu.add(flightsAdd);
        flightsMenu.add(flightsDel);
        // adding action listener for Flights menu items
        for (int i = 0; i < flightsMenu.getItemCount(); i++) {
            flightsMenu.getItem(i).addActionListener(this);
        }
        
        // adding Bookings menu and menu items
        bookingsMenu = new JMenu("Bookings");
        menuBar.add(bookingsMenu);
        bookingsIssue = new JMenuItem("Issue");
        bookingsUpdate = new JMenuItem("Update");
        bookingsCancel = new JMenuItem("Cancel");
        bookingsMenu.add(bookingsIssue);
        bookingsMenu.add(bookingsUpdate);
        bookingsMenu.add(bookingsCancel);
        // adding action listener for Bookings menu items
        for (int i = 0; i < bookingsMenu.getItemCount(); i++) {
            bookingsMenu.getItem(i).addActionListener(this);
        }

        // adding Customers menu and menu items
        customersMenu = new JMenu("Customers");
        menuBar.add(customersMenu);

        custView = new JMenuItem("View");
        custAdd = new JMenuItem("Add");
        custDel = new JMenuItem("Delete");

        customersMenu.add(custView);
        customersMenu.add(custAdd);
        customersMenu.add(custDel);
        // adding action listener for Customers menu items
        custView.addActionListener(this);
        custAdd.addActionListener(this);
        custDel.addActionListener(this);

        setSize(800, 500);

        setVisible(true);
        setAutoRequestFocus(true);
        toFront();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
/* Uncomment the following line to not terminate the console app when the window is closed */
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);        

    }	

/* Uncomment the following code to run the GUI version directly from the IDE */
    public static void main(String[] args) throws IOException, FlightBookingSystemException {
        //FlightBookingSystem fbs = FlightBookingSystemData.load();
    
    	List<FlightBookingSystem> fbsList = FlightBookingSystemData.load();
    	FlightBookingSystem fbs = fbsList.getFirst();
		
        new MainWindow(fbs);			
    }



    @Override
    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == adminExit) {
            try {
            	fbsList.set(0, fbs);
                FlightBookingSystemData.store(this.fbsList);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, ex, "Error", JOptionPane.ERROR_MESSAGE);
            }
            System.exit(0);
        } else if (ae.getSource() == flightsView) {
        	
           //this.flightListModifiable = displayFlights(this.flightListModifiable);
        	
        	displayFlights();
            
            
        } else if (ae.getSource() == flightsAdd) {
            new AddFlightWindow(this,this.flightListModifiable);
            
        } else if (ae.getSource() == flightsDel) {
        	//displayFlights();
            
            //this.getContentPane().removeAll();
            //this.getContentPane().add(new JScrollPane(table));
            
            //deleteFlight();
        	
        	
            //this.flightListModifiable = deleteFlight(this.flightListModifiable);
            deleteFlight();
            
        } else if (ae.getSource() == bookingsIssue) {
            
            
        } else if (ae.getSource() == bookingsCancel) {
            
            
        } else if (ae.getSource() == custView) {
            displayCustomers();
            
        } else if (ae.getSource() == custAdd) {
            new AddCustomerWindow(this);
            
        } else if (ae.getSource() == custDel) {
            
            
        }
        
    }

    public void displayFlights() {
    	List<Flight> flightListModifiable = fbs.getFlightsModifiable();
    	List<Flight> flightList = fbs.getFlights();
        // headers for the table
        String[] columns = new String[]{"Flight No", "Origin", "Destination", "Departure Date"};
        Object[][] data = new Object[flightListModifiable.size()][6];
        try {
        	int in = 0;
        	for(Flight flight2 : flightListModifiable) {
        		data[in][0] = flight2.getFlightNumber();
                data[in][1] = flight2.getOrigin();
                data[in][2] = flight2.getDestination();
                data[in][3] = flight2.getDepartureDate();
                in++;
        	}
        	/*
            for (int i = 0; i < flightListModifiable.size(); i++) {
                Flight flight = flightList.get(i);
                data[i][0] = flight.getFlightNumber();
                data[i][1] = flight.getOrigin();
                data[i][2] = flight.getDestination();
                data[i][3] = flight.getDepartureDate();
                
            }
            */
        }
        catch(IndexOutOfBoundsException ex) {
        	
        }

        table = new JTable(data, columns);
        
        table.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				int row = table.rowAtPoint(e.getPoint());
				System.out.println("This is the flights");
				try {
					new DisplayFlightPassengers(mw,fbs,row +1);
					
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
        this.data = data;
        this.getContentPane().removeAll();
        this.getContentPane().add(new JScrollPane(table));
        this.revalidate();
        
    }
    
    public void displayCustomers() {
    	List<Customer> customerList = fbs.getCustomers();
    	
    	String[] columns = new String[]{"Name", "Phone", "Email", "Number of bookings"};
    	
    	Object[][] data = new Object[customerList.size()][4];
        for (int i = 0; i < customerList.size(); i++) {
            Customer customer = customerList.get(i);
            data[i][0] = customer.getName();
            data[i][1] = customer.getPhone();
            data[i][2] = customer.getEmail();
            data[i][3] = customer.getBookings().size();
            //data[i][3] = flight.getDepartureDate();
            
        }
        
        table = new JTable(data, columns);
        table.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				System.out.println("this is the customers");
				
				
				int row = table.rowAtPoint(e.getPoint());
				//int col = table.columnAtPoint(e.getPoint());
				try {
					new DisplayCustomerBookingDetails(mw,fbs,row +1);
				} catch (FlightBookingSystemException e1) {
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
        this.getContentPane().removeAll();
        this.getContentPane().add(new JScrollPane(table));
        this.revalidate();
    	
    }
    
    public  void deleteFlight() {
    	List<Flight> flightListModifiable = fbs.getFlightsModifiable();
    	List<Flight> flightList = fbs.getFlights();
    	
    	String[] columns = new String[]{"Flight No", "Origin", "Destination", "Departure Date"};
    	
    	JTable table = new JTable();
    	
    	if(flightListModifiable.size() > 0) {
    		Object[][] data = new Object[flightListModifiable.size()][4];
    		
    		
    		int in = 0;
        	for(Flight flight2 : flightList) {
        		data[in][0] = flight2.getFlightNumber();
                data[in][1] = flight2.getOrigin();
                data[in][2] = flight2.getDestination();
                data[in][3] = flight2.getDepartureDate();
                in++;
        	}
        	/*
    		
    		for (int i = 0; i < flightListModifiable.size(); i++) {
    			
            	Flight flight = flightListModifiable.get(i);
            	data[i][0] = flight.getFlightNumber();
                data[i][1] = flight.getOrigin();
                data[i][2] = flight.getDestination();
                data[i][3] = flight.getDepartureDate();
                
                in++;
                
            }
            */
    		this.data = data;
    		
    	}
    	
    	table.setModel( new DefaultTableModel(this.data,columns));;
    	
    	table.addMouseListener(new MouseListener() {
            
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				int row = table.rowAtPoint(e.getPoint());
				System.out.println("This is the flights");
				try {
					int flightID = fbs.getFlights().get(in).getId();
					Command removeFlight = new RemoveFlight(row +1);
					flightListModifiable.remove(row);
					
					removeFlight.execute(fbs);
					DefaultTableModel dtm = (DefaultTableModel) table.getModel();
					dtm.removeRow(row);
					
					
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
        
    }
    /*
    public void deleteFlight() {
    	
    	List<Flight> flightsList = fbs.getFlights();
        // headers for the table
        String[] columns = new String[]{"Flight No", "Origin", "Destination", "Departure Date"};
        
        Object[][] data = this.data;
        
        
        JTable table = new JTable();
        
        for (int i = 0; i < flightsList.size(); i++) {
        	Flight flight = flightsList.get(i);
        	data[i][0] = flight.getFlightNumber();
            data[i][1] = flight.getOrigin();
            data[i][2] = flight.getDestination();
            data[i][3] = flight.getDepartureDate();
            
        }
        
        
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
			        	//temp[i][2] = data[i][2];
			        	//temp[i][3] = data[i][3];
					}
					
					for(int i = row + 1; i < data.length; i ++) {
						temp[i-1][0] = data[i][0];
						temp[i-1][1] = data[i][1];
						//temp[i-1][2] = data[i][2];
						//temp[i-1][3] = data[i][3];
					}
					
					
					Object[][] data1 = new Object[][]{data};
					data1 = temp;
					table.setModel(new DefaultTableModel(data1,columns));
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
    }

	*/

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
