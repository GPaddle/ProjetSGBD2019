/**
 * Sample Skeleton for 'BarreAction.fxml' Controller Class
 */

package Controller;

import com.jfoenix.controls.JFXButton;

import Principale.Main;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;

/**
 * @author Guillaume
 *	Controlleur dédié à la Barre d'action
 */
public class BarreAction {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

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

    /**
     * Fonction qui change la vue courante en fonction de l'appuie sur les différents boutons
     * 
     * @param event
     */
    @FXML
    void handle(ActionEvent event) {
    	if (event.getSource() instanceof JFXButton) {
    		
    		String txt = ((JFXButton) event.getSource()).getId();
    		if (txt.equals("ListeP")) {
    			// System.out.println("Liste p");
    			Main.setVue(1);

    		} else if (txt.equals("NonListP")) {
    			// System.out.println("Pas L Plat");
    			Main.setVue(2);

    		} else if (txt.equals("ServeurT")) {
    			// System.out.println("Serv t");
    			Main.setVue(3);

    		} else if (txt.equals("ChiffreA")) {
    			// System.out.println("CA + nb");
    			Main.setVue(4);

    		} else if (txt.equals("NonCA")) {
    			// System.out.println("Pas de CA");
    			Main.setVue(5);

    		} else if (txt.equals("MAJMont")) {
    			// System.out.println("Maj Mont");
    			Main.setVue(6);

    		} else if (txt.equals("Cancel")) {
    			// System.out.println("Fermer");
    			System.exit(0);
    		}
    		
    	}
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert ListeP != null : "fx:id=\"ListeP\" was not injected: check your FXML file 'BarreAction.fxml'.";
        assert NonListP != null : "fx:id=\"NonListP\" was not injected: check your FXML file 'BarreAction.fxml'.";
        assert ServeurT != null : "fx:id=\"ServeurT\" was not injected: check your FXML file 'BarreAction.fxml'.";
        assert ChiffreA != null : "fx:id=\"ChiffreA\" was not injected: check your FXML file 'BarreAction.fxml'.";
        assert NonCA != null : "fx:id=\"NonCA\" was not injected: check your FXML file 'BarreAction.fxml'.";
        assert MAJMont != null : "fx:id=\"MAJMont\" was not injected: check your FXML file 'BarreAction.fxml'.";
        assert Cancel != null : "fx:id=\"Cancel\" was not injected: check your FXML file 'BarreAction.fxml'.";

    }
}
