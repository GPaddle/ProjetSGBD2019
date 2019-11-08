package Controller;

import java.util.ArrayList;

import DAO.CommandeDAO;
import Metier.Commande;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class CommandeController {
    @FXML
    GridPane GP_Layout;

    @FXML
    private void initialize()
    {
        CommandeDAO commandeDAO = new CommandeDAO();
        ArrayList<Commande> commandes = (ArrayList<Commande>) commandeDAO.getAll();
        for (Commande commande: commandes
             ) {
            GP_Layout.add(new Label(""+commande.getNumCom()), 0, commandes.indexOf(commande));
            GP_Layout.add(new Label(""+commande.getNumTab()), 1, commandes.indexOf(commande));
            GP_Layout.add(new Label(commande.getDatCom()), 2, commandes.indexOf(commande));
            GP_Layout.add(new Label(""+commande.getNbPers()), 3, commandes.indexOf(commande));
            GP_Layout.add(new Label(commande.getDatPaie()), 4, commandes.indexOf(commande));
            GP_Layout.add(new Label(commande.getModPaie()), 5, commandes.indexOf(commande));
            GP_Layout.add(new Label(commande.getModPaie()), 6, commandes.indexOf(commande));
            GP_Layout.add(new Label(""+commande.getMontCom()), 7, commandes.indexOf(commande));
        }
    }
}
