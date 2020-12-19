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
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import Service.Pagination;
import javafx.scene.control.Label;


/**
 * FXML Controller class
 *
 * @author Dell
 */
public class AccueilAdmisController implements Initializable {

    UserService userService =new UserService();
    User u = new User();
   @FXML
    private Hyperlink idlogout;
    @FXML
    private ImageView iduser;
    @FXML
    private ImageView idprog;
    
    @FXML
    private ImageView idevnt;
    @FXML
    private Label idevntt;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
           String arg = (UserSession.getInstance(null, null, null, null, null, null, null).getUserName());
        
       try {
            u = userService.get(arg);
            
            javafx.scene.image.Image im = new javafx.scene.image.Image("http://localhost/PIDEVWEBB/web/uploads/images/" + u.getImage());
            iduser.setImage(im);
        } catch (SQLException ex) {
        }
         
    }    

    @FXML
    private void idlogout(ActionEvent event) {
       try {
           Pagination p = new Pagination("/fxml/login.fxml");
           ((Node)(event.getSource())).getScene().getWindow().hide();
           UserSession.getInstance(null, null, null, null, null, null, null).cleanUserSession();
       } catch (IOException ex) {
           System.out.println(ex.getMessage());
       }
    }


    @FXML
    private void idprog(MouseEvent event) {
       try {
           Pagination p = new Pagination("/fxml/listeUt.fxml");
           ((Node)(event.getSource())).getScene().getWindow().hide();
       } catch (IOException ex) {
           System.out.println(ex.getMessage());
       }
    }
    
   @FXML
    private void idevnt(MouseEvent event) {
       try {
           Pagination p = new Pagination("/fxml/AcceuilTech.fxml");
           ((Node)(event.getSource())).getScene().getWindow().hide();
       } catch (IOException ex) {
           System.out.println(ex.getMessage());
       }
    }
    
    private void idevntt(MouseEvent event) {
       try {
           Pagination p = new Pagination("/fxml/AcceuilTech.fxml");
           ((Node)(event.getSource())).getScene().getWindow().hide();
       } catch (IOException ex) {
           System.out.println(ex.getMessage());
       }
    }

  
}
