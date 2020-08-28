package Modele;

import java.util.HashSet;
import java.util.Set;

public class Plateau {
    private Set<Piece> piecesBlanches;
    private Set<Piece> piecesNoires;
    private Piece[][] M;

    public Plateau() {
        this.piecesBlanches = new HashSet<Piece>();
        this.piecesNoires = new HashSet<Piece>();
        this.M = new Piece[8][8];
    }

    public Piece getPiece(int i, int j) {
        if (i<0 || j<0 || i>=8 || j>=8) {
            return null;
        }
        return this.M[i][j];
    }

    public void setPiece(int i, int j, Piece piece) {
        this.M[i][j] = piece;
    }

    public Piece[][] getM() {
        return this.M;
    }
    public void setM(Piece[][] m) {
        this.M = m;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("      BLANCS      \n");
        for (int i = 0; i < 8; i++) {
            sb.append(i+1 + "|");
            for (int j = 0; j < 8; j++) {
                sb.append(this.M[i][j].toString() + "|");
            }
            sb.append("\n");
        }
        sb.append("  A B C D E F G H \n");
        sb.append("      NOIRS     \n");
        return sb.toString();
    }

    public void accepteGenererPositionsPiece(Piece p) {
        p.genererListePositions(this);
    }

    public Plateau copy(){
        Plateau p = new Plateau();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                p.setPiece(i, j, this.getPiece(i, j).copy());
                if (p.getPiece(i, j).isMemeCouleur(Couleur.BLANC)) {
                    p.getPiecesBlanches().add(p.getPiece(i, j));            
                }
                else if (p.getPiece(i, j).isMemeCouleur(Couleur.NOIR)) {
                    p.getPiecesNoires().add(p.getPiece(i, j));
                }
            }
        }
        return p;
    }

    public void miseAJourCoupsPossibles(){
        for (Piece piece : piecesBlanches) {
            accepteGenererPositionsPiece(piece);
        }
        for (Piece piece : piecesNoires) {
            accepteGenererPositionsPiece(piece);
        }
    }

    public Set<Piece> getPiecesBlanches() {
        return piecesBlanches;
    }

    public Set<Piece> getPiecesNoires() {
        return piecesNoires;
    }

    public boolean jouerCoup(Coup c){
        effectuerCoup(c);
        if (this.estFini()) {
            //System.out.println("simulation finie");
            return true;
        }
        return false;
    }

    public void effectuerCoup(Coup c) {
        Piece fin = getPiece(c.getFin()[0], c.getFin()[1]);
        Piece debut = getPiece(c.getDebut()[0], c.getDebut()[1]);
        fin.setPosition(Positions.getInstance().getLaPosition(c.getDebut()[0], c.getDebut()[1]));
        debut.setPosition(Positions.getInstance().getLaPosition(c.getFin()[0], c.getFin()[1]));
        Piece tmp = debut;
        debut = fin;
        fin = tmp;
        if (!debut.isMemeCouleur(Couleur.NEUTRE)) {
            if (debut.isMemeCouleur(Couleur.BLANC)) {
                piecesBlanches.remove(debut);
            }
            else{
                piecesNoires.remove(debut);
            }
            debut = new caseVide(Positions.getInstance().getLaPosition(c.getDebut()[0], c.getDebut()[1]));
        }
        setPiece(c.getDebut()[0], c.getDebut()[1], debut);
        setPiece(c.getFin()[0], c.getFin()[1], fin);
        miseAJourCoupsPossibles();
    }

    public Piece getRoiBlanc(){
        for (Piece p : this.piecesBlanches) {
            if (p.toString().equals("r")) {
                return p;
            }
        }
        return null;
    }
    public Piece getRoiNoir(){
        for (Piece p : this.piecesNoires) {
            if (p.toString().equals("R")) {
                return p;
            }
        }
        return null;
    }

    public boolean unRoiPris(){
        if (this.getRoiNoir() == null|| this.getRoiBlanc() == null) {
            return true;
        }
        return false;
    }

    public boolean estFini(){
        if (this.unRoiPris() || this.estEnEchecEtMat(this.getRoiNoir()) || this.estEnEchecEtMat(this.getRoiBlanc())) {
            return true;
        }
        return false;
    }

    public boolean estEnEchecEtMat(Piece roi){
        Object[] resultat = this.estEnEchec(roi);
        if ((Boolean)resultat[0]) {
            Piece echequeuse = (Piece)resultat[1];
            if (this.pasDeDeplacementPossible(roi) && this.pasDobstaclePossible(roi, echequeuse)) { // Obstacle ou prise de la pièce echequeuse
                //System.out.println("echec et mat");
                return true;
            }
        }
        return false;
    }

    public Object[] estEnEchec(Piece roi){
        Set<Piece> s;
        if (roi.isMemeCouleur(Couleur.BLANC)) {
            s = piecesNoires;
        }
        else{
            s = piecesBlanches;
        }
        for (Piece piece : s) {
            if (piece.getListePositions().contains(roi.getPosition())) {
                //System.out.println("roi en echec");
                return new Object[] {true, piece};
            }
        }
        return new Object[] {false, null};
    }

    public boolean pasDeDeplacementPossible(Piece roi){
        if (roi.isMemeCouleur(Couleur.BLANC)) {
            for (Integer[] p : roi.getListePositions()) {
                Plateau pCopy = this.copy();
                pCopy.effectuerCoup(new Coup(roi.getPosition(), p));
                if (!(Boolean)(pCopy.estEnEchec(pCopy.getRoiBlanc())[0])) {
                    //System.out.println("déplacement possible");
                    return false;
                }
            }    
        }
        else {
            for (Integer[] p : roi.getListePositions()) {
                Plateau pCopy = this.copy();
                pCopy.effectuerCoup(new Coup(roi.getPosition(), p));
                if (!(Boolean)(pCopy.estEnEchec(pCopy.getRoiNoir())[0])) {
                    //System.out.println("déplacement possible");
                    return false;
                }
            }    
        }
        return true;
    }

    public boolean pasDobstaclePossible(Piece roi, Piece echequeuse){
        //System.out.println("je suis là");
        Set<Piece> s;
        if (roi.isMemeCouleur(Couleur.BLANC)) {
            s = piecesBlanches;
            for (Piece piece : s) {
                for (Integer[] position : echequeuse.getListePositions()) {
                    if (piece.getListePositions().contains(position) && !piece.equals(roi)) {
                        Plateau pCopy = this.copy();
                        pCopy.effectuerCoup(new Coup(piece.getPosition(), position));
                        if (!(Boolean)pCopy.estEnEchec(pCopy.getRoiBlanc())[0]) {
                            //System.out.println("Obstacle possible");
                            return false;
                        }
                    }
                }
                if (piece.getListePositions().contains(echequeuse.getPosition())) {
                    Plateau pCopy = this.copy();
                    pCopy.effectuerCoup(new Coup(piece.getPosition(), echequeuse.getPosition()));
                    if (!(Boolean)pCopy.estEnEchec(pCopy.getRoiBlanc())[0]) {
                        //System.out.println("prise de la piece echequeuse possible");
                        return false;
                    }
                }
            }
        }
        else{
            s = piecesNoires;
            for (Piece piece : s) {
                for (Integer[] position : echequeuse.getListePositions()) {
                    if (piece.getListePositions().contains(position) && !piece.equals(roi)) {
                        Plateau pCopy = this.copy();
                        pCopy.effectuerCoup(new Coup(piece.getPosition(), position));
                        if (!(Boolean)pCopy.estEnEchec(pCopy.getRoiNoir())[0]) {
                            //System.out.println("Obstacle possible");
                            return false;
                        }
                    }
                }
                if (piece.getListePositions().contains(echequeuse.getPosition())) {
                    Plateau pCopy = this.copy();
                    pCopy.effectuerCoup(new Coup(piece.getPosition(), echequeuse.getPosition()));
                    if (!(Boolean)pCopy.estEnEchec(pCopy.getRoiNoir())[0]) {
                        //System.out.println("prise de la piece echequeuse possible");
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public boolean equals(Plateau p){
        if (p instanceof Plateau) {
            for (int i = 0; i < M.length; i++) {
                for (int j = 0; j < M.length; j++) {
                    if (!this.M[i][j].equals(p.getM()[i][j])) {
                        //System.out.println("différence entre " + this.M[i][j].toString() + p.getM()[i][j].toString());
                        return false;
                    }
                }
            }
            //System.out.println("true");
            return true;
        }
        return false;
    }
}