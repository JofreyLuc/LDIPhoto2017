package View;

import java.io.File;

import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class ImageViewer extends TabPane{

	/* Onglets de visualisation de la liste des pages et de la liste des images */
	private Tab pictures, pages;
	/* Taille maximale des thumbnails des images dans la liste */
	private final static int PICTURE_SIZE = 150;

	public ImageViewer() {
		super();
		pictures = createPicturesTab();
		pages = createPagesTab();
		this.getTabs().addAll(pictures, pages);
	}

	/* Cree l'onglet des la liste des images */
	private Tab createPicturesTab(){

		GridPane gp = new GridPane();

		/* TEMP : test de la vue */
		Image i1 = new Image(new File("./resources/cat1.jpg").toURI().toString());
		ImageView iv1 = new ImageView(i1);
		Image i2 = new Image(new File("./resources/cat2.jpg").toURI().toString());
		ImageView iv2 = new ImageView(i2);
		Image i3 = new Image(new File("./resources/cat3.jpeg").toURI().toString());
		ImageView iv3 = new ImageView(i3);
		Image i4 = new Image(new File("./resources/cat4.jpg").toURI().toString());
		ImageView iv4 = new ImageView(i4);
		Image i5 = new Image(new File("./resources/cat5.jpg").toURI().toString());
		ImageView iv5 = new ImageView(i5);
		ImageView iv6 = new ImageView(i1);
		ImageView iv7 = new ImageView(i2);
		ImageView iv8 = new ImageView(i3);
		ImageView iv9 = new ImageView(i4);
		ImageView iv10 = new ImageView(i5);
		ImageView iv11 = new ImageView(i1);
		ImageView iv12 = new ImageView(i2);
		ImageView iv13 = new ImageView(i3);
		ImageView iv14 = new ImageView(i4);
		ImageView iv15 = new ImageView(i5);

		resizeToThumbnail(iv1);
		resizeToThumbnail(iv2);
		resizeToThumbnail(iv3);
		resizeToThumbnail(iv4);
		resizeToThumbnail(iv5);
		resizeToThumbnail(iv6);
		resizeToThumbnail(iv7);
		resizeToThumbnail(iv8);
		resizeToThumbnail(iv9);
		resizeToThumbnail(iv10);
		resizeToThumbnail(iv11);
		resizeToThumbnail(iv12);
		resizeToThumbnail(iv13);
		resizeToThumbnail(iv14);
		resizeToThumbnail(iv15);

		gp.add(iv1, 0, 0);
		GridPane.setMargin(iv1, new Insets(15));
		gp.add(iv2, 1, 0);
		GridPane.setMargin(iv2, new Insets(15));
		gp.add(iv3, 0, 1);
		GridPane.setMargin(iv3, new Insets(15));
		gp.add(iv4, 1, 1);
		GridPane.setMargin(iv4, new Insets(15));
		gp.add(iv5, 0, 2);
		GridPane.setMargin(iv5, new Insets(15));
		gp.add(iv6, 1, 2);
		GridPane.setMargin(iv6, new Insets(15));
		gp.add(iv7, 0, 3);
		GridPane.setMargin(iv7, new Insets(15));
		gp.add(iv8, 1, 3);
		GridPane.setMargin(iv8, new Insets(15));
		gp.add(iv9, 0, 4);
		GridPane.setMargin(iv9, new Insets(15));
		gp.add(iv10, 1, 4);
		GridPane.setMargin(iv10, new Insets(15));
		gp.add(iv11, 0, 5);
		GridPane.setMargin(iv11, new Insets(15));
		gp.add(iv12, 1, 5);
		GridPane.setMargin(iv12, new Insets(15));
		gp.add(iv13, 0, 6);
		GridPane.setMargin(iv13, new Insets(15));
		gp.add(iv14, 1, 6);
		GridPane.setMargin(iv14, new Insets(15));
		gp.add(iv15, 0, 7);
		GridPane.setMargin(iv15, new Insets(15));
		/* TEMP */



		ScrollPane sp = new ScrollPane();
		sp.setContent(gp);

		Tab t = new Tab("Images", sp);
		t.setClosable(false);
		return t;
	}

	/* Redimensionne une imageView à la taille macimale autorisee des thumbnails */
	/* A bouger ? */
	private void resizeToThumbnail(ImageView iv){
		double width = iv.getImage().getWidth(), height = iv.getImage().getHeight();
		double coeff = PICTURE_SIZE / Math.max(width, height);
		iv.setFitWidth(width * coeff);
		iv.setFitHeight(height * coeff);
	}

	private Tab createPagesTab(){
		Tab t = new Tab("Pages");
		t.setClosable(false);
		return t;
	}
}
