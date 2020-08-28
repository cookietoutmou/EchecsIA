package Modele;

public class caseVide extends Piece {
    
    public caseVide(Integer[] position) {
        super(Couleur.NEUTRE, position);
    }

    @Override
    public void genererListePositions(Plateau m) {
        return;
    }

    @Override
    public String toString() {
        return " ";
    }

    @Override
    public Piece copy(){
        return new caseVide(this.position);
    }
}