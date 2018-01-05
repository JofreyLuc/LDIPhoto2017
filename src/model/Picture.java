package model;


import java.io.File;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public class Picture {

	public double x, y;
	private Image image;
	private int borderWidth;
	private Color borderColor;
	private double scale;
	private String legende;


	public Picture(String i, double x, double y)
	{
		this.image = new Image(i);
		this.x=x;
		this.y=y;
		this.borderWidth = 0;
		this.borderColor = null;
		this.scale=1.;
		this.legende="";
	}

	public Picture(Image i, double x, double y)
	{
		this.image = new WritableImage(i.getPixelReader(), (int)i.getWidth(), (int)i.getHeight());
		this.x=x;
		this.y=y;
		this.borderWidth = 0;
		this.borderColor = null;
		this.scale=1.;
		this.legende="";
	}

	public Picture(String i)
	{
		this.image = new Image(i);
		this.x = 0;
		this.y = 0;
		this.borderWidth = 0;
		this.borderColor = null;
		this.legende="";
	}

	public Image getImage()
	{
		return image;
	}

	public void applyBorder(int width, Color color){
		this.borderWidth = width;
		this.borderColor = color;
	}

	public void setScale(double value) {
		this.scale = value;
		
	}
	
	public double getScale()
	{
		return scale;
	}

	public int getBorderWidth() {
		return this.borderWidth;
	}

	public Color getBorderColor() {
		// TODO Auto-generated method stub
		return this.borderColor;
	}

	public void setLegende(String s) {
		this.legende=s;
		
	}

	public String getLegende() {
		// TODO Auto-generated method stub
		return legende;
	}


}
