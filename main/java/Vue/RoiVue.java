package Vue;

import java.io.File;

import javafx.scene.image.Image;

public class RoiVue extends PieceVue {

    public RoiVue() {
        super();
        this.imageBlanche = new Image((new File("C:/Users/yassine/Desktop/IA/Jeux_et_recherche_heuristique/Echecs2/src/main/resources/roiBlanc.jpg")).toURI().toString());
        this.imageNoire = new Image((new File("C:/Users/yassine/Desktop/IA/Jeux_et_recherche_heuristique/Echecs2/src/main/resources/roiNoir.jpg")).toURI().toString());
    }
}