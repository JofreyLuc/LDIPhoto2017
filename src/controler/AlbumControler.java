package controler;

import java.io.File;

import model.Album;
import model.Picture;

public class AlbumControler {

	// Public pour le moment pour les tests...
	public Album album;
	
	public AlbumControler()
	{
		album = new Album();
		
		// Pour le test, on ajoute des photos dans la page 1
		album.getPage(1).addPicture(new Picture(new File("./resources/img.jpg").toURI().toString(),5,5));
		album.getPage(1).addPicture(new Picture(new File("./resources/img.jpg").toURI().toString(),150,10));
		
	}
}
