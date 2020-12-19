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
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import Service.Pagination;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;





/**
 * FXML Controller class
 *
 * @author Dell
 */
public class LoginController implements Initializable {

    public static int test ;

    public static int idus;
    
    Stage dialogStage = new Stage();
    Scene scene;
    private ImageView image;
    private ImageView imagee;
    @FXML
    private TextField idusername;
    @FXML
    private PasswordField idpassword;
    @FXML
    private Button idMdpO;
    @FXML
    private Button idlogin;
    @FXML
    private Button idCreer;
    
    @FXML
    private FontAwesomeIconView btn_exit;
    @FXML
    private BorderPane bp;
    @FXML
    private Button BtinQuitter;
    private void handleButtonAction(MouseEvent event) {
      Stage stage = (Stage) btn_exit.getScene().getWindow();
        stage.close();
    }
    @FXML
    private void handleBtnCreer(ActionEvent event) throws IOException{
        Pagination p = new Pagination("/fxml/inscription.fxml");
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }
    
    @FXML
    private void handleBtnLogin(ActionEvent event) throws IOException{
         if (idusername.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Oups !");
            alert.setHeaderText("Oups !");
            alert.setContentText("Insert your email");
            alert.showAndWait();
        } else if (idpassword.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Oups !");
            alert.setHeaderText("Oups !");
            alert.setContentText("Insert your Password");
            alert.showAndWait();
        } 
        else {
            
            if (idpassword.getText().equals("user")){
       
           Pagination p = new Pagination("/fxml/AcceuilCC.fxml");
        ((Node)(event.getSource())).getScene().getWindow().hide(); 
        
        }else if (idpassword.getText().equals("admin")){
        
            Pagination p = new Pagination("/fxml/AdminHome.fxml");
        ((Node)(event.getSource())).getScene().getWindow().hide(); 
        }
        }
        
       /* UserService userService = new UserService();
        if (idusername.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Oups !");
            alert.setHeaderText("Oups !");
            alert.setContentText("Insert your email");
            alert.showAndWait();
        } else if (idpassword.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Oups !");
            alert.setHeaderText("Oups !");
            alert.setContentText("Insert your Password");
            alert.showAndWait();
        } else {
            try {
                User m = userService.get(idusername.getText());  
               
                if (m != null) {
                    String myName = m.getPassword();
              
                    if (idpassword.getText().equals( myName)) {
                        
                       UserSession.getInstance(m.getUserName(), m.getEmail(),m.getPassword(),m.getRoles(),m.getNom(),m.getPrenom(),m.getImage());
                       if (m.getRoles().equals("a:1:{i:0;s:10:\"ROLE_ADMIN\";}")){
                      String a = UserSession.getInstance(null, null, null, null, null, null, null).getUserName();
                         
                        Pagination p = new Pagination("/fxml/AdminHome.fxml");
                        ((Node)(event.getSource())).getScene().getWindow().hide();
                        
                         test = getInteger(idus);
                        
                       }
                       else{
                           System.out.println("errrr");
                    
                    Pagination p = new Pagination("/fxml/AcceuilCC.fxml");
                        ((Node)(event.getSource())).getScene().getWindow().hide();
                        
                        test = getInteger(idus);
                        
                       }
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Oups ! ");
                        alert.setHeaderText("Wrong Password ! ");
                        alert.setContentText("Wrong Password ! ");
                        alert.showAndWait();
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Oups !");
                    alert.setHeaderText("This account doesn't exist ! ");
                    alert.setContentText("This account doesn't exist ! ");
                    alert.showAndWait();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }*/
   }
    @FXML
    public void handleBtnMdp (ActionEvent event) throws IOException{
        Pagination p = new Pagination("/fxml/mdpOublie.fxml");
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         BtinQuitter.setOnAction((ActionEvent event) -> {
           
           ((Node)(event.getSource())).getScene().getWindow().hide();
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Bye!");
        alert.setHeaderText(null);
        alert.setContentText("We will miss you !");
        alert.showAndWait();
     });
    
            }  

  //  private int getInteger(int idus) {
    //    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    //}

    private int getInteger(int idus) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @FXML
    private void BtinQuitter(ActionEvent event) {
    }
  
  }

