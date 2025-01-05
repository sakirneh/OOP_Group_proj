package bcu.cmp5332.bookingsystem.commands;

import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

import java.io.IOException;

import bcu.cmp5332.bookingsystem.gui.MainWindow;
import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;

public class LoadGUI implements Command {

    @Override
    public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException, IOException {
        new MainWindow(flightBookingSystem);
    }
    
}
