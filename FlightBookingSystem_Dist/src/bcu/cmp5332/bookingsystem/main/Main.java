package bcu.cmp5332.bookingsystem.main;

import bcu.cmp5332.bookingsystem.data.FlightBookingSystemData;
import bcu.cmp5332.bookingsystem.commands.Command;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

import java.io.*;

import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException, FlightBookingSystemException {
    	List<FlightBookingSystem> fbsList = FlightBookingSystemData.load();
        FlightBookingSystem fbs = fbsList.getFirst();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Flight Booking System");
        System.out.println("Enter 'help' to see a list of available commands.");
        while (true) {
            System.out.print("> ");
            String line = br.readLine();
            if (line.equals("exit")) {
                break;
            }

            try {
                Command command = CommandParser.parse(line);
                command.execute(fbs);
                
            } catch (FlightBookingSystemException ex) {
                System.out.println(ex.getMessage());
            }
        }
        FlightBookingSystemData.store(fbsList);
        System.exit(0);
    }
}
