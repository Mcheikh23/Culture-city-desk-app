/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Entity.User;
import Service.UserService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Dell
 */
public class ModifRoleController implements Initializable {

    @FXML
    private JFXTextField role;
    @FXML
    private Button modifier;
    User user=new User();
    UserService usr = new UserService();
     @FXML
    private Label btn_exit;
     @FXML
    private void exit(MouseEvent event) {
      Stage stage = (Stage) btn_exit.getScene().getWindow();
        stage.close();
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        user = ProfilUtController.u ;
        System.out.println(user);
    }    

    @FXML
    private void modifier(ActionEvent event) { 
        if (role.getText().equals("admin") )
        {   user.setRoles("a:1:{i:0;s:10:\"ROLE_ADMIN\";}");
        usr.modifierUser(user); 
        } 
        else if (role.getText().equals("user") ) 
        { 
         user.setRoles("a:0:{}");
        usr.modifierUser(user);
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Role modifié avec succés ! ");
        alert.showAndWait();
    }
    
}
