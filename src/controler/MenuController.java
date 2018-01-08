package controler;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javafx.stage.FileChooser;
import javafx.stage.Window;
import model.Picture;

/**
 * Sous contr�leur g�rant les items de la barre de menu qui le n�cessitent.
 * @author Vincent et Jofrey
 *
 */
public class MenuController {

	/**
	 * Contr�leur principal de la fen�tre
	 */
	private WindowControler windowc;

	/**
	 * Assigne le contr�leur principal
	 * @param w
	 */
	public void setWindowControler(WindowControler w) {
		windowc = w;
	}

	/**
	 * Ouvre un fileChooser et renvoie les fichiers image s�lectionn�s, et null si aucun n'a �t� choisi
	 * @param owner - Fen�tre dont d�pend le fileChooser
	 * @return Picture[] ou null
	 */
	public Picture[] selectImages(Window owner){
		FileChooser fc = new FileChooser();
		fc.setTitle("Importer des images");
		fc.setInitialDirectory(new File(System.getProperty("user.home")));
		fc.getExtensionFilters().addAll(
				// Formats de fichier accept�s
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
