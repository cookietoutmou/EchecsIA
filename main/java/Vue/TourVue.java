package Vue;

import java.io.File;

import javafx.scene.image.Image;

public class TourVue extends PieceVue {

    public TourVue() {
        super();
        this.imageBlanche = new Image((new File("C:/Users/yassine/Desktop/IA/Jeux_et_recherche_heuristique/Echecs2/src/main/resources/tourBlanche.jpg")).toURI().toString());
        this.imageNoire = new Image((new File("C:/Users/yassine/Desktop/IA/Jeux_et_recherche_heuristique/Echecs2/src/main/resources/tourNoire.jpg")).toURI().toString()); 
    }
}