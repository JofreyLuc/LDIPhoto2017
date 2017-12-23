package Model;

import java.awt.Color;

// Probleme ici : Extend une Image ? Une ImageView ? (on est deja dans la vue si oui, mais ca permet de caser la bordure de l'image, sinon, la classe sert a quoi finalement ?)

public class Picture {

	/**
	 * URI indiquant l'emplacement de l'image
	 */
	private String pictureURI;
	//private Page page;
	/**
	 * Largeur de la bordure de l'image
	 */
	private double borderWidth;
	/**
	 * Couleur de la bordure de l'image
	 */
	private Color borderColor;

	public Picture(String uri)
	{
		//this.page = page;
		this.pictureURI = uri;
	}

	public void applyBorder(double width, Color color){
		this.borderWidth = width;
		this.borderColor = color;
	}

}
