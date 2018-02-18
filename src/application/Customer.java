package application;

/**
 * <h1>Customer Class</h1>
 * <p>
 * Each customer has their own firstname, surname, email, username, password and
 * Date of Birth (dob). This class consists of a series of setters and getters
 * used to retrieve and update details on each customer.
 * </p>
 * 
 * @author Donal McLaughlin and German Malsagov
 * @version 1.0.0
 */
public class Customer {
	/**
	 * Customer instance variables
	 */
	private String firstName;
	private String surname;
	private String email;
	private String username;
	private String password;
	private String dob;

	/**
	 * Default Customer Constructor .
	 */
	public Customer() {

	}

	/**
	 * Setters and Getters for customer details
	 * @return firstName , surname, email, username, password, date of birth
	 */
	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSurname() {
		return this.surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDob() {
		return this.dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

}
