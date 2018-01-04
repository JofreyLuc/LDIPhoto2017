package controler.events;

import controler.AlbumControler;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class OnClickImage implements EventHandler<MouseEvent> {

	AlbumControler a;
	public OnClickImage(AlbumControler a)
	{
		this.a=a;
	}
	
	@Override
	public void handle(MouseEvent e) {
		// TODO Auto-generated method stub
		this.a.onChangeCurrentImage((ImageView) e.getSource());
		
	}

}
