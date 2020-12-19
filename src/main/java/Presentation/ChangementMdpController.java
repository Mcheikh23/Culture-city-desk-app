/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Entity.User;
import Service.UserService;
import Utilitaire.UserSession;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import Service.Pagination;


/**
 * FXML Controller class
 *
 * @author Dell
 */
public class ChangementMdpController implements Initializable {
    @FXML
    private Label btn_exit;
    @FXML
    private JFXButton btn_retour;
    @FXML
    private void exit(MouseEvent event) {
      Stage stage = (Stage) btn_exit.getScene().getWindow();
        stage.close();
    }
    @FXML
    private JFXPasswordField old;
    @FXML
    private JFXPasswordField nouv;
    @FXML
    private JFXPasswordField nouv1;
    @FXML
    private JFXButton changer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void handleBtnAction(ActionEvent event) throws SQLException {
        String usr = UserSession.getInstance(null, null, null, null, null, null, null).getUserName();
        User u = new User();
        UserService userService = new UserService ();
        
            u = userService.get(usr);
                    
            if  (old.getText().equals(u.getPassword())  ){
            nouv.setVisible(true);
            nouv1.setVisible(true);
  if (!(nouv.getText().equals(nouv1.getText()))){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Les deux mots de passes ne sont pas identiques ! ");
            alert.showAndWait();
        }
        else {
        u.setPassword(u.getPassword());
        System.err.println("sssssss");
        //userService.modifierUserPass(u);
        String req="UPDATE user SET password=? WHERE id = "+u.getId() ; 
                System.err.println("zzzzzzzzzz");

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Mot de passe modifié avec succés ! ");
        alert.showAndWait();
        
        }           
    }
        
} 

    @FXML
    private void Retour(ActionEvent event) throws IOException {
         Pagination p = new Pagination("/fxml/monProfil.fxml");
         ((Node)(event.getSource())).getScene().getWindow().hide();
    }
    }
    

