/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Entity.Sujet;
import Service.Pagination;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class AcceuilForumController implements Initializable {

    @FXML
    private Pane content;
    @FXML
    private HBox id_afficherTous;
   @FXML
    private HBox id_ajouter;
     @FXML
    private HBox id_afficherMesSujets;
    @FXML
    private JFXTextField textRechercher;
    @FXML
    private Button logout;
    private HBox id_reclamation;
    @FXML
    private HBox id_mailing;
    @FXML
    private HBox id_statistiquesforum;
    @FXML
    private HBox id_rechercheavance;
    @FXML
    private HBox id_sms;
    @FXML
    private HBox id_facebook;
 
    @FXML
    void btn_afficherTous(MouseEvent event) {
         if (event.getTarget() == id_afficherTous) {
			try {
				AnchorPane newLoadedPane = FXMLLoader.load(getClass().getResource("/fxml/AffichageForum.fxml"));
				content.getChildren().clear();
				content.getChildren().add(newLoadedPane);
			} catch (IOException ex) {
				Logger.getLogger(AcceuilForumController.class.getName()).log(Level.SEVERE, null, ex);
			}
         }
         
         if (event.getTarget() == id_ajouter) {
			try {
				AnchorPane newLoadedPane = FXMLLoader.load(getClass().getResource("/fxml/AjoutSujet.fxml"));
				content.getChildren().clear();
				content.getChildren().add(newLoadedPane);
			} catch (IOException ex) {
				Logger.getLogger(AcceuilForumController.class.getName()).log(Level.SEVERE, null, ex);
			}
         }
         if (event.getTarget() == id_afficherMesSujets) {
			try {
				AnchorPane newLoadedPane = FXMLLoader.load(getClass().getResource("/fxml/MesSujets.fxml"));
				content.getChildren().clear();
				content.getChildren().add(newLoadedPane);
			} catch (IOException ex) {
				Logger.getLogger(AcceuilForumController.class.getName()).log(Level.SEVERE, null, ex);
			}
         }
          if (event.getTarget() == id_mailing) {
			try {
				AnchorPane newLoadedPane = FXMLLoader.load(getClass().getResource("/fxml/Reclamation.fxml"));
				content.getChildren().clear();
				content.getChildren().add(newLoadedPane);
			} catch (IOException ex) {
				Logger.getLogger(AcceuilForumController.class.getName()).log(Level.SEVERE, null, ex);
			}
         }
          if (event.getTarget() == id_statistiquesforum) {
			try {
				AnchorPane newLoadedPane = FXMLLoader.load(getClass().getResource("/fxml/Statistique.fxml"));
				content.getChildren().clear();
				content.getChildren().add(newLoadedPane);
			} catch (IOException ex) {
				Logger.getLogger(AcceuilForumController.class.getName()).log(Level.SEVERE, null, ex);
			}
         }
          if (event.getTarget() == id_rechercheavance) {
			try {
				AnchorPane newLoadedPane = FXMLLoader.load(getClass().getResource("/fxml/RechercheForum.fxml"));
				content.getChildren().clear();
				content.getChildren().add(newLoadedPane);
			} catch (IOException ex) {
				Logger.getLogger(AcceuilForumController.class.getName()).log(Level.SEVERE, null, ex);
			}
    }
          if (event.getTarget() == id_sms) {
			try {
				AnchorPane newLoadedPane = FXMLLoader.load(getClass().getResource("/fxml/Historique.fxml"));
				content.getChildren().clear();
				content.getChildren().add(newLoadedPane);
			} catch (IOException ex) {
				Logger.getLogger(AcceuilForumController.class.getName()).log(Level.SEVERE, null, ex);
			}
         }
           if (event.getTarget() == id_facebook) {
			try {
				AnchorPane newLoadedPane = FXMLLoader.load(getClass().getResource("/fxml/FacebookShare.fxml"));
				content.getChildren().clear();
				content.getChildren().add(newLoadedPane);
			} catch (IOException ex) {
				Logger.getLogger(AcceuilForumController.class.getName()).log(Level.SEVERE, null, ex);
			}
         }
    }
   
      
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    



    @FXML
    private void logout(ActionEvent event) {
        try {
           Pagination p = new Pagination("/fxml/AcceuilCC.fxml");
           ((Node)(event.getSource())).getScene().getWindow().hide();
           
       } catch (IOException ex) {
           System.out.println(ex.getMessage());
       }
    }
    
}
