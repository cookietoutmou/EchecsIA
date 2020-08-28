package Vue;

import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class ColonneChiffres extends VBox{
    public ColonneChiffres(){
        super();
        for (int i = 1; i < 9; i++) {
            Text t = new Text(((Integer)i).toString());
            t.setFont(Font.font("Abyssinica SIL", FontWeight.BOLD, FontPosture.REGULAR, 15));
            this.getChildren().add(t);
        }
        this.setSpacing(20);
    }
}