package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

/**
 * <h1>Sign Up Validation Class</h1>
 * <p>
 * This class is used to validate correct syntax for the sign up details. 
 * This class is also used to validate any changes to the customer profile details
 * </p>
 * 
 * @author Donal McLaughlin and German Malsagov
 * @version 1.0.0
 * 
 *
 */



public class SignUpValidation {
	
	
	/**
	 * 
	 * The validateFirstName method ensures that the user must enter their first name with letters only
	 * 
	 * @param firstName string
	 * @return If the first name entered is valid, return true. If not valid, return false with an alert.
	 */
	public static boolean validateFirstName(String firstName) {
		Pattern p = Pattern.compile("[a-zA-Z]+");
		Matcher m = p.matcher(firstName);
		if (m.find() && m.group().equals(firstName)) {
			return true;
		} else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Validate First Name");
			alert.setHeaderText(null);
			alert.setContentText("Please Enter Valid First Name. Must contain letters only.");
			alert.showAndWait();
			
			return false;
		}
	}
	
	/**
	 * 
	 * The validateSurname method ensures that the user must enter their surname with letters only
	 * 
	 * @param surname string
	 * @return If the surname entered is valid, return true. If not valid, return false with an alert.
	 */
	public static boolean validateSurname(String surname) {
		Pattern p = Pattern.compile("[a-zA-Z]+");
		Matcher m = p.matcher(surname);
		if (m.find() && m.group().equals(surname)) {
			return true;
		} else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Validate Surname");
			alert.setHeaderText(null);
			alert.setContentText("Please Enter Valid Surname");
			alert.showAndWait();
			
			return false;
		}
	}
	
	/**
	 * 
	 * The validateEmail method ensures that the user must enter their email address with particular validation rules set using a regular expression
	 * 
	 * Reference for email Regex: https://www.mkyong.com/regular-expressions/how-to-validate-email-address-with-regular-expression/
	 * 
	 * @param email string 
	 * @return If the surname entered is valid, return true. If not valid, return false with an alert.
	 */


	public static boolean validateEmail(String email) {
		Pattern p = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
		Matcher m = p.matcher(email);
		if (m.find() && m.group().equals(email)) {
			return true;
		} else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Validate Email");
			alert.setHeaderText(null);
			alert.setContentText("Please Enter Valid Email Address");
			alert.showAndWait();
			
			return false;
		}
	}
	
	/**
	 * 
	 * The validateUser method ensures that the user must create a username with the correct characteristics:
	 * Must be a minimum of 6 characters and contains only:letters, numbers, ".", "_" , "-" . 
	 * 
	 * 
	 * @param username string
	 * @return If the username entered is valid, return true. If not valid, return false with an alert.
	 */

	public static boolean validateUsername(String username) {
		Pattern p = Pattern.compile("^[a-zA-Z0-9._\\-]{6,}$");
		Matcher m = p.matcher(username);
		if (m.find() && m.group().equals(username)) {
			return true;
		} else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Validate Username");
			alert.setHeaderText(null);
			alert.setContentText("Please Enter Valid Username. Your username must be a minimum of 6 characters and contain only: letters, numbers, '.', '_' , '-'.");
			alert.showAndWait();
			
			return false;
		}
	}
	
	/**
	 * 
	 * Similarly to the username, the password must be a minimum of 6 characters and contain only:letters, numbers, ".", "_" , "-".
	 * 
	 * @param password - stores value of user's password. Used to check if password contains only valid characters and symbols
	 * @return If the password entered is valid, return true. If not valid, return false with an alert.
	 */

	public static boolean validatePassword(String password) {
		Pattern p = Pattern.compile("^[a-zA-Z0-9._\\-]{6,}$");
		Matcher m = p.matcher(password);
		if (m.find() && m.group().equals(password)) {
			return true;
		} else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Validate Password");
			alert.setHeaderText(null);
			alert.setContentText("Please Enter Valid Password. Your password must be a minimum of 6 characters and contain only: letters, numbers, '.', '_' , '-'.");
			alert.showAndWait();
			
			return false;
		}
	}
	
	/**
	 * Ensure that the user's date of birth is before today's date
	 * 
	 * @param dobStr - to store Date of Birth. Used to check if input date of birth is valid
	 * @return If the date of birth entered is valid, return true. If not valid, return false with an alert.
	 * @throws ParseException - displays exception message if error occurs during parsing String
	 */
	public static boolean validateDobStr(String dobStr) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		
		Date dateStr = formatter.parse(dobStr);
		if (dateStr.before(new Date())) {
		   return true;
		} else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Validate Date of Birth");
			alert.setHeaderText(null);
			alert.setContentText("Please The Valid Format For Your Date Of Birth");
			alert.showAndWait();
			
			return false;
		}
		

	}
}
