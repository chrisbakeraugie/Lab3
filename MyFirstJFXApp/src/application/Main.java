package application;
	
import org.opencv.core.*;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	
	private BorderPane rootElement;
	
	@Override
	public void start(Stage primaryStage) {
		
		
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("FirstFXML.fxml"));
			this.rootElement = (BorderPane) loader.load();
			
			Scene scene = new Scene(this.rootElement,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setTitle("JavaFX Meets Tons of Bugs");
			primaryStage.setScene(scene);
			primaryStage.show();
			
			Controller controller = loader.getController();
			controller.setMainApp(this);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	public BorderPane getRootElement() {
		return this.rootElement;
	}
	
	public static void main(String[] args) {
		
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		
		launch(args);
	}
}
