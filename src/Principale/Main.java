package Principale;


import java.util.ArrayList;
import com.jfoenix.controls.JFXButton;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

	public static ArrayList<AnchorPane> listePane = new ArrayList<>();
	public static int index = 0;
	public static AnchorPane root;

	public static Stage ps;

	@Override
	public void start(Stage primaryStage) throws Exception {
		/*
		 * AnchorPane root =
		 * (AnchorPane)FXMLLoader.load(getClass().getResource("View/Login.fxml"));
		 * primaryStage.setTitle("TypePlat"); Scene scene = new Scene(root);
		 * primaryStage.setScene(scene); primaryStage.show();
		 */

		listePane.add((AnchorPane) FXMLLoader.load(getClass().getResource("../View/Login.fxml")));
		listePane.add((AnchorPane) FXMLLoader.load(getClass().getResource("../View/Vue1.fxml")));
		listePane.add((AnchorPane) FXMLLoader.load(getClass().getResource("../View/Vue2.fxml")));
		listePane.add((AnchorPane) FXMLLoader.load(getClass().getResource("../View/Vue3.fxml")));
		listePane.add((AnchorPane) FXMLLoader.load(getClass().getResource("../View/Vue4.fxml")));
		listePane.add((AnchorPane) FXMLLoader.load(getClass().getResource("../View/Vue5.fxml")));
		listePane.add((AnchorPane) FXMLLoader.load(getClass().getResource("../View/Vue6.fxml")));

		ps = primaryStage;
		primaryStage.initStyle(StageStyle.TRANSPARENT);
		root = (AnchorPane) FXMLLoader.load(getClass().getResource("../View/Blank.fxml"));
		setVue(0);

		Scene scene = new Scene(root);
//		scene.setFill(javafx.scene.paint.Color.TRANSPARENT);
//		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

	public static void setVue(int i) {
		root.getChildren().remove(listePane.get(index));
		root.getChildren().add(listePane.get(i));
		index = i;
	}

	public static AnchorPane getVue(int i) {
		return listePane.get(i);
	}

	public static void Action(JFXButton bouton) {
		String txt = bouton.getId();
		if (txt.equals("ListeP")) {
			//System.out.println("Liste p");
			Main.setVue(1);
		} else if (txt.equals("NonListP")) {
			//System.out.println("Pas L Plat");
			Main.setVue(2);

		} else if (txt.equals("ServeurT")) {
			//System.out.println("Serv t");
			Main.setVue(3);

		} else if (txt.equals("ChiffreA")) {
			//System.out.println("CA + nb");
			Main.setVue(4);

		} else if (txt.equals("NonCA")) {
			//System.out.println("Pas de CA");
			Main.setVue(5);

		} else if (txt.equals("MAJMont")) {
			//System.out.println("Maj Mont");
			Main.setVue(6);

		} else if (txt.equals("Cancel")) {
			//System.out.println("Fermer");
			System.exit(0);
			

		}

	}
}
