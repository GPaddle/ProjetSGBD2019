/**
 * Sample Skeleton for 'Vue4.fxml' Controller Class
 */

package Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;

import Database.ConnectionSingleton;
import Principale.Main;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class Vue4 {
	@FXML // fx:id="Resultats"
	private GridPane Resultats; // Value injected by FXMLLoader

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="Valider"
    private JFXButton Valider; // Value injected by FXMLLoader

    @FXML // fx:id="ListeP"
    private JFXButton ListeP; // Value injected by FXMLLoader

    @FXML // fx:id="NonListP"
    private JFXButton NonListP; // Value injected by FXMLLoader

    @FXML // fx:id="ServeurT"
    private JFXButton ServeurT; // Value injected by FXMLLoader

    @FXML // fx:id="ChiffreA"
    private JFXButton ChiffreA; // Value injected by FXMLLoader

    @FXML // fx:id="NonCA"
    private JFXButton NonCA; // Value injected by FXMLLoader

    @FXML // fx:id="MAJMont"
    private JFXButton MAJMont; // Value injected by FXMLLoader

    @FXML // fx:id="Cancel"
    private JFXButton Cancel; // Value injected by FXMLLoader
	private ConnectionSingleton cs;

	@FXML // fx:id="dDebut"
	private JFXDatePicker dDebutPicker; // Value injected by FXMLLoader

	@FXML // fx:id="dFin"
	private JFXDatePicker dFinPicker; // Value injected by FXMLLoader

    @FXML
    void handle(ActionEvent event) {
    	Main.Action((JFXButton)event.getSource());
    	
    	if (((JFXButton) event.getSource()).getId().equals("Valider")) {
			int taille = Resultats.getChildren().size();
			
			for (int i = 0; i < taille; i++) {
				Resultats.getChildren().remove(0);
			}
			
			LocalDate dDeb;
			LocalDate dFin;

			dDeb = dDebutPicker.getValue();
			dFin = dFinPicker.getValue();

			if (dDeb != null && dFin != null) {

				/*
				 * System.out.println(dDeb); System.out.println(dFin);
				 */

				Connection c = cs.getConnection();

				PreparedStatement ps;
				try {
					ps = c.prepareStatement(
							"select serveur.nomserv, sum(montcom), count(*)\r\n" + 
							"from serveur\r\n" + 
							"       inner join affecter on serveur.numserv = affecter.numserv\r\n" + 
							"       inner join tabl on tabl.numtab = affecter.numtab\r\n" + 
							"       inner join commande on commande.numtab = tabl.numtab\r\n" + 
							"where datcom between to_date(?, 'YYYY-MM-DD') and to_date(?, 'YYYY-MM-DD')\r\n" + 
							"group by serveur.nomserv, serveur.numserv\r\n" + 
							"order by sum(montcom) DESC");
//SALUT GUILLAUME REGARDE HAUT DESSUS !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

					if (dDeb.isAfter(dFin)) {
						LocalDate dTemp = dFin;
						dFin = dDeb;
						dDeb = dTemp;
					}
					ps.setString(1, dDeb.toString());
					ps.setString(2, dFin.toString());
					
					ResultSet res = ps.executeQuery();

					int i = 1;
					while (res.next()) {
//					System.out.println(res.getInt(1));
						/*
						 * if (Resultats!=null) { System.out.println("EEE"); }
						 */
						Resultats.add(new Label("" + res.getString(1)), 0, i);
						Resultats.add(new Label("" + res.getDouble(2)), 1, i);
						Resultats.add(new Label("" + res.getInt(3)), 2, i);
						i++;

					}
					System.out.println("Fin de l'execution");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		}
    	
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert Valider != null : "fx:id=\"Valider\" was not injected: check your FXML file 'Vue4.fxml'.";
        assert ListeP != null : "fx:id=\"ListeP\" was not injected: check your FXML file 'Vue4.fxml'.";
        assert NonListP != null : "fx:id=\"NonListP\" was not injected: check your FXML file 'Vue4.fxml'.";
        assert ServeurT != null : "fx:id=\"ServeurT\" was not injected: check your FXML file 'Vue4.fxml'.";
        assert ChiffreA != null : "fx:id=\"ChiffreA\" was not injected: check your FXML file 'Vue4.fxml'.";
        assert NonCA != null : "fx:id=\"NonCA\" was not injected: check your FXML file 'Vue4.fxml'.";
        assert MAJMont != null : "fx:id=\"MAJMont\" was not injected: check your FXML file 'Vue4.fxml'.";
        assert Cancel != null : "fx:id=\"Cancel\" was not injected: check your FXML file 'Vue4.fxml'.";
		cs = ConnectionSingleton.getInstance();

    }
}
