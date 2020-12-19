/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author Hachem
 */
public class AdminForumController implements Initializable {

    @FXML
    private Button afficherAdmin;
    @FXML
    private Button retour;
    @FXML
    private Button partage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void AfficherAdmin(ActionEvent event) {
        try {
           Service.Pagination p = new Service.Pagination("/fxml/AffichageForum.fxml");
           ((Node)(event.getSource())).getScene().getWindow().hide();
           
       } catch (IOException ex) {
           System.out.println(ex.getMessage());
       }
    }

    @FXML
    private void retouradminforum(ActionEvent event) {
        try {
           Service.Pagination p = new Service.Pagination("/fxml/AdminHome.fxml");
           ((Node)(event.getSource())).getScene().getWindow().hide();
           
       } catch (IOException ex) {
           System.out.println(ex.getMessage());
       }
    }

    @FXML
    private void partagefacebook(ActionEvent event) {
         try {
           Service.Pagination p = new Service.Pagination("/fxml/FacebookShare.fxml");
           ((Node)(event.getSource())).getScene().getWindow().hide();
           
       } catch (IOException ex) {
           System.out.println(ex.getMessage());
       }
    }
    
}
