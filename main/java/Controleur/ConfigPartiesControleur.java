package Controleur;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Modele.Algorithme;
import Modele.Alpha_Beta;
import Modele.Couleur;
import Modele.Joueur;
import Modele.Monte_Carlo;
import Modele.Partie;
import Modele.Session;
import Vue.ConfigPartiesVue;
import Vue.FenetreAccueil;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.scene.control.CheckBox;
import javafx.scene.input.MouseEvent;

public class ConfigPartiesControleur {

    public static String idSession = "";
    private EventHandler<MouseEvent> restrictionCouleurs = new EventHandler<MouseEvent>() {

        @Override
        public void handle(MouseEvent event) {
            if (event.getSource().equals(ConfigPartiesVue.getInstance().getBlancsJ1())) {
                ConfigPartiesVue.getInstance().getNoirsJ2().setSelected(true);
                ConfigPartiesVue.getInstance().getNoirsJ1().setSelected(false);
                ConfigPartiesVue.getInstance().getBlancsJ2().setSelected(false);
            }
            else if (event.getSource().equals(ConfigPartiesVue.getInstance().getNoirsJ1())) {
                ConfigPartiesVue.getInstance().getNoirsJ2().setSelected(false);
                ConfigPartiesVue.getInstance().getBlancsJ1().setSelected(false);
                ConfigPartiesVue.getInstance().getBlancsJ2().setSelected(true);
            }
            else if (event.getSource().equals(ConfigPartiesVue.getInstance().getBlancsJ2())) {
                ConfigPartiesVue.getInstance().getNoirsJ2().setSelected(false);
                ConfigPartiesVue.getInstance().getNoirsJ1().setSelected(true);
                ConfigPartiesVue.getInstance().getBlancsJ1().setSelected(false);
            }
            else if (event.getSource().equals(ConfigPartiesVue.getInstance().getNoirsJ2())) {
                ConfigPartiesVue.getInstance().getBlancsJ1().setSelected(true);
                ConfigPartiesVue.getInstance().getNoirsJ1().setSelected(false);
                ConfigPartiesVue.getInstance().getBlancsJ2().setSelected(false);
            }
            if (!((CheckBox)event.getSource()).isSelected()) {
                ((CheckBox)event.getSource()).setSelected(true);
            }
        }
    };

    private EventHandler<MouseEvent> restrictionAlgo = new EventHandler<MouseEvent>() {

        @Override
        public void handle(MouseEvent event) {
            if (event.getSource().equals(ConfigPartiesVue.getInstance().getAlgo1CheckBoxAB())) {
                ConfigPartiesVue.getInstance().getAlgo1CheckBoxMC().setSelected(false);
            }
            else if (event.getSource().equals(ConfigPartiesVue.getInstance().getAlgo1CheckBoxMC())) {
                ConfigPartiesVue.getInstance().getAlgo1CheckBoxAB().setSelected(false);
            }
            else if (event.getSource().equals(ConfigPartiesVue.getInstance().getAlgo2CheckBoxAB())) {
                ConfigPartiesVue.getInstance().getAlgo2CheckBoxMC().setSelected(false);
            }
            else if (event.getSource().equals(ConfigPartiesVue.getInstance().getAlgo2CheckBoxMC())) {
                ConfigPartiesVue.getInstance().getAlgo2CheckBoxAB().setSelected(false);
            }
            if (ConfigPartiesVue.getInstance().getAlgo1CheckBoxAB().isSelected()) {
                ConfigPartiesVue.getInstance().getProfAB1TextField().setDisable(false);
                ConfigPartiesVue.getInstance().getNbrMC1TextField().setDisable(true);
            }
            else{
                ConfigPartiesVue.getInstance().getProfAB1TextField().setDisable(true);
            }
            if (ConfigPartiesVue.getInstance().getAlgo1CheckBoxMC().isSelected()) {
                ConfigPartiesVue.getInstance().getProfAB1TextField().setDisable(true);
                ConfigPartiesVue.getInstance().getNbrMC1TextField().setDisable(false);
            }
            else{
                ConfigPartiesVue.getInstance().getNbrMC1TextField().setDisable(true);
            }
            if (ConfigPartiesVue.getInstance().getAlgo2CheckBoxAB().isSelected()) {
                ConfigPartiesVue.getInstance().getProfAB2TextField().setDisable(false);
                ConfigPartiesVue.getInstance().getNbrMC2TextField().setDisable(true);
            }
            else{
                ConfigPartiesVue.getInstance().getProfAB2TextField().setDisable(true);
            }
            if (ConfigPartiesVue.getInstance().getAlgo2CheckBoxMC().isSelected()) {
                ConfigPartiesVue.getInstance().getProfAB2TextField().setDisable(true);
                ConfigPartiesVue.getInstance().getNbrMC2TextField().setDisable(false);
            }
            else{
                ConfigPartiesVue.getInstance().getNbrMC2TextField().setDisable(true);
            }
        }
    };

    private EventHandler<MouseEvent> retour = new EventHandler<MouseEvent>() {

        @Override
        public void handle(MouseEvent event){
            FenetreAccueil.getInstance().getScene().setRoot(FenetreAccueil.getInstance().getRacine());
        }
    };

    private EventHandler<MouseEvent> lancerLesParties = new EventHandler<MouseEvent>() {

        @Override
        public void handle(MouseEvent event){
            Thread principal = new Thread(new Task<Void>(){

                @Override
                protected Void call() throws Exception {
                    int nbParties = Integer.valueOf(ConfigPartiesVue.getInstance().getNbrPartiesTextField().getText());
                    String nomJ1 = ConfigPartiesVue.getInstance().getJoueur1TextField().getText();
                    String nomJ2 = ConfigPartiesVue.getInstance().getJoueur2TextField().getText();
                    Couleur couleurJ1 = (ConfigPartiesVue.getInstance().getBlancsJ1().isSelected())? Couleur.BLANC : Couleur.NOIR;
                    Couleur couleurJ2 = (ConfigPartiesVue.getInstance().getBlancsJ2().isSelected())? Couleur.BLANC : Couleur.NOIR;
                    int parametre1 = (ConfigPartiesVue.getInstance().getProfAB1TextField().isDisabled())? Integer.valueOf(ConfigPartiesVue.getInstance().getNbrMC1TextField().getText()) : Integer.valueOf(ConfigPartiesVue.getInstance().getProfAB1TextField().getText());
                    int parametre2 = (ConfigPartiesVue.getInstance().getProfAB2TextField().isDisabled())? Integer.valueOf(ConfigPartiesVue.getInstance().getNbrMC2TextField().getText()) : Integer.valueOf(ConfigPartiesVue.getInstance().getProfAB2TextField().getText());
                    Algorithme algo1 = (ConfigPartiesVue.getInstance().getAlgo1CheckBoxAB().isSelected())? new Alpha_Beta(parametre1) : new Monte_Carlo(parametre1);
                    Algorithme algo2 = (ConfigPartiesVue.getInstance().getAlgo2CheckBoxAB().isSelected())? new Alpha_Beta(parametre2) : new Monte_Carlo(parametre2);
                    ConfigPartiesVue.getInstance().getPartiesGagneesJ1().setVisible(true);
                    ConfigPartiesVue.getInstance().getPartiesGagneesJ2().setVisible(true);
                    ConfigPartiesVue.getInstance().setNombreDeParties(nbParties);
                    ConfigPartiesVue.getInstance().getProgression().setText(0 + " parties effectuees sur " + nbParties);
                    Session.setInstance(new Session(new Joueur(nomJ1, couleurJ1, algo1), new Joueur(nomJ2, couleurJ2, algo2), parametre1, parametre2));
                    try {
                        PreparedStatement ps = Main.connection.prepareStatement(
                                "INSERT INTO `echecs`.`sessions` (`nomJoueur1`, `couleurJoueur1`, `algoJoueur1`, `parametreJoueur1`, `nomJoueur2`, `couleurJoueur2`, `algoJoueur2`, `parametreJoueur2`) VALUES ('"
                                        + Session.getInstance().getJ1().getNom() + "', '"
                                        + Session.getInstance().getJ1().getC() + "', '"
                                        + Session.getInstance().getJ1().getAlgo().toString() + "', '"
                                        + Integer.toString(Session.getInstance().getParamJ1()) + "', '"
                                        + Session.getInstance().getJ2().getNom() + "', '"
                                        + Session.getInstance().getJ2().getC() + "', '"
                                        + Session.getInstance().getJ2().getAlgo().toString() + "', '"
                                        + Integer.toString(Session.getInstance().getParamJ2()) + "');");
                        ps.executeUpdate();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    PreparedStatement ps = Main.connection.prepareStatement("SELECT MAX(idSession) FROM echecs.sessions");
                    ResultSet rs = ps.executeQuery();
                    rs.first();
                    idSession = rs.getString(1);
                    for (int i = 1; i <= nbParties; i++) {
                        Partie partie = new Partie();
                        //System.out.println("nouvelle partie");
                        try {
                            partie.faire(i);
                        } catch (Throwable t) {
                            t.printStackTrace();
                            Main.o.println(t.toString());
                        }
                        
                        ConfigPartiesVue.getInstance().setPartiesEffectuees(ConfigPartiesVue.getInstance().getPartiesEffectuees() + 1);
                        ConfigPartiesVue.getInstance().getProgression().setText(ConfigPartiesVue.getInstance().getPartiesEffectuees().toString() + " parties effectuees sur " + ConfigPartiesVue.getInstance().getNombreDeParties().toString());
                    }
                    PreparedStatement pss = Main.connection.prepareStatement("INSERT INTO `echecs`.`statssessions` (`numeroSession`, `victoiresJoueur1`, `victoiresJoueur2`, `nombreParties`) VALUES (" + idSession + ", " + ConfigPartiesVue.getInstance().getNbrGagneesJ1() + ", " + ConfigPartiesVue.getInstance().getNbrGagneesJ2() + ", " + ConfigPartiesVue.getInstance().getNombreDeParties() + ");");
                    pss.executeUpdate();
                    return null;
                }
            });
            principal.setDaemon(true);
            principal.start();
        }
    };

    public EventHandler<MouseEvent> getRestrictionCouleurs() {
        return restrictionCouleurs;
    }
    public void setRestrictionCouleurs(EventHandler<MouseEvent> restrictionCouleurs) {
        this.restrictionCouleurs = restrictionCouleurs;
    }

    public ConfigPartiesControleur() {

    }

    public EventHandler<MouseEvent> getRestrictionAlgo() {
        return restrictionAlgo;
    }
    public void setRestrictionAlgo(EventHandler<MouseEvent> restrictionAlgo) {
        this.restrictionAlgo = restrictionAlgo;
    }

    public EventHandler<MouseEvent> getRetour() {
        return retour;
    }
    public void setRetour(EventHandler<MouseEvent> retour) {
        this.retour = retour;
    }

    public EventHandler<MouseEvent> getLancerLesParties() {
        return lancerLesParties;
    }
    public void setLancerLesParties(EventHandler<MouseEvent> lancerLesParties) {
        this.lancerLesParties = lancerLesParties;
    }
}