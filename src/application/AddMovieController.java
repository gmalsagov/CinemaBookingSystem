package application;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.Scanner;

import javax.imageio.ImageIO;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * 
 * <h1>The Add Movie Controller</h1>
 * <p>
 * The Add Movie Controller primarily deals with the employee part of the cinema
 * booking system. Movies can be added to the cinema. Movie details include:
 * movie title, movie image, movie description, movie times, movie start date
 * and movie end date. Details on cinema theatre availability for each day and
 * time for a particular movie can be displayed within the application and
 * exported to a csv file.
 * </p>
 * 
 * @author Donal McLaughlin and German Malsagov
 * @version 1.0.0
 *
 */
public class AddMovieController implements Initializable {
	// Adding movies
	@FXML
	private ImageView imageView;
	@FXML
	private TextField movieName;
	@FXML
	private TextArea movieDescription;
	@FXML
	private TextArea movieTimings;
	@FXML
	private DatePicker startDate;
	@FXML
	private DatePicker endDate;
	@FXML
	private Button empolyeeLogout;
	@FXML
	private Button submitMovie;

	// Viewing movies
	@FXML
	private DatePicker movieDatePickerStaff;
	@FXML
	private ComboBox<String> movieTitleComboStaff;
	@FXML
	private ComboBox<String> movieTimeComboStaff;
	@FXML
	private GridPane gridStaff;
	@FXML
	private Button btnFindMovie;
	@FXML
	private Label lbseatsBooked;
	@FXML
	private Label lbSeatsAvailable;

	// Export movie button
	@FXML
	private Button btnExport;

	private int seatsBookedCounter = 0;
	private int seatsAvailableCounter = 0;

	private SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	private ToggleButton[][] seats = new ToggleButton[5][3];

	private final FileChooser fileChooser = new FileChooser();
	private File file;

	private Stage stage;
	private Parent root;

	/**
	 * Logout Method: When logout buttons are clicked, the user is
	 * returned to the MainEnter frame.
	 * 
	 * @param event
	 *            Scene transition to the main enter frame.
	 * @throws IOException
	 *             if the filename specified is incorrect, an exception error is
	 *             thrown.
	 */
	public void logout(ActionEvent event) throws IOException {

		stage = (Stage) empolyeeLogout.getScene().getWindow();
		root = FXMLLoader.load(getClass().getResource("MainEnter.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	/**
	 * Export Method: Linked with the "Export to csv" button, a csv file
	 * will be generated under the file
	 * path: resources/exportedCinemaCSVfiles/cinema.csv
	 * 
	 * @param event
	 *            Generate a csv file of a list of movie titles, movie dates,
	 *            movie times and seat availability.
	 * @throws IOException
	 *             if the filename specified is incorrect, an exception error is
	 *             thrown.
	 */
	public void export(ActionEvent event) throws IOException {
		// movieTitle,movieDate,movieTime,seatsBooked,seatAvailable
		FileWriter fw = new FileWriter(new File("resources/exportedCinemaCSVfiles/cinema.csv"));
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

		// MainEnterController.loadMovies();
		for (Movie movie : MainEnterController.movies) {
			LocalDate startDate = LocalDate.parse(movie.getMovieStartDate(), formatter);
			LocalDate endDate = LocalDate.parse(movie.getMovieEndDate(), formatter);
			for (LocalDate d = startDate; !d.isAfter(endDate); d = d.plusDays(1)) {
				String[] times = movie.getMovieTimings().split(",");
				for (String time : times) {
					int seatsBooked = 0;
					try {
						String fileName = "resources/bookedMovies/" + movie.getMovieTitle() + "," + d.format(formatter)
								+ "," + time.replace(":", "-") + ".txt";
						Scanner scanner = new Scanner(new File(fileName));

						while (scanner.hasNext()) {
							scanner.nextLine();
							seatsBooked++;
						}
					} catch (Exception e) {
						seatsBooked = 0;
					}

					fw.write(movie.getMovieTitle() + "," + time + "," + d.format(formatter) + ",Seats Booked:"
							+ seatsBooked + ",Seats Available: " + (15 - seatsBooked) + "\n");

				}

			}

		}
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Information");
		alert.setHeaderText(null);
		alert.setContentText("Movies have been exported. File name: resources/exportedCinemaCSVfiles/cinema.csv");
		alert.showAndWait();
		fw.close();
	}

	/**
	 * Initialize Method: For particular frames within the application,
	 * some javafx components must be loaded with information before any event
	 * occurs, thus an initialize method is used.
	 */
	public void initialize(URL fxmlFileLocation, ResourceBundle resourceBundle) {

		// Initialize the AddMovie frame

		if (fxmlFileLocation.getFile().substring(fxmlFileLocation.getFile().lastIndexOf("/") + 1)
				.equalsIgnoreCase("AddMovie.fxml")) {

			// You cannot add a movie to a date before today's date
			startDate.setDayCellFactory(picker -> new DateCell() {
				@Override
				public void updateItem(LocalDate date, boolean empty) {
					super.updateItem(date, empty);
					setDisable(date.isBefore(LocalDate.now()));
					endDate.setValue(null);
				}
			});
			startDate.setEditable(false);

			// You cannot set an end date to a movie before it's start date
			endDate.setDayCellFactory(picker -> new DateCell() {
				@Override
				public void updateItem(LocalDate date, boolean empty) {
					super.updateItem(date, empty);
					setDisable(date.isBefore(startDate.getValue()));
				}
			});
			endDate.setEditable(false);

			// Load movie details
			MainEnterController.loadMovies();

			// Initially disable functionality of the date picker and time combo
			movieDatePickerStaff.setDisable(true);
			movieTimeComboStaff.setDisable(true);

			for (Movie movie : MainEnterController.movies) {
				try {
					// Only display movies that are not ended
					if (!sdf.parse(movie.getMovieEndDate()).before(new Date())) {

						if (!movieTitleComboStaff.getItems().contains(movie.getMovieTitle())) {
							movieTitleComboStaff.getItems().add(movie.getMovieTitle());
						}
						movieTitleComboStaff.getSelectionModel().selectedItemProperty()
								.addListener(new ChangeListener<String>() {
									@Override
									public void changed(ObservableValue<? extends String> selected, String oldItem,
											String newItem) {

										if (movie.getMovieTitle().equalsIgnoreCase(selected.getValue())) {

											movieDatePickerStaff.setDisable(false); // Enable Date Picker
											movieDatePickerStaff.getEditor().clear(); // Clear Date Picker
											movieDatePickerStaff.setValue(null); // Remove any strings within the date picker
											movieTimeComboStaff.getItems().clear(); // Remove all items within the combobox
											// Date formatting
											String[] startDates = movie.getMovieStartDate().split("-");
											String[] endDates = movie.getMovieEndDate().split("-");
											String[] timings = movie.getMovieTimings().split(",");
											movieDatePickerStaff.setDayCellFactory(picker -> new DateCell() {
												@Override
												public void updateItem(LocalDate date, boolean empty) {
													super.updateItem(date, empty);
													// Disable dates within date
													// picker
													setDisable(date.isBefore(LocalDate.now())
															|| date.isBefore(
																	LocalDate.of(Integer.parseInt(startDates[2]),
																			Integer.parseInt(startDates[1]),
																			Integer.parseInt(startDates[0])))
															|| date.isAfter(LocalDate.of(Integer.parseInt(endDates[2]),
																	Integer.parseInt(endDates[1]),
																	Integer.parseInt(endDates[0]))));
													movieTimeComboStaff.setDisable(false); // Enable time combobox
												}
											});
											movieDatePickerStaff.setEditable(false);
											for (int i = 0; i < timings.length; i++) {
												if (!movieTimeComboStaff.getItems().contains(timings[i])) {
													movieTimeComboStaff.getItems().add(timings[i]);
												}
											}

										}

									}

								});
					}
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		}

	};

	/**
	 * Find Movie Method:
	 * <p>
	 * Display the cinema theatre room as a grid of seats. Movie booking files
	 * are scanned and booked seats are highlighted. Labels show the number of
	 * seats booked and the number of seats available.
	 * </p>
	 * 
	 * @param event
	 *            when the movie title combobox, movie date picker and movie
	 *            time combobox are selected (in that order) an array of seats
	 *            identical to the customers view is displayed
	 * @throws IOException
	 *             if the filename specified is incorrect, an exception error is
	 *             thrown.
	 */
	public void findMovie(ActionEvent event) throws IOException {

		// Loading seat icons from image folder
		Image regularEmpty = new Image(getClass().getResourceAsStream("images/empty-seat.png"));
		Image seatOccupied = new Image(getClass().getResourceAsStream("images/taken-seat.png"));
		Image regularSelected = new Image(getClass().getResourceAsStream("images/your-seat.png"));
		Image wheelChairEmpty = new Image(getClass().getResourceAsStream("images/wheelchair-empty.png"));
		Image wheelChairSelected = new Image(getClass().getResourceAsStream("images/wheelchair-occupied.png"));

		// Ensure that the movie details collection components all contain the
		// valid information
		if (movieTitleComboStaff.getSelectionModel().isEmpty() || movieTimeComboStaff.getSelectionModel().isEmpty()
				|| movieDatePickerStaff.getValue().equals(null)) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Warning");
			alert.setHeaderText(null);
			alert.setContentText("Please fill in movie details");
			alert.showAndWait();
			return;
		} else {
			// Set up seats grid
			gridStaff.setPadding(new Insets(5, 0, 5, 0));
			gridStaff.setVgap(0);
			gridStaff.setHgap(0);
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 5; j++) {
					// Add buttons
					ToggleButton seat = new ToggleButton();
					GridPane.setConstraints(seat, j, i);
					gridStaff.getChildren().addAll(seat);

					// Creating final variables to resolve scope problem inside
					// if-else statements
					final int rowGrid = i;
					final int colGrid = j;

					// Setting up icons for empty seats
					if (rowGrid == 0 && (colGrid == 3 || colGrid == 4)) {
						seat.setGraphic(new ImageView(wheelChairEmpty));
					} else {
						seat.setGraphic(new ImageView(regularEmpty));
					}

					seat.setMaxSize(40, 40);

					seats[j][i] = seat;

				}
			}
		}
		// Scan the "movie title, movie date, movie time .txt" file to highlight
		// booked seats
		Scanner scanner;
		try {
			String movieNameStaff = movieTitleComboStaff.getSelectionModel().getSelectedItem();
			String movieTimeStaff = movieTimeComboStaff.getSelectionModel().getSelectedItem();
			String movieDateStaff = movieDatePickerStaff.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
			scanner = new Scanner(new File("resources/bookedMovies/" + movieNameStaff + "," + movieDateStaff + ","
					+ movieTimeStaff.replace(":", "-") + ".txt"));

			System.out.println(movieNameStaff + "," + movieDateStaff + "," + movieTimeStaff.replace(":", "-"));
			while (scanner.hasNext()) {
				String[] line = scanner.nextLine().split(",");
				System.out.println(line);
				seats[Integer.parseInt(line[0])][Integer.parseInt(line[1])].setSelected(true);
				seats[Integer.parseInt(line[0])][Integer.parseInt(line[1])].setDisable(true);
				seats[Integer.parseInt(line[0])][Integer.parseInt(line[1])].setGraphic(new ImageView(seatOccupied));
				seatsBookedCounter++;
			}
			// Calculate available seats
			seatsAvailableCounter = (15 - seatsBookedCounter);
			lbseatsBooked.setText("Seats Booked: " + seatsBookedCounter);
			lbSeatsAvailable.setText(("Seats Available: " + seatsAvailableCounter));

		} catch (FileNotFoundException e) {
			seatsBookedCounter = 0;
			seatsAvailableCounter = (15 - seatsBookedCounter);
			lbseatsBooked.setText("Seats Booked: " + seatsBookedCounter);
			lbSeatsAvailable.setText(("Seats Available: " + seatsAvailableCounter));
			// e.printStackTrace();
		}
	}

	/**
	 * Add Movie Method:
	 * <p>
	 * The Employee can add a new movie to the cinema bookings systems'
	 * collection by filling out textfields, datepickers and uploading images
	 * via filechoosers.
	 * </p>
	 * 
	 * @param event - button fired
	 * @throws IOException if the filename specified is incorrect, an exception error is
	 *             thrown.
	 */
	public void addMovieTransition(ActionEvent event) throws IOException {

		if (this.file != null) {
			String movieName = this.movieName.getText();
			String movieDescription = this.movieDescription.getText();
			String movieTimings = this.movieTimings.getText();
			String filePath = this.file.getAbsolutePath();
			String startDate = this.startDate.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
			String endDate = this.endDate.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
			// Format dates
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

			for (Movie movie : MainEnterController.movies) {
				LocalDate startDateObj = LocalDate.parse(movie.getMovieStartDate(), formatter);
				LocalDate endDateObj = LocalDate.parse(movie.getMovieEndDate(), formatter);
				LocalDate startDateEnteredObj = LocalDate.parse(startDate, formatter);
				LocalDate endDateEnteredObj = LocalDate.parse(endDate, formatter);

				String[] times = movie.getMovieTimings().split(",");

				for (String time : times) {
					System.out.println("Time: " + time);

					if ((!(endDateObj.isBefore(startDateEnteredObj) || startDateObj.isAfter(endDateEnteredObj))
							&& (movieTimings.contains(time)))) {

						System.out.println("getMovie: " + movie.getMovieTimings());
						System.out.println("movieTimings: " + movieTimings);
						System.out.println((movieTimings.contains(time)));
						Alert alert = new Alert(AlertType.CONFIRMATION);
						alert.setTitle("Information");
						alert.setHeaderText(null);
						alert.setContentText("There is already a movie for the same date / time");
						alert.showAndWait();
						return;

					}
				}
			}
			// Write each new movie into the movies.txt file
			FileWriter fw = new FileWriter(new File("resources/movies.txt"), true);
			fw.write(movieName + "\n");
			fw.write(movieDescription + "\n");
			fw.write(movieTimings + "\n");
			fw.write(filePath + "\n");
			fw.write(startDate + "\n");
			fw.write(endDate + "\n");
			fw.close();

			this.movieName.setText("");
			this.movieDescription.setText("");
			this.movieTimings.setText("");
			this.startDate.getEditor().clear();
			this.endDate.getEditor().clear();
			this.imageView.setImage(null);

			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information");
			alert.setHeaderText(null);
			alert.setContentText("Movie has been added");
			alert.showAndWait();
			// Create new movie object
			Movie movie = new Movie();
			movie.setMovieTitle(movieName);
			movie.setMovieDescription(movieDescription);
			movie.setFile(filePath);
			movie.setMovieEndDate(endDate);
			movie.setMovieStartDate(startDate);
			movie.setMovieTimings(movieTimings);
			MainEnterController.movies.add(movie);
		} else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Error");
			alert.setHeaderText(null);
			alert.setContentText("Please ensure an image file is selected");
			alert.showAndWait();

		}

	}

	/**
	 * Upload Images Method: A method used to navigate the user's
	 * computer's file explorer to upload an image onto the system booking
	 * system application.
	 * 
	 * @param event
	 *            Import a movie image into the application
	 * @throws IOException An exception is thrown when a file is not chosen
	 */
	public void uploadImages(ActionEvent event) throws IOException {
		file = fileChooser.showOpenDialog(new Stage());
		BufferedImage bf = ImageIO.read(file);
		Image image = SwingFXUtils.toFXImage(bf, null);

		imageView.setImage(image);

	}

}
