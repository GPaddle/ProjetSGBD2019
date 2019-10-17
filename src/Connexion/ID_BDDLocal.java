package Connexion;


public class ID_BDDLocal {
	private String url ="jdbc:oracle:thin:@localhost:1521:XE";
	private String id ="GPaddle";
	private String mdp ="GPaddle";
	
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
