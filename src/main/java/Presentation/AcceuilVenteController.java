/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Presentation.Pagination;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class AcceuilVenteController implements Initializable {

    @FXML
    private Hyperlink consCat;
    @FXML
    private Hyperlink consart;
    @FXML
    private Hyperlink conscommande;
    @FXML
    private ImageView exitgv;
    @FXML
    private ImageView rtradm;
 
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        consCat.setOnAction(event->{try {
                 Pagination p = new Pagination("/fxml/AffichageCategoriea.fxml");
             } catch (IOException ex) {
                 
             }
        ((Node)(event.getSource())).getScene().getWindow().hide();});
        consart.setOnAction(event->{try {
                 Pagination p = new Pagination("/fxml/consulterArticle.fxml");
             } catch (IOException ex) {
                 
             }
        ((Node)(event.getSource())).getScene().getWindow().hide();});
       conscommande.setOnAction(event->{try {
                 Pagination p = new Pagination("/fxml/ConsulterCommande.fxml");
             } catch (IOException ex) {
                 
             }
        ((Node)(event.getSource())).getScene().getWindow().hide();});
      
        // TODO
    } 

    @FXML
    private void BtnExit(MouseEvent event) {
        System.exit(0);
       
    }

    @FXML
    private void retourespadmin(MouseEvent event) {
       try {
           Service.Pagination p = new Service.Pagination("/fxml/AdminHome.fxml");
           ((Node)(event.getSource())).getScene().getWindow().hide();
           
       } catch (IOException ex) {
           System.out.println(ex.getMessage());
       }
    }
    }
    
  
   
    
    

