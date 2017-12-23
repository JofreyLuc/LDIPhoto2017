package Model;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Model implements Observable {

	/**
	 * Liste de toutes les images importées pour l'album
	 */
	private final ObservableList<Picture> pictures;
	/**
	 * Album contenant la liste des pages
	 */
	private final Album album;

	public Model(){
		this.pictures = FXCollections.observableArrayList();
		this.album = new Album();
	}

	public ObservableList<Picture> getPicturesList(){
		return pictures;
	}

	public void addPictures(Picture... newPictures){
		for (Picture p : newPictures){
			pictures.add(p);
		}
	}

	@Override
	public void addListener(InvalidationListener listener) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeListener(InvalidationListener listener) {
		// TODO Auto-generated method stub

	}
}
