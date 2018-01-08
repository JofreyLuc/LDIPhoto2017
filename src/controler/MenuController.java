package controler;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javafx.stage.FileChooser;
import javafx.stage.Window;
import model.Picture;

/**
 * Sous contrôleur gérant les items de la barre de menu qui le nécessitent.
 * @author Vincent et Jofrey
 *
 */
public class MenuController {

	/**
	 * Contrôleur principal de la fenêtre
	 */
	private WindowControler windowc;

	/**
	 * Assigne le contrôleur principal
	 * @param w
	 */
	public void setWindowControler(WindowControler w) {
		windowc = w;
	}

	/**
	 * Ouvre un fileChooser et renvoie les fichiers image sélectionnés, et null si aucun n'a été choisi
	 * @param owner - Fenêtre dont dépend le fileChooser
	 * @return Picture[] ou null
	 */
	public Picture[] selectImages(Window owner){
		FileChooser fc = new FileChooser();
		fc.setTitle("Importer des images");
		fc.setInitialDirectory(new File(System.getProperty("user.home")));
		fc.getExtensionFilters().addAll(
				// Formats de fichier acceptés
                new FileChooser.ExtensionFilter("Images", "*.JPG", "*.PNG", "*.JPEG", "*.PNG", "*.BMP")
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
