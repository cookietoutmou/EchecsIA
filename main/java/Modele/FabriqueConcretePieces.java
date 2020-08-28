package Modele;

public class FabriqueConcretePieces extends FabriqueAbstraitePieces {

    @Override
    public Piece creerCavalier(Couleur couleur, Integer[] position) {
        return new Cavalier(couleur, position);
    }

    @Override
    public Piece creerDame(Couleur couleur, Integer[] position) {
        return new Dame(couleur, position);
    }

    @Override
    public Piece creerFou(Couleur couleur, Integer[] position) {
        return new Fou(couleur, position);
    }

    @Override
    public Piece creerPion(Couleur couleur, Integer[] position) {
        return new Pion(couleur, position);
    }

    @Override
    public Piece creerRoi(Couleur couleur, Integer[] position) {
        return new Roi(couleur, position);
    }

    @Override
    public Piece creerTour(Couleur couleur, Integer[] position) {
        return new Tour(couleur, position);
    }

    @Override
    public Piece creerCaseVide(Integer[] position) {
        return new caseVide(position);
    }
}