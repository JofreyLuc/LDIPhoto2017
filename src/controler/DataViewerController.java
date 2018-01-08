package controler;

import java.io.File;

import controler.events.OnClickImageViewerRight;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Node;
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

	private ImageView currentViewerImage;

	private WindowControler windowc;

	//TODO : Utiliser une liste de Pictures du modele ? Dans l'album ?

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
			fp.getChildren().add(newPic);
		}
	}

	public ImageView getCurrentViewerImage(){
		return currentViewerImage;
	}

	public void setCurrentViewerImage(ImageView iv) {
		if (this.currentViewerImage != null) {
			this.currentViewerImage.setOpacity(1);
	    	this.currentViewerImage.setRotate(0);
	    	windowc.setAddButtonActive(false);
		}
		this.currentViewerImage = iv;
		if (this.currentViewerImage != null) {
			this.currentViewerImage.setOpacity(.5);
	    	this.currentViewerImage.setRotate(25);
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

	private void resizePaneToThumbnail(Pane p){
		double width = p.getPrefWidth(), height = p.getPrefHeight();
		double coeff = PICTURE_SIZE / Math.max(width, height);
		p.setPrefWidth(width * coeff);
		p.setPrefHeight(height * coeff);
		for (Node n : p.getChildren()){
			double ivWidth = ((ImageView)n).getImage().getWidth(), ivHeight = ((ImageView)n).getImage().getHeight();
			((ImageView)n).setFitWidth(ivWidth * coeff);
			((ImageView)n).setFitHeight(ivHeight * coeff);
			((ImageView)n).setX(((ImageView)n).getX() * coeff);
			((ImageView)n).setY(((ImageView)n).getY() * coeff);
		}
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
			pagePane.setStyle("-fx-background-color: white;");
			for(Picture pi : p.getPictures())
			{
				ImageView iv = new ImageView(pi.getImage());
				pagePane.getChildren().add(iv);
				iv.setX(pi.x);
				iv.setY(pi.y);
			}
			resizePaneToThumbnail(pagePane);
			fp.getChildren().add(pagePane);
		}
	}


}
