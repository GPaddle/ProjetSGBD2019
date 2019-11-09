package Metier;

public class Tabl {

	private int numTab;
	private int nbPlace;
	
	
	
	public Tabl(int numTab, int nbPlace) {
		super();
		this.numTab = numTab;
		this.nbPlace = nbPlace;
	}
	public int getNumTab() {
		return numTab;
	}
	public void setNumTab(int numtab) {
		this.numTab = numtab;
	}
	/**
	 * @return the nbplace
	 */
	public int getNbPlace() {
		return nbPlace;
	}
	/**
	 * @param nbplace the nbplace to set
	 */
	public void setNbPlace(int nbplace) {
		this.nbPlace = nbplace;
	}
	
}
