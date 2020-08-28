package Modele;

public class Joueur {
    private String nom;
    private Couleur c;
    private Algorithme algo;

    
    public Joueur(String nom, Couleur c, Algorithme algo) {
        this.nom = nom;
        this.c = c;
        this.algo = algo;
    }

    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }

    public Couleur getC() {
        return c;
    }
    public void setC(Couleur c) {
        this.c = c;
    }

    public Algorithme getAlgo() {
        return algo;
    }
    public void setAlgo(Algorithme algo) {
        this.algo = algo;
    }

    public boolean equals(Joueur j){
        return this.nom == j.getNom();
    }
}