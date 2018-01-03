package model;

import java.io.File;

import javafx.scene.image.Image;

public class Picture {
	
	public double x, y;
	Image image;
	
	
	public Picture(String i, double x, double y)
	{
		this.image = new Image(i);
		this.x=x;
		this.y=y;
	}
	
	public Image getImage()
	{
		return image;
	}
	
	

}
