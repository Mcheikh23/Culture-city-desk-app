/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Dell
 */
public class AcceuilTechController implements Initializable {

    @FXML
    private HBox id_evta;
    @FXML
    private Label evnta;
    @FXML
    private Pane content;
    @FXML
    private Button logout;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btn_afficherTouse(MouseEvent event) {
        
         if (event.getTarget() == id_evta) {
			try {
				AnchorPane newLoadedPane = FXMLLoader.load(getClass().getResource("/fxml/afficherE.fxml"));
				content.getChildren().clear();
				content.getChildren().add(newLoadedPane);
			} catch (IOException ex) {
				Logger.getLogger(AcceuilTechController.class.getName()).log(Level.SEVERE, null, ex);
			}
         }
        
    }

    @FXML
    private void logout(ActionEvent event) {
         try {
           Service.Pagination p = new Service.Pagination("/fxml/AdminHome.fxml");
           ((Node)(event.getSource())).getScene().getWindow().hide();
           
       } catch (IOException ex) {
           System.out.println(ex.getMessage());
       }
    }
    
}
