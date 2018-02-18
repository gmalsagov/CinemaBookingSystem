package application;

/**
 * <h1>Movie Class</h1>
 * 
 * <p>
 * A series of setters and getters used for retrieving and updating details on each movie within the cinema.
 * </p>
 * @author Donal McLaughlin and German Malsagov
 *
 */
public class Movie {
	private String movieTitle;
	private String movieDescription;
	private String movieTimings;
	private String file;
	private String movieStartDate;
	private String movieEndDate;
	/**
	 * Empty Movie Construtor used to create new instances of movie details as a means of reflection.
	 */
	public Movie() {
		
	}
	/**
	 * 
	 * Setters and Getters for Movie objects
	 * @param movieTitle - set Movie Title 
	 */
	
	public void setMovieTitle(String movieTitle) {
		this.movieTitle = movieTitle;
	}
	
	public String getMovieTitle() {
		return this.movieTitle;
	}
	
	public void setMovieDescription(String movieDescription) {
		this.movieDescription = movieDescription;
	}
	
	public String getMovieDescription() {
		return this.movieDescription;
	}
	
	public void setMovieTimings(String movieTimings) {
		this.movieTimings = movieTimings;
	}
	
	public String getMovieTimings() {
		return this.movieTimings;
	}
	
	public void setFile(String file) {
		this.file = file;
	}
	
	public String getFile() {
		return this.file;
	}
	
	public void setMovieStartDate(String movieStartDate) {
		this.movieStartDate = movieStartDate;
	}
	
	public String getMovieStartDate() {
		return this.movieStartDate;
	}
	
	public void setMovieEndDate(String movieEndDate) {
		this.movieEndDate = movieEndDate;
	}
	
	public String getMovieEndDate() {
		return this.movieEndDate;
	}
}
