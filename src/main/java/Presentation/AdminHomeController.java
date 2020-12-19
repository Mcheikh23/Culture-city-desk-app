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
public class AdminHomeController implements Initializable {

    @FXML
    private Button quit;
    @FXML
    private Button adminevents;
    @FXML
    private Button adminClub;
    @FXML
    private Button adminForum;
    @FXML
    private Button adminlib;
    @FXML
    private Button adminventes;
    @FXML
    private Button admincinema;
    @FXML
    private Button admingestionuser;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void quit(ActionEvent event) throws IOException {
        
        Service.Pagination p = new Service.Pagination("/fxml/login.fxml");
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }

    @FXML
    private void adminevents(ActionEvent event) {
           try {
           Service.Pagination p = new Service.Pagination("/fxml/AcceuilTech.fxml");
           ((Node)(event.getSource())).getScene().getWindow().hide();
       } catch (IOException ex) {
           System.out.println(ex.getMessage());
       }
        
    }

    @FXML
    private void adminClub(ActionEvent event) {
        try {
           Service.Pagination p = new Service.Pagination("/fxml/AccueilClub.fxml");
           ((Node)(event.getSource())).getScene().getWindow().hide();
           
       } catch (IOException ex) {
           System.out.println(ex.getMessage());
       }
    }

    @FXML
    private void adminForum(ActionEvent event) {
        try {
           Service.Pagination p = new Service.Pagination("/fxml/AdminForum.fxml");
           ((Node)(event.getSource())).getScene().getWindow().hide();
           
       } catch (IOException ex) {
           System.out.println(ex.getMessage());
       }
    }

    @FXML
    private void adminventes(ActionEvent event) {
        try {
           Service.Pagination p = new Service.Pagination("/fxml/AcceuilVente.fxml");
           ((Node)(event.getSource())).getScene().getWindow().hide();
           
       } catch (IOException ex) {
           System.out.println(ex.getMessage());
       }
    }

    @FXML
    private void admincinema(ActionEvent event) {
        try {
           Service.Pagination p = new Service.Pagination("/fxml/Cinema.fxml");
           ((Node)(event.getSource())).getScene().getWindow().hide();
           
       } catch (IOException ex) {
           System.out.println(ex.getMessage());
       }
    }

    @FXML
    private void admingestionuser(ActionEvent event) {
        try {
           Service.Pagination p = new Service.Pagination("/fxml/listeUt.fxml");
           ((Node)(event.getSource())).getScene().getWindow().hide();
       } catch (IOException ex) {
           System.out.println(ex.getMessage());
       }
    }

    @FXML
    private void adminlib(ActionEvent event) {
    }
    
}
