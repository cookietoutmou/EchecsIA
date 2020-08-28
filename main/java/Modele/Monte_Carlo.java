package Modele;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import Controleur.Main;

public class Monte_Carlo extends Algorithme{

    private int nombreSimulations;

    public Monte_Carlo(int nombreSimulations){
        super();
        this.nombreSimulations = nombreSimulations;
    }

    public Coup creerCoup(Plateau p, Joueur j){
        if (this.nombreSimulations == 0) {
            return creerCoupAleatoire(p, j);
        }
        return creerCoupMC(p, j);
    }

    public Coup creerCoupMC(Plateau p, Joueur j){
        Arbre arbre = creerArbre(p, j);
        Main.o.println(arbre.toString2());
        Main.o.println(p.toString());
        //System.out.println(arbre.toString2());
        //System.out.println(p.toString());
        Coup c = null;
        double max = 0.0;
        for (Arbre a : arbre.getListeFils()) {
            if (a.getNbrParties() > max) {
                c = a.getCoup();
                max = a.getNbrParties();
            }
        }
        Main.o.println("nbr gagnées: " + arbre.getValeur() + "/" + arbre.getNbrParties() + " max nbr de fois exploré: " + max + " coup associé: " + c.toString());
        //System.out.println("nbr gagnées: " + arbre.getValeur() + "/" + arbre.getNbrParties() + " max nbr de fois exploré: " + max + " coup associé: " + c.toString());
        return c;
    }

    public Arbre creerArbre(Plateau p, Joueur j){
        Arbre T = new Arbre(p);
        T.setPere(null);
        for (int i = 0; i < this.nombreSimulations; i++) {
            Arbre a = T;
            Arbre b;
            do {
                b = a;
                //Main.o.print("yo");
                //System.out.println("yo");
                a = selection(b);                
            } while (!a.isSansFils() && !a.equals(b));
            if (!a.getEtat().estFini()) {
                a = expansion(a, j); 
            }
            simulation(a, j);
            propagationArriere(a);
            Main.o.println("Arbre au rang " + i + ": " + T.toString2() + "\n");
            //System.out.println("Arbre au rang " + i + ": " + T.toString2() + "\n");
            //System.out.println(i);
        }
        return T;
    }

    public Arbre selection(Arbre a){
        List<Object[]> L = new ArrayList<Object[]>();
        if (a.isSansFils()) {
            return a;
        }
        for (Arbre arbre : a.getListeFils()) {
            Double score = banditFormula(arbre.getValeur(), arbre.getNbrParties()-arbre.getValeur(), a.getNbrParties());
            L.add(new Object[] {arbre, score});
        }
        Object[] max = noeudMax(L);
        //System.out.println((Double) max[1] + " " + banditFormula(0.0, 1.0, a.getNbrParties()));
        if ((Double) max[1] > banditFormula(0.0, 1.0, a.getNbrParties())) {
            //System.out.println("ici");
            return (Arbre)max[0];
        }
        return a;
    }

    public Arbre expansion(Arbre a, Joueur j){
        Set<Piece> s;
        if (j.getC() == Couleur.BLANC) {
            s = a.getEtat().getPiecesBlanches();
        }
        else{
            s = a.getEtat().getPiecesNoires();
        }
        if (a.isSansFils()) {
            Piece piece;
            do {
                piece = (Piece)s.toArray()[new Random().nextInt(s.size())];  
            } while (piece.getListePositions().size()==0);
            Integer[] position = piece.getListePositions().get(new Random().nextInt(piece.getListePositions().size()));
            Coup c = new Coup(piece.getPosition(), position);
            Plateau nouveauPlateau = a.getEtat().copy();
            nouveauPlateau.effectuerCoup(c);
            Arbre fils = new Arbre(nouveauPlateau);
            fils.setCoup(c);
            fils.setPere(a);
            a.getListeFils().add(fils);
            return fils;
        }
        Coup c;
        do {
            Piece piece;
            do {
                piece = (Piece)s.toArray()[new Random().nextInt(s.size())];    
            } while (piece.getListePositions().size()==0);
            Integer[] position = piece.getListePositions().get(new Random().nextInt(piece.getListePositions().size()));
            c = new Coup(piece.getPosition(), position);
        } while (a.coupDejaTraite(c));
        Plateau nouveauPlateau = a.getEtat().copy();
        nouveauPlateau.effectuerCoup(c);
        Arbre fils = new Arbre(nouveauPlateau);
        fils.setCoup(c);
        fils.setPere(a);
        a.getListeFils().add(fils);
        return fils;
    }

    public void simulation(Arbre a, Joueur j){
        if (a.getEtat().estFini()) {
            a.setNbrParties(a.getNbrParties() + 1.0);
            a.setValeur(a.getValeur() + 1.0);
        }
        else{
            int nbrCoup = 0;
            Joueur autreJoueur = null;
            if (j.equals(Session.getInstance().getJ1())) {
                autreJoueur = Session.getInstance().getJ2();
            }
            else{
                autreJoueur = Session.getInstance().getJ1();
            }
            Joueur joueurCourant = autreJoueur;
            Partie partie = new Partie(a.getEtat().copy());
            Coup c = partie.prepareCoup(joueurCourant, 0);
            while (!partie.getPlateau().jouerCoup(c) && nbrCoup < 500) {
                if (joueurCourant.equals(autreJoueur)) {
                    joueurCourant = j;
                }
                else{
                    joueurCourant = autreJoueur;
                }
                c = partie.prepareCoup(joueurCourant, 0);
                nbrCoup ++;
            }
            //Main.o.println(nbrCoup);
            //System.out.println(nbrCoup);
            if (nbrCoup >= 500) {
                a.setNbrParties(1);
                a.setValeur(.0);
            }
            else{
                if (joueurCourant.equals(j)) {
                    a.setNbrParties(1);
                    a.setValeur(1);
                }
                else{
                    a.setNbrParties(1);
                    a.setValeur(.0);
                }
            }
        }
    }

    public void propagationArriere(Arbre a){
        Arbre courant = a;
        if (a.getEtat().estFini()) {
            while (courant.hasPere()) {
                courant = courant.getPere();
                courant.setNbrParties(courant.getNbrParties() + 1);
                courant.setValeur(courant.getValeur() + 1);
            }    
        }
        else{
            while (courant.hasPere()) {
                courant = courant.getPere();
                courant.setNbrParties(courant.getNbrParties() + 1);
                courant.setValeur(courant.getValeur() + a.getValeur());
            }
        }
    }

    public Object[] noeudMax(List<Object[]> L){
        Double max = 0.0;
        int index = 0;
        int indexRetour = 0;
        for (Object[] o : L) {
            if ((Double)o[1] > max) {
                max = (Double)o[1];
                indexRetour = index;
            }
            index ++;
        }
        return L.get(indexRetour);
    }

    public Double banditFormula(double v, double d, double n){
        return v/(v+d) + 1.70*Math.sqrt(Math.log10(n)/(v+d));
    }

    public int getNombreSimulations() {
        return nombreSimulations;
    }
    public void setNombreSimulations(int nombreSimulations) {
        this.nombreSimulations = nombreSimulations;
    }

    public String toString(){
        return "Monte_Carlo";
    }
}