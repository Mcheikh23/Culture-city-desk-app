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
 * @author Hachem
 */
public class AcceuilCCController implements Initializable {

    @FXML
    private HBox id_evta;
    @FXML
    private Label evnta;
    @FXML
    private Label reserva;
    @FXML
    private Pane content1;
    @FXML
    private Pane content;
    @FXML
    private HBox id_sales;
    @FXML
    private HBox id_cinema;
    @FXML
    private HBox id_labrary;
    @FXML
    private HBox id_club;
    @FXML
    private HBox id_forum;
    @FXML
    private Button logoutapp;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btn_afficherTouse(MouseEvent event) {
        if (event.getTarget() == id_forum) {
			try {
				AnchorPane newLoadedPane = FXMLLoader.load(getClass().getResource("/fxml/ForumPub.fxml"));
				content.getChildren().clear();
				content.getChildren().add(newLoadedPane);
			} catch (IOException ex) {
				Logger.getLogger(AcceuilForumController.class.getName()).log(Level.SEVERE, null, ex);
			}
         }
        if (event.getTarget() == id_club) {
			try {
				AnchorPane newLoadedPane = FXMLLoader.load(getClass().getResource("/fxml/ConsulterClub.fxml"));
				content.getChildren().clear();
				content.getChildren().add(newLoadedPane);
			} catch (IOException ex) {
				Logger.getLogger(AcceuilForumController.class.getName()).log(Level.SEVERE, null, ex);
			}
         }
         if (event.getTarget() == id_labrary) {
			try {
				AnchorPane newLoadedPane = FXMLLoader.load(getClass().getResource("/fxml/LibPub.fxml"));
				content.getChildren().clear();
				content.getChildren().add(newLoadedPane);
			} catch (IOException ex) {
				Logger.getLogger(AcceuilForumController.class.getName()).log(Level.SEVERE, null, ex);
			}
         }
                 if (event.getTarget() == id_cinema) {
			try {
				AnchorPane newLoadedPane = FXMLLoader.load(getClass().getResource("/fxml/cinemaPub.fxml"));
				content.getChildren().clear();
				content.getChildren().add(newLoadedPane);
			} catch (IOException ex) {
				Logger.getLogger(AcceuilForumController.class.getName()).log(Level.SEVERE, null, ex);
			}
         }
                 if (event.getTarget() == id_sales) {
			try {
				AnchorPane newLoadedPane = FXMLLoader.load(getClass().getResource("/fxml/SalesPub.fxml"));
				content.getChildren().clear();
				content.getChildren().add(newLoadedPane);
			} catch (IOException ex) {
				Logger.getLogger(AcceuilForumController.class.getName()).log(Level.SEVERE, null, ex);
			}
         }
                 if (event.getTarget() == id_evta) {
			try {
				AnchorPane newLoadedPane = FXMLLoader.load(getClass().getResource("/fxml/EventsPub.fxml"));
				content.getChildren().clear();
				content.getChildren().add(newLoadedPane);
			} catch (IOException ex) {
				Logger.getLogger(AcceuilForumController.class.getName()).log(Level.SEVERE, null, ex);
			}
         }
    }

    @FXML
    private void logoutapp(ActionEvent event) {
         try {
           Service.Pagination p = new Service.Pagination("/fxml/login.fxml");
           ((Node)(event.getSource())).getScene().getWindow().hide();
           
       } catch (IOException ex) {
           System.out.println(ex.getMessage());
       }
    }
    
}
