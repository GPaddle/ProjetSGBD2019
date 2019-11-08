package Connexion;

public class ID_BDDLocal {
	
	private String url ="jdbc:mariadb://localhost:3306/goodfood";
	private String id ="root";
	private String mdp ="";
	
	public String getId() {
		return id;
	}
	
	public String getMdp() {
		return mdp;
	}

	public String getUrl() {
		return url;
	}
}