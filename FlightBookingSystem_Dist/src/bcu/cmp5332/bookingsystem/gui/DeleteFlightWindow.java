package bcu.cmp5332.bookingsystem.gui;

import bcu.cmp5332.bookingsystem.commands.RemoveFlight;
import bcu.cmp5332.bookingsystem.gui.MainWindow;
import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Flight;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

public class DeleteFlightWindow extends JDialog implements ActionListener {
    private FlightBookingSystem fbs;
    private List<Flight> flightList;
    private JTable flightTable;
    private JButton deleteButton;
    private JButton cancelButton;

    public DeleteFlightWindow(MainWindow parent) {
        super(parent, "Delete Flight", true);

        this.fbs = parent.getFlightBookingSystem();
        this.flightList = fbs.getFlights();

        setLayout(new BorderLayout());

        // Create a table of flights
        String[] columns = {"Flight No", "Origin", "Destination", "Departure Date"};
        Object[][] data = new Object[flightList.size()][4];
        for (int i = 0; i < flightList.size(); i++) {
            Flight flight = flightList.get(i);
            data[i][0] = flight.getFlightNumber();
            data[i][1] = flight.getOrigin();
            data[i][2] = flight.getDestination();
            data[i][3] = flight.getDepartureDate();
        }

        flightTable = new JTable(data, columns);
        flightTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPane = new JScrollPane(flightTable);
        add(scrollPane, BorderLayout.CENTER);

        // Buttons for delete and cancel
        JPanel buttonPanel = new JPanel();
        deleteButton = new JButton("Delete");
        cancelButton = new JButton("Cancel");

        buttonPanel.add(deleteButton);
        buttonPanel.add(cancelButton);

        add(buttonPanel, BorderLayout.SOUTH);

        deleteButton.addActionListener(this);
        cancelButton.addActionListener(this);

        setSize(600, 400);
        setLocationRelativeTo(parent);
        setVisible(true);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == deleteButton) {
            int selectedRow = flightTable.getSelectedRow();
            if (selectedRow != -1) {
                int flightID = selectedRow + 1; // +1 because flightID starts from 1, not 0
                try {
                    RemoveFlight removeFlight = new RemoveFlight(flightID);
                    removeFlight.execute(fbs);

                    // Refresh the flight display in the main window
                    ((MainWindow) getParent()).displayFlights();

                    JOptionPane.showMessageDialog(this, "Flight removed successfully.");
                    dispose();
                } catch (FlightBookingSystemException | IOException ex) {
                    JOptionPane.showMessageDialog(this, "Error removing flight: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please select a flight to delete.", "Error", JOptionPane.WARNING_MESSAGE);
            }
        } else if (e.getSource() == cancelButton) {
            dispose();
        }
    }
}

