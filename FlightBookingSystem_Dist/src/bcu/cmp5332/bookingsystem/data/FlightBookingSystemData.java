package bcu.cmp5332.bookingsystem.data;



import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FlightBookingSystemData {
    
    private static final List<DataManager> dataManagers = new ArrayList<>();
    private static final List<DataManager> testDataManagers = new ArrayList<>();
    
    
    
    public final static String bookingTestRESOURCE = "./resources/data/BookingTest.txt";
    public final static String bookingRESOURCE = "./resources/data/bookings.txt";
    
    private final static String customerRESOURCE = "./resources/data/customers.txt";
    private final static String customerTestRESOURCE = "./resources/data/CustomerTest.txt";
    
    private final static String flightRESOURCE = "./resources/data/flights.txt";
    private final static String flightTestRESOURCE = "./resources/data/FlightTest.txt";
    // runs only once when the object gets loaded to memory
    static {
    	testDataManagers.add(new FlightDataManager(flightTestRESOURCE));
        dataManagers.add(new FlightDataManager(flightRESOURCE));
        
        /* Uncomment the two lines below when the implementation of their 
        loadData() and storeData() methods is complete */
         testDataManagers.add(new CustomerDataManager(customerTestRESOURCE));
         dataManagers.add(new CustomerDataManager(customerRESOURCE));
         
         testDataManagers.add(new BookingDataManager(bookingTestRESOURCE));
         dataManagers.add(new BookingDataManager(bookingRESOURCE));
         
         
         
    }
    
    public static List<FlightBookingSystem> load() throws FlightBookingSystemException, IOException {
    	FlightBookingSystem testfbs = new FlightBookingSystem();
        FlightBookingSystem fbs = new FlightBookingSystem();
        List<FlightBookingSystem> fbsList = new ArrayList<>();
        
        
        for (DataManager dm : dataManagers) {
            dm.loadData(fbs);
        }
        
        for (DataManager dm : testDataManagers) {
            dm.loadData(testfbs);
        }
        
        
        
        fbsList.add(fbs);
        fbsList.add(testfbs);
        return fbsList;
    }

    public static void store(List<FlightBookingSystem> fbsList) throws IOException {

    	for(DataManager dm: dataManagers) {
    		dm.storeData(fbsList.getFirst());
    	}
    	
    	for(DataManager dm: testDataManagers) {
    		dm.storeData(fbsList.getLast());
    	}
    	
    }
    
}
