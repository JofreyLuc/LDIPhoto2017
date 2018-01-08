package controler;

import java.util.ArrayList;

import controler.events.OnClickImageViewerRight;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import model.Page;
import model.Picture;

/**
 * Controleur secondaire de la zone de droite affichant la liste des images import�es et la liste des pages dans deux onglets.
 * @author Vincent et Jofrey
 */

public class DataViewerController {

	/**
	 * Taille maximale des thumbnails des images dans la liste
	 */
	private final static int PICTURE_SIZE = 100;

	/**
	 * Image actuellement s�lectionn�e dans la liste des images
	 */
	private ImageView currentViewerImage;

	/**
	 * Contr�leur principal
	 */
	private WindowControler windowc;


	public DataViewerController() {
		currentViewerImage = null;
	}

	/**
	 * Setter du contr�leur principal
	 * @param w - Contr�leur de la fen�tre
	 */
	public void setWindowControler(WindowControler w) {
		windowc = w;
	}

	/**
	 * R�duit la largeur et la hauteur d'une image, proportionnellement, jusqu'� ce qu'aucune des deux valeurs ne d�passe PICTURE_SIZE
	 * @param iv - ImageView � r�duire
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
	 * @param pictures - L'ensemble d'images � ajouter
	 */
	public void addPicturesToViewer(FlowPane fp, Button b, Picture... pictures){
		for (Picture p : pictures) {
			ImageView newPic = new ImageView(p.getImage());

			// Listener de clic pour la s�lection
			newPic.setOnMouseClicked(new OnClickImageViewerRight(newPic, this));

			this.resizeToThumbnail(newPic);

			VBox box = new VBox();
			box.setAlignment(Pos.CENTER);
			box.getChildren().add(newPic);

			fp.getChildren().add(box);
		}
	}

	/**
	 * Getter de l'image actuellement s�lectionn�e
	 * @return ImageView
	 */
	public ImageView getCurrentViewerImage(){
		return currentViewerImage;
	}

	/**
	 * Met � jour l'image s�lectionn�e et l'image pr�c�demment s�lectionn�e
	 * @param iv - Nouvelle ImageView s�lectionn�e
	 */
	public void setCurrentViewerImage(ImageView iv) {
		if (this.currentViewerImage != null) {
			//D�selection de l'ancienne image
			currentViewerImage.getParent().setStyle("-fx-border-color: gray; -fx-border-width: 0;");
	    	windowc.setAddButtonActive(false);
		}
		this.currentViewerImage = iv;
		if (this.currentViewerImage != null) {
			//S�lection de la nouvelle image
			currentViewerImage.getParent().setStyle("-fx-border-color: gray; -fx-border-width: 3;");
			windowc.setAddButtonActive(true);
		}
	}

	/**
	 * Retire une image de l'onglet des images
	 * @param fp - Le panneau de l'onglet images
	 * @param toRemove - Picture � retirer
	 * @return true si l'op�ration a reussi
	 */
	public boolean removePictureFromViewer(FlowPane fp, Picture toRemove){
		return fp.getChildren().remove(toRemove);
	}

	/**
	 * Supprime la liste des pages et en affiche une nouvelle
	 * @param fp - Le panneau de l'onglet images
	 * @param pages - Le nouvel ensemble de pages � afficher
	 */
	public void refreshPagesView(FlowPane fp, ArrayList<Page> pages) {
		fp.getChildren().clear();
		int pNumber = 0;
		for (Page p : pages){
			pNumber++;

			Pane pagePane = new Pane();
			Scene s = new Scene(pagePane); // N�cessaire pour que le css soit appliqu�
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
