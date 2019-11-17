package Principale;

import java.io.IOException;
import java.util.ArrayList;
import com.jfoenix.controls.JFXButton;

import Fabrique.FabriqueDB;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

	public static ArrayList<AnchorPane> listePane = new ArrayList<>();
	public static int index = 0;
	public static AnchorPane root;
	public static FabriqueDB fdb;

	public static Stage ps;


	private double xOffset = 0;
    private double yOffset = 0;
    
	@Override
	public void start(Stage primaryStage) throws Exception {
		/*
		 * AnchorPane root =
		 * (AnchorPane)FXMLLoader.load(getClass().getResource("View/Login.fxml"));
		 * primaryStage.setTitle("TypePlat"); Scene scene = new Scene(root);
		 * primaryStage.setScene(scene); primaryStage.show();
		 */

		listePane.add((AnchorPane) FXMLLoader.load(getClass().getResource("../View/Login.fxml")));

		ps = primaryStage;
		primaryStage.initStyle(StageStyle.TRANSPARENT);
		root = (AnchorPane) FXMLLoader.load(getClass().getResource("../View/Blank.fxml"));
		
		root.setOnMousePressed(new EventHandler<MouseEvent>() {
	        @Override
	        public void handle(MouseEvent event) {
	            xOffset = event.getSceneX();
	            yOffset = event.getSceneY();
	        }
	    });
	    root.setOnMouseDragged(new EventHandler<MouseEvent>() {
	        @Override
	        public void handle(MouseEvent event) {
	            primaryStage.setX(event.getScreenX() - xOffset);
	            primaryStage.setY(event.getScreenY() - yOffset);
	        }
	    });
		
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
		if (i != 0) {
			root.getChildren().remove(listePane.get(listePane.size() - 1));

			root.getChildren().add(listePane.get(listePane.size() - 1));

		}
		setOnglet(i);
		index = i;
	}

	public static AnchorPane getVue(int i) {
		return listePane.get(i);
	}

	private static void setOnglet(int i) {
		if (root.getChildren().size() > 1) {
			AnchorPane Barre = (AnchorPane) root.getChildren().get(1);
			HBox menu = (HBox) Barre.getChildren().get(0);
			((JFXButton) (menu.getChildren().get(i-1))).setStyle("-fx-background-color:#0f02");
			if (index!=0) {
				((JFXButton) (menu.getChildren().get(index-1))).setStyle("-fx-background-color:#0002");
			}
		}
	}
}
