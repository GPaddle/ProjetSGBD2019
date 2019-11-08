package Metier;

public class Contient {
	private int numCom = 0;
	private int numPlat = 0;
	private int quantite = 0;
	
	public Contient(int nc, int np, int q) {
		numCom = nc;
		numPlat = np;
		quantite = q;
	}

	public int getNumCom() {
		return numCom;
	}

	public int getNumPlat() {
		return numPlat;
	}

	public int getQuantite() {
		return quantite;
	}
	
}
