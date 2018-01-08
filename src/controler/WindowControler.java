package controler;

import java.io.File;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Slider;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import model.Picture;


public class WindowControler {

	@FXML // Correspond au Pane contenant les images au centre de la page
	private Pane imagepane;

	@FXML
	private Tab tabPages;

	@FXML // FlowPane contenant la liste d'images � droite de la page
	private FlowPane flowPaneImages;

	@FXML // FlowPane contenant la liste de pages � droite de la page
	private FlowPane flowPanePages;

	@FXML
	private MenuItem menuNouvelAlbum;

	@FXML
	private MenuItem menuQuitter;

	@FXML
	private MenuItem menuNouvellePage;

	@FXML
	private MenuItem menuAjoutImages;

	@FXML // TextField contenant l'abs. de l'image selectionn�e dans le Pane de gauche
	private TextField fieldX;

	@FXML // TextField contenant l'ord. de l'image selectionn�e dans le Pane de gauche
	private TextField fieldY;

	@FXML // TextField contenant la largeur de l'image
	private TextField fieldborderWidth;

	@FXML // Slider permettant de redimensionner l'image, de 10% � 100% de la taille de base
	private Slider scaleDimension;

	@FXML // Bouton permettant l'ajout d'une image dans la page actuelle
	private Button boutonAjoutImagePage;

	@FXML
	private ColorPicker borderColorPicker;

	@FXML
	private TextField fieldLegende;

	@FXML
	private TextField labelNoPage;

	@FXML
	private Button previousPage;

	@FXML
	private Button nextPage;
	
	@FXML
	private Button deleteButton;

	// Controleurs
	private AlbumControler albumc;
	private DataViewerController datac;
	private MenuController menuc;

	public WindowControler(AlbumControler ac, DataViewerController dc, MenuController mc){
		albumc = ac;
		albumc.setWindowControler(this);
		datac = dc;
		datac.setWindowControler(this);
		menuc = mc;
		menuc.setWindowControler(this);
	}

	@FXML
	private void initialize(){

		/***TEST***/
		this.albumc.setPageOnPane(this.imagepane, 1);

		Picture[] ap = {
		(new Picture(new File("./resources/cat1.jpg").toURI().toString())),
		(new Picture(new File("./resources/cat2.jpg").toURI().toString())),
		};
		datac.addPicturesToViewer(flowPaneImages, boutonAjoutImagePage, ap);

		/***TEST***/

		//TODO : Rafraichissement du viewer des pages : on focus PagesTab ?
		boutonAjoutImagePage.setOnAction((event) -> {
			addCurrentViewerImageToPage();
		});
		boutonAjoutImagePage.setDisable(true);

		menuNouvelAlbum.setOnAction((event) -> {
			generateNewAlbum();
		});

		menuQuitter.setOnAction((event) -> {
			System.exit(0);
		});

		menuNouvellePage.setOnAction((event) -> {
			addNewPage();
		});

		menuAjoutImages.setOnAction((event) -> {
			importPictures();
		});


		tabPages.setOnSelectionChanged((event) -> {
			datac.refreshPagesView(flowPanePages, albumc.getPages());
		});

		nextPage.setOnAction((event) -> {
			goToNextPage();
		});

		previousPage.setOnAction((event) -> {
			goToPreviousPage();
		});

		// Event sur pression du scaleDimension

		scaleDimension.setOnMouseDragged((mouseEvent)->{resizeImage();});
		scaleDimension.setOnMouseClicked((mouseEvent) ->{resizeImage();});

		// Events sur changement des textFields

		this.fieldX.textProperty().addListener((observable, oldValue, newValue) -> {
			
			double new_double;
			try{
				new_double = Double.parseDouble(newValue);

			}catch(Exception e){
				new_double = 0;
				this.fieldX.setText("0");
			}

			this.albumc.moveCurrentImage(new_double, Double.parseDouble(this.fieldY.getText()));

		});

		this.fieldY.textProperty().addListener((observable, oldValue, newValue) -> {
			double new_double;
			try{
				new_double = Double.parseDouble(newValue);

			}catch(Exception e ){
				new_double = 0;
				this.fieldY.setText("0");
			}

			this.albumc.moveCurrentImage(Double.parseDouble(this.fieldX.getText()), new_double);

		});
		
		this.imagepane.setOnScroll((value)->{
			if(albumc.current_image!=null){
			int place_image = albumc.images.indexOf(albumc.current_image);
			double scale = albumc.album.getPage(albumc.current_page).getPictures().get(place_image).getScale();
			
			if(value.getDeltaY()>0 )
			{
				scale=Math.max(scale-0.1,0.1);
				
			}
			else
			{
				scale=Math.min(scale+0.1,1);
			}
			
			this.albumc.resizeImage(scale*100);
			setscalePaneValue(scale*100);
			}
			});

		this.fieldborderWidth.textProperty().addListener((observable, oldValue, newValue) -> {
			double new_double;
			try{
				new_double = Double.parseDouble(newValue);

			}catch(Exception e ){
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

		this.fieldLegende.textProperty().addListener((observable, oldValue, newValue) -> {
			albumc.changeLegende(newValue);
		});

		this.borderColorPicker.setOnAction(e -> albumc.changeBordureColor(borderColorPicker.getValue()));
		
		this.deleteButton.setOnAction(e -> albumc.deleteCurrentImage(imagepane));

		this.setCurrentPageImage(null);

		refreshNavButtonsState();
	}

	private void importPictures(){
		Picture[] p = menuc.selectImages(imagepane.getScene().getWindow());
		if (p!=null){
			addPicturesToViewer(p);
		}
	}

	private void addNewPage() {
		albumc.addNewPage();
		albumc.setPageOnPane(imagepane, albumc.getNbPages());
		datac.refreshPagesView(flowPanePages, albumc.getPages());
		refreshNavButtonsState();
	}

	private void goToNextPage() {
		albumc.setPageOnPane(imagepane, albumc.current_page + 1);
		refreshNavButtonsState();
		datac.refreshPagesView(flowPanePages, albumc.getPages());
	}

	private void goToPreviousPage() {
		albumc.setPageOnPane(imagepane, albumc.current_page - 1);
		refreshNavButtonsState();
		datac.refreshPagesView(flowPanePages, albumc.getPages());
	}

	private void refreshNavButtonsState() {
		int page = albumc.current_page;
		int nbPages = albumc.getNbPages();
		nextPage.setDisable(page == nbPages);
		previousPage.setDisable(page == 1);
		labelNoPage.setText(Integer.toString(page));
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
		this.albumc.addPictureToPage(datac.getCurrentViewerImage(), 1, 1, imagepane);
	}

	private void generateNewAlbum(){
		albumc.newAlbum();
		albumc.setPageOnPane(imagepane, 1);
		refreshNavButtonsState();
	}

	/**
	 * Change l'image selectionn�e, et en informe le controleur
	 */
	public void setCurrentPageImage(ImageView source) {
		albumc.setCurrentImage(source);
		// TODO: Changer toutes les prop du pane de gauche en cons�quence



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
	 * Change les coordonn�es dans les fields du panneau de gauche
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

	public void setfieldLegende(String legende) {
		this.fieldLegende.setText(legende);

	}
	
	public void setdeleteButtonEnabled(boolean b){
		this.deleteButton.setDisable(!b);
	}
	
	public void setscaleDimensionEnabled(boolean b){
		this.scaleDimension.setDisable(!b);
	}
	
	public void setFieldsEnabled(boolean b)
	{
		this.fieldX.setDisable(!b);
		this.fieldY.setDisable(!b);
		this.fieldLegende.setDisable(!b);
		this.fieldborderWidth.setDisable(!b);
	}


}
