package Metier;

public class TypePlat {
	
	private int numTypePlat = 0;
    private String nomTypePlat = "";

    public TypePlat(int num, String nom){
        this.numTypePlat = num;
        this.nomTypePlat = nom;
    }

    public int getNumTypePlat() {
        return numTypePlat;
    }

    public void setNumTypePlat(int numTypePlat) {
        this.numTypePlat = numTypePlat;
    }

    public String getNomTypePlat() {
        return nomTypePlat;
    }

    public void setNomTypePlat(String nomTypePlat) {
        this.nomTypePlat = nomTypePlat;
    }
}
