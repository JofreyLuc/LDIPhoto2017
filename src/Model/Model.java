package Model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Model {

	/* Liste de toutes les images importees */
	private final ObservableList<Picture> pictures = FXCollections.observableArrayList();
	/* Album (contenant la liste des pages */
	private final ObjectProperty<Album> album = new SimpleObjectProperty<>(null);

	public Album getAlbum() {
		return album.get();
	}

	public void setAlbum(Album a) {
		album.set(a);
	}

	public ObservableList<Picture> getPicturesList(){
		return pictures;
	}

	public void addPictures(Picture... newPictures){
		for (Picture p : newPictures){
			pictures.add(p);
		}
	}
}
