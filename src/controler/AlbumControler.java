package controler;

import java.io.File;

import controler.events.OnClickImage;
import controler.events.OnDragImage;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import model.Album;
import model.Page;
import model.Picture;

public class AlbumControler {
	
	int current_page;
	ImageView current_image;
	WindowControler windowControler;

	// Public pour le moment pour les tests...
	public Album album;
	
	public AlbumControler()
	{
		album = new Album();
		current_page=1;
		
		// Pour le test, on ajoute des photos dans la page 1
		album.getPage(1).addPicture(new Picture(new File("./resources/img.jpg").toURI().toString(),5,5));
		album.getPage(1).addPicture(new Picture(new File("./resources/img.jpg").toURI().toString(),150,10));
		
	}
	
	
	public void setPageOnPane(Pane p, int num_page)
	{
		current_page=num_page;
		
		Page pg = album.getPage(num_page);
		for(Picture pi : pg.getPictures())
		{
			ImageView iv = new ImageView(pi.getImage());
			p.getChildren().add(iv);
			iv.setX(pi.x);
			iv.setY(pi.y);
			iv.setOnMousePressed(new OnClickImage(this));
			iv.setOnMouseDragged(new OnDragImage(this));
			
		}
	}


	public void onChangeCurrentImage(ImageView source) {
		this.windowControler.setCurrentImage(source);
	}


	public void setWindowControler(WindowControler windowControler) {
		this.windowControler = windowControler;
		
	}


	public void setCurrentImage(ImageView source) {
		this.current_image = source;
	}


	public void moveCurrentImage(double x, double y) {
		if(current_image!=null)
		{
		this.current_image.setX(Math.min(Math.max(0,x), windowControler.getPaneWidth()-current_image.getImage().getWidth()));
		this.current_image.setY(Math.min(Math.max(0,y), windowControler.getPaneHeight()-current_image.getImage().getHeight()));
		}
		
	}
}
