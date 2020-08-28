package Controleur;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Vue.FenetreAccueil;
import Vue.HistoriqueVue;
import Vue.PartieVue;
import Vue.SessionVue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class SessionControleur {

    private String session;
    private SessionVue SV;
    private EventHandler<MouseEvent> boutonRetour = new EventHandler<MouseEvent>() {

        @Override
        public void handle(MouseEvent event) {
            FenetreAccueil.getInstance().getScene().setRoot(HistoriqueVue.getInstance().getGroup());
        }
    };

    private EventHandler<MouseEvent> voirSession = new EventHandler<MouseEvent>() {

        @Override
        public void handle(MouseEvent event) {
            String ligne = getSV().getlV().getSelectionModel().getSelectedItem();
            String partie = ligne.split("|")[0];
            PartieVue PV = new PartieVue(partie, getSession());
            FenetreAccueil.getInstance().getScene().setRoot(PV.getGroup());
        }
    };

    public SessionControleur(String session, SessionVue SV) {
        this.session = session;
        this.SV = SV;
    }

    public ObservableList<String> peuplerListe(String session) {
        ObservableList<String> obsl = FXCollections.observableArrayList();
        PreparedStatement ps;
        try {
            ps = Main.connection.prepareStatement("SELECT numeroPartie,victoire FROM `echecs`.`victoires` WHERE numeroSession=" + session + ";");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String str = rs.getString(1) + " | Vainqueur = Joueur J" + rs.getString(2) + " | ";
                PreparedStatement pss = Main.connection.prepareStatement("SELECT MAX(numeroCoup) FROM `echecs`.`historique` WHERE numeroSession=" + this.session + " AND numeroPartie=" + rs.getString(1) + ";");
                ResultSet rss = pss.executeQuery();
                rss.next();
                str += "en " + rss.getString(1) + " coups";    
                obsl.add(str);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return obsl;
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

    public String getSession() {
        return session;
    }
    public void setSession(String session) {
        this.session = session;
    }

    public SessionVue getSV() {
        return SV;
    }
    public void setSV(SessionVue sV) {
        SV = sV;
    }
}