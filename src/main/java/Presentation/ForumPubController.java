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
import Service.Pagination;
/**
 * FXML Controller class
 *
 * @author Hachem
 */
public class ForumPubController implements Initializable {

    @FXML
    private Button gestionForum;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         gestionForum.setOnAction(event->{try {
                 Pagination p = new Pagination("/fxml/AcceuilForum.fxml");
             } catch (IOException ex) {
                 
             }
        ((Node)(event.getSource())).getScene().getWindow().hide();});
    }    

    @FXML
    private void gestionForum(ActionEvent event) {
    }
    
}
