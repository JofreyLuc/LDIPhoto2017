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

/**
 * Contrôleur principal de l'application.
 * C'est lui qui est référencé en tant que contrôleur du fichier FXML; c'est donc lui qui
 * pourra faire les liaisons avec les éléments de l'interface à l'aide des annotations @FXML.
 * Il initialise les écouteurs des éléments crées en FXML et les lie avec les fonctions des
 * contrôleurs secondaires qui effectuent les traitements nécessaires.
 * @author Vincent et Jofrey
 *
 */
public class WindowControler {

	/**
	 * Pane centrale contenant la vue de la page actuelle
	 */
	@FXML
	private Pane imagepane;

	/**
	 * Onglet contenant la FlowPane de la liste des pages
	 */
	@FXML
	private Tab tabPages;

	/**
	 * FlowPane contenant la liste d'images à droite de la page
	 */
	@FXML
	private FlowPane flowPaneImages;

	/**
	 * FlowPane contenant la liste de pages à droite de la page
	 */
	@FXML
	private FlowPane flowPanePages;

	/**
	 * Option "Nouvel album" du menu
	 */
	@FXML
	private MenuItem menuNouvelAlbum;

	/**
	 * Option "Quitter" du menu
	 */
	@FXML
	private MenuItem menuQuitter;

	/**
	 * Option "Nouvelle page" du menu
	 */
	@FXML
	private MenuItem menuNouvellePage;

	/**
	 * Option "Ajouter images.." du menu
	 */
	@FXML
	private MenuItem menuAjoutImages;

	/**
	 * TextField contenant l'abscisse de l'image selectionnée dans le Pane de gauche
	 */
	@FXML
	private TextField fieldX;

	/**
	 * TextField contenant l'ordonnée de l'image selectionnée dans le Pane de gauche
	 */
	@FXML
	private TextField fieldY;

	/**
	 * TextField contenant la largeur de l'image
	 */
	@FXML
	private TextField fieldborderWidth;

	/**
	 * Slider permettant de redimensionner l'image, de 10% à 100% de la taille de base
	 */
	@FXML
	private Slider scaleDimension;

	/**
	 * Bouton permettant l'ajout d'une image dans la page actuelle
	 */
	@FXML
	private Button boutonAjoutImagePage;

	/**
	 * ColorPicker permettant de choisir la couleur de la bordure
	 */
	@FXML
	private ColorPicker borderColorPicker;

	/**
	 * Champ permettant d'éditer la légende d'une image
	 */
	@FXML
	private TextField fieldLegende;

	/**
	 * Label indiquant le numéro de la page actuelle
	 */
	@FXML
	private TextField labelNoPage;

	/**
	 * Bouton permettant d'afficher la page précédente
	 */
	@FXML
	private Button previousPage;

	/**
	 * Bouton permettant d'afficher la prochaine page
	 */
	@FXML
	private Button nextPage;

	/**
	 * Bouton de suppression de l'image sélectionnée
	 */
	@FXML
	private Button deleteButton;

	/**
	 * Sous-contrôleur de la zone "Album"
	 */
	private AlbumControler albumc;

	/**
	 * Sous-contrôleur de la zone "DataViewer"
	 */
	private DataViewerController datac;

	/**
	 * Sous-contrôleur de la zone "Menu"
	 */
	private MenuController menuc;

	/**
	 * Constructeur par défaut, affecte les sous-contrôleurs et s'assigne comme contrôleur principal à chacun d'entre eux
	 * @param ac
	 * @param dc
	 * @param mc
	 */
	public WindowControler(AlbumControler ac, DataViewerController dc, MenuController mc){
		albumc = ac;
		albumc.setWindowControler(this);
		datac = dc;
		datac.setWindowControler(this);
		menuc = mc;
		menuc.setWindowControler(this);
	}

	/**
	 * Initialisation FXML : Initialise tous les listeners des éléments FXML
	 */
	@FXML
	private void initialize(){

		/*** TEST à décommenter pour charger des images ***/
		/*Picture[] ap = {
		(new Picture(new File("./resources/cat1.jpg").toURI().toString())),
		(new Picture(new File("./resources/cat2.jpg").toURI().toString())),
		};
		datac.addPicturesToViewer(flowPaneImages, boutonAjoutImagePage, ap);*/
		/***TEST***/

		// Bouton d'ajout des images à la page
		boutonAjoutImagePage.setOnAction((event) -> {
			addCurrentViewerImageToPage();
		});
		boutonAjoutImagePage.setDisable(true);

		// Items du menu
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

		// Rafraichissement de la liste des pages
		tabPages.setOnSelectionChanged((event) -> {
			datac.refreshPagesView(flowPanePages, albumc.getPages());
		});

		// Boutons de navigation
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

		// Changement de la dimension de l'image avec la molette de la souris
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

		// Champ permettant d'éditer la largeur de la bordure
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

		// Champ permettant d'éditer la légende
		this.fieldLegende.textProperty().addListener((observable, oldValue, newValue) -> {
			albumc.changeLegende(newValue);
		});

		// ColorPicker de la bordure
		this.borderColorPicker.setOnAction(e -> albumc.changeBordureColor(borderColorPicker.getValue()));

		// Bouton de suppression de l'image
		this.deleteButton.setOnAction(e -> albumc.deleteCurrentImage(imagepane));

		// Aucune image actuellement sélectionnée
		this.setCurrentPageImage(null);

		refreshNavButtonsState();
	}

	/**
	 * Ajoute des images à la liste des images importées
	 */
	private void importPictures(){
		Picture[] p = menuc.selectImages(imagepane.getScene().getWindow());
		if (p!=null){
			addPicturesToViewer(p);
		}
	}

	/**
	 * Ajoute une nouvelle page vierge à l'album
	 */
	private void addNewPage() {
		albumc.addNewPage();
		albumc.setPageOnPane(imagepane, albumc.getNbPages());
		datac.refreshPagesView(flowPanePages, albumc.getPages());
		refreshNavButtonsState();
	}

	/**
	 * Affiche la prochaine page de l'album
	 */
	private void goToNextPage() {
		albumc.setPageOnPane(imagepane, albumc.current_page + 1);
		refreshNavButtonsState();
		datac.refreshPagesView(flowPanePages, albumc.getPages());
	}

	/**
	 * Affiche la précédente page de l'album
	 */
	private void goToPreviousPage() {
		albumc.setPageOnPane(imagepane, albumc.current_page - 1);
		refreshNavButtonsState();
		datac.refreshPagesView(flowPanePages, albumc.getPages());
	}

	/**
	 * Met à jour l'état des boutons et du label de navigation dans l'album (désactive les boutons)
	 */
	private void refreshNavButtonsState() {
		int page = albumc.current_page;
		int nbPages = albumc.getNbPages();
		nextPage.setDisable(page == nbPages);
		previousPage.setDisable(page == 1);
		labelNoPage.setText(Integer.toString(page));
	}

	/**
	 * Ajoute des images dans le panneau de droite grace au DataViewerController
	 * @param pictures - Les images à ajouter
	 */
	private void addPicturesToViewer(Picture... pictures)
	{
		this.datac.addPicturesToViewer(flowPaneImages, boutonAjoutImagePage, pictures);
	}

	public void setAddButtonActive(boolean b){
		boutonAjoutImagePage.setDisable(!b);
	}

	/**
	 * Ajoute une nouvelle Picture dans la page a partir de l'ImageView qui est sélectionnée dans le panneau de droite
	 */
	private void addCurrentViewerImageToPage() {
		this.albumc.addPictureToPage(datac.getCurrentViewerImage(), 1, 1, imagepane);
	}

	/**
	 * Supprime l'album précédent et en génère un nouveau
	 */
	private void generateNewAlbum(){
		albumc.newAlbum();
		albumc.setPageOnPane(imagepane, 1);
		refreshNavButtonsState();
	}

	/**
	 * Change l'image sélectionnée dans la page, et en informe le controleur
	 */
	public void setCurrentPageImage(ImageView source) {
		albumc.setCurrentImage(source);

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
	 * @return double
	 */
	public double getPaneWidth() {
		return imagepane.getWidth();
	}

	/**
	 * Retourne la hauteur du panneau central
	 * @return double
	 */
	public double getPaneHeight() {
		return imagepane.getHeight();
	}

	/**
	 * Change les coordonnées dans les fields du panneau de gauche
	 * @param x
	 * @param y
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
	 * @param scale
	 */
	public void setscalePaneValue(double scale) {
		this.scaleDimension.adjustValue(scale);
	}

	/**
	 * Met à jour la valeur du champ de la lageur de la bordure
	 * @param i
	 */
	public void setBorderWidth(int i) {
		this.fieldborderWidth.setText(""+i);
	}

	/**
	 * Assigne une couleur au colorPicker
	 * @param borderColor
	 */
	public void setColorPicker(Color borderColor) {
		this.borderColorPicker.setValue(borderColor);
	}

	/**
	 * Assigne une valeur au champ de la légende
	 * @param legende
	 */
	public void setfieldLegende(String legende) {
		this.fieldLegende.setText(legende);
	}

	/**
	 * Active ou désactive le bouton de suppression
	 * @param b
	 */
	public void setdeleteButtonEnabled(boolean b){
		this.deleteButton.setDisable(!b);
	}

	/**
	 * Active ou désactive le slider de redimensionnement
	 * @param b
	 */
	public void setscaleDimensionEnabled(boolean b){
		this.scaleDimension.setDisable(!b);
	}

	/**
	 * Active ou désactive les champs de modification de l'image
	 * @param b
	 */
	public void setFieldsEnabled(boolean b)
	{
		this.fieldX.setDisable(!b);
		this.fieldY.setDisable(!b);
		this.fieldLegende.setDisable(!b);
		this.fieldborderWidth.setDisable(!b);
	}


}
