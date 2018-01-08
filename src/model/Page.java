package model;

import java.util.ArrayList;

/**
 * Classe représentant une page de l'album
 *
 */
public class Page {
	
	/**
	 * Liste d'images qui composent la page
	 */
	ArrayList<Picture> pictures;
	
	/**
	 * Constructeur par défaut
	 */
	public Page()
	{
		pictures = new ArrayList<>();
	}
	
	/**
	 * Ajoute une image dans la page
	 * @param p Image à ajouter dans la page
	 */
	public void addPicture(Picture p)
	{
		this.pictures.add(p);
	}

	/**
	 * Retourne toutes les images de la page
	 * @return Liste des images de la page
	 */
	public ArrayList<Picture> getPictures() {
		return pictures;
	}

	
}
