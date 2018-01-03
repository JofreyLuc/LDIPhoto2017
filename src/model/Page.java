package model;

import java.util.ArrayList;

public class Page {
	
	ArrayList<Picture> pictures;
	
	public Page()
	{
		pictures = new ArrayList<>();
	}
	
	public void addPicture(Picture p)
	{
		this.pictures.add(p);
	}

	public ArrayList<Picture> getPictures() {
		return pictures;
	}

	
}
