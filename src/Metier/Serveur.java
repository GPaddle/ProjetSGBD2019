package Metier;

public class Serveur {

	private int numServ = 0;
	private String nomServ = "";
	private String grade = "";
	
	public Serveur(int ns, String nserv, String g) {
		numServ = ns;
		nomServ = nserv;
		grade = g;
	}

	public int getNumServ() {
		return numServ;
	}

	public String getNomServ() {
		return nomServ;
	}

	public String getGrade() {
		return grade;
	}
	
}
