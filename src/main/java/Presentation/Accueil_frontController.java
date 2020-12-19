/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Utilitaire.UserSession;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import Service.Pagination;


/**
 * FXML Controller class
 *
 * @author Dell
 */
public class Accueil_frontController implements Initializable {

    @FXML
    private ImageView news;
    @FXML
    private ImageView event;
    @FXML
    private ImageView prog;
    @FXML
    private ImageView vente;
    @FXML
    private ImageView forum;
    @FXML
    private ImageView connecter;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

    }

    @FXML
    private void evenement(MouseEvent event) {
        try {
            Pagination p = new Pagination("/fxml/ConsulterEvenementUser.fxml");
            ((Node) (event.getSource())).getScene().getWindow().hide();
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void nwes(MouseEvent event) {

    }

    @FXML
    private void programme(MouseEvent event) {
        try {
            Pagination p = new Pagination("/fxml/progs.fxml");
            ((Node) (event.getSource())).getScene().getWindow().hide();
      
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void ticket(MouseEvent event) {
          try {
            Pagination p = new Pagination("/fxml/ListPlan.fxml");
            ((Node) (event.getSource())).getScene().getWindow().hide();
      
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
        
    }

    @FXML
    private void forum(MouseEvent event) {
    }

}
