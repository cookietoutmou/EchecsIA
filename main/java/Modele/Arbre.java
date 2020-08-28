package Modele;

import java.util.ArrayList;
import java.util.List;

public class Arbre {
	private Arbre pere;
	private double valeur; // nombre de victoires du noeud dans le cas de Monte Carlo
	private double nbrParties; // utile seuelement dans MC
	private typeDeNoeud type;
	private Plateau etat;
	private Coup coup; // coup qui a amené à avoir l'état courant (this.etat)
	private List<Arbre> listeFils;
	
	public Arbre(){
		listeFils = new ArrayList<Arbre>();
	}
	public Arbre(typeDeNoeud type) {
		this.type = type;
		listeFils = new ArrayList<Arbre>();
	}
	public Arbre(Plateau p){
		this.etat = p;
		listeFils = new ArrayList<Arbre>();
		this.nbrParties = 0.0;
		this.valeur = 0.0;
	}

	public double getValeur() {
		return valeur;
	}
	public void setValeur(double valeur) {
		this.valeur = valeur;
	}

	public Plateau getEtat() {
		return this.etat;
	}
	public void setEtat(Plateau etat) {
		this.etat = etat;
	}

	public List<Arbre> getListeFils() {
		return this.listeFils;
	}
	public void setListeFils(ArrayList<Arbre> listeFils) {
		this.listeFils = listeFils;
	}
	public boolean isSansFils(){
		if (this.listeFils == null || this.listeFils.size() == 0) {
			return true;
		}
		return false;
	}
	
	public typeDeNoeud getType() {
		return this.type;
	}
	public void setType(typeDeNoeud type) {
		this.type = type;
	}
	
	public void setToutLesTypes(){
		if (this.isSansFils()) {
			return;
		}
		for (Arbre fils : this.getListeFils()) {
			fils.setType((this.type==typeDeNoeud.MIN)? typeDeNoeud.MAX : typeDeNoeud.MIN);
			fils.setToutLesTypes();
		}
	}

	public boolean coupDejaTraite(Coup c){
		for (Arbre arbre : listeFils) {
			if (arbre.getCoup().equals(c)) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public String toString() {
		StringBuilder s;
		if (this.isSansFils()) {
			s = new StringBuilder("(" + valeur + ", [])");
			return s.toString();
		}
		s = new StringBuilder("(" + valeur + ", [");
		for (Arbre arbre : this.listeFils) {
			s.append(arbre.toString() + ", ");		
		}
		return s.substring(0, s.length()-2) + "])";
	}

	public String toString2() {
		StringBuilder s;
		if (this.isSansFils()) {
			s = new StringBuilder("(" + this.valeur + "/" + this.nbrParties + ", [])");
			return s.toString();
			//return "";
		}
		s = new StringBuilder("(" + this.valeur + "/" + this.nbrParties + ", [");
		for (Arbre arbre : this.listeFils) {
			s.append(arbre.toString2() + ", ");
		}
		return s.substring(0, s.length()-2) + "])";
	}

	public Coup getCoup() {
		return coup;
	}
	public void setCoup(Coup coup) {
		this.coup = coup;
	}

	public double getNbrParties() {
		return nbrParties;
	}
	public void setNbrParties(double nbrParties) {
		this.nbrParties = nbrParties;
	}

	public Arbre getPere() {
		return pere;
	}
	public void setPere(Arbre pere) {
		this.pere = pere;
	}
	public boolean hasPere(){
		if (this.pere != null) {
			return true;
		}
		return false;
	}

	public boolean equals(Arbre a){
		if (a instanceof Arbre) {
			return this.etat.equals(a.getEtat());
		}
		return false;
	}
}
