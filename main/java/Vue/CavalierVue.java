package Vue;

import java.io.File;

import javafx.scene.image.Image;

public class CavalierVue extends PieceVue {

    public CavalierVue() {
        super();
        this.imageBlanche = new Image((new File("C:/Users/yassine/Desktop/IA/Jeux_et_recherche_heuristique/Echecs2/src/main/resources/cavalierBlanc.jpg")).toURI().toString());
        this.imageNoire = new Image((new File("C:/Users/yassine/Desktop/IA/Jeux_et_recherche_heuristique/Echecs2/src/main/resources/cavalierNoir.jpg")).toURI().toString());
    }
}