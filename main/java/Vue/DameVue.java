package Vue;

import java.io.File;

import javafx.scene.image.Image;

public class DameVue extends PieceVue {

    public DameVue() {
        super();
        this.imageBlanche = new Image((new File("C:/Users/yassine/Desktop/IA/Jeux_et_recherche_heuristique/Echecs2/src/main/resources/dameBlanche.jpg")).toURI().toString());
        this.imageNoire = new Image((new File("C:/Users/yassine/Desktop/IA/Jeux_et_recherche_heuristique/Echecs2/src/main/resources/dameNoire.jpg")).toURI().toString());
    }
}