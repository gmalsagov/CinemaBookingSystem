package application;
/**
 * <h1>Booking Class</h1>
 * <p>
 * Each booking contains a movie title, movie date and movie time. This class consists of a series of setters and getters
 * used to retrieve and update details on each booking.
 * </p>
 * 
 * @author Donal McLaughlin and German Malsagov
 * @version 1.0.0 
 *
 */
public class Booking {
	/**
	 * Booking instance variables
	 */
	private String selectedMovieTitle ;
	private String selectedMovieDate;
	private String selectedMovieTime;
	
	/**
	 * Default Booking Constructor 
	 */
	public Booking() {
		
	}
	
	/**
	 * Getter to get value of selected movie title
	 * @return - String for Selected Movie Title
	 */
	public String getSelectedMovieTitle() {
		return this.selectedMovieTitle;
	}
	
	/**
	 * Setter to set value of selected movie title
	 *@param selectedMovieTitle - String containing value of selected movie title 
	 */
	public void setSelectedMovieTitle(String selectedMovieTitle) {
		this.selectedMovieTitle = selectedMovieTitle;
	}
	/**
	 * Getter to get value of selected movie time
	 * @return - String for Selected Movie Time
	 */
	public String getSelectedMovieTime() {
		return this.selectedMovieTime;
	}
	
	/**
	 * Setter to set value of selected movie time
	 * @param selectedMovieTime - String containing value of selected movie time 
	 */
	public void setSelectedMovieTime(String selectedMovieTime) {
		this.selectedMovieTime = selectedMovieTime;
	}
	/**
	 * Getter to get value of selected movie date
	 * @return - String for Selected Movie Date
	 */
	public String getSelectedMovieDate() {
		return this.selectedMovieDate;
	}
	/**
	 * Setter to set value of selected movie date
	 * @param selectedMovieDate - String containing value of selected movie date
	 */
	public void setSelectedMovieDate(String selectedMovieDate) {
		this.selectedMovieDate = selectedMovieDate;
	}
		
	
}
