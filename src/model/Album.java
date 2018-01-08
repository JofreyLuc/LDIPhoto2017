package model;

import java.util.ArrayList;

/**
 * Cette classe repr�sente un album photo
 *
 */
public class Album {

	/**
	 * Liste des Pages contenant l'album
	 */
	ArrayList<Page> pages;

	/**
	 * Constructeur par d�faut
	 */
	public Album()
	{
		pages = new ArrayList<>();
		Page p = new Page();
		pages.add(p);
	}

	/**
	 * Ajoute une page � l'album
	 * @return Le num�ro de la page ajout�e
	 */
	public int addNewPage(){
		Page p = new Page();
		pages.add(p);
		return pages.indexOf(p);
	}

	/**
	 * Retourne la page � l'indice i
	 * @param i Indice de la page
	 * @return La page d'index i
	 */
	public Page getPage(int i){
		return pages.get(i-1);
	}

	/**
	 * Retourne la liste des pages de l'album
	 * @return
	 */
	public ArrayList<Page> getPages(){
		return pages;
	}

}
