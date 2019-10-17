package Connexion;

public class ID_BDDLocal {
	
	private String url ="jdbc:oracle:thin:@localhost:1521:XE";
	private String id ="achterfe2u";
	private String mdp ="mdpOracle";
	
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