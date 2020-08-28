package Controleur;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Vue.FenetreAccueil;
import Vue.HistoriqueVue;
import Vue.SessionVue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class ControleurHistorique {

        private EventHandler<MouseEvent> boutonRetour = new EventHandler<MouseEvent>() {
            
            @Override
            public void handle(MouseEvent event){
                FenetreAccueil.getInstance().getScene().setRoot(FenetreAccueil.getInstance().getRacine());
            }
        };

        private EventHandler<MouseEvent> voirSession = new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                String ligne = HistoriqueVue.getInstance().getlV().getSelectionModel().getSelectedItem();
                String session = ligne.split("|")[0];
                SessionVue SV = new SessionVue(session);
                FenetreAccueil.getInstance().getScene().setRoot(SV.getGroup());
            }
        };

    public ObservableList<String> peuplerListe() {
        ObservableList<String> obsl = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = Main.connection.prepareStatement("SELECT idSession,couleurJoueur1,algoJoueur1,parametreJoueur1,couleurJoueur2,algoJoueur2,parametreJoueur2,victoiresJoueur1,victoiresJoueur2 FROM `echecs`.`sessions` JOIN `echecs`.`statssessions` ON `echecs`.`sessions`.`idSession`=`echecs`.`statssessions`.`numeroSession`;");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String str = "";
                for(int i = 1; i<10; i++){
                    switch (i) {
                        case 1:
                            str += rs.getString(i) + "| ";
                            break;
                        case 2: 
                            str += "J1: " + rs.getString(i) + " ";
                            break;
                        case 3:
                            str += rs.getString(i) + " ";
                            break;
                        case 4:
                            str += rs.getString(i) + " ";
                            break;
                        case 5:
                            str += "| J2:" + rs.getString(i) + " ";
                            break;
                        case 6:
                            str += rs.getString(i) + " ";
                            break;
                        case 7:
                            str += rs.getString(i) + " ";
                            break;
                        case 8:
                            str += "| Resultat: J1 " + rs.getString(i) + " -";
                            break;
                        case 9:
                            str += " " + rs.getString(i) + " J2";
                            break;
                        default:
                            break;
                    }
                }
                obsl.add(str);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return obsl;
    }

    public ControleurHistorique() {
    }

    public EventHandler<MouseEvent> getBoutonRetour() {
        return boutonRetour;
    }
    public void setBoutonRetour(EventHandler<MouseEvent> boutonRetour) {
        this.boutonRetour = boutonRetour;
    }

    public EventHandler<MouseEvent> getVoirSession() {
        return voirSession;
    }
    public void setVoirSession(EventHandler<MouseEvent> voirSession) {
        this.voirSession = voirSession;
    }
}