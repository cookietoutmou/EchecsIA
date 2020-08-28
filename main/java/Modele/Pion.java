package Modele;

import java.util.ArrayList;
import java.util.Collections;

public class Pion extends Piece {

    public Pion(Couleur couleur, Integer[] position) {
        super(couleur, position);
    }

    @Override
    public String toString() {
        if (this.isMemeCouleur(Couleur.BLANC)) {
            return "p";
        }
        return "P";
    }

    @Override
    public Piece copy(){
        Pion p = new Pion(this.couleur, this.position);
        p.setListePosition(new ArrayList<Integer[]>(this.listePositions));
        return p;
    }

    @Override
    public void genererListePositions(Plateau plateau) {
        this.listePositions.clear();
        if (this.couleur == Couleur.BLANC) {
            if (this.position[0]+1 < 8 && plateau.getPiece(this.position[0]+1, this.position[1]).isMemeCouleur(Couleur.NEUTRE)) {
                this.listePositions.add(Positions.getInstance().getLaPosition(this.position[0]+1, this.position[1]));    
            }
            if (this.position[0]+1 < 8 && this.position[1]+1 < 8 && plateau.getPiece(this.position[0]+1, this.position[1]+1).isMemeCouleur(Couleur.NOIR)) {
                this.listePositions.add(Positions.getInstance().getLaPosition(this.position[0]+1, this.position[1]+1));
            }
            if (this.position[0]+1 < 8 && this.position[1]-1 >= 0 && plateau.getPiece(this.position[0]+1, this.position[1]-1).isMemeCouleur(Couleur.NOIR)) {
                this.listePositions.add(Positions.getInstance().getLaPosition(this.position[0]+1, this.position[1]-1));
            }
            if (this.position[0] == 1 && plateau.getPiece(3, this.position[1]).isMemeCouleur(Couleur.NEUTRE)) {
                this.listePositions.add(Positions.getInstance().getLaPosition(3, this.position[1]));
            }
        }
        else{
            if (this.position[0]-1 >= 0 && plateau.getPiece(this.position[0]-1, this.position[1]).isMemeCouleur(Couleur.NEUTRE)){
                this.listePositions.add(Positions.getInstance().getLaPosition(this.position[0]-1, this.position[1]));
            }
            if (this.position[0]-1 >= 0 && this.position[1]+1 < 8 && plateau.getPiece(this.position[0]-1, this.position[1]+1).isMemeCouleur(Couleur.BLANC)) {
                this.listePositions.add(Positions.getInstance().getLaPosition(this.position[0]-1, this.position[1]+1));
            }
            if (this.position[0]-1 >= 0 && this.position[1]-1 >= 0 && plateau.getPiece(this.position[0]-1, this.position[1]-1).isMemeCouleur(Couleur.BLANC)) {
                this.listePositions.add(Positions.getInstance().getLaPosition(this.position[0]-1, this.position[1]-1));
            }
            if (this.position[0] == 6 && plateau.getPiece(4, this.position[1]).isMemeCouleur(Couleur.NEUTRE)) {
                this.listePositions.add(Positions.getInstance().getLaPosition(4, this.position[1]));
            }
        }
        Collections.shuffle(this.listePositions);
    }
}