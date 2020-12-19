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
public class ClubPubController implements Initializable {

    @FXML
    private Button gestionclub;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         gestionclub.setOnAction(event->{try {
                 Service.Pagination p = new Service.Pagination("/fxml/AccueilClub.fxml");
             } catch (IOException ex) {
                 
             }
        ((Node)(event.getSource())).getScene().getWindow().hide();});
    }    

    @FXML
    private void gestionclub(ActionEvent event) {
    }
    
}
