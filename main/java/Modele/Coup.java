package Modele;

public class Coup { // chaque piece sa liste de coups possibles, dépend si une pièce fait obstacle
                    // ou si une piece en diagonale pour le pion.
    Integer[] debut;
    Integer[] fin;
    
    public Coup(Integer[] debut, Integer[] fin) {
        this.debut = debut;
        this.fin = fin;
    }

    public Integer[] getDebut() {
        return debut;
    }
    public void setDebut(Integer[] debut) {
        this.debut = debut;
    }

    public Integer[] getFin() {
        return fin;
    }
    public void setFin(Integer[] fin) {
        this.fin = fin;
    }

    @Override
    public String toString(){
        return Integer.toString(this.debut[0]+1) + Integer.toString(this.debut[1]+1) + "  --->  " + Integer.toString(this.fin[0]+1) + Integer.toString(this.fin[1]+1); 
    }
}