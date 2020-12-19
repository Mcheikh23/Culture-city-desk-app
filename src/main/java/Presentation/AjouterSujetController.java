/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Entity.Categorie;
import Entity.Sujet;
import Entity.User;
import Service.UserService;
import Service.SujetService;
import com.mycompany.projetman.MainApp;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class AjouterSujetController implements Initializable {

@FXML
    private JFXTextField f_titre;

    @FXML
    private JFXButton id_add;
  @FXML
    private JFXTextArea id_description;
@FXML
    private JFXComboBox<Categorie> id_categorie;
 @FXML
    private AnchorPane id_page_ajout;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         id_categorie.getItems().removeAll(id_categorie.getItems());
        id_categorie.getItems().addAll(Categorie.Astronomie,Categorie.Bio_informatique
        ,Categorie.Electronique,Categorie.Informatique,Categorie.Robotique,Categorie.Transport);
    }   
    
    
   public static  SujetService sujetService = new SujetService();
            
     @FXML
    void add(ActionEvent event) throws SQLException {
    
         Sujet sujet = new Sujet(MainApp.user.getId(),id_description.getText() 
                 );
         
         sujetService.ajouterSujet(sujet);
          try {
                        
                       AnchorPane newLoadedPane = FXMLLoader.load(getClass().getResource("/fxml/AjoutSujet.fxml"));
                        id_page_ajout.getChildren().clear();
			id_page_ajout.getChildren().add(newLoadedPane);
                    } catch (IOException ex) {
                        Logger.getLogger(AjouterSujetController.class.getName()).log(Level.SEVERE, null, ex);
                    }
         Image img = new Image("/confirmation.jpg");
         Notifications notificationBuilder = Notifications.create()
                 .title("Demande D'ajout")
                 .text("Mercie pour attendre la réponse")
                 .graphic(new ImageView(img))
                 .hideAfter(Duration.seconds(5))
                 .position(Pos.TOP_RIGHT);
         
         notificationBuilder.showConfirm();
                 
                 
    }
}
