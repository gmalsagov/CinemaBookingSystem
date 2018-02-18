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
 * <h1>Validate Payments Details</h1>
 * <p>
 * This class is used to validate correct syntax of payment details in the Payment frame.
 * </p>
 * 
 * @author Donal McLaughlin and German Malsagov
 *
 */


public class ValidatePaymentDetails {
	/**
	 * Validate Name method:
	 * <p>
	 * Ensure that the name only consists of letters only
	 * </p>
	 * @param txtField - name in String form
	 * @return - If the name entered is valid, return true. If not valid, return false with an alert.
	 */
	public static boolean validateName(TextField txtField) {
		Pattern p = Pattern.compile("[a-zA-Z\\s]+");
		Matcher m = p.matcher(txtField.getText());
		if (m.find() && m.group().equals(txtField.getText())) {
			return true;
		} else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Validate Name");
			alert.setHeaderText(null);
			alert.setContentText("Please Enter Valid Name");
			alert.showAndWait();
			
			return false;
		}
	}
	/**
	 * Validate Credit Card Number:
	 * <p>
	 * Ensure that the Credit Card Number consists of letters and spaces only
	 * </p>
	 * @param txtField -credit card number in String form 
	 * @return - If the credit card number entered is valid, return true. If not valid, return false with an alert.
	 */
	public static boolean validateCardNumber(TextField txtField) {
		Pattern p = Pattern.compile("[0-9\\s]+");
		Matcher m = p.matcher(txtField.getText());
		if (m.find() && m.group().equals(txtField.getText()) && txtField.getText().length() == 16) {
			return true;
		} else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Validate Card Number");
			alert.setHeaderText(null);
			alert.setContentText("Please Enter Valid 16-digit Card Number");
			alert.showAndWait();
			
			return false;
		}
	}
	/**
	 * Validate CVV Number:
	 * <p>
	 * Ensure that the CVV Number consists of numbers only.
	 * </p>
	 * 
	 * @param txtField -CVV number in String form 
	 * @return - If the CVV number entered is valid, return true. If not valid, return false with an alert.
	 */
	public static boolean validateCVVNumber(TextField txtField) {
		Pattern p = Pattern.compile("[0-9]+");
		Matcher m = p.matcher(txtField.getText());
		String ccv = txtField.getText();

		if (m.find() && m.group().equals(ccv) && ccv.length() == 3) {
			return true;
		} else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Validate CCV Number");
			alert.setHeaderText(null);
			alert.setContentText("Please Enter Valid CVV Number (3-digit long)");
			alert.showAndWait();
			
			return false;
		}
	}
	/**
	 * Validate Expiry Date Method:
	 * <p>
	 * Ensure that the expiry date consists of numbers only and has a format MM/yy.
	 * </p>
	 * 
	 * @param txtField -expiry date in String form 
	 * @return - If the expiry date entered is valid, return true. If not valid, return false with an alert.
	 * @throws ParseException - displays exception message on console if error occurs during parsing of a string
	 */
	public static boolean validateExpiryDate(TextField txtField) throws ParseException {
		Pattern p = Pattern.compile("^[0-9]{2}/[0-9]{2}$");
		Matcher m = p.matcher(txtField.getText());
		String expDate = txtField.getText();
		
		SimpleDateFormat formatter = new SimpleDateFormat("MM/yy");
		Date expiryFrmt = formatter.parse(expDate);
		
//		System.out.println(expiryFrmt + "and");
//		System.out.println(new Date() + "and");

		if (m.find() && m.group().equals(expDate)) {
			return true;
		} else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Validate Expiry Date");
			alert.setHeaderText(null);
			alert.setContentText("Please Enter Valid Expiry Date");
			alert.showAndWait();
			
			return false;
		}
	}

}
