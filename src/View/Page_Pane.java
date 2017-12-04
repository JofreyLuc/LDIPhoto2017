package View;

import java.io.File;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class Page_Pane extends VBox {
	
	// Champs des events handlers, a deplacer pour plus de rigueur éventuellement (si on veut vraiment respecter mvc a la lettre)
	double ori_x, ori_y, orgTranslateX, orgTranslateY;
	ImageModifier imageModifier;
	
	public Page_Pane(double x, double y, ImageModifier imagemod)
	{
		this.setMaxSize(x, y);
		this.setMinSize(x, y);
		this.setBackground(new Background(new BackgroundFill(Color.WHITE,CornerRadii.EMPTY, Insets.EMPTY)));
		imageModifier = imagemod;
	}
	
	public void addImage(String uri)
	{
		Image img = new Image(uri);
		ImageView iv = new ImageView(img);
		this.getChildren().add(iv);
		
		// Ajout du contrôle de l'image via les MouseEvent
		iv.setOnMouseDragged(mydragevent);
		iv.setOnMousePressed(myclickevent);
		
		
	}
	
	
	// Event Handler du Drag : A deplacer
	EventHandler<MouseEvent> mydragevent = new EventHandler<MouseEvent>()
	{

		@Override
		public void handle(MouseEvent event) {
			
			ImageView image = (ImageView)(event.getSource());
			double offsetX = event.getSceneX() - ori_x;
            double offsetY = event.getSceneY() - ori_y;
            double newTranslateX = orgTranslateX + offsetX;
            double newTranslateY = orgTranslateY + offsetY;
             
            image.setTranslateX(newTranslateX);
            image.setTranslateY(newTranslateY);
		}

	};
	
	// Event Handler du Click : A deplacer
	EventHandler<MouseEvent> myclickevent = new EventHandler<MouseEvent>()
	{

		@Override
		public void handle(MouseEvent arg0) {
			ori_x = arg0.getSceneX();
			ori_y = arg0.getSceneY();
			orgTranslateX = ((ImageView)(arg0.getSource())).getTranslateX();
            orgTranslateY = ((ImageView)(arg0.getSource())).getTranslateY();
            imageModifier.setImage((ImageView)(arg0.getSource()));
		}

	};

}
