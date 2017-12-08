package View;

import java.io.File;

import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class ImageViewer extends TabPane{

	private Tab pictures, pages;

	public ImageViewer() {
		super();
		pictures = createPicturesTab();
		pages = createPagesTab();
		this.getTabs().addAll(pictures, pages);
	}

	private Tab createPicturesTab(){


		Image i1 = new Image(new File("./resources/cat1.jpg").toURI().toString());
		ImageView iv1 = new ImageView(i1);
		Image i2 = new Image(new File("./resources/cat2.jpg").toURI().toString());
		ImageView iv2 = new ImageView(i2);
		Image i3 = new Image(new File("./resources/cat3.jpeg").toURI().toString());
		ImageView iv3 = new ImageView(i3);
		Image i4 = new Image(new File("./resources/cat4.jpg").toURI().toString());
		ImageView iv4 = new ImageView(i4);
		Image i5 = new Image(new File("./resources/cat5.jpg").toURI().toString());
		ImageView iv5 = new ImageView(i5);


		GridPane gp = new GridPane();
		gp.add(iv1, 0, 0);
		gp.add(iv2, 1, 0);
		gp.add(iv3, 0, 1);
		gp.add(iv4, 1, 1);
		gp.add(iv5, 0, 2);
		ScrollPane sp = new ScrollPane();
		sp.setContent(gp);

		Tab t = new Tab("Images", sp);
		t.setClosable(false);
		return t;
	}

	private Tab createPagesTab(){
		Tab t = new Tab("Pages");
		t.setClosable(false);
		return t;
	}
}
