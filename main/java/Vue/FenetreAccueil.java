package Vue;

import Controleur.TraitementBoutonsAccueil;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;

public class FenetreAccueil {
    private Group racine = new Group();
    private Scene scene = new Scene(racine, 600, 500, Color.GRAY);
    private Button bt1 = new Button();
    private Button bt2 = new Button();
    private TraitementBoutonsAccueil TBA = new TraitementBoutonsAccueil(bt1, bt2);
    private static FenetreAccueil fenetreAccueil = new FenetreAccueil();

    private FenetreAccueil(){
        this.bt1.setText("Lancer des parties");
        this.bt2.setText("Consulter l'historique");
        this.bt1.setTranslateX(200);
        this.bt1.setTranslateY(240);
        this.bt2.setTranslateX(350);
        this.bt2.setTranslateY(240);
        this.racine.getChildren().addAll(bt1, bt2);
    }

    public static FenetreAccueil getInstance(){
        return fenetreAccueil;
    }

    public Group getRacine() {
        return racine;
    }
    public void setRacine(Group racine) {
        this.racine = racine;
    }

    public Scene getScene() {
        return scene;
    }
    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public Button getBt1() {
        return bt1;
    }
    public void setBt1(Button bt1) {
        this.bt1 = bt1;
    }

    public Button getBt2() {
        return bt2;
    }
    public void setBt2(Button bt2) {
        this.bt2 = bt2;
    }
    public TraitementBoutonsAccueil getTBA() {
        return TBA;
    }
    public void setTBA(TraitementBoutonsAccueil tBA) {
        TBA = tBA;
    }
}