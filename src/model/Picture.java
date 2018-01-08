package model;


import java.io.File;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

/**
 * Classe repr�sentant une image d'un album photo
 *
 */
public class Picture {

	/**
	 * Abscisse de l'image
	 */
	public double x;
	
	/**
	 * Ordonn�e de l'image
	 */
	public double y;
	
	/**
	 * Source de l'image
	 */
	private Image image;
	
	/**
	 * Epaisseur du cadre de l'image
	 */
	private int borderWidth;
	
	/**
	 * Couleur du cadre de l'image
	 */
	private Color borderColor;
	
	/**
	 * Echelle � laquelle est affich�e l'image dans l'album
	 */
	private double scale;
	
	/**
	 * L�gende de l'image
	 */
	private String legende;

	/**
	 * Constructeur d'une Image � des coordonn�es pr�cises
	 * @param i Source de l'image
	 * @param x Abscisse de l'image
	 * @param y Ordonn�e de l'image
	 */
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

	/**
	 * Constructeur d'une image � partir d'un fichier, � des coordonn�es par d�faut (0,0)
	 * @param i Source de l'image
	 */
	public Picture(String i)
	{
		this.image = new Image(i);
		this.x = 0;
		this.y = 0;
		this.borderWidth = 0;
		this.borderColor = null;
		this.legende="";
	}

	/**
	 * Getter
	 * @return La source de l'image
	 */
	public Image getImage()
	{
		return image;
	}

	/**
	 * Change le cadre de l'image
	 * @param width Nouvelle �paisseur
	 * @param color Nouvelle couleur
	 */
	public void applyBorder(int width, Color color){
		this.borderWidth = width;
		this.borderColor = color;
	}

	/**
	 * Change l'�chelle de l'image
	 * @param value Nouvelle �chelle
	 */
	public void setScale(double value) {
		this.scale = value;
		
	}
	
	/**
	 * Getter - Echelle de l'image
	 * @return L'�chelle de l'image
	 */
	public double getScale()
	{
		return scale;
	}

	/**
	 * Getter - Retourne l'�paisseur du cadre
	 * @return Epaisseur du cadre
	 */
	public int getBorderWidth() {
		return this.borderWidth;
	}

	/**
	 * Getter - Retourne la couleur du cadre
	 * @return Couleur du cadre
	 */
	public Color getBorderColor() {
		return this.borderColor;
	}

	/**
	 * Setter - Change la l�gende de l'image
	 * @param s Nouvelle l�gende de l'image
	 */
	public void setLegende(String s) {
		this.legende=s;
		
	}

	/**
	 * Getter - Retourne la l�gende de l'image
	 * @return La l�gende de l'image
	 */
	public String getLegende() {
		return legende;
	}


}
