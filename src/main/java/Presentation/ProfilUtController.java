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
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
public class ProfilUtController implements Initializable {

    @FXML
    private ImageView idImg;
    @FXML
    private Label idUser; 
    @FXML
    private Label idEmail;
    @FXML
    private JFXButton idLogout;
    @FXML
    private JFXButton retour;
    @FXML
    private Label idRole; 
    @FXML
    private Label idNom;
    @FXML
    private Label idPrenom;
    private User user;
    UserService userService = new UserService();
    private static ProfilUtController instance;
    @FXML
    private Hyperlink modifier;
    @FXML
    private Hyperlink supp;
    public static User u;
    UserService us = new UserService();
    
    
    public ProfilUtController() {
        instance = this;
    }
    @FXML
    private Label btn_exit;
    @FXML
    private void aaaa(MouseEvent event) {
      Stage stage = (Stage) btn_exit.getScene().getWindow();
        stage.close();
    }
     public void setUser(User user) {
        this.user = user;
        idUser.setText(this.user.getUserName());
        idEmail.setText(this.user.getEmail());
        idNom.setText(this.user.getNom());
        Image im = new Image("http://localhost/PIDEVWEB/web/uploads/images/" + user.getImage());
        idImg.setImage(im);
        idPrenom.setText(this.user.getPrenom());
        if (user.getRoles().equals("a:1:{i:0;s:10:\"ROLE_ADMIN\";}"))
        idRole.setText("Administrateur");
        else
            idRole.setText("Simple utilisateur");
            ProfilUtController.u = user;

    }
    @Override
    public void initialize(URL url, ResourceBundle rb){
        

    }    
   
  
    public User getUser() { return this.user; }
    
    static public ProfilUtController getInstance() { 
        return instance;
    }
    @FXML 
    private void logout(ActionEvent event) throws SQLException, IOException {
           UserSession.getInstance(null, null,null,null,null,null,null).cleanUserSession();
           Pagination p = new Pagination("/fxml/login.fxml");
          ((Node)(event.getSource())).getScene().getWindow().hide();
    }

   @FXML
    void retour(ActionEvent event) throws IOException {
        Pagination p = new Pagination("/fxml/listeUt.fxml");
        ((Node)(event.getSource())).getScene().getWindow().hide();
    } 

    @FXML
    private void modifier(ActionEvent event) throws IOException, SQLException {
        User wal = new User();
       wal =  us.get(idUser.getText());
        System.out.println(wal+"ddddddddddddddddd");
        if (wal.getRoles().equals("a:1:{i:0;s:10:\"ROLE_ADMIN\";}")){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("Vous ne pouvez pas modifier le role d'un administrateur ! ");
        alert.showAndWait();
        }
        else {
            
            Pagination p = new Pagination("/fxml/modifRole.fxml");
        ((Node)(event.getSource())).getScene().getWindow().hide();
        }
    }

    @FXML
    private void supp(ActionEvent event) throws IOException, SQLException {
          User wal = new User();
       wal =  us.get(idUser.getText());
        System.out.println(wal);
        if (wal.getRoles().equals("a:1:{i:0;s:10:\"ROLE_ADMIN\";}")){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("Vous ne pouvez pas supprimer un administrateur ! ");
        alert.showAndWait();}
        else { 
        userService.supprimerUser(user);
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Utilisateur supprimé avec succés ! ");
        alert.showAndWait();
        Pagination p = new Pagination("/fxml/listeUt.fxml");
        ((Node)(event.getSource())).getScene().getWindow().hide();
        }
    }
}
