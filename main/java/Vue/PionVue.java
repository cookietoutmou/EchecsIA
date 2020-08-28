package Vue;

import java.io.File;

import javafx.scene.image.Image;

public class PionVue extends PieceVue {

    public PionVue() {
        super();
        this.imageBlanche = new Image((new File("C:/Users/yassine/Desktop/IA/Jeux_et_recherche_heuristique/Echecs2/src/main/resources/pionBlanc.jpg")).toURI().toString());
        this.imageNoire = new Image((new File("C:/Users/yassine/Desktop/IA/Jeux_et_recherche_heuristique/Echecs2/src/main/resources/pionNoir.jpg")).toURI().toString()); 
    }
}