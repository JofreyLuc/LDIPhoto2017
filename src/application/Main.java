package application;
	
import controler.AlbumControler;
import controler.WindowControler;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			AlbumControler albumc = new AlbumControler();
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/test.fxml"));
			loader.setController(new WindowControler(albumc));
			VBox root = loader.load();
			Scene scene = new Scene(root,1024,768);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
