package controler;

import java.io.File;
import java.util.ArrayList;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
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
	private TextField fieldX;
	
	@FXML
	private TextField fieldY;
	
	@FXML
	private Slider scrollDimension;

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
		
		
		// Scroll event
		scrollDimension.setOnMouseDragged(new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent event) {
				resizeImage();
			}
		});
		
		this.setCurrentImage(null);
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
		if(source!=null)
		{
			setCoordField(source.getX(),source.getY());
			this.scrollDimension.setDisable(false);
		}
		else
		{
			this.scrollDimension.setDisable(true);
			setCoordField(0,0);
		}
	}

	public double getPaneWidth() {
		return imagepane.getWidth();
	}

	public double getPaneHeight() {
		return imagepane.getHeight();
	}

	public void setCoordField(double x, double y) {
		fieldX.setText(""+x);
		fieldY.setText(""+y);
	}
	
	private void resizeImage() {
		this.albumc.resizeImage(this.scrollDimension.getValue());
	}
	
	
}
