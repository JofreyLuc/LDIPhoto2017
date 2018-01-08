package controler.events;

import controler.AlbumControler;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

/**
 * Classe impl�mentant l'action d'un clic souris sur une image du panneau central
 *
 */
public class OnClickImage implements EventHandler<MouseEvent> {

	/**
	 * Classe contr�leur li�e � l'album
	 */
	AlbumControler a;
	
	/**
	 * Constructeur du EventHandler
	 * @param a : Contr�leur li� � l'album
	 */
	public OnClickImage(AlbumControler a)
	{
		this.a=a;
	}
	
	/**
	 * Gestionnaire de l'event
	 */
	public void handle(MouseEvent e) {
		this.a.onChangeCurrentImage((ImageView)((VBox)e.getSource()).getChildren().get(0));
		
	}

}
