package model;

import java.awt.Color;
import java.io.File;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;

public class Picture {

	public double x, y;
	private Image image;
	private double borderWidth;
	private Color borderColor;

	public Picture(String i, double x, double y)
	{
		this.image = new Image(i);
		this.x=x;
		this.y=y;
		this.borderWidth = 0;
		this.borderColor = null;
	}

	public Picture(Image i, double x, double y)
	{
		this.image = new WritableImage(i.getPixelReader(), (int)i.getWidth(), (int)i.getHeight());
		this.x=x;
		this.y=y;
		this.borderWidth = 0;
		this.borderColor = null;
	}

	public Picture(String i)
	{
		this.image = new Image(i);
		this.x = 0;
		this.y = 0;
		this.borderWidth = 0;
		this.borderColor = null;
	}

	public Image getImage()
	{
		return image;
	}

	public void applyBorder(double width, Color color){
		this.borderWidth = width;
		this.borderColor = color;
	}


}
