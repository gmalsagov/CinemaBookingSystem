package application;

import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.net.URL;
import java.nio.file.Files;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.stream.Collectors;

import javax.imageio.ImageIO;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * <h1>MainEnterController</h1>
 * 
 * <p>
 * The Main Enter Controller contains the majority of functionalities of this
 * cinema booking software concerning the customer user interface. For the
 * customers point-of-view, this controller class generates text files, accesses
 * text files and calls methods from other classes. Methods in this controller
 * are used to validate user input and to store the user input into text files.
 * An initializer method is used to preload data into javafx containers
 * (labels/comboboxes) for multiple frames.

 * <p>
 * 
 * @author German Malsagov and Donal McLaughlin
 * @version 1.0.0
 */
public class MainEnterController implements Initializable {

	static Stage stage;
	static Parent root;

	private ArrayList<Customer> customers;
	private ArrayList<Booking> bookings;
	
	public static ArrayList<Movie> movies = new ArrayList<Movie>();
	
	//Main Frame
	@FXML
	private Button btnCustForm;
	@FXML
	private Button btnCustSignIn;
	@FXML
	private Button btnStaffSignIn;
	
	@FXML
	private Button btnCustLoginToBooking;
	@FXML
	private Button btnCustToSeatBooking;
	@FXML
	private Button btnCustToTicketBooking;
	@FXML
	private Button btnCustSignUpConfirmed;
	@FXML
	private Button btnGoBack;
	@FXML
	private Button btnToBookingSummary;
	@FXML
	private Button btnToPayment;
	@FXML
	private Button btnToConfirmPayment;
	@FXML
	private Button btnEmployeeLogin;
	@FXML
	private Button btnLogOut;
	@FXML
	private Button btnChangeDetails;
	@FXML
	private Button btnCancel;
	
	// To store the sign up process
	@FXML
	private TextField txtFirstName;
	@FXML
	private TextField txtSurname;
	@FXML
	private TextField txtEmail;
	@FXML
	private TextField txtUsername;
	@FXML
	private PasswordField txtPassword;
	
	//Change Details
	@FXML
	private TextField txtFirstNameChange;
	@FXML
	private TextField txtSurnameChange;
	@FXML
	private TextField txtEmailChange;
	@FXML
	private TextField txtUsernameChange;
	@FXML
	private PasswordField txtPasswordChange;
	@FXML
	private DatePicker dobChange;
	@FXML
	private Button btnSubmitChanges;


	@FXML
	private DatePicker dob;

	// Sign up Validation
	@FXML
	private Label lbFirstname;
	@FXML
	private Label lbSurname;
	@FXML
	private Label lbDob;
	@FXML
	private Label lbUsername;
	@FXML
	private Label lbPassword;
	@FXML
	private Label lbEmail;

	// To store the customer login process
	@FXML
	private TextField usernameTextField;
	@FXML
	private PasswordField passwordTextField;
	@FXML
	private Label lbUsernameWelcome;

	// To store ticket booking process
	
	// ArrayList of Ticket Prices (Adult, Child, Senior, Student)
	ObservableList<? extends Number> price = FXCollections.observableArrayList(13.25, 6.95, 10.75, 10.75);
	
	@FXML
	private ComboBox<Integer> comboAdult;
	@FXML
	private ComboBox<Integer> comboChild;
	@FXML
	private ComboBox<Integer> comboSenior;
	@FXML
	private ComboBox<Integer> comboStudent;
	@FXML
	private Label labelAdult;
	@FXML
	private Label labelChild;
	@FXML
	private Label labelSenior;
	@FXML
	private Label labelStudent;
	@FXML
	private Label labelTotal;
	@FXML
	private Label labelPriceA;
	@FXML
	private Label labelPriceC;
	@FXML
	private Label labelPriceSe;
	@FXML
	private Label labelPriceSt;
	@FXML
	private double subTotal1;
	@FXML
	private double subTotal2;
	@FXML
	private double subTotal3;
	@FXML
	private double subTotal4;
	@FXML
	private double subTotal;
	private static int value1;
	private static int value2;
	private static int value3;
	private static int value4;
	@FXML
	private Label Quantity;
	@FXML
	private Label lbAvailableSeats;

	// Variables to store booking information
	private static int quantityTotal;
	private static double totalCost;
	
	// To store seat booking process
	@FXML
	private GridPane grid = new GridPane();
	@FXML
	private Label seatsSelected;
	@FXML
	private static ToggleButton[][] seats = new ToggleButton[5][3];
	private static int counterSeats;
	private static int wheelchairCount;
	@FXML
    private ImageView seatSelected;
	@FXML
    private ImageView seatTaken;
	@FXML
    private ImageView seatEmpt;
	@FXML
	private Label labelSubtotalSeat;


	// To store the employee login process
	@FXML
	private TextField staffUsername;
	@FXML
	private PasswordField staffPassword;


	// To store the movie listings
	@FXML
	private ComboBox<String> movieCombo;
	@FXML
	private Label labelMovieDescription;
	@FXML
	private ImageView imageMovie;
	@FXML
	private DatePicker selectDate;
	@FXML
	private ComboBox<String> selectTime;
	@FXML
	private Label labelSelectedSeats;

	@FXML
	private DatePicker movieDatePicker;

	@FXML
	private TextArea bookingsHistory;
	@FXML
	private TableView<BookingsHistory> tableView;
	@FXML
	private Button deleteBookingButton;
	
	private SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

	private static String userName;
	//removed private
	private static String movieName;
	private static String movieTime;
	private static String movieDate;
	private static String firstName;
	
	private static String userProfile;
	private static String userProfileChanged;

	
	// Booking Summary page
	@FXML
	private Label labelMovieName;
	@FXML
	private Label labelDateSummary;
	@FXML
	private Label labelSeatsFinal;
	@FXML
	private Label labelTicketTotal;
	@FXML
	private Label labelTotalCost;
	@FXML
	private Button btnPromo;
	private static Boolean promoValid;
	private static int countPromo = 0;
	
	private static String storedPromo;
	static ObservableList<String> selectedSeatsSummary = FXCollections.observableArrayList();
	private static String seatsForSetter;
	
	
	// Payment Page
	@FXML
	private ComboBox<String> comboPaymentMethods;
	@FXML
	private TextField txtCardName;
	@FXML
	private TextField txtCardNumber;
	@FXML
	private TextField txtExpiryDate;
	@FXML
	private TextField txtCvvNumber;
	
	
	// Payment Confirmed Page
	@FXML
	private Button btnHome;
	@FXML
	private Button btnExit;
	
	/**
	 * Getters and Setters
	 * @param quantityTotal - variable to store total number of selected tickets
	 */
	public static void setQuantityTotal(int quantityTotal) {
		MainEnterController.quantityTotal = quantityTotal;
	}

	public static int getQuantityTotal() {
		return quantityTotal;
	}
	
	public static void setSelectedSeatsSummary(String seats) {
		
		MainEnterController.seatsForSetter = seats;
	}
	public static String getSelectedSeatsSummary() {
		
		return seatsForSetter;
	}
	

	/*
	 * (non-Javadoc)
	 * @see javafx.fxml.Initializable#initialize(java.net.URL, java.util.ResourceBundle)
	 * 
	 * This method initializes all contents once cinema booking system is lauched.
	 * This includes comboboxes, labels, 
	 */
	
	/**
	 * Initialize method:
	 * This method initializes all contents once cinema booking system is launched. This includes comboboxes and labels.
	 * 
	 * <br>References: <br>Disable datepicker dates :https://docs.oracle.com/javase/8/javafx/user-interface-tutorial/date-picker.htm
	 */
	public void initialize(URL fxmlFileLocation, ResourceBundle resourceBundle) {
		
		//Change Profile Details, first insert original details then alter textboxes and submit changes
		if (fxmlFileLocation.getFile().substring(fxmlFileLocation.getFile().lastIndexOf("/") + 1)
				.equalsIgnoreCase("ChangeDetails.fxml")) {
			
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
						 
			customers = CustomerList.getInstance().getCustomerList();
			for (Customer customer : customers) {
				if (customer.getUsername().equals(MainEnterController.userName)){
				txtFirstNameChange.setText(customer.getFirstName());
				txtSurnameChange.setText(customer.getSurname());
				txtEmailChange.setText(customer.getEmail());
				txtUsernameChange.setText(customer.getUsername());
				txtPasswordChange.setText(customer.getPassword());
				dobChange.setValue(LocalDate.parse(customer.getDob(), formatter));

				}

			}

			customers = null;

		}
		
		//Initialize the seat booking page
		if (fxmlFileLocation.getFile().substring(fxmlFileLocation.getFile().lastIndexOf("/") + 1)
				.equalsIgnoreCase("SeatBooking.fxml")) {
			grid.setPadding(new Insets(5, 5, 5, 5));
			grid.setVgap(0);
			grid.setHgap(0);
			
			// Loading seat icons from image folder
			Image regularEmpty = new Image(getClass().getResourceAsStream("images/empty-seat.png"));
			Image seatOccupied = new Image(getClass().getResourceAsStream("images/taken-seat.png"));
			Image regularSelected = new Image(getClass().getResourceAsStream("images/your-seat.png"));
			Image wheelChairEmpty = new Image(getClass().getResourceAsStream("images/wheelchair-empty.png"));
			Image wheelChairSelected = new Image(getClass().getResourceAsStream("images/wheelchair-occupied.png"));

			labelSubtotalSeat.setText("£" + String.format("%.2f", totalCost));
			
			// Interating over seats array
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 5; j++) {

					ToggleButton seat = new ToggleButton();
					GridPane.setConstraints(seat, j, i);
					grid.getChildren().addAll(seat);
					
					// Creating final variables to resolve scope problem inside if-else statements
					final int rowGrid = i;
					final int colGrid = j;
					
					// Setting up icons for empty seats
					if (rowGrid == 0 && (colGrid == 3 || colGrid == 4)) {
						seat.setGraphic(new ImageView(wheelChairEmpty));
					} else {
					seat.setGraphic(new ImageView(regularEmpty));
					}
					
					seat.setMaxSize(40, 40);
					seat.setOnAction((ActionEvent e) -> {
						
						
						// selected seat (regular and wheelchair)
						if (seat.isSelected()) {
							
							// Counting number of selected seats
							counterSeats++;
							
							if (rowGrid == 0 && (colGrid == 3 || colGrid == 4)) {
								seat.setGraphic(new ImageView(wheelChairSelected));
								wheelchairCount++;
								System.out.println(wheelchairCount);
								Alert alert = new Alert(AlertType.CONFIRMATION);
								alert.setTitle("WheelChair Seat Selected");
								alert.setHeaderText(null);
								alert.setContentText("YOU'VE SELECTED A WHEELCHAIR SPACE\n\n"
										+ "If you need a carer seat, enter your CEA number as"
										+ " a promo code when choosing your tickets and a carer"
										+ " seat will be added to your booking free of charge. "
										+ "You can reserve one carer seat per wheelchair space.");
								alert.showAndWait().ifPresent(response -> {
								     if (response == ButtonType.CANCEL) {
								    	 
								    	 	// Unselect seat if user pressed cancel
								    	 	seat.setSelected(false);
								    	 	seat.setGraphic(new ImageView(wheelChairEmpty));
								    	 	counterSeats--;
										wheelchairCount--;
								     }
								 });
								
							} else {
								seat.setGraphic(new ImageView(regularSelected));
							}
						
							labelSelectedSeats.setText(String.valueOf(counterSeats) + " of "
									+ String.valueOf(getQuantityTotal()) + " selected");
							
						} else {
							// empty seats (regular and wheelchair
							if (rowGrid == 0 && (colGrid == 3 || colGrid == 4)) {
								seat.setGraphic(new ImageView(wheelChairEmpty));
							} else {
							seat.setGraphic(new ImageView(regularEmpty));
							}
							--counterSeats;
							labelSelectedSeats.setText(String.valueOf(counterSeats) + " of "
									+ String.valueOf(getQuantityTotal()) + " selected");
						}
						
						// Alerting user when he/she exceeds number of selected tickets
						if (counterSeats > quantityTotal) {
							Alert alert = new Alert(AlertType.WARNING);
							alert.setTitle("Confirmation");
							alert.setHeaderText(null);
							alert.setContentText("You exceeded total number of tickets selected");
							alert.showAndWait();
							if (rowGrid == 0 && (colGrid == 3 || colGrid == 4)) {
								seat.setGraphic(new ImageView(wheelChairEmpty));
							} else {
								seat.setGraphic(new ImageView(regularEmpty));
							}
							--counterSeats;
							seat.setSelected(false);
							
							labelSelectedSeats.setText(String.valueOf(counterSeats) + " of "
									+ String.valueOf(getQuantityTotal()) + " selected");
						}
					});
					seats[j][i] = seat;

				}
			}
			Scanner scanner;
			try {
				scanner = new Scanner(new File("resources/bookedMovies/" + MainEnterController.movieName + "," + MainEnterController.movieDate + ","
						+ MainEnterController.movieTime.replace(":", "-") + ".txt"));

				while (scanner.hasNext()) {
					String[] line = scanner.nextLine().split(",");
					seats[Integer.parseInt(line[0])][Integer.parseInt(line[1])].setSelected(true);
					seats[Integer.parseInt(line[0])][Integer.parseInt(line[1])].setDisable(true);
					seats[Integer.parseInt(line[0])][Integer.parseInt(line[1])].setGraphic(new ImageView(seatOccupied));
				}
			} catch (FileNotFoundException e) {
				
			}

		}
		
		//Set up film choice frame
		if (fxmlFileLocation.getFile().substring(fxmlFileLocation.getFile().lastIndexOf("/") + 1)
				.equalsIgnoreCase("FilmChoiceFrame.fxml")) {
			
			
				lbUsernameWelcome.setText("Hello " + MainEnterController.userName);
		
				    //Fill in booking history table
				tableView.setPlaceholder(new Label("Your bookings will display here"));
				        Collection<BookingsHistory> list;
						try {
							list = Files.readAllLines(new File("resources/customerBookings/" + MainEnterController.userName + ".txt").toPath())
							                .stream()
							                .map(line -> {
							                    String[] details = line.split(";");
							                    BookingsHistory bh = new BookingsHistory();
							                    bh.setMovieTitle(details[0]);
							                    bh.setMovieDate(details[1]);
							                    bh.setMovieTime(details[2]);
							                    bh.setMovieSeatsTotal(details[3]);
							                    bh.setMovieSeatsTotalChar(details[4]);
							                    return bh;
							                })
							                .collect(Collectors.toList());
						

				        ObservableList<BookingsHistory> details = FXCollections.observableArrayList(list);

				       // TableView<BookingsHistory> tableView = new TableView<>();
				        TableColumn<BookingsHistory, String> col1 = new TableColumn<>("Movie Title");
				        TableColumn<BookingsHistory, String> col2 = new TableColumn<>("Date");
				        TableColumn<BookingsHistory, String> col3 = new TableColumn<>("Time");
				        TableColumn<BookingsHistory, String> col4 = new TableColumn<>("Booked Seats");
				        TableColumn<BookingsHistory, String> col5 = new TableColumn<>("Booked Seats Code");

				        tableView.getColumns().addAll(col1, col2, col3, col4, col5);
				      
				        col1.setCellValueFactory(data -> data.getValue().movieTitleProperty());
				        col2.setCellValueFactory(data -> data.getValue().movieDateProperty());
				        col3.setCellValueFactory(data -> data.getValue().movieTimeProperty());
				        col4.setCellValueFactory(data -> data.getValue().movieSeatsTotalProperty());
				        col5.setCellValueFactory(data -> data.getValue().movieSeatsTotalCharProperty());
				        
				        // Hiding column with indecies
				        col5.setVisible(false);
				        tableView.setItems(details);
				        
				        // Hiding extra column
				        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
				        
				        
				        //Delete Booking Button
				        deleteBookingButton.setOnAction(e -> {
				        	
				        	
				            BookingsHistory selectedItem = tableView.getSelectionModel().getSelectedItem();
				            
				            DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH-mm");
				    		
				    			String date1Str = selectedItem.getMovieDate() + " " + selectedItem.getMovieTime();
				    			Date date1 = null;
				    				try {
				    					date1 = dateFormat.parse(date1Str);
				    				} catch (ParseException e2) {
				    					System.out.println("Cant parse date");
				    					// TODO Auto-generated catch block
				    					e2.printStackTrace();
				    				}
				    		
				    				//Today's date
				    				Date dateToday = new Date();
				    	
					    		System.out.println(dateFormat.format(dateToday));
					    		System.out.println(dateFormat.format(date1));
					    		
					    		if (dateToday.after(date1)){
					    			
					    			Alert alert1 = new Alert(AlertType.WARNING);
					    			alert1.setTitle("WARNING");
					    			alert1.setHeaderText(null);
					    			alert1.setContentText("You cannot remove a booking after the movie show time");
					    			alert1.showAndWait();
					    			
					    		} else {
					    			
					    				Alert alert = new Alert(AlertType.CONFIRMATION);
									alert.setTitle("CONFIRMATION");
									alert.setHeaderText(null);
									alert.setContentText("Are you sure you want to delete this booking?");
									alert.showAndWait().ifPresent(response -> {
										
									     if (response == ButtonType.OK) {
									    	 
									    	 
									    	 tableView.getItems().remove(selectedItem);
									            
									            //Remove Seats and details from *username*.txt
									            Writer writer = null;
									            Writer writer2 = null;
									            try {
									                File file = new File("resources/customerBookings/" + MainEnterController.userName + ".txt");
									                File file2 = new File("resources/bookedMovies/" + selectedItem.getMovieTitle() + "," + selectedItem.getMovieDate() + "," + selectedItem.getMovieTime() + ".txt");
									                writer = new BufferedWriter(new FileWriter(file));
									                writer2 = new BufferedWriter(new FileWriter(file2));
									                
									                for (BookingsHistory bookings : details) {
									                	
									                	if (selectedItem.getMovieTitle().equals(bookings.getMovieTitle()) && selectedItem.getMovieDate().equals(bookings.getMovieDate()) && selectedItem.getMovieTime().equals(bookings.getMovieTime())){
									                	String[] seatsa = bookings.getMoviesSeatsTotalChar().split(" ");
									                	String seatsReformed = "";
									                	for (int i = 0; i< seatsa.length; i++){
									                		seatsReformed += seatsa[i] + " \n";
									                	}
									                	System.out.println(seatsReformed);
									                	
									                    String text2 = seatsReformed.replaceAll(" ","");
									                    writer2.write(text2);
									                	}
									                	String text = bookings.getMovieTitle() + ";" + bookings.getMovieDate() + ";"
									                				+ bookings.getMovieTime() + ";" + bookings.getMoviesSeatsTotal() + ";"
									                				+ bookings.getMoviesSeatsTotalChar() + "\n";
									                	 writer.write(text);
									                }
									            } catch (Exception ex) {
									                ex.printStackTrace();
									            }
									            
									            finally {
									            	
									                try {
														writer.flush();
														 writer.close();
														 writer2.flush();
														 writer2.close();
													} catch (IOException e1) {
														// TODO Auto-generated catch block
														e1.printStackTrace();
													}
									            }
									            
									            Alert alert2 = new Alert(AlertType.INFORMATION);
												alert2.setTitle("CONFIRMATION");
												alert2.setHeaderText(null);
												alert2.setContentText("Booking removed successfully!");
												alert2.showAndWait();
									            
									            
									    	 
									     }
									}); // End of response clause
					    		}
				        }); // End of delete clause   
				        
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}


			loadMovies();
			
			//Disable datepickers and comboboxes without movie title
			 movieDatePicker.setDisable(true); //
			 selectTime.setDisable(true); //

			for (Movie movie : movies) {
				try {
					// Only display movies that are not ended
					if (!sdf.parse(movie.getMovieEndDate()).before(new Date())) {
						
						if (!movieCombo.getItems().contains(movie.getMovieTitle())){
						movieCombo.getItems().add(movie.getMovieTitle());
						}
						movieCombo.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
							@Override
							public void changed(ObservableValue<? extends String> selected, String oldItem,
									String newItem) {

								if (movie.getMovieTitle().equalsIgnoreCase(selected.getValue())) {
									movieDatePicker.setDisable(false); //
									movieDatePicker.getEditor().clear(); //
									movieDatePicker.setValue(null); //
									selectTime.getItems().clear(); // 
									labelMovieDescription.setText(movie.getMovieDescription());
									BufferedImage bf;
									try {
										bf = ImageIO.read(new File(movie.getFile()));
										Image image = SwingFXUtils.toFXImage(bf, null);
										imageMovie.setImage(image);
									} catch (IOException e) {
										e.printStackTrace();
									}

									String[] startDates = movie.getMovieStartDate().split("-");
									String[] endDates = movie.getMovieEndDate().split("-");
									String[] timings = movie.getMovieTimings().split(",");
									movieDatePicker.setDayCellFactory(picker -> new DateCell() {
										@Override
										public void updateItem(LocalDate date, boolean empty) {
											super.updateItem(date, empty);

											setDisable(date.isBefore(LocalDate.now())
													|| date.isBefore(LocalDate.of(Integer.parseInt(startDates[2]),
															Integer.parseInt(startDates[1]),
															Integer.parseInt(startDates[0])))
													|| date.isAfter(LocalDate.of(Integer.parseInt(endDates[2]),
															Integer.parseInt(endDates[1]),
															Integer.parseInt(endDates[0]))));
											selectTime.setDisable(false); //
										}
									});
									movieDatePicker.setEditable(false);
									for (int i = 0; i < timings.length; i++) {
										if (!selectTime.getItems().contains(timings[i])){
										selectTime.getItems().add(timings[i]);
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
			
		}//end setup film choice frame
		

		// Setting up TicketBooking page
		if (fxmlFileLocation.getFile().substring(fxmlFileLocation.getFile().lastIndexOf("/") + 1)
				.equalsIgnoreCase("TicketBooking.fxml")) {
			
			int seatsBooked = 0;
            
            Scanner scanner;
            
                try {
                    scanner = new Scanner(new File("resources/bookedMovies/" + MainEnterController.movieName + "," + MainEnterController.movieDate + ","
                            + MainEnterController.movieTime.replace(":", "-") + ".txt"));
                    
                    while (scanner.hasNext()) {
                        scanner.nextLine();
                        seatsBooked++;
                    }
                    lbAvailableSeats.setText(String.valueOf(15 - seatsBooked));
                } catch (FileNotFoundException e) {
                		lbAvailableSeats.setText(String.valueOf(15));
                }
			
			ObservableList<Integer> quantity = FXCollections.observableArrayList();

			// Populating quantity comboboxes with values
			for (int i = 0; i < 10; i++) {
				quantity.add(i);
			}
			
			// Ensure all combos are empty
			comboAdult.getItems().removeAll();
			comboChild.getItems().removeAll();
			comboSenior.getItems().removeAll();
			comboStudent.getItems().removeAll();
			
			// Populate combos with quantity values
			comboAdult.getItems().addAll(quantity);
			comboChild.getItems().addAll(quantity);
			comboSenior.getItems().addAll(quantity);
			comboStudent.getItems().addAll(quantity);


			// Adding prices to labels
			labelPriceA.setText("£" + price.get(0).toString());
			labelPriceC.setText("£" + price.get(1).toString());
			labelPriceSe.setText("£" + price.get(2).toString());
			labelPriceSt.setText("£" + price.get(3).toString());

			
			// Calculating Adult Totals
			comboAdult.setOnAction((event) -> {

				MainEnterController.value1 = (int) comboAdult.getSelectionModel().getSelectedItem();
				subTotal1 = (price.get(0).doubleValue()) * value1;
				
				// Calculated total values in all combos to update total labels after each combo is changed
				quantityTotal = value1 + value2 + value3 + value4;
				totalCost = (price.get(0).doubleValue() * value1 + (price.get(1).doubleValue()) * value2
				+ (price.get(2).doubleValue()) * value3 + (price.get(3).doubleValue())*value4);
			
				labelAdult.setText("£" + String.format("%.2f", subTotal1));
				labelTotal.setText("£" + String.format("%.2f", totalCost));
				
				setQuantityTotal(quantityTotal);
				Quantity.setText(String.valueOf(quantityTotal));

			});

			// Calculating Child Totals
			comboChild.setOnAction((event) -> {

				MainEnterController.value2 = (int) comboChild.getSelectionModel().getSelectedItem();
				subTotal2 = (price.get(1).doubleValue()) * value2;
				quantityTotal = value1 + value2 + value3 + value4;
				totalCost = (price.get(0).doubleValue() * value1 + (price.get(1).doubleValue()) * value2
						+ (price.get(2).doubleValue()) * value3 + (price.get(3).doubleValue())*value4);
				
				labelChild.setText("£" + String.format("%.2f", subTotal2));

				labelTotal.setText("£" + String.format("%.2f", totalCost));
				
				setQuantityTotal(quantityTotal);
				Quantity.setText(String.valueOf(quantityTotal));

			});

			// Calculating Senior Totals
			comboSenior.setOnAction((event) -> {

				MainEnterController.value3 = (int) comboSenior.getSelectionModel().getSelectedItem();
				subTotal3 = (price.get(2).doubleValue()) * value3;
				quantityTotal = value1 + value2 + value3 + value4;
				totalCost = (price.get(0).doubleValue() * value1 + (price.get(1).doubleValue()) * value2
						+ (price.get(2).doubleValue()) * value3 + (price.get(3).doubleValue())*value4);
				
				labelSenior.setText("£" + String.format("%.2f", subTotal3));

				labelTotal.setText("£" + String.format("%.2f", totalCost));
				
				setQuantityTotal(quantityTotal);
				Quantity.setText(String.valueOf(quantityTotal));

			});

			// Calculating Student Totals
			comboStudent.setOnAction((event) -> {

				MainEnterController.value4 = (int) comboStudent.getSelectionModel().getSelectedItem();
				subTotal4 = (price.get(3).doubleValue()) * value4;
				quantityTotal = value1 + value2 + value3 + value4;
				totalCost = (price.get(0).doubleValue() * value1 + (price.get(1).doubleValue()) * value2
						+ (price.get(2).doubleValue()) * value3 + (price.get(3).doubleValue())*value4);
				
				labelStudent.setText("£" + String.format("%.2f", subTotal4));
				labelTotal.setText("£" + String.format("%.2f", totalCost));
				
				setQuantityTotal(quantityTotal);
				Quantity.setText(String.valueOf(quantityTotal));

			});
			
		}
		
		
		
		// Setting up Booking Summary page
		
		if (fxmlFileLocation.getFile().substring(fxmlFileLocation.getFile().lastIndexOf("/") + 1)
				.equalsIgnoreCase("OrderSummary.fxml")) {			
			
			String rowSummary = "";
			String seatsBookedSummaryTotal = "";
			String seatsSummary = "";
			char row = 'A';
						
			// Iterating over 5x3 matrix with i = (0:2) and j = (0:4)
			for (int i = 0; i < seats[0].length; i++) {
				
				// Initialized j and set to 0 because it throws indexoutofbounds exception when inside loop
				int j = 0;
								
				for (j = 0; j < seats.length; j++) {
					
					if ((seats[j][i].isSelected()) && (!seats[j][i].isDisable())) {
						
						row = (char) ('A' + i);
						rowSummary = "Row: " + String.valueOf(row);
						int sN = j + 1;
						seatsSummary += (", Seat: " + String.valueOf(sN));
					}

				}
				seatsBookedSummaryTotal += rowSummary + seatsSummary;
				seatsBookedSummaryTotal += "\n";
				rowSummary = "";
				seatsSummary = "";
			}
			
			//System.out.println(seatsBookedSummaryTotal);
			
			
			labelMovieName.setText(movieName);
			labelDateSummary.setText(movieDate + ", " + movieTime);
			labelSeatsFinal.setText(seatsBookedSummaryTotal);
			labelTicketTotal.setText(String.valueOf(quantityTotal));
			labelTotalCost.setText("£" + String.format("%.2f", totalCost));
			
			// Setting up promocode button
			btnPromo.setOnAction((ActionEvent e) -> {
				final Stage dialog = new Stage();
                dialog.initModality(Modality.APPLICATION_MODAL);
                dialog.initOwner(stage);
                VBox dialogVbox = new VBox(20);
                TextField promocode = new TextField();
                dialogVbox.getChildren().add(new Text("Use Promo Code"));
                Button applyPromo = new Button("Apply code");
                dialogVbox.getChildren().addAll(promocode, applyPromo);
                Scene dialogScene = new Scene(dialogVbox, 200, 150);
                dialog.setScene(dialogScene);
                dialog.show();
                
                applyPromo.setOnAction((ActionEvent e1) -> {
                	
            		try (Scanner scanner = new Scanner(new File("promocodes.txt"))){
            			while (scanner.hasNext()) {
            				storedPromo = scanner.nextLine();
            				if (Objects.equals(storedPromo, promocode.getText()) 
            						&& MainEnterController.countPromo == 0) {
            					MainEnterController.promoValid = true;
            					Alert alert = new Alert(AlertType.CONFIRMATION);
                    			alert.setTitle("Promo Code Applied");
                    			alert.setHeaderText("Promo Validation");
                    			alert.setContentText("Thanks your promo code was applied to your order");
                    			alert.showAndWait();  
            					dialog.close();
                        		}
        					break;
            			}
            			 if (!(Objects.equals(storedPromo, promocode.getText()))) {
                    			Alert alert = new Alert(AlertType.WARNING);
                    			alert.setTitle("Promo Is Not Valid");
                    			alert.setHeaderText("Promo Validation");
                    			alert.setContentText("Unfortunately, your promocode is not valid");
                    			alert.showAndWait();
                    			
                    	 } else if (Objects.equals(storedPromo, promocode.getText()) && !(MainEnterController.countPromo == 0)) {
			                	Alert alert = new Alert(AlertType.WARNING);
			        			alert.setTitle("Promo Code Already Applied");
			        			alert.setHeaderText("Promo Validation");
			        			alert.setContentText("Promocode already applied. You can only use one promo code");
			        			alert.showAndWait();
			        			dialog.close();
                			}
            			 
            			 if (promoValid && MainEnterController.countPromo == 0) {
                        	 	try {
                        	 			MainEnterController.countPromo++;
                        	 			System.out.println(MainEnterController.countPromo);
                        	 			totalCost = totalCost - price.get(0).doubleValue();
                                		labelTotalCost.setText("£" + String.format("%.2f", totalCost));
                             	} catch (Exception e2) {
                            			e2.printStackTrace();
                            		}
                         }
            			
            		} catch (IOException e3) {
            	        e3.printStackTrace();
            	    }
                });
			});
		}
		
	}// end of initialize

	/**
	 * signUpTransition method: controls button events within the customer side of the application. Button events consist of
	 * page transitions as well as gathering user input from textfields, comboboxes and datepickers, then storing the data into 
	 * text documents.
	 * 
	 * @param event - Directs the user to appropriate frame and gathers user input
	 * @throws IOException display exception if filename is not found
	 * @throws ParseException exception will be thrown when parsing the cinemaUsers.txt file
	 */
	public void signUpTransition(ActionEvent event) throws IOException, ParseException, CustomerException {
		
		//Button to confirm changes to customer profile
		if (event.getSource() == btnSubmitChanges) {
			
			String firstNameChanged = txtFirstNameChange.getText();
			String surnameChanged = txtSurnameChange.getText();
			String emailChanged = txtEmailChange.getText();
			String usernameChanged = txtUsernameChange.getText();
			String passwordChanged = txtPasswordChange.getText();
			String dobStrChanged = dobChange.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
			String dobStrChangedUnformatted = dobChange.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
			
			userProfileChanged = firstNameChanged + "," + surnameChanged + "," + emailChanged + "," + usernameChanged + "," + passwordChanged + "," + dobStrChanged;
			
			if (SignUpValidation.validateFirstName(firstNameChanged) && 
					SignUpValidation.validateSurname(surnameChanged) &&
					SignUpValidation.validateEmail(emailChanged) &&
					SignUpValidation.validateUsername(usernameChanged) &&
					SignUpValidation.validatePassword(passwordChanged) &&
					SignUpValidation.validateDobStr(dobStrChangedUnformatted)) {
			
			//Change Details
				customers = CustomerList.getInstance().getCustomerList();

				boolean cust = false;

				for (Customer customer : customers){
					if (customer.getUsername().equals(MainEnterController.userName)){
					String userProfileBefore = customer.getFirstName() + "," + customer.getSurname() + "," + customer.getEmail() + "," + customer.getUsername() + "," + customer.getPassword() + "," + customer.getDob();
					System.out.println(customer.getFirstName() + "," + customer.getSurname() + "," + customer.getEmail() + "," + customer.getUsername() + "," + customer.getPassword() + "," + customer.getDob());
					System.out.println(userProfileChanged);
					ChangeCustomerProfile.modifyFile("resources/cinemaUsers.txt", userProfileBefore, userProfileChanged);

					//Change username.txt file name
					File file = new File("resources/customerBookings/" + customer.getUsername() + ".txt");
					File file2 = new File("resources/customerBookings/" + usernameChanged + ".txt");
					file.renameTo(file2);

					cust = true;
					}
				}
				if (cust == false){
					throw new CustomerException("User Not Found");
				}
			
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Confirmation");
			alert.setHeaderText(null);
			alert.setContentText("Your profile details have been changed");
			alert.showAndWait().ifPresent(response -> {
				
			     if (response == ButtonType.OK) {
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

					txtFirstNameChange.clear();
					txtSurnameChange.clear();
					txtEmailChange.clear();
					txtUsernameChange.clear();
					txtPasswordChange.clear();
					dobChange.setValue(null);

					txtFirstNameChange.setText(firstNameChanged);
			 		txtSurnameChange.setText(surnameChanged);
					txtEmailChange.setText(emailChanged);
					txtUsernameChange.setText(usernameChanged);
					txtPasswordChange.setText(passwordChanged);
					dobChange.setValue(LocalDate.parse(dobStrChanged, formatter));
					System.out.println("Test: " + surnameChanged);
			     }
			});

			}

			customers = null;
		}
		

		customers = CustomerList.getInstance().getCustomerList();
		if (event.getSource() == btnCustForm) {
			stage = (Stage) btnCustForm.getScene().getWindow();
			root = FXMLLoader.load(getClass().getResource("SignUpForm.fxml"));
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();

		}
		
		//Button the Change Details Page
		if (event.getSource() == btnChangeDetails) {
			stage = (Stage) btnChangeDetails.getScene().getWindow();
			root = FXMLLoader.load(getClass().getResource("ChangeDetails.fxml"));
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();

		}
		
		//Back Buttons
		if (event.getSource() == btnGoBack) {
			stage = (Stage) btnGoBack.getScene().getWindow();
			root = FXMLLoader.load(getClass().getResource("MainEnter.fxml"));
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		}
		
		// Cancel change of details
		if (event.getSource() == btnCancel) {
			stage = (Stage) btnCancel.getScene().getWindow();
			root = FXMLLoader.load(getClass().getResource("FilmChoiceFrame.fxml"));
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		}
		//Button from home page to customer sign in page
		if (event.getSource() == btnCustSignIn) {
			stage = (Stage) btnCustSignIn.getScene().getWindow();
			root = FXMLLoader.load(getClass().getResource("CustomerSignIn.fxml"));
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();

		}
		
		//Button from home page to staff sign in page
		if (event.getSource() == btnStaffSignIn) {
			stage = (Stage) btnCustSignIn.getScene().getWindow();
			root = FXMLLoader.load(getClass().getResource("StaffSignIn.fxml"));
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();

		}
		
		//Button from customer login page to film choice page
		if (event.getSource() == btnCustLoginToBooking) {
			String usernameStr = usernameTextField.getText();
			String passwordStr = passwordTextField.getText();
			boolean foundUser = false;
			for (Customer customer : customers) {
				if ((customer.getUsername().equalsIgnoreCase(usernameStr))
						&& (customer.getPassword().equalsIgnoreCase(passwordStr))) {
					foundUser = true;
					MainEnterController.userName = usernameStr;
				}
			}

			if (!foundUser) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Confirmation");
				alert.setHeaderText(null);
				alert.setContentText("Invalid username / password - please try again");
				alert.showAndWait();
			} else {
				stage = (Stage) btnCustLoginToBooking.getScene().getWindow();
				root = FXMLLoader.load(getClass().getResource("FilmChoiceFrame.fxml"));
				Scene scene = new Scene(root);
				stage.setScene(scene);
				stage.show();
				// load movies from file

			}

		}

		//Button from film choice page to ticket booking page
		if (event.getSource() == btnCustToTicketBooking) {

			// Catching null pointers
			if (!(movieCombo.getSelectionModel().getSelectedItem() == null)) {
				String selectedMovieTitle = movieCombo.getSelectionModel().getSelectedItem().toString();
				MainEnterController.movieName = selectedMovieTitle;
			}
			
			if (!(movieDatePicker.getValue() == null)) {
				String selectedMovieDate = movieDatePicker.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
				MainEnterController.movieDate = selectedMovieDate;
			}
			
			if (!(selectTime.getSelectionModel().getSelectedItem() == null)) {
				String selectedMovieTime = selectTime.getSelectionModel().getSelectedItem().toString();
				MainEnterController.movieTime = selectedMovieTime;
			}
			
						
			if (movieCombo.getSelectionModel().getSelectedItem() == null) {
				Alert alert = new Alert(AlertType.WARNING);
    				alert.setTitle("Select Movie");
    				alert.setHeaderText("Movie Validation");
    				alert.setContentText("Please select movie to watch");
    				alert.showAndWait();
    				
			} else if (movieDatePicker.getValue() == null) {
				Alert alert2 = new Alert(AlertType.WARNING);
    				alert2.setTitle("Select Date");
    				alert2.setHeaderText("Date Validation");
    				alert2.setContentText("Please select movie date");
    				alert2.showAndWait();	
			} else if (selectTime.getValue() == null) {
				Alert alert3 = new Alert(AlertType.WARNING);
    				alert3.setTitle("Select Time");
    				alert3.setHeaderText("Time Validation");
    				alert3.setContentText("Please select movie time");
    				alert3.showAndWait();
			} else {
				
				try {
					stage = (Stage) btnCustToTicketBooking.getScene().getWindow();
					root = FXMLLoader.load(getClass().getResource("TicketBooking.fxml"));
					Scene scene = new Scene(root);
					stage.setScene(scene);
					stage.show();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}

		//Logout buttons to home page
		if (event.getSource() == btnLogOut) {

			stage = (Stage) btnLogOut.getScene().getWindow();
			root = FXMLLoader.load(getClass().getResource("MainEnter.fxml"));
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();

		}

		//Sign up confirmation button
		if (event.getSource() == btnCustSignUpConfirmed) {
			stage = (Stage) btnCustSignUpConfirmed.getScene().getWindow();

			String firstName = txtFirstName.getText();
			String surname = txtSurname.getText();
			String email = txtEmail.getText();
			String username = txtUsername.getText();
			String password = txtPassword.getText();
			String dobStr = null;
			String dobStrUnformatted = null;
			if (!(dob.getValue() == null)) {
				dobStr = dob.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
				dobStrUnformatted = dob.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
			}
			
			if (dobStrUnformatted == null) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Validate Date of Birth");
				alert.setHeaderText(null);
				alert.setContentText("Please The Valid Format For Your Date Of Birth");
				alert.showAndWait();
			}
			
			
			if (SignUpValidation.validateFirstName(firstName) && 
					SignUpValidation.validateSurname(surname) &&
					SignUpValidation.validateEmail(email) &&
					SignUpValidation.validateUsername(username) &&
					SignUpValidation.validatePassword(password) &&
					SignUpValidation.validateDobStr(dobStrUnformatted)) {
				
				for (Customer customer1 : customers) {
					if ((customer1.getUsername().equalsIgnoreCase(username))) {
						
						Alert alert = new Alert(AlertType.WARNING);
						alert.setTitle("Validate Details");
						alert.setHeaderText(null);
						alert.setContentText("Username Already Exits");
						alert.showAndWait();
						return;
					} else if (customer1.getEmail().equalsIgnoreCase(email)) {
						Alert alert = new Alert(AlertType.WARNING);
						alert.setTitle("Validate Details");
						alert.setHeaderText(null);
						alert.setContentText("Email Already Used");
						alert.showAndWait();
						return;
					}
				}
					

			FileWriter fw = new FileWriter(new File("resources/cinemaUsers.txt"), true);
			FileWriter userFW = new FileWriter(new File("resources/customerBookings/" + MainEnterController.userName + ".txt"), true);
			fw.write(firstName + "," + surname + "," + email + "," + username + "," + password + "," + dobStr + "\n");
			fw.close();
			userProfile = firstName + "," + surname + "," + email + "," + username + "," + password + "," + dobStr;
			Customer customer = new Customer();
			customer.setFirstName(firstName);
			customer.setSurname(surname);
			customer.setEmail(email);
			customer.setUsername(username);
			customer.setPassword(password);
			customer.setDob(dobStr);
			customers.add(customer);
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Confirmation");
			alert.setHeaderText(null);
			alert.setContentText("Account has been created - you can now login");
			alert.showAndWait();
			root = FXMLLoader.load(getClass().getResource("MainEnter.fxml"));
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		}
		 }

		if (event.getSource() == btnEmployeeLogin) {
			String staffUsername = this.staffUsername.getText();
			String staffPassword = this.staffPassword.getText();
			if ((staffUsername.equalsIgnoreCase("admin")) && (staffPassword.equalsIgnoreCase("password"))) {
				stage = (Stage) btnEmployeeLogin.getScene().getWindow();
				root = FXMLLoader.load(getClass().getResource("AddMovie.fxml"));
				Scene scene = new Scene(root);
				stage.setScene(scene);
				stage.show();
			} else {
				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("Confirmation");
				alert.setHeaderText(null);
				alert.setContentText("Invalid username / password - please try again");
				alert.showAndWait();
			}
		}
		}

	public class CustomerException extends Exception {

		public CustomerException() {
			super();
		}

		public CustomerException(String s) {
			super(s);
		}
	}
		
	/**
	 * Load Movies Method: scan each line of the movies.txt and set each corresponding line to a movie detail. A new movie object
	 * is created and added to the 'movies' ArrayList within the Movie class.
	 */
	public static void loadMovies() {
		try {
			Scanner scanner = new Scanner(new File("resources/movies.txt"));
			while (scanner.hasNext()) {
				String movieTitle = scanner.nextLine();
				String movieDesc = scanner.nextLine();
				String timings = scanner.nextLine();
				String movieImage = scanner.nextLine();
				String movieStartDate = scanner.nextLine();
				String movieEndDate = scanner.nextLine();
				Movie movie = new Movie();
				movie.setMovieTitle(movieTitle);
				movie.setMovieDescription(movieDesc);
				movie.setMovieTimings(timings);
				movie.setFile(movieImage);
				movie.setMovieStartDate(movieStartDate);
				movie.setMovieEndDate(movieEndDate);
				movies.add(movie);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	/**
	 * Ticket Booking Transition Method: primarily used for button events within the ticket booking frame.
	 * 
	 * @param event when a button is clicked, user input is stored in text files or using setters and getters. Transitions to 
	 * other frames also occur.
	 * @throws Exception display exception
	 */
	public void ticketBookingTransition(ActionEvent event) throws Exception, QuantityTotalException {

		try {
			if (event.getSource() == btnCustToSeatBooking) {
				
				//Ensure the customer has picked at least one ticket type
                Integer.parseInt(lbAvailableSeats.getText());
                if (quantityTotal < 0) {
                	throw new QuantityTotalException("Quantity underflow");
				} else if (quantityTotal == 0){
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information");
                    alert.setHeaderText(null);
                    alert.setContentText("You must select at least one ticket type");
                    alert.showAndWait();
                    return;
                } else if (quantityTotal > Integer.parseInt(lbAvailableSeats.getText())){
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information");
                    alert.setHeaderText(null);
                    alert.setContentText("We're sorry, we do not have availability for that quantity of seats.");
                    alert.showAndWait();
                    return;
                }
                else {

				quantityTotal = value1 + value2 + value3 + value4;

				stage = (Stage) btnCustToSeatBooking.getScene().getWindow();
				root = FXMLLoader.load(getClass().getResource("SeatBooking.fxml"));

				Scene scene = new Scene(root);
				stage.setScene(scene);

				stage.show();
                }
			}

			if (event.getSource() == btnHome) {
				stage = (Stage) btnHome.getScene().getWindow();
				root = FXMLLoader.load(getClass().getResource("FilmChoiceFrame.fxml"));
				Scene scene = new Scene(root);
				stage.setScene(scene);
				stage.show();
			}

			if (event.getSource() == btnLogOut) {

				stage = (Stage) btnLogOut.getScene().getWindow();
				root = FXMLLoader.load(getClass().getResource("MainEnter.fxml"));
				Scene scene = new Scene(root);
				stage.setScene(scene);
				stage.show();

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public class QuantityTotalException extends Exception {

		public QuantityTotalException() {
			super();
		}

		public QuantityTotalException(String s) {
			super(s);
		}

	}


	/**
	 * Seat Booking Transition Method: primarily used for button events within the seat booking frame.
	 * 
	 * @param event when a button is clicked, user input is stored in text files or using setters and getters. Transitions to 
	 * other frames also occur.
	 * @throws Exception display exception
	 */
	public void seatBookingTransition(ActionEvent event) throws Exception {
		Stage stage;
		Parent root;

		try {

			if (event.getSource() == btnToBookingSummary) {
				
				if(counterSeats < quantityTotal){
                    
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information");
                    alert.setHeaderText(null);
                    alert.setContentText("You must choose all specified seats");
                    alert.showAndWait();
                    return;
                    
                } else {

				stage = (Stage) btnToBookingSummary.getScene().getWindow();
				root = FXMLLoader.load(getClass().getResource("OrderSummary.fxml"));
				Scene scene = new Scene(root);
				stage.setScene(scene);
				stage.show();
				
				
				// LEFT THIS BIT UNCOMMENTED BECAUSE seats[i][j].isSelected()) THROWS NULL POINTER EXCEPTION FROM initialize
//				// Iterate over seats array to record chosen seats
				
				for (int i = 0; i < seats.length; i++) {
					for (int j = 0; j < seats[0].length; j++) {

						if ((seats[i][j].isSelected()) && (!seats[i][j].isDisable())) {
							
							//NEED TO HAVE IN INT,INT FORMAT FOR GRIDS TO READ IT
							
							// adding +1 to compensate starting from 0
							String row = "Row: " + (j + 1) + ",";
							String seat = " Seat Number: " + (i + 1);
							
//							fw.write((i + 1) + "," + (j + 1) + "\n");
//							userFW.write(row + seat + "\n");
							
							
							setSelectedSeatsSummary(row + seat);
							
						}
						
					}
				}
                }

			}
			//Back button
			if (event.getSource() == btnHome) {
				
				counterSeats = 0;
				stage = (Stage) btnHome.getScene().getWindow();
				root = FXMLLoader.load(getClass().getResource("FilmChoiceFrame.fxml"));
				Scene scene = new Scene(root);
				stage.setScene(scene);
				stage.show();

			}
			//Log out button
			if (event.getSource() == btnLogOut) {

				stage = (Stage) btnLogOut.getScene().getWindow();
				root = FXMLLoader.load(getClass().getResource("MainEnter.fxml"));
				Scene scene = new Scene(root);
				stage.setScene(scene);
				stage.show();

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	/**
	 *  Order Summary Transition Method: primarily used for button events within the order summary frame.
	 * 
	 * @param event when a button is clicked, user input is stored in text files or using setters and getters. Transitions to 
	 * other frames also occur.
	 * @throws Exception display exception
	 */
	public void orderTransition(ActionEvent event) throws Exception {

		try {
			//Button to payment
			if (event.getSource() == btnToPayment) {
				stage = (Stage) btnToPayment.getScene().getWindow();
				root = FXMLLoader.load(getClass().getResource("Payment.fxml"));
				Scene scene = new Scene(root);
				stage.setScene(scene);
				stage.show();

			}
			//Home Button
			if (event.getSource() == btnHome) {
				counterSeats = 0;
				stage = (Stage) btnHome.getScene().getWindow();
				root = FXMLLoader.load(getClass().getResource("FilmChoiceFrame.fxml"));
				Scene scene = new Scene(root);
				stage.setScene(scene);
				stage.show();
			}
			//Logout Button
			if (event.getSource() == btnLogOut) {

				stage = (Stage) btnLogOut.getScene().getWindow();
				root = FXMLLoader.load(getClass().getResource("MainEnter.fxml"));
				Scene scene = new Scene(root);
				stage.setScene(scene);
				stage.show();

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Payment Transition Method: primarily used for button events within the order summary frame.
	 * 
	 * @param event when a button is clicked, user input is stored in text files or using setters and getters. Transitions to 
	 * other frames also occur.
	 * @throws Exception display exception
	 */
	public void paymentTransition(ActionEvent event) throws Exception {
		Stage stage = null;
		Parent root = null;

		try {
			//Button to payment confirmed page
			if (event.getSource() == btnToConfirmPayment) {
				
				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("Confirmation");
				alert.setHeaderText(null);
				alert.setContentText("Are you sure you want to proceed?");
				alert.showAndWait().ifPresent(response -> {
					
				     if (response == ButtonType.OK) {
				    	 
				    	 String expiryDate = txtExpiryDate.getText();
				    	 //Ensure correct validation of card information
				    	 	try {
								if (ValidatePaymentDetails.validateName(txtCardName) && 
								ValidatePaymentDetails.validateCardNumber(txtCardNumber) &&
								ValidatePaymentDetails.validateCVVNumber(txtCvvNumber) &&
								ValidatePaymentDetails.validateExpiryDate(txtExpiryDate)) {
										
											try {
												
												// create file writer for userName.txt
												FileWriter userFW = new FileWriter(new File("resources/customerBookings/" + MainEnterController.userName + ".txt"), true);
												MainEnterController.movieTime = MainEnterController.movieTime.replace(":", "-");
												
												// create file writer for movieName,movieDate,movieTime.txt
												FileWriter fw = new FileWriter(new File("resources/bookedMovies/" + MainEnterController.movieName + ","
														+ MainEnterController.movieDate + "," + MainEnterController.movieTime + ".txt"), true);
												String seatsTotal ="";
												String seatsTotal2 = "";
												char rowBooking = 'A';
												
												// iterating over rows (i)
												for (int i = 0; i < seats[0].length; i++) {
													int j=0;
													
													// iterating over columns (j)
													for (j = 0; j < seats.length; j++) {
														
														// checking if seats are selected and not reserved
														if ((seats[j][i].isSelected()) && (!seats[j][i].isDisable())) {
															
															// setting row to letter format and correcting seat number (starts from 0)
															rowBooking = (char)('A' + i);
															int seatNum = j + 1;
																													
															seatsTotal += String.valueOf(rowBooking) + seatNum + " "; 
															seatsTotal2 += j + "," + i + " ";

															fw.write(j + "," + i + "\n");														
															
														}
													}
												}
												
												// Writing to file userName.txt
												userFW.write(/*"Movie: " +*/ MainEnterController.movieName + ";" + MainEnterController.movieDate + ";" +  MainEnterController.movieTime + ";" + seatsTotal + ";" + seatsTotal2 + "\n");
												
												fw.close();
												userFW.close();

											
											} catch (Exception ex) {
												ex.printStackTrace();
											}
																					
										// Loading Booking Confirmed Page
										try {
											counterSeats = 0;
											Stage stage2 = (Stage) btnToConfirmPayment.getScene().getWindow();
											Parent root2 = FXMLLoader.load(getClass().getResource("PaymentConfirmed.fxml"));
											Scene scene = new Scene(root2);
											stage2.setScene(scene);
											stage2.show();
										} catch (IOException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
										
										
									}
							} catch (ParseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
				    	 
				     }
				     
				});
			}

			
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}

				
			
			

		try {
			//Back button
			if (event.getSource() == btnHome) {
				counterSeats = 0;
				stage = (Stage) btnHome.getScene().getWindow();
				root = FXMLLoader.load(getClass().getResource("FilmChoiceFrame.fxml"));
				Scene scene = new Scene(root);
				stage.setScene(scene);
				stage.show();
			}
			//Logout Button
			if (event.getSource() == btnLogOut) {

				stage = (Stage) btnLogOut.getScene().getWindow();
				root = FXMLLoader.load(getClass().getResource("MainEnter.fxml"));
				Scene scene = new Scene(root);
				stage.setScene(scene);
				stage.show();

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	/**
	 * Payment Confirmed Transition Method: primarily used for button events within the payment confirmed frame.
	 * 
	 * @param event 2 buttons are present in the payment confirmed frame: home button (transitions to film choice frame), 
	 * and exit button (closes application).
	 * @throws Exception display exception
	 */
	public void paymentConfirmedTransition(ActionEvent event) throws Exception {
		Stage stage;
		Parent root;
		
		if (event.getSource() == btnHome) {
			counterSeats = 0;
			stage = (Stage) btnHome.getScene().getWindow();
			root = FXMLLoader.load(getClass().getResource("FilmChoiceFrame.fxml"));
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		}
		
		if (event.getSource() == btnExit) {
			Platform.exit();
		}
	}

}
