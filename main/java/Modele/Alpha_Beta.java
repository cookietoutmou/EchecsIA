package Modele;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class Alpha_Beta extends Algorithme{

    private int profondeur;
    private Joueur joueur;
    private Map<String, Integer> valeursPieces;
    private Random r = new Random();

    public Alpha_Beta(int profondeur){
        super();
        this.profondeur = profondeur;
        this.valeursPieces = new HashMap<String, Integer>();
        this.valeursPieces.put("p", 100);
        this.valeursPieces.put("f", 300);
        this.valeursPieces.put("c", 300);
        this.valeursPieces.put("t", 500);
        this.valeursPieces.put("d", 900);
        this.valeursPieces.put("P", 100);
        this.valeursPieces.put("F", 300);
        this.valeursPieces.put("C", 300);
        this.valeursPieces.put("T", 500);
        this.valeursPieces.put("D", 900);
        this.valeursPieces.put("r", 10000);
        this.valeursPieces.put("R", 10000);
    }

    public double doAlphaBeta(Arbre noeud, double alpha, double beta){
        if (noeud.isSansFils()) {
            //System.out.println("noeud sans fils de valeur " + noeud.getValeur());
            return noeud.getValeur();
        }
        double A = alpha;
        double B = beta;
        if (noeud.getType() == typeDeNoeud.MIN) {
            Collections.shuffle(noeud.getListeFils());
            for (Arbre fils : noeud.getListeFils()) {
               B = min(B, doAlphaBeta(fils, A, B));
               if (A >= B) {
                   noeud.setValeur(A);
                   return A;
               }
            }
            noeud.setValeur(B);
            return B;
        }
        else{
            Collections.shuffle(noeud.getListeFils());
            for (Arbre fils : noeud.getListeFils()){
                A = max(A, doAlphaBeta(fils, A, B));
                if (A >= B) {
                    noeud.setValeur(B);
                    return B;
                }
            }
        }
        noeud.setValeur(A);
        return A;
    }

    public double max(double a, double b){
        return (a>=b)? a : b;
    }
    public double min(double a, double b){
        return (a>=b)? b : a;
    }

    public int valeurMateriel(Plateau p, Joueur j){
        int valeur = 0;
        if (j.getC() == Couleur.BLANC) {
            for (Piece piece : p.getPiecesBlanches()) {
                if (this.valeursPieces.containsKey(piece.toString())) {
                    if (piece.toString() == "r") {
                        valeur += this.valeursPieces.get(piece.toString());
                    }
                    valeur += this.valeursPieces.get(piece.toString());
                }
            }
            for (Piece piece : p.getPiecesNoires()) {
                if (this.valeursPieces.containsKey(piece.toString())) {
                    valeur -= this.valeursPieces.get(piece.toString());
                }
            }
        }
        else{
            for (Piece piece : p.getPiecesBlanches()) {
                if (this.valeursPieces.containsKey(piece.toString())) {
                    valeur -= this.valeursPieces.get(piece.toString());
                }
            }
            for (Piece piece : p.getPiecesNoires()) {
                if (this.valeursPieces.containsKey(piece.toString())) {
                    if (piece.toString() == "R") {
                        valeur += this.valeursPieces.get(piece.toString());
                    }
                    valeur += this.valeursPieces.get(piece.toString());
                }
            }
        }
        return valeur;
    }

    public int fonctionEval(Plateau p, Joueur j){
        return valeurMateriel(p, j);
    }

    public Arbre creerArbre(Plateau p, Joueur j, int prof){
        Arbre racine = new Arbre();
        boolean positionExiste = false;
        if (prof > 0) {
            Set<Piece> s;
            if (j.getC() == Couleur.BLANC) {
                s = p.getPiecesBlanches();
            }
            else{
                s = p.getPiecesNoires();
            }
            for (Piece piece : s) {
                for (Integer[] position : piece.getListePositions()) {
                    positionExiste = true;
                    Plateau pCopy = p.copy();
                    Coup coup = new Coup(piece.getPosition(), position);
                    pCopy.effectuerCoup(coup);
                    Arbre arbre;
                    if (j.equals(Session.getInstance().getJ1())) {
                        arbre = creerArbre(pCopy, Session.getInstance().getJ2(), prof-1);
                    }
                    else{
                        arbre = creerArbre(pCopy, Session.getInstance().getJ1(), prof-1);
                    }
                    arbre.setCoup(coup);
                    racine.getListeFils().add(arbre);
                }
            }
            if (!positionExiste) {
                racine.setValeur(fonctionEval(p, this.joueur));    
            }
        }
        else{
            racine.setValeur(fonctionEval(p, this.joueur));
        }
        return racine;
    }

    public Coup creerCoup(Plateau p , Joueur j){
        if (this.profondeur == 0) {
            return creerCoupAleatoire(p, j);
        }
        return creerCoupAB(p, j);
    }
    
    public Coup creerCoupAB(Plateau p, Joueur j){
        this.joueur = j;
        Arbre arbreDeRecherche = creerArbre(p, j, this.profondeur);
        arbreDeRecherche.setType(typeDeNoeud.MAX);
        arbreDeRecherche.setToutLesTypes();
        double max = doAlphaBeta(arbreDeRecherche, Integer.MIN_VALUE, Integer.MAX_VALUE);
        //System.out.println(arbreDeRecherche.toString());
        //Collections.shuffle(arbreDeRecherche.getListeFils());
        for (Arbre fils : arbreDeRecherche.getListeFils()) {
            if (fils.getValeur() == max) {
                return fils.getCoup();
            }
        }
        return null;
    }


    public int getProfondeur() {
        return profondeur;
    }
    public void setProfondeur(int profondeur) {
        this.profondeur = profondeur;
    }

    public Map<String, Integer> getValeursPieces() {
        return valeursPieces;
    }

    public void setValeursPieces(Map<String, Integer> valeursPieces) {
        this.valeursPieces = valeursPieces;
    }

    public String toString(){
        return "Alpha_Beta";
    }

    public Random getR() {
        return r;
    }
    public void setR(Random r) {
        this.r = r;
    }
}