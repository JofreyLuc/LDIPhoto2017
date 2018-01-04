package controler;

import java.io.File;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import model.Page;
import model.Picture;

/**
 * Pseudo-controleur de la zone de droite affichant la liste des images importees et la liste des pages dans deux onglets.
 * @author Jofrey
 */

public class DataViewerController {

	/**
	 *  Taille maximale des thumbnails des images dans la liste
	 */
	/* A BOUGER ? */
	private final static int PICTURE_SIZE = 100;

	private Button addPictureToPage;

	public DataViewerController() {

	}

	/**
	 * Reduit la largeur et la hauteur d'une image, proportionnellement, jusqu'� ce qu'aucune des deux valeurs ne d�passe PICTURE_SIZE
	 * @param iv - ImageView a reduire
	 */
	private void resizeToThumbnail(ImageView iv){
		double width = iv.getImage().getWidth(), height = iv.getImage().getHeight();
		double coeff = PICTURE_SIZE / Math.max(width, height);
		iv.setFitWidth(width * coeff);
		iv.setFitHeight(height * coeff);
	}

	/**
	 * Ajoute un ensemble d'images dans l'onglet des images
	 * @param fp - Le panneau de l'onglet images
	 * @param pictures - L'ensemble d'images a ajouter
	 */
	public void addPicturesToViewer(FlowPane fp, Picture... pictures){
		for (Picture p : pictures) {
			ImageView newPic = new ImageView(p.getImage());
			newPic.setFocusTraversable(true);
			newPic.setOnMouseClicked((event) -> {
				newPic.requestFocus();
			});

			this.resizeToThumbnail(newPic);
			fp.getChildren().add(newPic);
		}
	}

	/**
	 * Retire une image de l'onglet des images
	 * @param fp - Le panneau de l'onglet images
	 * @param toRemove - Picture � retirer
	 * @return true si l'operation a reussi
	 */
	public boolean removePictureFromViewer(FlowPane fp, Picture toRemove){
		return fp.getChildren().remove(toRemove);
	}

	/**
	 * Supprime la liste des pages et en affiche une nouvelle
	 * @param fp - Le panneau de l'onglet images
	 * @param pages - Le nouvel ensemble de pages a afficher
	 */
	public void refreshPagesView(FlowPane fp, Page... pages) {
		fp.getChildren().clear();
		for (Page p : pages){
			Pane pagePane = new Pane();
			pagePane.setPrefSize(450, 600);
			pagePane.getStylesheets().add("-fx-background-color: white");
			for(Picture pi : p.getPictures())
			{
				ImageView iv = new ImageView(pi.getImage());
				pagePane.getChildren().add(iv);
				iv.setX(pi.x);
				iv.setY(pi.y);
			}
			WritableImage snapshot = pagePane.snapshot(new SnapshotParameters(), null);
			ImageView pagePreview = new ImageView(snapshot);
			this.resizeToThumbnail(pagePreview);
			fp.getChildren().add(pagePreview);
		}
	}


}