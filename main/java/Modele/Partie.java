package Modele;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import Controleur.ConfigPartiesControleur;
import Controleur.Main;
import Vue.ConfigPartiesVue;

public class Partie {
    private static int numeroCoup = 0;
    private Plateau plateau;
    private boolean finie;

    public Partie() {
        this.finie = false;
        creerPartieClassique();
    }
    public Partie(Plateau plateau){
        this.finie=false;
        this.plateau = plateau;
    }

    public Plateau getPlateau() {
        return plateau;
    }

    public void setPlateau(Plateau plateau) {
        this.plateau = plateau;
    }

    public boolean isFinie() {
        return finie;
    }

    public void setFinie(boolean finie) {
        this.finie = finie;
    }

    public void creerPartieClassique() {
        this.plateau = new Plateau();
        FabriqueAbstraitePieces fab = new FabriqueConcretePieces();
        for (int j = 0; j < 8; j++) {
            plateau.setPiece(1, j, fab.creerPion(Couleur.BLANC, Positions.getInstance().getLaPosition(1, j)));
            plateau.getPiecesBlanches().add(plateau.getPiece(1, j));
            plateau.setPiece(6, j, fab.creerPion(Couleur.NOIR, Positions.getInstance().getLaPosition(6, j)));
            plateau.getPiecesNoires().add(plateau.getPiece(6, j));
        }
        plateau.setPiece(0, 0, fab.creerTour(Couleur.BLANC, Positions.getInstance().getLaPosition(0, 0)));
        plateau.getPiecesBlanches().add(plateau.getPiece(0, 0));
        plateau.setPiece(0, 7, fab.creerTour(Couleur.BLANC, Positions.getInstance().getLaPosition(0, 7)));
        plateau.getPiecesBlanches().add(plateau.getPiece(0, 7));
        plateau.setPiece(7, 0, fab.creerTour(Couleur.NOIR, Positions.getInstance().getLaPosition(7, 0)));
        plateau.getPiecesNoires().add(plateau.getPiece(7, 0));
        plateau.setPiece(7, 7, fab.creerTour(Couleur.NOIR, Positions.getInstance().getLaPosition(7, 7)));
        plateau.getPiecesNoires().add(plateau.getPiece(7, 7));
        plateau.setPiece(0, 1, fab.creerCavalier(Couleur.BLANC, Positions.getInstance().getLaPosition(0, 1)));
        plateau.getPiecesBlanches().add(plateau.getPiece(0, 1));
        plateau.setPiece(0, 6, fab.creerCavalier(Couleur.BLANC, Positions.getInstance().getLaPosition(0, 6)));
        plateau.getPiecesBlanches().add(plateau.getPiece(0, 6));
        plateau.setPiece(7, 1, fab.creerCavalier(Couleur.NOIR, Positions.getInstance().getLaPosition(7, 1)));
        plateau.getPiecesNoires().add(plateau.getPiece(7, 1));
        plateau.setPiece(7, 6, fab.creerCavalier(Couleur.NOIR, Positions.getInstance().getLaPosition(7, 6)));
        plateau.getPiecesNoires().add(plateau.getPiece(7, 6));
        plateau.setPiece(0, 2, fab.creerFou(Couleur.BLANC, Positions.getInstance().getLaPosition(0, 2)));
        plateau.getPiecesBlanches().add(plateau.getPiece(0, 2));
        plateau.setPiece(0, 5, fab.creerFou(Couleur.BLANC, Positions.getInstance().getLaPosition(0, 5)));
        plateau.getPiecesBlanches().add(plateau.getPiece(0, 5));
        plateau.setPiece(7, 2, fab.creerFou(Couleur.NOIR, Positions.getInstance().getLaPosition(7, 2)));
        plateau.getPiecesNoires().add(plateau.getPiece(7, 2));
        plateau.setPiece(7, 5, fab.creerFou(Couleur.NOIR, Positions.getInstance().getLaPosition(7, 5)));
        plateau.getPiecesNoires().add(plateau.getPiece(7, 5));
        plateau.setPiece(0, 3, fab.creerDame(Couleur.BLANC, Positions.getInstance().getLaPosition(0, 3)));
        plateau.getPiecesBlanches().add(plateau.getPiece(0, 3));
        plateau.setPiece(7, 3, fab.creerDame(Couleur.NOIR, Positions.getInstance().getLaPosition(7, 3)));
        plateau.getPiecesNoires().add(plateau.getPiece(7, 3));
        plateau.setPiece(0, 4, fab.creerRoi(Couleur.BLANC, Positions.getInstance().getLaPosition(0, 4)));
        plateau.getPiecesBlanches().add(plateau.getPiece(0, 4));
        plateau.setPiece(7, 4, fab.creerRoi(Couleur.NOIR, Positions.getInstance().getLaPosition(7, 4)));
        plateau.getPiecesNoires().add(plateau.getPiece(7, 4));
        for (int i = 2; i < 6; i++) {
            for (int j = 0; j < 8; j++) {
                plateau.setPiece(i, j, fab.creerCaseVide(Positions.getInstance().getLaPosition(i, j)));
            }
        }
        this.plateau.miseAJourCoupsPossibles();
    }

    public void creerPartie() {
        this.plateau = new Plateau();
        FabriqueAbstraitePieces fab = new FabriqueConcretePieces();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (i == 2 && (j == 2 || j == 3 || j == 4 || j == 5)) {
                    this.plateau.setPiece(i, j, fab.creerPion(Couleur.BLANC, Positions.getInstance().getLaPosition(i, j)));
                } else if (i == 5 && (j == 2 || j == 3 || j == 4 || j == 5)) {
                    this.plateau.setPiece(i, j, fab.creerPion(Couleur.NOIR, Positions.getInstance().getLaPosition(i, j)));
                } else if (i == 1) {
                    if (j == 2) {
                        this.plateau.setPiece(i, j, fab.creerFou(Couleur.BLANC, Positions.getInstance().getLaPosition(i, j)));
                    } else if (j == 3) {
                        this.plateau.setPiece(i, j, fab.creerRoi(Couleur.BLANC, Positions.getInstance().getLaPosition(i, j)));
                    } else if (j == 4) {
                        this.plateau.setPiece(i, j, fab.creerCavalier(Couleur.BLANC, Positions.getInstance().getLaPosition(i, j)));
                    } else if (j == 5) {
                        this.plateau.setPiece(i, j, fab.creerTour(Couleur.BLANC, Positions.getInstance().getLaPosition(i, j)));
                    } else {
                        this.plateau.setPiece(i, j, fab.creerCaseVide(Positions.getInstance().getLaPosition(i, j)));
                    }
                } else if (i == 6) {
                    if (j == 2) {
                        this.plateau.setPiece(i, j, fab.creerFou(Couleur.NOIR, Positions.getInstance().getLaPosition(i, j)));
                    } else if (j == 3) {
                        this.plateau.setPiece(i, j, fab.creerRoi(Couleur.NOIR, Positions.getInstance().getLaPosition(i, j)));
                    } else if (j == 4) {
                        this.plateau.setPiece(i, j, fab.creerCavalier(Couleur.NOIR, Positions.getInstance().getLaPosition(i, j)));
                    } else if (j == 5) {
                        this.plateau.setPiece(i, j, fab.creerTour(Couleur.NOIR, Positions.getInstance().getLaPosition(i, j)));
                    } else {
                        this.plateau.setPiece(i, j, fab.creerCaseVide(Positions.getInstance().getLaPosition(i, j)));
                    }
                } else {
                    this.plateau.setPiece(i, j, fab.creerCaseVide(Positions.getInstance().getLaPosition(i, j)));
                }
                if (this.plateau.getPiece(i, j).isMemeCouleur(Couleur.BLANC)) {
                    this.plateau.getPiecesBlanches().add(this.plateau.getPiece(i, j));
                } else if (this.plateau.getPiece(i, j).isMemeCouleur(Couleur.NOIR)) {
                    this.plateau.getPiecesNoires().add(this.plateau.getPiece(i, j));
                }
            }
        }
    }

    public Coup prepareCoup(Joueur j, int parametre) {
        if (parametre == 0) {
            return j.getAlgo().creerCoupAleatoire(this.plateau, j);
        }
        if (numeroCoup > 500 && j.getAlgo().toString().equals("Alpha_Beta")) {
            if (((Alpha_Beta)j.getAlgo()).getR().nextInt(10) == 0) {
                return j.getAlgo().creerCoupAleatoire(this.plateau, j);
            }
        }
        return j.getAlgo().creerCoup(this.plateau, j);
    }

    public void jouerCoup(Coup c, int numeroPartie) {
        Main.o.println(c.toString());
        //System.out.println(c.toString());
        if (this.plateau.jouerCoup(c)) {
            //System.out.println("Partie terminée");
            this.finie = true;
        }
        numeroCoup += 1;
        try {
            PreparedStatement ps = Main.connection.prepareStatement("INSERT INTO echecs.historique (numeroSession, numeroPartie, numeroCoup, PreLig, PreCol, Lig, Col) VALUES (" + ConfigPartiesControleur.idSession + ", " + numeroPartie + ", " + numeroCoup +", " + c.getDebut()[0] + ", " + c.getDebut()[1] + ", " + c.getFin()[0] + ", " + c.getFin()[1] + ");");
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void faire(int numeroPartie){
        Main.o.println("numero partie: " + numeroPartie);
        Joueur j = (Session.getInstance().getJ1().getC()==Couleur.NOIR)? Session.getInstance().getJ1() : Session.getInstance().getJ2();
        int paramJ = (Session.getInstance().getJ1().equals(j))? Session.getInstance().getParamJ1() : Session.getInstance().getParamJ2();
        while (!this.finie) {
            if (j.equals(Session.getInstance().getJ1())) {
                j = Session.getInstance().getJ2();
                paramJ = Session.getInstance().getParamJ2();
            }
            else{
                j = Session.getInstance().getJ1();
                paramJ = Session.getInstance().getParamJ1();
            }
            this.jouerCoup(prepareCoup(j, paramJ), numeroPartie);
            Main.o.println(this.plateau.toString());
            //System.out.println(this.plateau.toString());
        }
        Main.o.println("Le joueur " + j.getNom() + " a gagné.");
        //System.out.println("Le joueur " + j.getNom() + " a gagné.");
        numeroCoup = 0;
        if (j.equals(Session.getInstance().getJ1())) {
            ConfigPartiesVue.getInstance().setNbrGagneesJ1(ConfigPartiesVue.getInstance().getNbrGagneesJ1() + 1);
            ConfigPartiesVue.getInstance().getPartiesGagneesJ1().setText("Le joueur 1 a gagne " + ConfigPartiesVue.getInstance().getNbrGagneesJ1().toString() + " parties");
            try {
                PreparedStatement ps = Main.connection.prepareStatement("INSERT INTO echecs.victoires (numeroSession, numeroPartie, victoire) VALUES ("
                        + ConfigPartiesControleur.idSession + ", " + numeroPartie + ", " + 1 + ")");
                ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else{
            ConfigPartiesVue.getInstance().setNbrGagneesJ2(ConfigPartiesVue.getInstance().getNbrGagneesJ2() + 1);
            ConfigPartiesVue.getInstance().getPartiesGagneesJ2().setText("Le joueur 2 a gagne " + ConfigPartiesVue.getInstance().getNbrGagneesJ2().toString() + " parties");
            try {
                PreparedStatement ps = Main.connection.prepareStatement("INSERT INTO echecs.victoires (numeroSession, numeroPartie, victoire) VALUES ("
                        + ConfigPartiesControleur.idSession + ", " + numeroPartie + ", " + 2 + ")");
                ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}