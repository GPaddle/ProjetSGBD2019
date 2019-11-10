/**
 * Sample Skeleton for 'Vue6.fxml' Controller Class
 */

package Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.sun.javafx.collections.ObservableListWrapper;

import DAO.CommandeDAO;
import DAO.TablDAO;
import Database.ConnectionSingleton;
import Metier.Commande;
import Metier.Tabl;
import Principale.Main;

import java.net.URL;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

public class Vue6 {

	@FXML // ResourceBundle that was given to the FXMLLoader
	private ResourceBundle resources;

	@FXML // URL location of the FXML file that was given to the FXMLLoader
	private URL location;

	@FXML // fx:id="calculer"
	private JFXButton calculer; // Value injected by FXMLLoader

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

    @FXML // fx:id="Résultat"
    private Text Résultat; // Value injected by FXMLLoader

	@FXML
	void handle(ActionEvent event) {
		Main.Action((JFXButton) event.getSource());

		if (((JFXButton) event.getSource()).getId().equals("calculer")) {

			/*
			 * System.out.println(dDeb); System.out.println(dFin);
			 */

			int numCom;
			numCom = liste.getValue();
			Connection c = cs.getConnection();

			CallableStatement cstmt;
			try {
				cstmt = c.prepareCall("{CALL majMontant(?)}");

				cstmt.setInt(1, numCom);
				cstmt.executeUpdate();
				
				CommandeDAO cd = new CommandeDAO();
				List<Commande> co = cd.getAll();
				double valeur=-1;

				for (Commande commande : co) {
					if (commande.getNumCom()==numCom) {
						valeur=commande.getMontCom();
					}
				}
				
				Résultat.setText(valeur+" €");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Fin de l'execution");

		}

	}

	@FXML // This method is called by the FXMLLoader when initialization is complete
	void initialize() {
		assert calculer != null : "fx:id=\"calculer\" was not injected: check your FXML file 'Vue6.fxml'.";
		assert ListeP != null : "fx:id=\"ListeP\" was not injected: check your FXML file 'Vue6.fxml'.";
		assert NonListP != null : "fx:id=\"NonListP\" was not injected: check your FXML file 'Vue6.fxml'.";
		assert ServeurT != null : "fx:id=\"ServeurT\" was not injected: check your FXML file 'Vue6.fxml'.";
		assert ChiffreA != null : "fx:id=\"ChiffreA\" was not injected: check your FXML file 'Vue6.fxml'.";
		assert NonCA != null : "fx:id=\"NonCA\" was not injected: check your FXML file 'Vue6.fxml'.";
		assert MAJMont != null : "fx:id=\"MAJMont\" was not injected: check your FXML file 'Vue6.fxml'.";
		assert Cancel != null : "fx:id=\"Cancel\" was not injected: check your FXML file 'Vue6.fxml'.";
		CommandeDAO cd = new CommandeDAO();

		List<Commande> al = cd.getAll();

		Integer[] als = new Integer[al.size()];

		for (int i = 0; i < als.length; i++) {
			als[i] = al.get(i).getNumCom();
		}

		ObservableList<Integer> alNumTab = new ObservableListWrapper<>(null);

		liste.getItems().setAll(als);

		cs = ConnectionSingleton.getInstance();
	}
}
