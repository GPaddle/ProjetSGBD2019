package Controller;

import java.util.ArrayList;

import DAO.AffecterDAO;
import Metier.Affecter;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class AffecterController {
    @FXML
    GridPane GP_Layout;

    @FXML
    private void initialize()
    {
        AffecterDAO affecterDAO = new AffecterDAO();
        ArrayList<Affecter> affecters = (ArrayList<Affecter>) affecterDAO.getAll();
        for (Affecter affecter: affecters
             ) {
            GP_Layout.add(new Label(""+affecter.getNumTab()), 0, affecters.indexOf(affecter));
            GP_Layout.add(new Label(affecter.getDataff()), 1, affecters.indexOf(affecter));
            GP_Layout.add(new Label(""+affecter.getNumServ()), 2, affecters.indexOf(affecter));
        }
    }
}