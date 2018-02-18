package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

/**
 * <h1>Main Class</h1>
 * <p>
 * The Main Class is used to launch the application by loading the MainEnter frame. The main class constructs an instance
 * (CustomerList), then calls the start method to load the first FXML file (MainEnter.fxml).
 * <p>
 * 
 * @author Donal McLaughlin and German Malsagov
 *
 */

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/application/MainEnter.fxml"));
			Scene scene = new Scene(root,900,600);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			CustomerList.getInstance().loadData();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * Main Method for the Main Class:
	 * Launch the Application.
	 * 
	 * @param args - arguments command-line 
	 */
	public static void main(String[] args) {
		launch(args);
	}
	
}
