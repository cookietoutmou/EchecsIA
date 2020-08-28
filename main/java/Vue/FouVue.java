package Vue;

import java.io.File;

import javafx.scene.image.Image;

public class FouVue extends PieceVue {

    public FouVue() {
        super();
        this.imageBlanche = new Image((new File("C:/Users/yassine/Desktop/IA/Jeux_et_recherche_heuristique/Echecs2/src/main/resources/fouBlanc.jpg")).toURI().toString());
        this.imageNoire = new Image((new File("C:/Users/yassine/Desktop/IA/Jeux_et_recherche_heuristique/Echecs2/src/main/resources/fouNoir.jpg")).toURI().toString());
    }
}