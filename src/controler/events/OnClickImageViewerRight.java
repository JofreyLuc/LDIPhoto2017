package controler.events;

import controler.DataViewerController;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * Ecouteur gérant le changement de sélection des images du panneau de droite, pour savoir sur quelle image l'on vient de cliquer
 * @author Vincent et Jofrey
 */
public class OnClickImageViewerRight implements EventHandler<MouseEvent> {

	/**
	 * ImageView contenant l'image (gagnera/perdra le style de focus)
	 */
	private ImageView img;

	/**
	 * Contrôleur de la liste des images
	 */
	private DataViewerController datac;

	/**
	 * Constructeur de l'écouteur
	 * @param i - L'image écoutée
	 * @param d - Contrôleur dont dépend l'image
	 */
	public OnClickImageViewerRight(ImageView i, DataViewerController d) {
		this.img = i;
		datac = d;
	}

	@Override
	public void handle(MouseEvent event) {
		// Assigne cette image en tant que sélectionnée
		datac.setCurrentViewerImage(this.img);
	}

}
