package Modele;

import java.util.ArrayList;
import java.util.Collections;

public class Roi extends Piece {

    public Roi(Couleur couleur, Integer[] position) {
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
            return "r";
        }
        return "R";
    }

    @Override
    public Piece copy(){
        Roi r = new Roi(this.couleur, this.position);
        r.setListePosition(new ArrayList<Integer[]>(this.listePositions));
        return r;
    }

    @Override
    public void genererListePositions(Plateau plateau) {
        this.listePositions.clear();
        int i = this.position[0];
        int j = this.position[1]+1;
        if (j < 8 && !plateau.getPiece(i, j).isMemeCouleur(this.couleur)) {
            this.listePositions.add(Positions.getInstance().getLaPosition(i, j));
        }
        i = this.position[0]+1;
        j = this.position[1]+1;
        if (i < 8 && j < 8 && !plateau.getPiece(i, j).isMemeCouleur(this.couleur)) {
            this.listePositions.add(Positions.getInstance().getLaPosition(i, j));
        }
        i = this.position[0]+1;
        j = this.position[1];
        if (i < 8 && !plateau.getPiece(i, j).isMemeCouleur(this.couleur)) {
            this.listePositions.add(Positions.getInstance().getLaPosition(i, j));
        }
        i = this.position[0]+1;
        j = this.position[1]-1;
        if (i < 8 && j >= 0 && !plateau.getPiece(i, j).isMemeCouleur(this.couleur)) {
            this.listePositions.add(Positions.getInstance().getLaPosition(i, j));
        }
        i = this.position[0];
        j = this.position[1]-1;
        if (j >= 0 && !plateau.getPiece(i, j).isMemeCouleur(this.couleur)) {
            this.listePositions.add(Positions.getInstance().getLaPosition(i, j));
        }
        i = this.position[0]-1;
        j = this.position[1]-1;
        if (i >= 0 && j >= 0 && !plateau.getPiece(i, j).isMemeCouleur(this.couleur)) {
            this.listePositions.add(Positions.getInstance().getLaPosition(i, j));
        }
        i = this.position[0]-1;
        j = this.position[1];
        if (i >= 0 && !plateau.getPiece(i, j).isMemeCouleur(this.couleur)) {
            this.listePositions.add(Positions.getInstance().getLaPosition(i, j));
        }
        i = this.position[0]-1;
        j = this.position[1]+1;
        if (i >= 0 && j < 8 && !plateau.getPiece(i, j).isMemeCouleur(this.couleur)) {
            this.listePositions.add(Positions.getInstance().getLaPosition(i, j));
        }
        Collections.shuffle(this.listePositions);
    }
}