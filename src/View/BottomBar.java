package View;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class BottomBar extends HBox {
	
	Button next, prev;
	TextField value;
	
	public BottomBar(){
		super();
		prev = new Button("Précédent");
		this.getChildren().add(prev);
		TextField value = new TextField("0");
		value.setEditable(false);
		this.getChildren().add(value);
		Button next = new Button("Suivant");
		this.getChildren().add(next);
	}

}
