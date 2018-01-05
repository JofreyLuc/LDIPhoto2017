package controler.events;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

/**
 * Ecouteur gerant le changement de focus des images du panneau de droite, pour savoir sur quelle image l'on vient de cliquer
 * @author Jofrey
 */
public class OnFocusImageViewerRight implements ChangeListener<Boolean> {

	/**
	 * Bouton d'ajout de l'image à la page (sera active/desactive)
	 */
	private Button addToPageButton;

	/**
	 * ImageView contenant l'image (gagnera/perdre le style de focus)
	 */
	private ImageView img;

	public OnFocusImageViewerRight(ImageView i, Button b) {
		this.img = i;
		this.addToPageButton = b;
	}

	@Override
	public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
		if (newValue) {
			// On gagne le focus
	    	this.img.setOpacity(.5);
	    	this.img.setRotate(25);
	    	this.addToPageButton.setDisable(false);
	    }
	    else {
	    	// On perd le focus
	    	this.img.setOpacity(1);
	    	this.img.setRotate(0);
	    	this.addToPageButton.setDisable(true);
	    }
	}

}
