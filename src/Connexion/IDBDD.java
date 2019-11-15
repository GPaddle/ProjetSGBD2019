package Connexion;

public class IDBDD {
	
	private String url ="jdbc:mariadb://localhost:3306";
	private String id ="root";
	private String mdp ="";
	

	//jdbc:oracle:thin:@localhost:1521:XE
	//jdbc:mariadb://localhost:3306

	
	public IDBDD(String url, String id, String mdp) {
		super();
		this.url = url;
		this.id = id;
		this.mdp = mdp;
	}

	public IDBDD() {
	}

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