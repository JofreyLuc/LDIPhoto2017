package controler.events;

import controler.DataViewerController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * Ecouteur gerant le changement de focus des images du panneau de droite, pour savoir sur quelle image l'on vient de cliquer
 * @author Jofrey
 */
public class OnFocusImageViewerRight implements EventHandler<MouseEvent> {

	/**
	 * ImageView contenant l'image (gagnera/perdre le style de focus)
	 */
	private ImageView img;

	private DataViewerController datac;

	public OnFocusImageViewerRight(ImageView i, DataViewerController d) {
		this.img = i;
		datac = d;
	}

	@Override
	public void handle(MouseEvent event) {
		datac.setCurrentViewerImage(this.img);
	}

}
