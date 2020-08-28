package Modele;

public abstract class FabriqueAbstraitePieces {
    public abstract Piece creerPion(Couleur couleur, Integer[] position);
    public abstract Piece creerTour(Couleur couleur, Integer[] position);
    public abstract Piece creerFou(Couleur couleur, Integer[] position);
    public abstract Piece creerCavalier(Couleur couleur, Integer[] position);
    public abstract Piece creerDame(Couleur couleur, Integer[] position);
    public abstract Piece creerRoi(Couleur couleur, Integer[] position);
    public abstract Piece creerCaseVide(Integer[] position);
}