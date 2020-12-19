/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import Entity.User;
import Service.MailingMdpOub;
import Service.UserService;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javafx.scene.Node;
import javafx.stage.Stage;
import javax.mail.MessagingException;

import Service.Pagination;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
/**
 * FXML Controller class
 *
 * @author Hachem
 */
public class MdpOublieController implements Initializable {

    @FXML
    private JFXButton idRetour;
    @FXML
    private TextField mdpOtxt;
    @FXML
    private JFXButton idEnv;
    @FXML
    private Label btn_exit;
    @FXML
    private JFXButton id_sms;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
UserService userService = new UserService ();
    User u;
    @FXML
    private void handleBtnRetour(ActionEvent event) throws IOException {
        Pagination p = new Pagination("/fxml/login.fxml");
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }

    @FXML
    private void handleBtnMdp(ActionEvent event) {
         final String fromEmail = "culture.tunisie2020@gmail.com  "; //requires valid gmail id
         final String password = "culture12345"; // correct password for gmail id
	 final String toEmail = "mohameed.gharbiii@gmail.com"; // can be any email id 
		
		System.out.println("TLSEmail Start");
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
                props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		props.put("mail.smtp.port", "587"); //TLS Port
		props.put("mail.smtp.auth", "true"); //enable authentication
		props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS
		
                //create Authenticator object to pass in Session.getInstance argument
		Authenticator auth = new Authenticator() {
			//override the getPasswordAuthentication method
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(fromEmail, password);
			}
		};
		Session session = Session.getInstance(props, auth);
		
		MailingMdpOub.sendEmail(session, toEmail,"Mot de passe oubliée Membre",mdpOtxt.getText());
    }

    @FXML
    private void exit(MouseEvent event) {
        Stage stage = (Stage) btn_exit.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void handleBtnMdpSMS(ActionEvent event) {
    }
    
}
