package controler;

import java.io.File;
import java.util.ArrayList;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
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

	@FXML // TextField contenant l'abs. de l'image selectionnée dans le Pane de gauche
	private TextField fieldX;

	@FXML // TextField contenant l'ord. de l'image selectionnée dans le Pane de gauche
	private TextField fieldY;
	
	@FXML // TextField contenant la largeur de l'image
	private TextField fieldborderWidth;

	@FXML // Slider permettant de redimensionner l'image, de 10% à 100% de la taille de base
	private Slider scaleDimension;

	@FXML // Bouton permettant l'ajout d'une image dans la page actuelle
	private Button boutonAjoutImagePage;
	
	@FXML
	private ColorPicker borderColorPicker;

	// Controleurs
	private AlbumControler albumc;
	private DataViewerController datac;

	public WindowControler(AlbumControler ac, DataViewerController dc){
		albumc = ac;
		albumc.setWindowControler(this);
		datac = dc;
		datac.setWindowControler(this);
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
		datac.addPicturesToViewer(flowPaneImages, boutonAjoutImagePage, ap);

		datac.refreshPagesView(flowPanePages, albumc.album.getPage(1));
		/***TEST***/

		//TODO : Rafraichissement du viewer des pages : on focus PagesTab ?
		//TODO : Bouton d'ajout de l'image à la page : A bouger ?
		boutonAjoutImagePage.setOnAction((event) -> {
			addCurrentViewerImageToPage();
		});

		//TODO : Gestion des menus (a mettre dans une classe separee ?)
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



		// Event sur pression du scaleDimension
		scaleDimension.setOnMouseDragged(new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent event) {
				resizeImage();
			}
		});
		
		// Events sur changement des textFields
		
		this.fieldX.textProperty().addListener((observable, oldValue, newValue) -> {
			double new_double;
			try{
				new_double = Double.parseDouble(newValue);
				
			}catch(NumberFormatException e ){
				new_double = 0;
				this.fieldX.setText("0");
			}
			
			this.albumc.moveCurrentImage(new_double, Double.parseDouble(this.fieldY.getText()));
			
		});
		
		this.fieldY.textProperty().addListener((observable, oldValue, newValue) -> {
			double new_double;
			try{
				new_double = Double.parseDouble(newValue);
				
			}catch(NumberFormatException e ){
				new_double = 0;
				this.fieldY.setText("0");
			}
			
			this.albumc.moveCurrentImage(Double.parseDouble(this.fieldX.getText()), new_double);
			
		});
		
		this.fieldborderWidth.textProperty().addListener((observable, oldValue, newValue) -> {
			double new_double;
			try{
				new_double = Double.parseDouble(newValue);
				
			}catch(NumberFormatException e ){
				new_double = 0;
				this.fieldborderWidth.setText("0");
			}
			if(new_double<0)
			{
				new_double=0;
				this.fieldborderWidth.setText("0");
			}
			
			this.albumc.changeBordureWidth(new_double);
			
		});
		
		this.borderColorPicker.setOnAction(new EventHandler() {
			@Override
			public void handle(Event e) {
				albumc.changeBordureColor(borderColorPicker.getValue());				
			}
        });

		this.setCurrentPageImage(null);
	}

	private void importPictures(){
		System.out.println("import images");
		//TODO : filechooser etc.
	}


	/**
	 * Ajoute des images dans le panneau de droite grace au DataViewerController.
	 * @param pictures - Les images a ajouter
	 */
	private void addPicturesToViewer(Picture... pictures)
	{
		this.datac.addPicturesToViewer(flowPaneImages, boutonAjoutImagePage, pictures);
	}

	public void setAddButtonActive(boolean b){
		boutonAjoutImagePage.setDisable(!b);
	}

	/**
	 * Ajoute une nouvelle Picture dans la page a partir de l'ImageView qui est en focus dans le panneau de droite
	 */
	private void addCurrentViewerImageToPage() {
		this.albumc.addPictureToPage(datac.getCurrentViewerImage(), imagepane.getWidth() / 2, imagepane.getHeight() / 2, imagepane);
	}

	/**
	 * Change l'image selectionnée, et en informe le controleur
	 */
	public void setCurrentPageImage(ImageView source) {
		albumc.setCurrentImage(source);
		// TODO: Changer toutes les prop du pane de gauche en conséquence

		
		if(source!=null)
		{
			this.scaleDimension.setDisable(false);
		}
		else
		{
			this.scaleDimension.setDisable(true);
		}
	}

	/**
	 * Retourne la largeur du panneau central
	 */
	public double getPaneWidth() {
		return imagepane.getWidth();
	}

	/**
	 * Retourne la hauteur du panneau central
	 */
	public double getPaneHeight() {
		return imagepane.getHeight();
	}

	/**
	 * Change les coordonnées dans les fields du panneau de gauche
	 */
	public void setCoordField(double x, double y) {
		fieldX.setText(""+x);
		fieldY.setText(""+y);
	}

	/**
	 * Modifie la taille de l'image en fonction du scaleDimension
	 */
	private void resizeImage() {
		this.albumc.resizeImage(this.scaleDimension.getValue());
	}

	/**
	 * Change la valeur du scalePane
	 */
	public void setscalePaneValue(double scale) {
		this.scaleDimension.adjustValue(scale);
		
	}

	public void setBorderWidth(int i) {
		this.fieldborderWidth.setText(""+i);
		
	}

	public void setColorPicker(Color borderColor) {
		this.borderColorPicker.setValue(borderColor);
		
	}


}
