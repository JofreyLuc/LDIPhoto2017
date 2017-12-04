package View;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Accordion;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class ImageModifier extends Accordion {
	
	ImageView image_modified;
	TextField field_abs, field_ord;
	
	public ImageModifier()
	{
		super();
		GridPane grid = new GridPane();
		TitledPane title = new TitledPane("Position de l'image", grid);
		
		grid.add(new Label("Abscisse"), 0,0);
		field_abs = new TextField();
		grid.add(field_abs, 1,0);
		
		field_ord = new TextField();
		grid.add(new Label("Ordonnée"), 0,1);
		grid.add(field_ord, 1,1);
		
		this.getPanes().add(title);
		
		GridPane grid2 = new GridPane();
		TitledPane title2 = new TitledPane("Bordure de l'image", grid2);
		
		grid2.add(new Label("Type"), 0,0);
		ObservableList<String> options =
				FXCollections.observableArrayList("Sans", "Simple");
		ComboBox liste = new ComboBox(options);
		grid2.add(liste, 1,0);
		
		this.getPanes().add(title2);
	}
	
	public void setImage(ImageView img)
	{
		image_modified = img;
		refresh();
	}
	
	public void refresh()
	{
		field_abs.setText(String.valueOf(image_modified.localToScene(image_modified.getBoundsInParent()).getMinX()));
		field_ord.setText(String.valueOf(image_modified.localToScene(image_modified.getBoundsInParent()).getMinY()));
	}

}
