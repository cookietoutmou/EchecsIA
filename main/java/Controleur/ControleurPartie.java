package Controleur;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Modele.Coup;
import Modele.Positions;
import Vue.FenetreAccueil;
import Vue.HistoriqueVue;
import Vue.PartieVue;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class ControleurPartie {
    private PartieVue PV;
    private ResultSet rs;
    private ResultSet rss;
    private int indiceListe = 0;
    private List<Object[]> piecesPerdues = new ArrayList<Object[]>();

    private EventHandler<KeyEvent> naviguerPartie = new EventHandler<KeyEvent>() {

        @Override
        public void handle(KeyEvent event) {
            if (event.getCode() == KeyCode.RIGHT && indiceListe < PV.getHistoriqueCoups().size()) {
                Coup coup = PV.getHistoriqueCoups().get(indiceListe);
                ImageView fin = ((ImageView)PV.getGV().getNodeByRowColumnIndex(coup.getFin()[0]+1, coup.getFin()[1]+1).getChildren().get(1));
                if (fin.getImage() != null) {
                    //Si le coup prend une piece, on se souvient de ce qui s'est passé pour la reproduire au cas où on veut revenir en arrière avec la flèche gauche
                    piecesPerdues.add(new Object[] {indiceListe, fin.getImage()});
                }
                PV.getGV().effectuerCoup(coup);
                indiceListe ++;
            }
            else if (event.getCode() == KeyCode.LEFT && indiceListe > 0) {
                Coup coup = PV.getHistoriqueCoupsInverse().get(indiceListe-1);
                ImageView debut = ((ImageView)PV.getGV().getNodeByRowColumnIndex(coup.getDebut()[0]+1, coup.getDebut()[1]+1).getChildren().get(1));
                PV.getGV().effectuerCoup(coup);
                if (piecesPerdues.size() > 0 && (Integer)piecesPerdues.get(piecesPerdues.size()-1)[0] == indiceListe-1) {
                    debut.setImage((Image)piecesPerdues.remove(piecesPerdues.size()-1)[1]);
                }
                indiceListe --;
            }
        }
    };

    private EventHandler<MouseEvent> boutonRetour = new EventHandler<MouseEvent>() {

        @Override
        public void handle(MouseEvent event) {
            FenetreAccueil.getInstance().getScene().setRoot(HistoriqueVue.getInstance().getGroup());
            FenetreAccueil.getInstance().getScene().removeEventHandler(KeyEvent.KEY_PRESSED, getNaviguerPartie());
        }
    };

    public ControleurPartie(String partie, String session, PartieVue PV) {
        FenetreAccueil.getInstance().getScene().setOnKeyPressed(this.naviguerPartie);
        this.PV = PV;
        try {
            PreparedStatement ps = Main.connection
                    .prepareStatement("SELECT PreLig, PreCol, Lig, Col FROM `echecs`.`historique` WHERE numeroSession="
                            + session + " AND numeroPartie=" + partie + " ORDER BY numeroCoup;");
            this.rs = ps.executeQuery();
            PreparedStatement pss = Main.connection.prepareStatement(
                    "SELECT nomJoueur1,couleurJoueur1,algoJoueur1,parametreJoueur1,nomJoueur2,couleurJoueur2,algoJoueur2,parametreJoueur2 FROM `echecs`.`sessions` WHERE idSession="
                            + session + ";");
            this.rss = pss.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void peuplerListe(List<Coup> l, List<Coup> ld) {
        try {
            while (rs.next()) {
                l.add(new Coup(
                        Positions.getInstance().getLaPosition(Integer.parseInt(rs.getString(1)),
                                Integer.parseInt(rs.getString(2))),
                        Positions.getInstance().getLaPosition(Integer.parseInt(rs.getString(3)),
                                Integer.parseInt(rs.getString(4)))));
                ld.add(new Coup(
                    Positions.getInstance().getLaPosition(Integer.parseInt(rs.getString(3)),
                            Integer.parseInt(rs.getString(4))),
                    Positions.getInstance().getLaPosition(Integer.parseInt(rs.getString(1)),
                            Integer.parseInt(rs.getString(2)))));
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void montrerCaracPartie(){
        try {
            rss.next();
            this.PV.getJ1().setText(rss.getString(1));
            this.PV.getJ2().setText(rss.getString(5));
            if (rss.getString(2).equals("BLANC")) {
                this.PV.getJ1().setTranslateX(270);
                this.PV.getJ2().setTranslateX(270);
                this.PV.getJ1().setTranslateY(20);
                this.PV.getJ2().setTranslateY(470);
                this.PV.getAlgoJ1().setTranslateX(500);
                this.PV.getAlgoJ1().setTranslateY(20);
                this.PV.getAlgoJ2().setTranslateX(500);
                this.PV.getAlgoJ2().setTranslateY(470);
            }
            else{
                this.PV.getJ2().setTranslateX(270);
                this.PV.getJ1().setTranslateX(270);
                this.PV.getJ2().setTranslateY(20);
                this.PV.getJ1().setTranslateY(470);
                this.PV.getAlgoJ1().setTranslateX(500);
                this.PV.getAlgoJ1().setTranslateY(470);
                this.PV.getAlgoJ2().setTranslateX(500);
                this.PV.getAlgoJ2().setTranslateY(20);
            }
            this.PV.getAlgoJ1().setText(rss.getString(3) + "\nParametre: " + rss.getString(4));
            this.PV.getAlgoJ2().setText(rss.getString(7) + "\nParametre: " + rss.getString(8));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public EventHandler<MouseEvent> getBoutonRetour() {
        return boutonRetour;
    }
    public void setBoutonRetour(EventHandler<MouseEvent> boutonRetour) {
        this.boutonRetour = boutonRetour;
    }

    public ResultSet getRs() {
        return rs;
    }
    public void setRs(ResultSet rs) {
        this.rs = rs;
    }

    public ResultSet getRss() {
        return rss;
    }
    public void setRss(ResultSet rss) {
        this.rss = rss;
    }

    public EventHandler<KeyEvent> getNaviguerPartie() {
        return naviguerPartie;
    }
    public void setNaviguerPartie(EventHandler<KeyEvent> naviguerPartie) {
        this.naviguerPartie = naviguerPartie;
    }
}