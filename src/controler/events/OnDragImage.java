package controler.events;

import controler.AlbumControler;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;


/**
 * Classe impl�mentant l'action d'un drag souris sur une image du panneau central
 *
 */
public class OnDragImage implements EventHandler<MouseEvent> {

	/**
	 * Classe contr�leur li�e � l'album
	 */
	AlbumControler a;

	/**
	 * Constructeur du EventHandler
	 * @param a : Contr�leur li� � l'album
	 */
	public OnDragImage(AlbumControler a)
	{
		this.a=a;
	}

	/**
	 * Gestionnaire de l'event
	 */
	public void handle(MouseEvent e) {
		double w = ((BorderPane)((Pane)((VBox)e.getSource()).getParent()).getParent()).getWidth()/2 -((Pane)((VBox)e.getSource()).getParent()).getWidth()/2;
		double h = ((BorderPane)((Pane)((VBox)e.getSource()).getParent()).getParent()).getHeight()/2 -((Pane)((VBox)e.getSource()).getParent()).getHeight()/2;
		this.a.moveCurrentImage(e.getSceneX()-w, e.getSceneY()-h);
	}

}
