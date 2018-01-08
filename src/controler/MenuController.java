package controler;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javafx.stage.FileChooser;
import javafx.stage.Window;
import model.Picture;

public class MenuController {

	private WindowControler windowc;



	public void setWindowControler(WindowControler w) {
		windowc = w;
	}


	public Picture[] selectImages(Window owner){
		FileChooser fc = new FileChooser();
		fc.setTitle("Importer des images");
		fc.setInitialDirectory(new File(System.getProperty("user.home")));
		fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Images", "*.JPG", "*.PNG", "*.JPEG", "*.PNG", "*.GIF", "*.BMP")
            );
		List<File> list = fc.showOpenMultipleDialog(owner);
		if (list != null) {
			ArrayList<File> imports = new ArrayList<>(list);
			Picture[] pics = new Picture[imports.size()];
			for (int i = 0; i < imports.size(); i++){
				pics[i] = new Picture(imports.get(i).toURI().toString());
			}
			return pics;
		}
		return null;
	}

}
