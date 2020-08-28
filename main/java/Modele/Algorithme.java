package Modele;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public abstract class Algorithme {
    public abstract Coup creerCoup(Plateau p, Joueur j);
    public Coup creerCoupAleatoire(Plateau p, Joueur j){
        Set<Object[]> s = new HashSet<Object[]>();
        Object[] choix;
        Random r = new Random();
        if (j.getC() == Couleur.BLANC) {
            for (Piece piece : p.getPiecesBlanches()) {
                for (Integer[] position : piece.getListePositions()) {
                    s.add(new Object[] {piece.getPosition(), position});
                }
            }
            if (s.size()==0) {
                for (Piece piece : p.getPiecesBlanches()) {
                    s.add(new Object[] {piece.getPosition(), piece.getPosition()});
                    break;
                }
            }
        }
        else{
            for (Piece piece : p.getPiecesNoires()) {
                for (Integer[] position : piece.getListePositions()) {
                    s.add(new Object[] {piece.getPosition(), position});
                }
            }
            if (s.size()==0) {
                for (Piece piece : p.getPiecesNoires()) {
                    s.add(new Object[] {piece.getPosition(), piece.getPosition()});
                    break;
                }
            }
        }
        choix = (Object[])s.toArray()[r.nextInt(s.size())];
        return new Coup((Integer[])choix[0], (Integer[])choix[1]);
    }
}