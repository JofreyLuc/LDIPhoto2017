package Model;


// Probleme ici : Extend une Image ? Une ImageView ? (on est deja dans la vue si oui, mais ca permet de caser la bordure de l'image, sinon, la classe sert a quoi finalement ?)

public class Picture {
	
	private String uri;
	private Page page;
	
	public Picture(Page page, String uri)
	{
		this.page = page;
		this.uri = uri;
	}

}
