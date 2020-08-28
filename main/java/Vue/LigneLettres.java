package Vue;

import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class LigneLettres extends HBox{
    public LigneLettres(){
        super();
        String[] lettres = new String[] {"A", "B", "C", "D", "E", "F", "G", "H"};
        for (int i = 0; i < 8; i++) {
            Text t = new Text(lettres[i]);
            t.setFont(Font.font("Abyssinica SIL", FontWeight.BOLD, FontPosture.REGULAR, 15));
            this.getChildren().add(t);
        }
        this.setSpacing(30);
    }
}