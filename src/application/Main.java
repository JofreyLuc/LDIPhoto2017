package application;

import java.io.File;

import View.BottomBar;
import View.ImageModifier;
import View.ImageViewer;
import View.Page_Pane;
import javafx.application.Application;
import javafx.geometry.Pos;
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

			root.setTop(menuBar);

			/* TODO : Liens avec le modele dans tous les éléments graphiques */

			/* Ajout du menu de côté permettant la modification d'une image */
			ImageModifier modifier = new ImageModifier();
			root.setLeft(modifier);

			/* Ajout de la zone d'image */
			Page_Pane sizedPane = new Page_Pane(400,650, modifier); // 400;650, dimension à voir lors de la création de l'album, ici pour l'exemple
			root.setCenter(sizedPane);

			/* Ajout des boutons sous l'image */
			BottomBar bar = new BottomBar();
			root.setBottom(bar);
			bar.setAlignment(Pos.TOP_CENTER);

			/* Ajout d'une image pour test */
			// Pour le moment, une image peut "sortir" de leur cadre reservé, je sais pas encore comment fair epour que non, assez confu pour le moment mais je trouverai ca demain :)

			sizedPane.addImage(new File("./resources/img.jpg").toURI().toString());

			/* Ajout du visionneur d'images à droite de la page */
			ImageViewer iv = new ImageViewer();
			root.setRight(iv);


			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			/* Lance l'appli en fullscreen non redimensionnable*/
			primaryStage.setMaximized(true);
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
