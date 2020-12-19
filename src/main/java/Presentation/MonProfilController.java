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
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import Service.Pagination;


/**
 * FXML Controller class
 *
 * @author Dell
 */
public class MonProfilController implements Initializable {

   
    @FXML
    private Hyperlink idModif;
    @FXML
    private JFXPasswordField idPass1;
    @FXML
    private ImageView idImg1;
    @FXML
    private Label idUser1; 
    @FXML
    private Label idEmail1;
    @FXML
    private JFXButton idLogout1;
    @FXML
    private Label btn_exit;
    @FXML
    private JFXButton retour;
    @FXML
    private void handleButtonAction(MouseEvent event) {
      Stage stage = (Stage) btn_exit.getScene().getWindow();
        stage.close();
    }
    @FXML
    private Label idRole1; 
    @FXML
    private Label idNom1;
    @FXML
    private Label idPrenom1;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        UserSession instance = UserSession.getInstance(null, null, null, null, null, null, null);
        idUser1.setText(instance.getUserName());
        idEmail1.setText(instance.getEmail());
        idPass1.setText(instance.getPassword());
        if(instance.getRoles().equals("a:1:{i:0;s:10:\"ROLE_ADMIN\";}"))
        idRole1.setText("Administrateur");
        else
            idRole1.setText("Simple utilisateur");
        Image im = new Image("http://localhost/PIDEVWEB/web/uploads/images/" + instance.getImage());
        idImg1.setImage(im);
        idNom1.setText(instance.getNom());
        idPrenom1.setText(instance.getPrenom());
        
    }    
    public void setUser(User user) {
       UserSession instance = UserSession.getInstance(null, null, null, null, null, null, null);
        idUser1.setText(instance.getUserName());
        idEmail1.setText(instance.getEmail());
        idPass1.setText(instance.getPassword());
        if(instance.getRoles().equals("a:1:{i:0;s:10:\"ROLE_ADMIN\";}"))
        idRole1.setText("Administrateur");
        else
            idRole1.setText("Simple utilisateur");
        Image im = new Image("http://localhost/PIDEVWEB/web/uploads/images/" + instance.getImage());
        idImg1.setImage(im);
        idNom1.setText(instance.getNom());
        idPrenom1.setText(instance.getPrenom());

    }
    
    @FXML
    public void handleBtnAction (ActionEvent event) throws IOException{
        UserSession.getInstance(null, null, null, null, null, null, null).cleanUserSession();
        Pagination p = new Pagination("/fxml/login.fxml");
        ((Node)(event.getSource())).getScene().getWindow().hide();

    }
    @FXML
    public void handleMdpAction (ActionEvent event) throws IOException{
        Pagination p = new Pagination("/fxml/ChangementMdp.fxml");
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }

    @FXML
    private void retour(ActionEvent event) throws IOException, SQLException {
       String a = UserSession.getInstance(null, null, null, null, null, null, null).getUserName();
       UserService aa = new UserService();
       User u = new User();
       u = aa.get(a);
       if (u.getRoles().equals("a:1:{i:0;s:10:\"ROLE_ADMIN\";}")){
        Pagination p = new Pagination("/fxml/AccueilAdmis.fxml");
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }
       else {
           System.out.println("cccccccc");
       }
    }
}