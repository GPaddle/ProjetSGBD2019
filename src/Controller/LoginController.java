/**
 * Sample Skeleton for 'Login.fxml' Controller Class
 */

package Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import Connexion.IDBDD;
import Database.ConnectionSingleton;
import Principale.Main;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

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
	void handle(ActionEvent event) {
		String userN = userName.getText();
		String passWord = pass.getText();
		String URL = adress.getText();

		System.out.println(
				"Nom d'utilisateur : " + userN + "\nMot de passe : " + passWord + "\nURL base de donnée : " + URL);
		Main.Action((JFXButton) event.getSource());

		connect(userN, passWord, URL);

	}

	private void connect(String userN, String passWord, String uRL) {
		ConnectionSingleton cs;
		if (userN.equals("") && passWord.equals("") && uRL.equals("")) {
			cs = ConnectionSingleton.getInstance();
			
		} else {
			cs = ConnectionSingleton.getInstance(uRL, userN, passWord);
		}
		System.out.println(cs);

		// System.out.println(root);

		try {

			Main.listePane.add((AnchorPane) FXMLLoader.load(getClass().getResource("../View/Vue1.fxml")));
			Main.listePane.add((AnchorPane) FXMLLoader.load(getClass().getResource("../View/Vue2.fxml")));
			Main.listePane.add((AnchorPane) FXMLLoader.load(getClass().getResource("../View/Vue3.fxml")));
			Main.listePane.add((AnchorPane) FXMLLoader.load(getClass().getResource("../View/Vue4.fxml")));
			Main.listePane.add((AnchorPane) FXMLLoader.load(getClass().getResource("../View/Vue5.fxml")));
			Main.listePane.add((AnchorPane) FXMLLoader.load(getClass().getResource("../View/Vue6.fxml")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Main.setVue(1);

	}

	@FXML // This method is called by the FXMLLoader when initialization is complete
	void initialize() {
		assert adress != null : "fx:id=\"adress\" was not injected: check your FXML file 'Login.fxml'.";
		assert userName != null : "fx:id=\"userName\" was not injected: check your FXML file 'Login.fxml'.";
		assert pass != null : "fx:id=\"pass\" was not injected: check your FXML file 'Login.fxml'.";
		assert validate != null : "fx:id=\"validate\" was not injected: check your FXML file 'Login.fxml'.";

	}
}
