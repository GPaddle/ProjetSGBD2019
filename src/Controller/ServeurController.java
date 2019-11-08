package Controller;

import java.util.ArrayList;

import DAO.ServeurDAO;
import Metier.Serveur;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class ServeurController {
	public class AffecterController {
	    @FXML
	    GridPane GP_Layout;

	    @FXML
	    private void initialize()
	    {
	        ServeurDAO ServeurDAO = new ServeurDAO();
	        ArrayList<Serveur> serveurs = (ArrayList<Serveur>) ServeurDAO.getAll();
	        for (Serveur serveur: serveurs
	             ) {
	            GP_Layout.add(new Label(""+serveur.getNumServ()), 0, serveurs.indexOf(serveur));
	            GP_Layout.add(new Label(""+serveur.getNomServ()), 1, serveurs.indexOf(serveur));
	            GP_Layout.add(new Label(""+serveur.getNumServ()), 2, serveurs.indexOf(serveur));
	        }
	    }
	}
}