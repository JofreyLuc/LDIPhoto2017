package model;

import java.util.ArrayList;

public class Album {

	ArrayList<Page> pages;


	public Album()
	{
		pages = new ArrayList<>();
		Page p = new Page();
		pages.add(p);
	}


	public Page getPage(int i){
		return pages.get(i-1);
	}

	public ArrayList<Page> getPages(){
		return pages;
	}

}
