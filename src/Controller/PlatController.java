package Controller;

import DAO.PlatDAO;
import Metier.Plat;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import java.util.ArrayList;

public class PlatController {

    @FXML
    GridPane GP_Layout;

    @FXML
    private void initialize()
    {
        PlatDAO PlatDAO = new PlatDAO();
        ArrayList<Plat> plats = (ArrayList<Plat>) PlatDAO.getAll();
        for (Plat Plat: plats
             ) {
            GP_Layout.add(new Label(""+Plat.getNumPlat()), 0, plats.indexOf(Plat));
            GP_Layout.add(new Label(Plat.getLibelle()), 1, plats.indexOf(Plat));
            GP_Layout.add(new Label(Plat.getType()), 1, plats.indexOf(Plat));            
            GP_Layout.add(new Label(""+Plat.getPrixunit()), 1, plats.indexOf(Plat));
        }
    }
}