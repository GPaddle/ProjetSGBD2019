/**
 * Sample Skeleton for 'Login.fxml' Controller Class
 */

package Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXSpinner;
import com.jfoenix.controls.JFXTextField;
import com.sun.javafx.collections.ObservableListWrapper;

import Connexion.IDBDD;
import DAO.CommandeDAO;
import Database.ConnectionSingleton;
import Fabrique.FabriqueMariaDB;
import Fabrique.FabriqueOracle;
import Metier.Commande;
import Principale.Main;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * @author Guillaume
 *	
 *	Controlleur dédié à la page de login
 */
public class LoginController {

	@FXML // ResourceBundle that was given to the FXMLLoader
	private ResourceBundle resources;

	@FXML // URL location of the FXML file that was given to the FXMLLoader
	private URL location;

	@FXML // fx:id="adress"
	private JFXTextField adress; // Value injected by FXMLLoader

	@FXML // fx:id="userName"
	private JFXTextField userName; // Value injected by FXMLLoader

	@FXML // fx:id="pass"
	private JFXPasswordField pass; // Value injected by FXMLLoader

	@FXML // fx:id="validate"
	private JFXButton validate; // Value injected by FXMLLoader

	@FXML
	private AnchorPane parentContent;

	@FXML
	private AnchorPane root;

	@FXML
	private JFXComboBox<String> typeBD;

	@FXML
	private VBox log;

	@FXML
	private JFXButton Cancel;

	@FXML
	private JFXSpinner Spinner;

	String[] als = { "Oracle", "MariaDB" };

	/**
	 * Utilisation des différents boutons
	 * Lance la connexion avec les différents champs remplis
	 * 
	 * @param event
	 */
	@FXML
	void handle(ActionEvent event) {

		if (((JFXButton) event.getSource()).getId().equals("Cancel")) {
			System.exit(0);
		}
		String userN = userName.getText();
		String passWord = pass.getText();
		String URL = adress.getText();

		String txt = "Nom d'utilisateur : " + userN + "\nMot de passe : ";
		for (int i = 0; i < passWord.length(); i++) {
			txt += "*";
		}
		txt += "\nURL base de donnée : " + URL;
		System.out.println(txt);

		connect(userN, passWord, URL);

	}

	private void connect(String userN, String passWord, String uRL) {
		/*
		 * log.setVisible(false); Spinner.setVisible(true); try { Thread.sleep(1000); }
		 * catch (InterruptedException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 */
		ConnectionSingleton cs;

		if (typeBD.getValue() == null || typeBD.getValue().equals("Oracle")) {
			Main.fdb = new FabriqueOracle();
		} else if (typeBD.getValue().equals("MariaDB")) {
			Main.fdb = new FabriqueMariaDB();
		} else {
			// TODO add other SGBD
			System.err.println("PB SGBD");
		}

		if (userN.equals("") && passWord.equals("") && uRL.equals("")) {
			cs = ConnectionSingleton.getInstance();

		} else {
			cs = ConnectionSingleton.getInstance(uRL, userN, passWord);
		}

		try {

//			Main.root.getChildren().remove(log);

			Main.listePane.add((AnchorPane) FXMLLoader.load(getClass().getResource("../View/Vue1.fxml")));
			Main.listePane.add((AnchorPane) FXMLLoader.load(getClass().getResource("../View/Vue2.fxml")));
			Main.listePane.add((AnchorPane) FXMLLoader.load(getClass().getResource("../View/Vue3.fxml")));
			Main.listePane.add((AnchorPane) FXMLLoader.load(getClass().getResource("../View/Vue4.fxml")));
			Main.listePane.add((AnchorPane) FXMLLoader.load(getClass().getResource("../View/Vue5.fxml")));
			Main.listePane.add((AnchorPane) FXMLLoader.load(getClass().getResource("../View/Vue6.fxml")));
			Main.listePane.add((AnchorPane) FXMLLoader.load(getClass().getResource("../View/BarreAction.fxml")));

			System.out.println("\n\n\n");

			Main.setVue(1);
		} catch (IOException e) {

			System.err.println("\n\n\nProblème de connexion\n\nVeuillez relancer l'application");
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			System.exit(1);
		}

	}

	@FXML // This method is called by the FXMLLoader when initialization is complete
	void initialize() {
		assert adress != null : "fx:id=\"adress\" was not injected: check your FXML file 'Login.fxml'.";
		assert userName != null : "fx:id=\"userName\" was not injected: check your FXML file 'Login.fxml'.";
		assert pass != null : "fx:id=\"pass\" was not injected: check your FXML file 'Login.fxml'.";
		assert validate != null : "fx:id=\"validate\" was not injected: check your FXML file 'Login.fxml'.";
		assert typeBD != null : "fx:id=\"typeBD\" was not injected: check your FXML file 'Login.fxml'.";

		typeBD.getItems().setAll(als);

	}
}
