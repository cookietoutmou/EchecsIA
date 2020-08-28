package Vue;

import Controleur.ControleurHistorique;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

public class HistoriqueVue {
    private ListView<String> lV = new ListView<String>();
    private Button voirLaSession = new Button("Voir la session");
    private Button retourBouton = new Button("Retour");
    private ControleurHistorique CH = new ControleurHistorique();
    private Group group = new Group();
    private static HistoriqueVue instance = new HistoriqueVue();

    private HistoriqueVue(){
        ObservableList<String> names = CH.peuplerListe();
        lV.setItems(names);
        lV.setMinSize(580, 450);
        lV.setMaxSize(580, 450);
        lV.setTranslateX(10);
        lV.setTranslateY(10);
        voirLaSession.setTranslateX(510);
        voirLaSession.setTranslateY(470);
        retourBouton.setTranslateX(10);
        retourBouton.setTranslateY(470);
        retourBouton.setOnMouseClicked(CH.getBoutonRetour());
        voirLaSession.setOnMouseClicked(CH.getVoirSession());
        group.getChildren().addAll(lV, voirLaSession, retourBouton);
    }

    public Button getVoirLaSession() {
        return voirLaSession;
    }
    public void setVoirLaSession(Button voirLaSession) {
        this.voirLaSession = voirLaSession;
    }

    public Button getRetourBouton() {
        return retourBouton;
    }
    public void setRetourBouton(Button retourBouton) {
        this.retourBouton = retourBouton;
    }

    public Group getGroup() {
        return group;
    }
    public void setGroup(Group group) {
        this.group = group;
    }

    public static HistoriqueVue getInstance() {
        return instance;
    }

    public ListView<String> getlV() {
        return lV;
    }
    public void setlV(ListView<String> lV) {
        this.lV = lV;
    }
}