/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import facebook4j.Account;
import facebook4j.FacebookException;
import facebook4j.FacebookFactory;
import facebook4j.auth.AccessToken;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import facebook4j.Facebook;
import facebook4j.FacebookException;
import facebook4j.FacebookFactory;
import facebook4j.ResponseList;
import facebook4j.auth.AccessToken;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import utils.DataSource;

/**
 * FXML Controller class
 *
 * @author Hachem
 */
public class FacebookShareController implements Initializable {
Connection c = DataSource.getInstance().getConnection();
    Statement ste;

    @FXML
    private TextArea txtsujet;
    @FXML
    private TextArea titresujet;
    @FXML
    private Button id_share;

    /**
     * Initializes the controller class.
     */
    private Facebook facebook;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void share(ActionEvent event) {
        facebook = new FacebookFactory().getInstance();
        //"name": "Hachem Wasli",
       //"id": "2748764785136545"
        facebook.setOAuthAppId("","");
        String accessTokenString = "EAAmHsWcRPucBAGamvK4WE8CZBQp4iFNggHCbwgbOVN3hWrZBlwz0sS273pk7JTdYXcguyYQTAIvT2gyhi1mBH7UFNvZC0AZCkfqjCjt5K2ZCnjS0NH3EzWIhNL3mq0O4UUJ5EmmecJZAZBsoCjyvlKs8OfVdZAoGZCx0HT5CK2CV5FkPq6d8Y6qYu6lzJcWYLeQaonTG5kCdTzwZDZD";
        AccessToken accessToken = new AccessToken(accessTokenString);
        facebook.setOAuthAccessToken(accessToken);
           //set access token
        // ResponseList<Account> accounts = facebook.getAccounts();
       // Account pageAccount = accounts.get(0);
       
        java.util.Date date1= new java.util.Date();  
        String temp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date1);
       try{
       facebook.postStatusMessage("Titre:"+titresujet.getText()+"\nDate:"+temp + "\nContenue:"+txtsujet.getText());
       // facebook.postStatusMessage("Hello World!");
       System.out.println("Sujet partagé!");
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Sujet Partagé!.");
        alert.setHeaderText(null);
        alert.setContentText("Succes");
        alert.showAndWait();
       
       
       }
       
            catch(FacebookException fe){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Sujet déja existant !");
        alert.setHeaderText(null);
        alert.setContentText("Error");
        alert.showAndWait();
    }
    }
}

