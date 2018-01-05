package controler.events;

import controler.AlbumControler;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;


/**
 * Classe implémentant l'action d'un drag souris sur une image du panneau central
 *
 */
public class OnDragImage implements EventHandler<MouseEvent> {

	AlbumControler a;

	public OnDragImage(AlbumControler a)
	{
		this.a=a;
	}

	@Override
	public void handle(MouseEvent e) {
		double w = ((BorderPane)((Pane)((VBox)e.getSource()).getParent()).getParent()).getWidth()/2 -((Pane)((VBox)e.getSource()).getParent()).getWidth()/2;
		double h = ((BorderPane)((Pane)((VBox)e.getSource()).getParent()).getParent()).getHeight()/2 -((Pane)((VBox)e.getSource()).getParent()).getHeight()/2;
		this.a.moveCurrentImage(e.getSceneX()-w, e.getSceneY()-h);
	}

}
