package Vue;

import java.util.ArrayList;
import java.util.List;

import Controleur.ControleurPartie;
import Modele.Coup;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class PartieVue{
    private Text j1 = new Text();
    private Text j2 = new Text();
    private ColonneChiffres chiffres = new ColonneChiffres();
    private LigneLettres lettres = new LigneLettres();
    private Text tourJoueur = new Text();
    private Text vainqueur = new Text();
    private GrilleVue gV = new GrilleVue();
    private Button retourBouton = new Button("Retour");
    private Text algoJ1 = new Text();
    private Text algoJ2 = new Text();
    private ControleurPartie CP; 
    private List<Coup> historiqueCoups = new ArrayList<Coup>();
    private List<Coup> historiqueCoupsInverse = new ArrayList<Coup>();
    private Group group = new Group();

    public PartieVue(String partie, String session) {
        CP = new ControleurPartie(partie, session, this);
        CP.peuplerListe(historiqueCoups, historiqueCoupsInverse);
        CP.montrerCaracPartie();
        this.j1.setFont(Font.font("Abyssinica SIL", FontWeight.BOLD, FontPosture.REGULAR, 20));
        this.j2.setFont(Font.font("Abyssinica SIL", FontWeight.BOLD, FontPosture.REGULAR, 20));
        this.gV.setTranslateX(130);
        this.gV.setTranslateY(80);
        this.chiffres.setTranslateX(110);
        this.chiffres.setTranslateY(90);
        this.lettres.setTranslateX(145);
        this.lettres.setTranslateY(400);
        this.tourJoueur.setX(10);
        this.tourJoueur.setY(240);
        this.vainqueur.setX(450);
        this.vainqueur.setY(230);
        this.vainqueur.setFont(Font.font("Abyssinica SIL", FontWeight.BOLD, FontPosture.REGULAR, 20));
        this.vainqueur.setFill(Color.RED);
        this.tourJoueur.setFont(Font.font("Abyssinica SIL", FontWeight.BOLD, FontPosture.REGULAR, 12));
        this.retourBouton.setTranslateX(10);
        this.retourBouton.setTranslateY(470);
        this.retourBouton.setFocusTraversable(false);
        this.retourBouton.setOnMouseClicked(CP.getBoutonRetour());
        this.group.getChildren().addAll(this.j1, this.j2, this.chiffres, this.lettres, this.gV, this.tourJoueur, this.vainqueur, this.algoJ1, this.algoJ2, this.retourBouton);
    }

    public Text getJ1() {
        return j1;
    }
    public void setJ1(Text j1) {
        this.j1 = j1;
    }

    public Text getJ2() {
        return j2;
    }
    public void setJ2(Text j2) {
        this.j2 = j2;
    }

    public GrilleVue getGV() {
        return gV;
    }
    public void setGV(GrilleVue gV) {
        this.gV = gV;
    }

    public Group getGroup() {
        return group;
    }
    public void setGroup(Group group) {
        this.group = group;
    }

    public Text getVainqueur() {
        return vainqueur;
    }
    public void setVainqueur(Text vainqueur) {
        this.vainqueur = vainqueur;
    }

    public Text getTourJoueur() {
        return tourJoueur;
    }
    public void setTourJoueur(Text tourJoueur) {
        this.tourJoueur = tourJoueur;
    }

    public ControleurPartie getCP() {
        return CP;
    }
    public void setCP(ControleurPartie cP) {
        CP = cP;
    }

    public List<Coup> getHistoriqueCoups() {
        return historiqueCoups;
    }
    public void setHistoriqueCoups(List<Coup> historiqueCoups) {
        this.historiqueCoups = historiqueCoups;
    }

    public Button getRetourBouton() {
        return retourBouton;
    }
    public void setRetourBouton(Button retourBouton) {
        this.retourBouton = retourBouton;
    }

    public Text getAlgoJ1() {
        return algoJ1;
    }
    public void setAlgoJ1(Text algoJ1) {
        this.algoJ1 = algoJ1;
    }

    public Text getAlgoJ2() {
        return algoJ2;
    }
    public void setAlgoJ2(Text algoJ2) {
        this.algoJ2 = algoJ2;
    }

    public GrilleVue getgV() {
        return gV;
    }
    public void setgV(GrilleVue gV) {
        this.gV = gV;
    }

    public List<Coup> getHistoriqueCoupsInverse() {
        return historiqueCoupsInverse;
    }
    public void setHistoriqueCoupsInverse(List<Coup> historiqueCoupsInverse) {
        this.historiqueCoupsInverse = historiqueCoupsInverse;
    }
}