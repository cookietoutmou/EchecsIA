package Modele;

import java.util.ArrayList;
import java.util.List;

public abstract class Piece {
    Couleur couleur;
    Integer[] position;
    List<Integer[]> listePositions;

    public Piece(){}

    public Piece(Couleur couleur, Integer[] position) {
        this.couleur = couleur;
        this.position = position;
        this.listePositions = new ArrayList<Integer[]>();
    }

    public Couleur getCouleur() {
        return couleur;
    }
    public void setCouleur(Couleur couleur) {
        this.couleur = couleur;
    }

    public List<Integer[]> getListePositions() {
        return this.listePositions;
    }

    public void setListePosition(List<Integer[]> listePositions) {
        this.listePositions = listePositions;
    }

    @Override
    public abstract String toString();

    public boolean equals(Piece p){
        if (p instanceof Piece) {
            return this.position[0] == p.getPosition()[0] && this.position[1] == p.getPosition()[1] && this.couleur == p.getCouleur();
        }
        return false;
    }

    public boolean isMemeCouleur(Couleur couleur){
        if (this.couleur == couleur) {
            return true;
        }
        return false;
    }

    public abstract void genererListePositions(Plateau m);
    public abstract Piece copy();

    public void setPosition(Integer[] position){
        this.position = position;
    }

    public Integer[] getPosition(){
        return this.position;
    }
}