package model;

import java.util.ArrayList;

/**
 * Cette classe représente un album photo
 *
 */
public class Album {

	/**
	 * Liste des Pages contenant l'album
	 */
	ArrayList<Page> pages;

	/**
	 * Constructeur par défaut
	 */
	public Album()
	{
		pages = new ArrayList<>();
		Page p = new Page();
		pages.add(p);
	}

	/**
	 * Ajoute une page à l'album
	 * @return Le numéro de la page ajoutée
	 */
	public int addNewPage(){
		Page p = new Page();
		pages.add(p);
		return pages.indexOf(p);
	}

	/**
	 * Retourne la page à l'indice i
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
