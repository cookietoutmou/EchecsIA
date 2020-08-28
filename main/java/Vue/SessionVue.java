package Vue;

import Controleur.SessionControleur;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

public class SessionVue {
    private ListView<String> lV = new ListView<String>();
    private Button voirLaPartie = new Button("Voir la partie");
    private Button retourBouton = new Button("Retour");
    private SessionControleur CS;
    private Group group = new Group();

    public SessionVue(String session){
        CS = new SessionControleur(session, this);
        ObservableList<String> names = CS.peuplerListe(session);
        lV.setItems(names);
        lV.setMinSize(580, 450);
        lV.setMaxSize(580, 450);
        lV.setTranslateX(10);
        lV.setTranslateY(10);
        voirLaPartie.setTranslateX(510);
        voirLaPartie.setTranslateY(470);
        retourBouton.setTranslateX(10);
        retourBouton.setTranslateY(470);
        retourBouton.setOnMouseClicked(CS.getBoutonRetour());
        voirLaPartie.setOnMouseClicked(CS.getVoirSession());
        group.getChildren().addAll(lV, voirLaPartie, retourBouton);
    }

    public ListView<String> getlV() {
        return lV;
    }
    public void setlV(ListView<String> lV) {
        this.lV = lV;
    }

    public Button getVoirLaPartie() {
        return voirLaPartie;
    }
    public void setVoirLaPartie(Button voirLaPartie) {
        this.voirLaPartie = voirLaPartie;
    }

    public Button getRetourBouton() {
        return retourBouton;
    }
    public void setRetourBouton(Button retourBouton) {
        this.retourBouton = retourBouton;
    }

    public SessionControleur getCS() {
        return CS;
    }
    public void setCS(SessionControleur cS) {
        CS = cS;
    }

    public Group getGroup() {
        return group;
    }
    public void setGroup(Group group) {
        this.group = group;
    }
}