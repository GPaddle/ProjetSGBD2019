package Controller;

import java.util.ArrayList;

import DAO.TablDAO;
import Metier.Tabl;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class TablController {
    @FXML
    GridPane GP_Layout;

    @FXML
    private void initialize()
    {
        TablDAO affecterDAO = new TablDAO();
        ArrayList<Tabl> tabls = (ArrayList<Tabl>) affecterDAO.getAll();
        for (Tabl tabl: tabls
             ) {
            GP_Layout.add(new Label(""+tabl.getNumTab()), 0, tabls.indexOf(tabl));
            GP_Layout.add(new Label(""+tabl.getNbPlace()), 1, tabls.indexOf(tabl));        }
    }
}
