package controler;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import model.Page;
import model.Picture;


public class WindowControler {
	
	// Correspond au Pane contenant les images au centre de la page
	@FXML
	private Pane imagepane;
	
	private AlbumControler albumc;
	
	public WindowControler(AlbumControler ac){
		albumc = ac;
		//initialize();
	}
	
	@FXML
	private void initialize(){
		this.setPage(albumc.album.getPage(1));
	}
	
	
	
	private void setPage(Page p)
	{
		for(Picture pi : p.getPictures())
		{
			ImageView iv = new ImageView(pi.getImage());
			this.imagepane.getChildren().add(iv);
			iv.setX(pi.x);
			iv.setY(pi.y);
			
		}
	}

}
