package bcu.cmp5332.bookingsystem.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

import bcu.cmp5332.bookingsystem.commands.AddCustomer;
import bcu.cmp5332.bookingsystem.commands.Command;
import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;


public class AddCustomerWindow extends JFrame implements ActionListener{

	private MainWindow mw;
    private JTextField nameText = new JTextField();
    private JTextField phoneText = new JTextField();
    private JTextField emailText = new JTextField();

    private JButton addBtn = new JButton("Add");
    private JButton cancelBtn = new JButton("Cancel");

    public AddCustomerWindow(MainWindow mw) {
        this.mw = mw;
        initialize();
    }
    
    private void initialize() {
    	try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {

        }
    	
    	setTitle("Add a New Customer");
    	
    	setSize(350, 220);
    	
    	JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(4, 2));
        topPanel.add(new JLabel("Customer Name : "));
        topPanel.add(nameText);
        topPanel.add(new JLabel("Customer Phone : "));
        topPanel.add(phoneText);
        topPanel.add(new JLabel("Customer Email : "));
        topPanel.add(emailText);
        
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(1, 3));
        
        bottomPanel.add(new JLabel("     "));
        bottomPanel.add(addBtn);
        bottomPanel.add(cancelBtn);
        
        addBtn.addActionListener(this);
        cancelBtn.addActionListener(this);

        this.getContentPane().add(topPanel, BorderLayout.CENTER);
        this.getContentPane().add(bottomPanel, BorderLayout.SOUTH);
        setLocationRelativeTo(mw);

        setVisible(true);
    }
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub
		if (ae.getSource() == addBtn) {
            try {
				addCustomer();
			} catch (IOException | FlightBookingSystemException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        } else if (ae.getSource() == cancelBtn) {
            this.setVisible(false);
        }
	}
	
	private void addCustomer() throws IOException, FlightBookingSystemException{
		
		try {
			String name = nameText.getText();
			String phone = phoneText.getText();
			String email = emailText.getText();
			// create and execute the AddCustomer Command
			Command addCustomer = new AddCustomer(name, phone, email);
			
			addCustomer.execute(mw.getFlightBookingSystem());
			// refresh the view with the list of customers
			mw.displayCustomers();
			// hide (close) the AddFlightWindow  
			this.setVisible(false);
		}catch (FlightBookingSystemException ex) {
            JOptionPane.showMessageDialog(this, ex, "Error", JOptionPane.ERROR_MESSAGE);
        }
	}

}
