package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Album {

	private ObservableList<Page> pages;

	public Album()
	{
		pages = FXCollections.observableArrayList();
	}

	public void newPage(){
		pages.add(new Page(pages.size()));
	}

	public void removePage(int indexToRemove){
		/**
		 * TODO
		 */
	}
}
