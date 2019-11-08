package Metier;

public class Commande {

	private int numCom = 0;
	private int numTab = 0;
	private String datCom = "";
	private int nbPers = 0;
	private String datPaie = "";
	private String modPaie = "";
	private double montCom = 0.0;
	
	public Commande(int nc, int nt, String dc, int np, String dp, String mp, double mc) {
		numCom = nc;
		numTab = nt;
		datCom = dc;
		nbPers = np;
		datPaie = dp;
		modPaie = mp;
		modPaie = mp;
		montCom = mc;
	}

	public int getNumCom() {
		return numCom;
	}

	public int getNumTab() {
		return numTab;
	}

	public String getDatCom() {
		return datCom;
	}

	public int getNbPers() {
		return nbPers;
	}

	public String getDatPaie() {
		return datPaie;
	}

	public String getModPaie() {
		return modPaie;
	}

	public double getMontCom() {
		return montCom;
	}
	
	
	
	
}
