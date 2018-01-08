package controler;

import java.io.File;
import java.util.ArrayList;

import controler.events.OnClickImageViewerRight;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
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
	//TODO: A bouger ?
	private final static int PICTURE_SIZE = 100;

	private ImageView currentViewerImage;

	private WindowControler windowc;

	public DataViewerController() {
		currentViewerImage = null;
	}

	public void setWindowControler(WindowControler w) {
		windowc = w;
	}

	/**
	 * Reduit la largeur et la hauteur d'une image, proportionnellement, jusqu'à ce qu'aucune des deux valeurs ne dépasse PICTURE_SIZE
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
	public void addPicturesToViewer(FlowPane fp, Button b, Picture... pictures){
		for (Picture p : pictures) {
			ImageView newPic = new ImageView(p.getImage());

			newPic.setOnMouseClicked(new OnClickImageViewerRight(newPic, this));

			this.resizeToThumbnail(newPic);

			VBox box = new VBox();
			box.setAlignment(Pos.CENTER);
			box.getChildren().add(newPic);

			fp.getChildren().add(box);
		}
	}

	public ImageView getCurrentViewerImage(){
		return currentViewerImage;
	}

	public void setCurrentViewerImage(ImageView iv) {
		if (this.currentViewerImage != null) {
			currentViewerImage.getParent().setStyle("-fx-border-color: gray; -fx-border-width: 0;");
	    	windowc.setAddButtonActive(false);
		}
		this.currentViewerImage = iv;
		if (this.currentViewerImage != null) {
			currentViewerImage.getParent().setStyle("-fx-border-color: gray; -fx-border-width: 3;");
			windowc.setAddButtonActive(true);
		}
	}

	/**
	 * Retire une image de l'onglet des images
	 * @param fp - Le panneau de l'onglet images
	 * @param toRemove - Picture à retirer
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
	public void refreshPagesView(FlowPane fp, ArrayList<Page> pages) {
		fp.getChildren().clear();
		int pNumber = 0;
		for (Page p : pages){
			pNumber++;

			Pane pagePane = new Pane();
			Scene s = new Scene(pagePane); // Necessaire pour que le css soit applique
			pagePane.setPrefSize(450, 600);
			pagePane.setStyle("-fx-background-color: white;");

			// Construction de la page
			for(Picture pic : p.getPictures())
			{
				ImageView iv = new ImageView(pic.getImage());
				VBox box = new VBox();
				box.getChildren().add(iv);
				pagePane.getChildren().add(box);

				box.setLayoutX(pic.x);
				box.setLayoutY(pic.y);
				iv.setFitHeight(iv.getImage().getHeight()*pic.getScale());
				iv.setPreserveRatio(true);

				if(pic.getBorderWidth()>=1 && pic.getBorderColor()!=null)
					box.setStyle("-fx-border-color: rgb("+pic.getBorderColor().getRed()*255+","+pic.getBorderColor().getGreen()*255+","+pic.getBorderColor().getBlue()*255+");"+ "-fx-border-width: "+pic.getBorderWidth()+";");
				else
					box.setStyle("");

				Label l = new Label(pic.getLegende());
				box.getChildren().add(l);
				box.setAlignment(Pos.CENTER);
				l.setStyle("-fx-text-alignment: center;");

			}
			// Snapshot de la page et ajout
			WritableImage snapshot = pagePane.snapshot(new SnapshotParameters(), null);
			ImageView pagePreview = new ImageView(snapshot);
			this.resizeToThumbnail(pagePreview);

			VBox vbox = new VBox();
			Label no = new Label(Integer.toString(pNumber));
			vbox.setAlignment(Pos.CENTER);
			vbox.getChildren().addAll(pagePreview, no);

			fp.getChildren().add(vbox);
		}
	}


}
