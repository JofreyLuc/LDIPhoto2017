package Model;

import java.util.ArrayList;

import javafx.scene.image.ImageView;

public class Page {
	
	private Album album;
	private ArrayList<Picture> images;
	
	public Page(Album album){
		this.album = album;
		images = new ArrayList<>();
	}

}
