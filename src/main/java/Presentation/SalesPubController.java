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
public class SalesPubController implements Initializable {

    @FXML
    private Button gestionsales;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         gestionsales.setOnAction(event->{try {
                 Service.Pagination p = new Service.Pagination("/fxml/Listedesarticles.fxml");
             } catch (IOException ex) {
                 
             }
        ((Node)(event.getSource())).getScene().getWindow().hide();});
    }    

       

    @FXML
    private void gestionssales(ActionEvent event) {
    }
    

}