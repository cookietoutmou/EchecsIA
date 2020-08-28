package Modele;

import java.util.ArrayList;
import java.util.Collections;

public class Fou extends Piece {

    public Fou(Couleur couleur, Integer[] position) {
        super(couleur, position);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof String) {
            return this.toString().equals(((String) obj).toString());
        }
        return false;
    }

    @Override
    public String toString() {
        if (this.isMemeCouleur(Couleur.BLANC)) {
            return "f";
        }
        return "F";
    }

    @Override
    public Piece copy(){
        Fou f = new Fou(this.couleur, this.position);
        f.setListePosition(new ArrayList<Integer[]>(this.listePositions));
        return f;
    }

    @Override
    public void genererListePositions(Plateau plateau) {
        this.listePositions.clear();
        int i = this.position[0]+1;
        int j = this.position[1]+1;
        while (i<8 && j<8) {
            if (plateau.getPiece(i, j).getCouleur()==Couleur.NEUTRE) {
                this.listePositions.add(Positions.getInstance().getLaPosition(i, j));
            }
            else if (plateau.getPiece(i, j).isMemeCouleur(this.couleur)) {
                break;
            }
            else{
                this.listePositions.add(Positions.getInstance().getLaPosition(i, j));
                break;
            }
            i++;
            j++;
        }
        i = this.position[0] + 1;
        j = this.position[1] - 1;
        while (i<8 && j>=0) {
            if (plateau.getPiece(i, j).getCouleur()==Couleur.NEUTRE) {
                this.listePositions.add(Positions.getInstance().getLaPosition(i, j));
            }
            else if (plateau.getPiece(i, j).isMemeCouleur(this.couleur)) {
                break;
            }
            else{
                this.listePositions.add(Positions.getInstance().getLaPosition(i, j));
                break;
            }
            i++;
            j--;
        }
        i = this.position[0] - 1;
        j = this.position[1] - 1;
        while (i>=0 && j>=0) {
            if (plateau.getPiece(i, j).getCouleur()==Couleur.NEUTRE) {
                this.listePositions.add(Positions.getInstance().getLaPosition(i, j));
            }
            else if (plateau.getPiece(i, j).isMemeCouleur(this.couleur)) {
                break;
            }
            else{
                this.listePositions.add(Positions.getInstance().getLaPosition(i, j));
                break;
            }
            i--;
            j--;
        }
        i = this.position[0] - 1;
        j = this.position[1] + 1;
        while (i>=0 && j<8) {
            if (plateau.getPiece(i, j).getCouleur()==Couleur.NEUTRE) {
                this.listePositions.add(Positions.getInstance().getLaPosition(i, j));
            }
            else if (plateau.getPiece(i, j).isMemeCouleur(this.couleur)) {
                break;
            }
            else{
                this.listePositions.add(Positions.getInstance().getLaPosition(i, j));
                break;
            }
            i--;
            j++;
        }
        Collections.shuffle(this.listePositions);
    }
}