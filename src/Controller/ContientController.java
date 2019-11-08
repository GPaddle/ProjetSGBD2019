package Controller;

import java.util.ArrayList;

import DAO.ContientDAO;
import Metier.Contient;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class ContientController {
    @FXML
    GridPane GP_Layout;

    @FXML
    private void initialize()
    {
        ContientDAO ContientDAO = new ContientDAO();
        ArrayList<Contient> contients = (ArrayList<Contient>) ContientDAO.getAll();
        for (Contient contient: contients
             ) {
            GP_Layout.add(new Label(""+contient.getNumCom()), 0, contients.indexOf(contient));
            GP_Layout.add(new Label(""+contient.getNumPlat()), 1, contients.indexOf(contient));
            GP_Layout.add(new Label(""+contient.getQuantite()), 2, contients.indexOf(contient));
        }
    }
}