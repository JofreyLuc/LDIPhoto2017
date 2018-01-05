package controler.events;

import controler.AlbumControler;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

/**
 * Classe implémentant l'action d'un clic souris sur une image du panneau central
 *
 */
public class OnClickImage implements EventHandler<MouseEvent> {

	AlbumControler a;
	public OnClickImage(AlbumControler a)
	{
		this.a=a;
	}
	
	@Override
	public void handle(MouseEvent e) {
		// TODO Auto-generated method stub
		this.a.onChangeCurrentImage((ImageView)((VBox)e.getSource()).getChildren().get(0));
		
	}

}
