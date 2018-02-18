package application;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * <h1>Booking History Class</h1>
 * 
 * <p>
 * The Bookings History Class contains String Property setter and getter methods which are used 
 * to store details into the bookings history table in the film choice frame. 
 * <p>
 * 
 * @author Donal McLaughlin and German Malsagov
 * @version 1.0.0
 *
 */

public class BookingsHistory {

	StringProperty movieTitle = new SimpleStringProperty();
	StringProperty movieDate = new SimpleStringProperty();
	StringProperty movieTime = new SimpleStringProperty();
	StringProperty rowNumber = new SimpleStringProperty();
	StringProperty colNumber = new SimpleStringProperty();
	StringProperty movieSeatsTotal = new SimpleStringProperty();
	StringProperty movieSeatsTotalChar = new SimpleStringProperty();

	/**
	 * Constructor for movieTitleProperty
	 * Setters and Getters
	 * @return -  StringProperty movie title 
	 */
	public final StringProperty movieTitleProperty() {
		return this.movieTitle;
	}

/**
 * @return Representation of this StringProperty object: movieTitle.
 */
	public final java.lang.String getMovieTitle() {
		return this.movieTitleProperty().get();
	}

/**
 * 
 * @param movieTitle - Set the wrapped value: movieTitle
 */
	public final void setMovieTitle(final java.lang.String movieTitle) {
		this.movieTitleProperty().set(movieTitle);
	}

	/**
	 * Constructor for movieDateProperty
	 * Setters and Getters
	 * @return -  StringProperty movie date 
	 */
	public final StringProperty movieDateProperty() {
		return this.movieDate;
	}

	/**
	 * @return Representation of this StringProperty object: movieDate.
	 */
	public final java.lang.String getMovieDate() {
		return this.movieDateProperty().get();
	}

	/**
	 * 
	 * @param movieDate- Set the wrapped value: movieDate
	 */
	public final void setMovieDate(final java.lang.String movieDate) {
		this.movieDateProperty().set(movieDate);
	}

	/**
	 * Constructor for movieTimeProperty
	 * Setters and Getters
	 * @return -  StringProperty movie time 
	 */
	public final StringProperty movieTimeProperty() {
		return this.movieTime;
	}

	/**
	 * @return Representation of this StringProperty object: movieTime.
	 */
	public final java.lang.String getMovieTime() {
		return this.movieTimeProperty().get();
	}
	/**
	 * 
	 * @param movieTime - Set the wrapped value: movieTime
	 */
	public final void setMovieTime(final java.lang.String movieTime) {
		this.movieTimeProperty().set(movieTime);
	}
	/**
	 * Constructor for rowNumberProperty
	 * Setters and Getters
	 * @return -  StringProperty movie rowNumber 
	 */
	public final StringProperty rowNumberProperty() {
		return this.rowNumber;
	}

	/**
	 * @return Representation of this StringProperty object: movieRowNumber.
	 */
	public final java.lang.String getRowNumber() {
		return this.rowNumberProperty().get();
	}

	/**
	 * 
	 * @param rowNumber - Set the wrapped value: rowNumber
	 */
	public final void setRowNumber(final java.lang.String rowNumber) {
		this.rowNumberProperty().set(rowNumber);
	}

	/**
	 * Constructor for movieColNumberProperty
	 * Setters and Getters
	 * @return -  StringProperty movie colNumber 
	 */
	public final StringProperty colNumberProperty() {
		return this.colNumber;
	}

	/**
	 * @return Representation of this StringProperty object: movieColNumber.
	 */
	public final java.lang.String getColNumber() {
		return this.colNumberProperty().get();
	}
	/**
	 * 
	 * @param colNumber - Set the wrapped value: colNumber
	 */
	
	public final void setColNumber(final java.lang.String colNumber) {
		this.colNumberProperty().set(colNumber);
	}

	/**
	 * Constructor for movieSeatsTotalProperty
	 * Setters and Getters
	 * @return -  StringProperty movie title 
	 */
	public final StringProperty movieSeatsTotalProperty() {
		return this.movieSeatsTotal;
	}

	/**
	 * @return Representation of this StringProperty object: movieSeatsTotal.
	 */
	public final java.lang.String getMoviesSeatsTotal() {
		return this.movieSeatsTotalProperty().get();
	}

	/**
	 * 
	 * @param movieSeatsTotal - Set the wrapped value: movieSeatsTotal
	 */
	public final void setMovieSeatsTotal(final java.lang.String movieSeatsTotal) {
		this.movieSeatsTotalProperty().set(movieSeatsTotal);
	}
	/**
	 * Constructor for movieSeatsTotalCharProperty
	 * Setters and Getters
	 * @return -  StringProperty movie title 
	 */
	public final StringProperty movieSeatsTotalCharProperty() {
		return this.movieSeatsTotalChar;
	}
	/**
	 * @return Representation of this StringProperty object: movieSeatsTotalChar.
	 */
	
	public final java.lang.String getMoviesSeatsTotalChar() {
		return this.movieSeatsTotalCharProperty().get();
	}

	/**
	 * 
	 * @param movieSeatsTotalChar - Set the wrapped value: movieSeatsTotalChar
	 */
	public final void setMovieSeatsTotalChar(final java.lang.String movieSeatsTotalChar) {
		this.movieSeatsTotalCharProperty().set(movieSeatsTotalChar);
	}

}
