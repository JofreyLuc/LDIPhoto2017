package controler.events;

import controler.AlbumControler;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class OnDragImage implements EventHandler<MouseEvent> {

	AlbumControler a;
	
	public OnDragImage(AlbumControler a)
	{
		this.a=a;
	}
	
	@Override
	public void handle(MouseEvent e) {
		this.a.moveCurrentImage(e.getX(), e.getY());
	}

}
