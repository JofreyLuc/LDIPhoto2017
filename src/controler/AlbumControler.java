package controler;

import java.io.File;
import java.util.ArrayList;

import controler.events.OnClickImage;
import controler.events.OnDragImage;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
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
		pi.setScale(0.5);
		album.getPage(1).addPicture(pi);
		
		album.getPage(1).addPicture(new Picture(new File("./resources/img.jpg").toURI().toString(),150,10));
		images = new ArrayList<>();

	}

/**
 * Change la page selectionnée dans le panneau central
 */
	public void setPageOnPane(Pane p, int num_page)
	{
		current_page=num_page;

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
		p.getChildren().add(iv);
		iv.setX(pic.x);
		iv.setY(pic.y);
		//iv.setScaleX(pic.getScale());
		//iv.setScaleY(pic.getScale());
		iv.setFitHeight(iv.getImage().getHeight()*pic.getScale());
		iv.setPreserveRatio(true);
		iv.setOnMousePressed(new OnClickImage(this));
		iv.setOnMouseDragged(new OnDragImage(this));
		images.add(iv);	
		
	}

	/**
	 * Ajoute l'image à la page
	 */
	public void addPictureToPage(ImageView iv, double x, double y, Pane imagepane) {
		Picture p = new Picture(iv.getImage(), x, y);
		album.getPage(current_page).addPicture(p);
		addPictureToPane(p, imagepane);
		
		// On modifie le modele en conséquence
		images.add(iv);
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
			this.current_image.setX(Math.min(Math.max(1,x), windowControler.getPaneWidth()-current_image.getImage().getWidth()*scale-1));
			this.current_image.setY(Math.min(Math.max(1,y), windowControler.getPaneHeight()-current_image.getImage().getHeight()*scale-1));
			//On change les valeurs des fields
			this.windowControler.setCoordField(this.current_image.getX(), this.current_image.getY());
			
			
			// On modifie les coordonnées de la Picture associée
			
			this.album.getPage(this.current_page).getPictures().get(place_image).x = this.current_image.getX();
			this.album.getPage(this.current_page).getPictures().get(place_image).y = this.current_image.getY();

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

	}
}
