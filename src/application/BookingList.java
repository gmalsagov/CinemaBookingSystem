package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * <h1>Booking List Class</h1>
 * 
 * <p>
 * For a particular instance, this class is used to retrieve booking details.
 * </p>
 * @author Donal McLaughlin and German Malsagov
 * @version 1.0.0
 *
 */
public class BookingList {
	private ArrayList<Booking> bookings = new ArrayList<Booking>();
	private static BookingList instance;
	
	 /**
	  * Instance Getter Method
	  * @return instance
	  */
	public static BookingList getInstance() {
		if (instance == null) {
			instance = new BookingList();
		}
		return instance;
	}

	/**
	 * Get movie title, movie date and movie time for a particular instance
	 * @return bookings
	 */
	public ArrayList<Booking> getBookingList() {
		return bookings;
	}
	/**
	 * Load Movie details from bookings.txt file
	 */
	public void loadData() {
		try {
			Scanner scanner = new Scanner(new File("bookings.txt"));
			while (scanner.hasNext()) {
				String line = scanner.nextLine();
				String[] tokens = line.split(",");
				Booking booking = new Booking();
				booking.setSelectedMovieTitle(tokens[0]);
				booking.setSelectedMovieDate(tokens[1]);
				booking.setSelectedMovieTime(tokens[2]);
				this.bookings.add(booking);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
