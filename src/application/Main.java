package application;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root,400,400);

			/* Menus */
			MenuBar menuBar = new MenuBar();

			Menu menuFile = new Menu("Fichier");
			MenuItem newAlbum = new MenuItem("Nouvel album...");
			MenuItem quit = new MenuItem("Quitter");
			menuFile.getItems().addAll(newAlbum, new SeparatorMenuItem(), quit);

			Menu menuAlbum = new Menu("Album");
			MenuItem newPage = new MenuItem("Ajouter une page");
			MenuItem deletePage = new MenuItem("Supprimer une page");
			MenuItem importPictures = new MenuItem("Importer des images...");
			menuAlbum.getItems().addAll(newPage, deletePage, new SeparatorMenuItem(), importPictures);

			menuBar.getMenus().addAll(menuFile, menuAlbum);

			/* Comportement etrange */
			//root.getChildren().add(menuBar);
			root.setTop(menuBar);

			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			/* Lance l'appli en fullscreen */
			primaryStage.setMaximized(true);
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
