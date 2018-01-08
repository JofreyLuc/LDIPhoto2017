package application;

import controler.AlbumControler;
import controler.DataViewerController;
import controler.MenuController;
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
			DataViewerController datac = new DataViewerController();
			MenuController menuc = new MenuController();

			FXMLLoader loader = new FXMLLoader(getClass().getResource("/test.fxml"));
			loader.setController(new WindowControler(albumc, datac, menuc));
			VBox root = loader.load();

			Scene scene = new Scene(root,1000,700);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setMinWidth(920);
			primaryStage.setMinHeight(700);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace(); 
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
