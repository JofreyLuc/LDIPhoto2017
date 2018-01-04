package controler;

import java.io.File;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import model.Page;
import model.Picture;


public class WindowControler {

	@FXML // Correspond au Pane contenant les images au centre de la page
	private Pane imagepane;

	@FXML // FlowPane contenant la liste d'images à droite de la page
	private FlowPane flowPaneImages;

	@FXML // FlowPane contenant la liste de pages à droite de la page
	private FlowPane flowPanePages;

	@FXML
	private MenuItem menuNouvelAlbum;

	@FXML
	private MenuItem menuQuitter;

	@FXML
	private MenuItem menuNouvellePage;

	@FXML
	private MenuItem menuAjoutImages;

	@FXML
	private Button boutonAjoutImagePage;

	private AlbumControler albumc;
	private DataViewerController datac;

	public WindowControler(AlbumControler ac, DataViewerController dc){
		albumc = ac;
		albumc.setWindowControler(this);
		datac = dc;
	}

	@FXML
	private void initialize(){

		/***TEST***/
		this.albumc.setPageOnPane(this.imagepane, 1);

		Picture[] ap = {
		(new Picture(new File("./resources/cat1.jpg").toURI().toString())),
		(new Picture(new File("./resources/img.jpg").toURI().toString())),
		(new Picture(new File("./resources/cat2.jpg").toURI().toString())),
		(new Picture(new File("./resources/img.jpg").toURI().toString())),
		(new Picture(new File("./resources/img.jpg").toURI().toString())),
		(new Picture(new File("./resources/cat5.jpg").toURI().toString())),
		(new Picture(new File("./resources/img.jpg").toURI().toString())),
		(new Picture(new File("./resources/cat1.jpg").toURI().toString())),
		(new Picture(new File("./resources/img.jpg").toURI().toString())),
		(new Picture(new File("./resources/cat1.jpg").toURI().toString())),
		(new Picture(new File("./resources/img.jpg").toURI().toString())),
		(new Picture(new File("./resources/cat2.jpg").toURI().toString())),
		(new Picture(new File("./resources/img.jpg").toURI().toString())),
		(new Picture(new File("./resources/img.jpg").toURI().toString())),
		(new Picture(new File("./resources/cat5.jpg").toURI().toString())),
		(new Picture(new File("./resources/img.jpg").toURI().toString())),
		(new Picture(new File("./resources/cat1.jpg").toURI().toString())),
		(new Picture(new File("./resources/img.jpg").toURI().toString()))
		};
		datac.addPicturesToViewer(flowPaneImages, ap);

		datac.refreshPagesView(flowPanePages, albumc.album.getPage(1));
		/***TEST***/

		// Gestion des menus (a mettre dans une classe separee ?)
		menuNouvelAlbum.setOnAction((event) -> {
			//generateNewAlbum()
		});

		menuQuitter.setOnAction((event) -> {
			//quit()
		});

		menuNouvellePage.setOnAction((event) -> {
			//addNewPage()
		});

		menuAjoutImages.setOnAction((event) -> {
			importPictures();
		});

		boutonAjoutImagePage.setOnAction((event) -> {
			//addPictureToPage();
		});
	}

	private void importPictures(){
		System.out.println("import images");
		//TODO : filechooser etc.
	}



	private void addPicturesToViewer(Picture... pictures)
	{
		this.datac.addPicturesToViewer(flowPaneImages, pictures);
	}

	public void setCurrentImage(ImageView source) {
		albumc.setCurrentImage(source);
		// Il faudra aussi changer toutes les prop du pane de gauche en conséquence

	}

	public double getPaneWidth() {
		return imagepane.getWidth();
	}

	public double getPaneHeight() {
		return imagepane.getHeight();
	}
}
