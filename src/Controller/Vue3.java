/**
 * Sample Skeleton for 'Vue3.fxml' Controller Class
 */

package Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.sun.javafx.collections.ObservableListWrapper;

import DAO.TablDAO;
import Database.ConnectionSingleton;
import Metier.Tabl;
import Principale.Main;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableListBase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class Vue3 {
	@FXML // fx:id="Resultats"
	private GridPane Resultats; // Value injected by FXMLLoader

	@FXML // ResourceBundle that was given to the FXMLLoader
	private ResourceBundle resources;

	@FXML // fx:id="dDebut"
	private JFXDatePicker dDebutPicker; // Value injected by FXMLLoader

	@FXML // fx:id="dFin"
	private JFXDatePicker dFinPicker; // Value injected by FXMLLoader

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

	@FXML // fx:id="liste"
	private JFXComboBox<Integer> liste; // Value injected by FXMLLoader

	@FXML
	void handle(ActionEvent event) {
		Main.Action((JFXButton) event.getSource());

		if (((JFXButton) event.getSource()).getId().equals("Valider")) {
			int taille = Resultats.getChildren().size();
			
			for (int i = 0; i < taille; i++) {
				Resultats.getChildren().remove(0);
			}
			
			LocalDate dDeb;
			LocalDate dFin;
			int table;

			table = liste.getValue();

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
							"select distinct nomserv, to_char(dataff, 'DD/MM/YYYY')\r\n" + "from serveur\r\n"
									+ "       inner join affecter on serveur.numserv = affecter.numserv\r\n"
									+ "where numtab = ?\r\n"
									+ "  and dataff between to_date(?, 'YYYY-MM-DD') and to_date(?, 'YYYY-MM-DD')");

					if (dDeb.isAfter(dFin)) {
						LocalDate dTemp = dFin;
						dFin = dDeb;
						dDeb = dTemp;
					}
					ps.setInt(1, table);
					ps.setString(2, dDeb.toString());
					ps.setString(3, dFin.toString());
					
					ResultSet res = ps.executeQuery();

					int i = 1;
					while (res.next()) {
//					System.out.println(res.getInt(1));
						/*
						 * if (Resultats!=null) { System.out.println("EEE"); }
						 */
						Resultats.add(new Label("" + res.getString(1)), 0, i);
						Resultats.add(new Label("" + res.getString(2)), 1, i);
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
		assert Valider != null : "fx:id=\"Valider\" was not injected: check your FXML file 'Vue3.fxml'.";
		assert ListeP != null : "fx:id=\"ListeP\" was not injected: check your FXML file 'Vue3.fxml'.";
		assert NonListP != null : "fx:id=\"NonListP\" was not injected: check your FXML file 'Vue3.fxml'.";
		assert ServeurT != null : "fx:id=\"ServeurT\" was not injected: check your FXML file 'Vue3.fxml'.";
		assert ChiffreA != null : "fx:id=\"ChiffreA\" was not injected: check your FXML file 'Vue3.fxml'.";
		assert NonCA != null : "fx:id=\"NonCA\" was not injected: check your FXML file 'Vue3.fxml'.";
		assert MAJMont != null : "fx:id=\"MAJMont\" was not injected: check your FXML file 'Vue3.fxml'.";
		assert Cancel != null : "fx:id=\"Cancel\" was not injected: check your FXML file 'Vue3.fxml'.";

		TablDAO td = new TablDAO();

		List<Tabl> al = td.getAll();

		Integer[] als = new Integer[al.size()];

		for (int i = 0; i < als.length; i++) {
			als[i] = al.get(i).getNumTab();
		}

		ObservableList<Integer> alNumTab = new ObservableListWrapper<>(null);

		liste.getItems().setAll(als);

		cs = ConnectionSingleton.getInstance();

	}
}
