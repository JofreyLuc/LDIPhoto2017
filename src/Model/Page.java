package Model;

import java.util.ArrayList;

import javafx.scene.image.ImageView;

public class Page {

	/**
	 * Liste des Pictures/Positions de cette Page
	 */
	private ArrayList<PicturePositionPair<Picture, Position>> picturesPositions;

	private int pageNumber;

	public Page(int pn){
		this.picturesPositions = new ArrayList<>();
		pageNumber = pn;
	}

	public void addPicture(Picture pi, Position po){
		this.picturesPositions.add(new PicturePositionPair<Picture, Position>(pi, po));
	}

	/**
	 * Retourne la Position d'une Picture donnée.
	 * @param pi : Picture donnée
	 * @return Position; null si la Picture n'existe pas dans la page; null si la Picture d'entrée est null
	 */
	public Position getPicturePosition(Picture pi){
		if (pi == null) return null;
		for (PicturePositionPair<Picture, Position> p : picturesPositions){
			if (p.getPicture() == pi){
				return p.getPosition();
			}
		}
		return null;
	}
}
