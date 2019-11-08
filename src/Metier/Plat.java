package Metier;

public class Plat {
	
    private int numPlat = 0;
    private String libelle= "";
    private String type="";
    private double prixunit=0.0;

    public Plat(int num, String nom, String type, double prix){
        this.numPlat = num;
        this.libelle = nom;
        this.type = type;
        this.prixunit = prix;
    }

	public String getLibelle() {
		return libelle;
	}

	public int getNumPlat() {
		return numPlat;
	}

	public String getType() {
		return type;
	}

	public double getPrixunit() {
		return prixunit;
	}
}