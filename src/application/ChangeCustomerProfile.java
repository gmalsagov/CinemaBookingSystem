package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * <h1>Change Customer Profile Class</h1>
 * 
 * <p>
 * This class scans the cinemaUser.txt file and rewrites it with any changes
 * specified by the user in the ChangeDetails frame. Reference:
 * 
 * </p>
 * Reference:
 * http://javaconceptoftheday.com/modify-replace-specific-string-in-text-file-in-java/
 * 
 * @author Donal McLaughlin and German Malsagov
 * @version 1.0.0
 *
 */

public class ChangeCustomerProfile {

	/**
	 * 
	 * Modify file method:
	 * 
	 * This method scans the cinemaUsers.txt file and rewrites the file with the
	 * same name but with changes to the customer's details.
	 * 
	 * @param filePath
	 *            - of the cinemaUsers.txt file: /resources/cinemaUsers.txt
	 * @param oldString
	 *            - old user details
	 * @param newString
	 *            - details changed by the user
	 */
	public static void modifyFile(String filePath, String oldString, String newString) {
		File fileToBeModified = new File(filePath);
		String oldContent = "";
		BufferedReader reader = null;
		FileWriter writer = null;

		try {
			reader = new BufferedReader(new FileReader(fileToBeModified));
			String line = reader.readLine();

			while (line != null) {
				//Read old cinemaUsers.txt file and assign to oldContent
				oldContent = oldContent + line + System.lineSeparator();

				line = reader.readLine();
			}

			
			// Replace the old details with the new details

			String newContent = oldContent.replaceAll(oldString, newString);

			// Rewrite the new file with the changed details

			writer = new FileWriter(fileToBeModified);

			writer.write(newContent);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {

			try {
				// Close files
				reader.close();
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}