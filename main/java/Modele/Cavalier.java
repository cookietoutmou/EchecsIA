package Modele;

import java.util.ArrayList;
import java.util.Collections;

public class Cavalier extends Piece {

    public Cavalier(Couleur couleur, Integer[] position) {
        super(couleur, position);
    }

    @Override
    public String toString() {
        if (this.isMemeCouleur(Couleur.BLANC)) {
            return "c";
        }
        return "C";
    }

    @Override
    public Piece copy(){
        Cavalier c = new Cavalier(this.couleur, this.position);
        c.setListePosition(new ArrayList<Integer[]>(this.listePositions));
        return c;
    }

    @Override
    public void genererListePositions(Plateau plateau) {
        this.listePositions.clear();
        if (this.position[0]+2 < 8 && this.position[1]+1 < 8 && !plateau.getPiece(this.position[0]+2, this.position[1]+1).isMemeCouleur(this.couleur)) {
            this.listePositions.add(Positions.getInstance().getLaPosition(this.position[0]+2, this.position[1]+1));
        }
        if (this.position[0]+2 < 8 && this.position[1] - 1 >= 0 && !plateau.getPiece(this.position[0]+2, this.position[1]-1).isMemeCouleur(this.couleur)) {
            this.listePositions.add(Positions.getInstance().getLaPosition(this.position[0]+2, this.position[1]-1));
        }
        if (this.position[0]+1 < 8 && this.position[1]+2 < 8 && !plateau.getPiece(this.position[0]+1, this.position[1]+2).isMemeCouleur(this.couleur)) {
            this.listePositions.add(Positions.getInstance().getLaPosition(this.position[0]+1, this.position[1]+2));
        }
        if (this.position[0]+1 < 8 && this.position[1]-2 >= 0 && !plateau.getPiece(this.position[0]+1, this.position[1]-2).isMemeCouleur(this.couleur)) {
            this.listePositions.add(Positions.getInstance().getLaPosition(this.position[0]+1, this.position[1]-2));
        }
        if (this.position[0]-1 >= 0 && this.position[1]+2 < 8 && !plateau.getPiece(this.position[0]-1, this.position[1]+2).isMemeCouleur(this.couleur)) {
            this.listePositions.add(Positions.getInstance().getLaPosition(this.position[0]-1, this.position[1]+2));
        }
        if (this.position[0]-1 >= 0 && this.position[1]-2 >= 0 && !plateau.getPiece(this.position[0]-1, this.position[1]-2).isMemeCouleur(this.couleur)) {
            this.listePositions.add(Positions.getInstance().getLaPosition(this.position[0]-1, this.position[1]-2));
        }
        if (this.position[0]-2 >= 0 && this.position[1]+1 < 8 && !plateau.getPiece(this.position[0]-2, this.position[1]+1).isMemeCouleur(this.couleur)) {
            this.listePositions.add(Positions.getInstance().getLaPosition(this.position[0]-2, this.position[1]+1));
        }
        if (this.position[0]-2 >= 0 && this.position[1]-1 >= 0 && !plateau.getPiece(this.position[0]-2, this.position[1]-1).isMemeCouleur(this.couleur)) {
            this.listePositions.add(Positions.getInstance().getLaPosition(this.position[0]-2, this.position[1]-1));
        }
        Collections.shuffle(this.listePositions);
    }
}