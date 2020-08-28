package Vue;

import Controleur.ConfigPartiesControleur;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class ConfigPartiesVue {
    private Text joueur1Text = new Text("Joueur 1:");
    private Text joueur2Text = new Text("Joueur 2:");
    private TextField joueur1TextField = new TextField();
    private TextField joueur2TextField = new TextField();
    private Text couleurJ1Text = new Text("Choix couleur joueur 1:");
    private Text couleurJ2Text = new Text("Choix couleur joueur 2:");
    private CheckBox blancsJ1 = new CheckBox("Blancs");
    private CheckBox noirsJ1 = new CheckBox("Noirs");
    private CheckBox blancsJ2 = new CheckBox("Blancs");
    private CheckBox noirsJ2 = new CheckBox("Noirs");
    private Text algo1Text = new Text("Choix de l'algorithme:");
    private Text algo2Text = new Text("Choix de l'algorithme:");
    private CheckBox algo1CheckBoxAB = new CheckBox("Alpha-Beta");
    private CheckBox algo1CheckBoxMC = new CheckBox("Monte-Carlo");
    private CheckBox algo2CheckBoxAB = new CheckBox("Alpha_Beta");
    private CheckBox algo2CheckBoxMC = new CheckBox("Monte-Carlo");
    private Text profAB1Text = new Text("Profondeur:");
    private Text nbrMC1Text = new Text("Echantillon:");
    private Text profAB2Text = new Text("Profondeur:");
    private Text nbrMC2Text = new Text("Echantillon:");
    private TextField profAB1TextField = new TextField();
    private TextField nbrMC1TextField = new TextField();
    private TextField profAB2TextField = new TextField();    
    private TextField nbrMC2TextField = new TextField();
    private Text nbrPartiesText = new Text("Nombre de parties:");
    private TextField nbrPartiesTextField = new TextField("1");
    private Button lancerLesParties = new Button("Lancer les parties");
    private Integer partiesEffectuees = 0;
    private Integer nombreDeParties = 0;
    private Text progression = new Text();
    private Text partiesGagneesJ1 = new Text();
    private Integer nbrGagneesJ1 = 0;
    private Integer nbrGagneesJ2 = 0;
    private Text partiesGagneesJ2 = new Text();
    private Button retourBouton = new Button("Retour");
    private ConfigPartiesControleur CPC = new ConfigPartiesControleur();
    private Group racine = new Group();
    private static ConfigPartiesVue instance = new ConfigPartiesVue();

    private ConfigPartiesVue() {
        joueur1Text.setTranslateX(30);
        joueur1Text.setTranslateY(30);
        joueur2Text.setTranslateX(330);
        joueur2Text.setTranslateY(30);
        joueur1TextField.setTranslateX(90);
        joueur1TextField.setTranslateY(15);
        joueur2TextField.setTranslateX(390);
        joueur2TextField.setTranslateY(15);
        couleurJ1Text.setTranslateX(30);
        couleurJ1Text.setTranslateY(80);
        couleurJ2Text.setTranslateX(330);
        couleurJ2Text.setTranslateY(80);
        blancsJ1.setTranslateX(160);
        noirsJ1.setTranslateX(220);
        blancsJ2.setTranslateX(460);
        noirsJ2.setTranslateX(520);
        blancsJ1.setTranslateY(65);
        blancsJ2.setTranslateY(65);
        noirsJ1.setTranslateY(65);
        noirsJ2.setTranslateY(65);
        algo1Text.setTranslateX(0);
        algo1Text.setTranslateY(130);
        algo2Text.setTranslateX(300);
        algo2Text.setTranslateY(130);
        algo1CheckBoxAB.setTranslateX(120);
        algo1CheckBoxAB.setTranslateY(115);
        algo1CheckBoxMC.setTranslateX(205);
        algo1CheckBoxMC.setTranslateY(115);
        algo2CheckBoxAB.setTranslateX(420);
        algo2CheckBoxAB.setTranslateY(115);
        algo2CheckBoxMC.setTranslateX(505);
        algo2CheckBoxMC.setTranslateY(115);
        profAB1Text.setTranslateX(30);
        profAB1Text.setTranslateY(180);
        profAB2Text.setTranslateX(330);
        profAB2Text.setTranslateY(180);
        profAB1TextField.setTranslateX(105);
        profAB1TextField.setTranslateY(165);
        profAB1TextField.setDisable(true);
        profAB2TextField.setTranslateX(405);
        profAB2TextField.setTranslateY(165);
        profAB2TextField.setDisable(true);
        nbrMC1Text.setTranslateX(30);
        nbrMC1Text.setTranslateY(230);
        nbrMC2Text.setTranslateX(330);
        nbrMC2Text.setTranslateY(230);
        nbrMC1TextField.setTranslateX(110);
        nbrMC1TextField.setTranslateY(215);
        nbrMC1TextField.setDisable(true);
        nbrMC2TextField.setTranslateX(410);
        nbrMC2TextField.setTranslateY(215);
        nbrMC2TextField.setDisable(true);
        nbrPartiesText.setTranslateX(180);
        nbrPartiesText.setTranslateY(280);
        nbrPartiesTextField.setTranslateX(290);
        nbrPartiesTextField.setTranslateY(265);
        retourBouton.setTranslateX(30);
        retourBouton.setTranslateY(450);
        lancerLesParties.setTranslateX(490);
        lancerLesParties.setTranslateY(450);
        partiesGagneesJ1.setText("Le joueur 1 a gagne " + nbrGagneesJ1.toString() + " parties");
        partiesGagneesJ2.setText("Le joueur 2 a gagne " + nbrGagneesJ2.toString() + " parties");
        partiesGagneesJ1.setTranslateX(30);
        partiesGagneesJ1.setTranslateY(380);
        partiesGagneesJ2.setTranslateX(390);
        partiesGagneesJ2.setTranslateY(380);
        blancsJ1.setOnMouseClicked(CPC.getRestrictionCouleurs());
        blancsJ2.setOnMouseClicked(CPC.getRestrictionCouleurs());
        noirsJ1.setOnMouseClicked(CPC.getRestrictionCouleurs());
        noirsJ2.setOnMouseClicked(CPC.getRestrictionCouleurs());
        algo1CheckBoxAB.setOnMouseClicked(CPC.getRestrictionAlgo());
        algo1CheckBoxMC.setOnMouseClicked(CPC.getRestrictionAlgo());
        algo2CheckBoxAB.setOnMouseClicked(CPC.getRestrictionAlgo());
        algo2CheckBoxMC.setOnMouseClicked(CPC.getRestrictionAlgo());
        progression.setTranslateX(230);
        progression.setTranslateY(330);
        progression.setText(partiesEffectuees.toString() + " parties effectuees sur " + nombreDeParties.toString());
        retourBouton.setOnMouseClicked(CPC.getRetour());
        lancerLesParties.setOnMouseClicked(CPC.getLancerLesParties());
        racine.getChildren().addAll(joueur1Text, joueur2Text, joueur1TextField, joueur2TextField, couleurJ1Text, couleurJ2Text, blancsJ1, noirsJ1, blancsJ2, noirsJ2, algo1Text, algo2Text, algo1CheckBoxAB, algo2CheckBoxAB, algo1CheckBoxMC, algo2CheckBoxMC, profAB1Text, profAB2Text, profAB1TextField, profAB2TextField, nbrMC1Text, nbrMC2Text, nbrMC1TextField, nbrMC2TextField, nbrPartiesText, nbrPartiesTextField, progression, retourBouton, lancerLesParties, partiesGagneesJ1, partiesGagneesJ2);
    }

    public Text getJoueur1Text() {
        return joueur1Text;
    }
    public void setJoueur1Text(Text joueur1Text) {
        this.joueur1Text = joueur1Text;
    }

    public Text getJoueur2Text() {
        return joueur2Text;
    }
    public void setJoueur2Text(Text joueur2Text) {
        this.joueur2Text = joueur2Text;
    }

    public TextField getJoueur1TextField() {
        return joueur1TextField;
    }
    public void setJoueur1TextField(TextField joueur1TextField) {
        this.joueur1TextField = joueur1TextField;
    }

    public TextField getJoueur2TextField() {
        return joueur2TextField;
    }
    public void setJoueur2TextField(TextField joueur2TextField) {
        this.joueur2TextField = joueur2TextField;
    }

    public Text getAlgo1Text() {
        return algo1Text;
    }
    public void setAlgo1Text(Text algo1Text) {
        this.algo1Text = algo1Text;
    }

    public Text getAlgo2Text() {
        return algo2Text;
    }
    public void setAlgo2Text(Text algo2Text) {
        this.algo2Text = algo2Text;
    }

    public CheckBox getAlgo1CheckBoxAB() {
        return algo1CheckBoxAB;
    }
    public void setAlgo1CheckBoxAB(CheckBox algo1CheckBoxAB) {
        this.algo1CheckBoxAB = algo1CheckBoxAB;
    }

    public CheckBox getAlgo1CheckBoxMC() {
        return algo1CheckBoxMC;
    }
    public void setAlgo1CheckBoxMC(CheckBox algo1CheckBoxMC) {
        this.algo1CheckBoxMC = algo1CheckBoxMC;
    }

    public CheckBox getAlgo2CheckBoxAB() {
        return algo2CheckBoxAB;
    }
    public void setAlgo2CheckBoxAB(CheckBox algo2CheckBoxAB) {
        this.algo2CheckBoxAB = algo2CheckBoxAB;
    }

    public CheckBox getAlgo2CheckBoxMC() {
        return algo2CheckBoxMC;
    }
    public void setAlgo2CheckBoxMC(CheckBox algo2CheckBoxMC) {
        this.algo2CheckBoxMC = algo2CheckBoxMC;
    }

    public Text getProfAB1Text() {
        return profAB1Text;
    }
    public void setProfAB1Text(Text profAB1Text) {
        this.profAB1Text = profAB1Text;
    }

    public Text getNbrMC1Text() {
        return nbrMC1Text;
    }
    public void setNbrMC1Text(Text nbrMC1Text) {
        this.nbrMC1Text = nbrMC1Text;
    }

    public Text getProfAB2Text() {
        return profAB2Text;
    }
    public void setProfAB2Text(Text profAB2Text) {
        this.profAB2Text = profAB2Text;
    }

    public Text getNbrMC2Text() {
        return nbrMC2Text;
    }
    public void setNbrMC2Text(Text nbrMC2Text) {
        this.nbrMC2Text = nbrMC2Text;
    }

    public TextField getProfAB1TextField() {
        return profAB1TextField;
    }
    public void setProfAB1TextField(TextField profAB1TextField) {
        this.profAB1TextField = profAB1TextField;
    }

    public TextField getNbrMC1TextField() {
        return nbrMC1TextField;
    }
    public void setNbrMC1TextField(TextField nbrMC1TextField) {
        this.nbrMC1TextField = nbrMC1TextField;
    }

    public TextField getProfAB2TextField() {
        return profAB2TextField;
    }
    public void setProfAB2TextField(TextField profAB2TextField) {
        this.profAB2TextField = profAB2TextField;
    }

    public TextField getNbrMC2TextField() {
        return nbrMC2TextField;
    }
    public void setNbrMC2TextField(TextField nbrMC2TextField) {
        this.nbrMC2TextField = nbrMC2TextField;
    }

    public Text getNbrPartiesText() {
        return nbrPartiesText;
    }
    public void setNbrPartiesText(Text nbrPartiesText) {
        this.nbrPartiesText = nbrPartiesText;
    }

    public TextField getNbrPartiesTextField() {
        return nbrPartiesTextField;
    }
    public void setNbrPartiesTextField(TextField nbrPartiesTextField) {
        this.nbrPartiesTextField = nbrPartiesTextField;
    }

    public Button getRetourBouton() {
        return retourBouton;
    }
    public void setRetourBouton(Button retourBouton) {
        this.retourBouton = retourBouton;
    }

    public ConfigPartiesControleur getCPC() {
        return CPC;
    }
    public void setCPC(ConfigPartiesControleur cPC) {
        CPC = cPC;
    }

    public Group getRacine() {
        return racine;
    }
    public void setRacine(Group racine) {
        this.racine = racine;
    }

    public static ConfigPartiesVue getInstance() {
        return instance;
    }

    public Text getCouleurJ1Text() {
        return couleurJ1Text;
    }
    public void setCouleurJ1Text(Text couleurJ1Text) {
        this.couleurJ1Text = couleurJ1Text;
    }

    public Text getCouleurJ2Text() {
        return couleurJ2Text;
    }
    public void setCouleurJ2Text(Text couleurJ2Text) {
        this.couleurJ2Text = couleurJ2Text;
    }

    public CheckBox getBlancsJ1() {
        return blancsJ1;
    }
    public void setBlancsJ1(CheckBox blancsJ1) {
        this.blancsJ1 = blancsJ1;
    }

    public CheckBox getNoirsJ1() {
        return noirsJ1;
    }
    public void setNoirsJ1(CheckBox noirsJ1) {
        this.noirsJ1 = noirsJ1;
    }

    public CheckBox getBlancsJ2() {
        return blancsJ2;
    }
    public void setBlancsJ2(CheckBox blancsJ2) {
        this.blancsJ2 = blancsJ2;
    }

    public CheckBox getNoirsJ2() {
        return noirsJ2;
    }
    public void setNoirsJ2(CheckBox noirsJ2) {
        this.noirsJ2 = noirsJ2;
    }

    public Button getLancerLesParties() {
        return lancerLesParties;
    }
    public void setLancerLesParties(Button lancerLesParties) {
        this.lancerLesParties = lancerLesParties;
    }

    public Text getPartiesGagneesJ1() {
        return partiesGagneesJ1;
    }
    public void setPartiesGagneesJ1(Text partiesGagneesJ1) {
        this.partiesGagneesJ1 = partiesGagneesJ1;
    }

    public Text getPartiesGagneesJ2() {
        return partiesGagneesJ2;
    }
    public void setPartiesGagneesJ2(Text partiesGagneesJ2) {
        this.partiesGagneesJ2 = partiesGagneesJ2;
    }

    public Integer getPartiesEffectuees() {
        return partiesEffectuees;
    }
    public void setPartiesEffectuees(Integer partiesEffectuees) {
        this.partiesEffectuees = partiesEffectuees;
    }

    public Integer getNombreDeParties() {
        return nombreDeParties;
    }
    public void setNombreDeParties(Integer nombreDeParties) {
        this.nombreDeParties = nombreDeParties;
    }

    public Integer getNbrGagneesJ1() {
        return nbrGagneesJ1;
    }
    public void setNbrGagneesJ1(Integer nbrGagneesJ1) {
        this.nbrGagneesJ1 = nbrGagneesJ1;
    }

    public Integer getNbrGagneesJ2() {
        return nbrGagneesJ2;
    }
    public void setNbrGagneesJ2(Integer nbrGagneesJ2) {
        this.nbrGagneesJ2 = nbrGagneesJ2;
    }

    public Text getProgression() {
        return progression;
    }
    public void setProgression(Text progression) {
        this.progression = progression;
    }
}