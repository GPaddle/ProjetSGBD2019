package Metier;

public class Affecter {
	private int numTab = 0;
	private String dataff = "";
	private int numServ = 0;
	
	public Affecter(int n, String d, int ns) {
		numTab = n;
		dataff = d;
		numServ = ns;
	}

	public int getNumTab() {
		return numTab;
	}

	public String getDataff() {
		return dataff;
	}

	public int getNumServ() {
		return numServ;
	}
	
}
