package controler;

import java.io.File;
import java.util.ArrayList;

import controler.events.OnClickImage;
import controler.events.OnDragImage;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import model.Album;
import model.Page;
import model.Picture;

public class AlbumControler {

	/**
	 * Liste des ImageView de la page actuelle, pour pouvoir faire le lien avec le modele
	 * Dans la liste, l'élément i est l'ImageView correspondant à la Picture i de Page
	 */
	ArrayList<ImageView> images;

	/**
	 * Page actuellement en avant sur le panneau central
	 */
	int current_page;

	/**
	 * Image actuellement selectionnée dans le panneau central
	 */
	ImageView current_image;

	/**
	 * Controleur
	 */
	WindowControler windowControler;


	/**
	 * Modèle associé
	 */
	public Album album;

	public AlbumControler()
	{
		album = new Album();
		current_page=1;

		// Pour le test, on ajoute des photos dans la page 1
		Picture pi = new Picture(new File("./resources/img.jpg").toURI().toString(),5,5);
		pi.setLegende("Hola");
		pi.applyBorder(50, new Color(0, 0.5, 0.5,1));
		pi.setScale(0.5);
		album.getPage(1).addPicture(pi);

		album.getPage(1).addPicture(new Picture(new File("./resources/img.jpg").toURI().toString(),150,10));
		images = new ArrayList<>();

	}

	public ArrayList<Page> getPages(){
		return album.getPages();
	}

	public int getNbPages(){
		return album.getPages().size();
	}

	public void addNewPage(){
		album.addNewPage();
	}

	public void newAlbum(){
		album = new Album();
		current_page=1;
		images.clear();
		current_image = null;
		windowControler.setCurrentPageImage(null);
	}

/**
 * Change la page selectionnée dans le panneau central
 */
	public void setPageOnPane(Pane p, int num_page)
	{
		p.getChildren().clear();
		images.clear();
		current_page=num_page;
		current_image = null;
		windowControler.setCurrentPageImage(null);

		Page pg = album.getPage(num_page);
		for(Picture pi : pg.getPictures())
		{
			this.addPictureToPane(pi, p);
		}
	}


	/**
	 * Ajoute une image au panneau central
	 */
	private void addPictureToPane(Picture pic, Pane p){
		ImageView iv = new ImageView(pic.getImage());
		images.add(iv);
		VBox box = new VBox();
		box.getChildren().add(iv);
		//Label lab = new Label("Coucou");
		//box.getChildren().add(lab);
		p.getChildren().add(box);
		//iv.setX(pic.x);
		iv.getParent().setLayoutX(pic.x);
		iv.getParent().setLayoutY(pic.y);
		iv.setFitHeight(iv.getImage().getHeight()*pic.getScale());
		iv.setPreserveRatio(true);


		this.changeBordure(iv);
		this.changeLegende(iv);
		box.setOnMousePressed(new OnClickImage(this));
		box.setOnMouseDragged(new OnDragImage(this));

		// Test CSS bordure
		//iv.getParent().setStyle("-fx-border-color: rgb(255,0,0);"+ "-fx-border-width: 1;");


	}

	/**
	 * Ajoute l'image à la page
	 */
	public void addPictureToPage(ImageView iv, double x, double y, Pane imagepane) {
		Image newImage = new WritableImage(iv.getImage().getPixelReader(), (int)iv.getImage().getWidth(), (int)iv.getImage().getHeight());
		ImageView newIV = new ImageView(newImage);

		int effectiveWidth = (int)Math.min(iv.getImage().getWidth(), imagepane.getWidth() - 2);
		int effectiveHeight = (int)Math.min(iv.getImage().getHeight(), imagepane.getHeight() - 2);

		newIV.setPreserveRatio(true);
		newIV.setFitWidth(effectiveWidth);
		newIV.setFitHeight(effectiveHeight);

		Image resizedImage = newIV.snapshot(null, null);

		Picture p = new Picture(resizedImage, x, y);
		album.getPage(current_page).addPicture(p);
		addPictureToPane(p, imagepane);

	}

	public void onChangeCurrentImage(ImageView source) {
		this.windowControler.setCurrentPageImage(source);
	}


	public void setWindowControler(WindowControler windowControler) {
		this.windowControler = windowControler;

	}

/**
 * Change l'image selectionnée dans le panneau central
 */
	public void setCurrentImage(ImageView source) {
		if(this.current_image!=null)
			this.current_image.setOpacity(1);
		this.current_image = source;
		if(this.current_image!=null)
		{

			this.current_image.setOpacity(0.5);
			int place_image = this.images.indexOf(this.current_image);
			this.windowControler.setscalePaneValue(this.album.getPage(this.current_page).getPictures().get(place_image).getScale()*100);


			this.windowControler.setCoordField(this.album.getPage(this.current_page).getPictures().get(place_image).x, this.album.getPage(this.current_page).getPictures().get(place_image).y);
			this.windowControler.setBorderWidth(this.album.getPage(this.current_page).getPictures().get(place_image).getBorderWidth());
			this.windowControler.setColorPicker(this.album.getPage(this.current_page).getPictures().get(place_image).getBorderColor());
			this.changeLegende(this.current_image);
			this.windowControler.setdeleteButtonEnabled(true);
			this.windowControler.setscaleDimensionEnabled(true);
			this.windowControler.setFieldsEnabled(true);

		}
		else
		{
			this.windowControler.setdeleteButtonEnabled(false);
			this.windowControler.setfieldLegende("");
			this.windowControler.setscaleDimensionEnabled(false);
			this.windowControler.setCoordField(0, 0);
			this.windowControler.setFieldsEnabled(false);
			
		}
	}

/**
 * Déplace l'image selectionnée aux coordonnées indiquées
 */
	public void moveCurrentImage(double x, double y) {
		if(current_image!=null)
		{
			// On change les coord de la vue
			int place_image = this.images.indexOf(this.current_image);
			double scale = this.album.getPage(this.current_page).getPictures().get(place_image).getScale();
			this.current_image.getParent().setLayoutX(Math.min(Math.max(1,x), windowControler.getPaneWidth()-current_image.getParent().getBoundsInParent().getWidth()-1));
			this.current_image.getParent().setLayoutY(Math.min(Math.max(1,y), windowControler.getPaneHeight()-current_image.getParent().getBoundsInParent().getHeight()-1));


			// this.current_image.getParent().getLayoutBounds().getMinX()

			// On modifie les coordonnées de la Picture associée

			//this.album.getPage(this.current_page).getPictures().get(place_image).x = this.current_image.getX();
			//this.album.getPage(this.current_page).getPictures().get(place_image).y = this.current_image.getY();
			this.album.getPage(this.current_page).getPictures().get(place_image).x = this.current_image.getParent().getLayoutX();
			this.album.getPage(this.current_page).getPictures().get(place_image).y = this.current_image.getParent().getLayoutY();

			//On change les valeurs des fields
			this.windowControler.setCoordField(this.album.getPage(this.current_page).getPictures().get(place_image).x, this.album.getPage(this.current_page).getPictures().get(place_image).y);


		}

	}

/**
 * Re-scale l'image selectionnée
 */
	public void resizeImage(double value) {
		//this.current_image.setScaleX(value/100);
		//this.current_image.setScaleY(value/100);
		this.current_image.setFitHeight(this.current_image.getImage().getHeight()*value/100);

		// On enregistre le scale dans Picture pour une réutilisation postérieure
		int place_image = this.images.indexOf(this.current_image);
		this.album.getPage(this.current_page).getPictures().get(place_image).setScale(value/100);
		this.moveCurrentImage(this.album.getPage(this.current_page).getPictures().get(place_image).x, this.album.getPage(this.current_page).getPictures().get(place_image).y);

	}

	public void changeBordure(ImageView img)
	{
		//TODO: Faire une methode pour retourner ca, j'ia l'impression de l'avoir cc souvent ?
		int place_image = this.images.indexOf(img);
		Picture pi = this.album.getPage(this.current_page).getPictures().get(place_image);
		if(pi.getBorderWidth()>=1 && pi.getBorderColor()!=null)
			img.getParent().setStyle("-fx-border-color: rgb("+pi.getBorderColor().getRed()*255+","+pi.getBorderColor().getGreen()*255+","+pi.getBorderColor().getBlue()*255+");"+ "-fx-border-width: "+pi.getBorderWidth()+";");
		else
			img.getParent().setStyle("");
	}

	public void changeBordureWidth(double d) {
		if(current_image!=null)
		{
		int place_image = this.images.indexOf(this.current_image);
		Picture pi = this.album.getPage(this.current_page).getPictures().get(place_image);
		if(pi.getBorderColor()==null)
			pi.applyBorder((int)d, new Color(0,0,0,1));
		else
			pi.applyBorder((int)d, pi.getBorderColor());
		this.changeBordure(current_image);
		}
	}

	public void changeBordureColor(Color c)
	{
		if(current_image!=null){
		int place_image = this.images.indexOf(this.current_image);
		Picture pi = this.album.getPage(this.current_page).getPictures().get(place_image);
		pi.applyBorder(pi.getBorderWidth(), c);
		this.changeBordure(current_image);
		}
	}

	public void changeLegende(String newValue) {
		if(current_image!=null){
		int place_image = this.images.indexOf(this.current_image);
		Picture pi = this.album.getPage(this.current_page).getPictures().get(place_image);
		pi.setLegende(newValue);
		this.changeLegende(current_image);
		}
		else
			this.windowControler.setfieldLegende("");

	}

	public void changeLegende(ImageView img)
	{
		if(current_image!=null){
		int place_image = this.images.indexOf(img);
		Picture pi = this.album.getPage(this.current_page).getPictures().get(place_image);
		VBox parent = ((VBox)img.getParent());
		if(parent.getChildren().size()==1)
		{
			Label l = new Label(pi.getLegende());
			parent.getChildren().add(l);
			parent.setAlignment(Pos.CENTER);
			l.setStyle("-fx-text-alignment: center;");
		}
		else
		{
			Label l = (Label) parent.getChildren().get(1);
			l.setText(pi.getLegende());
		}
		
		if(pi.getLegende().equals(""))
		{
			parent.getChildren().remove(1);
			this.windowControler.setfieldLegende("");
		}
		else
			this.windowControler.setfieldLegende(pi.getLegende());
		}
		else
			this.windowControler.setfieldLegende("");
	}

	public void deleteCurrentImage(Pane p) {
		p.getChildren().remove(this.current_image.getParent());
		int place_image = this.images.indexOf(this.current_image);
		this.album.getPage(this.current_page).getPictures().remove(place_image);
		this.images.remove(place_image);
		this.setCurrentImage(null);
	}
}
