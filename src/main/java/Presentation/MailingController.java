package Presentation;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import Service.Mailing;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;

/**
 * FXML Controller class
 *
 * @author user
 */
public class MailingController implements Initializable {

    @FXML
    private TextArea mail;
    @FXML
    private Button send;
    @FXML
    private TextField password;
    @FXML
    private TextField Email;
    @FXML
    private Hyperlink consult;
    @FXML
    private Hyperlink bestclub;
    @FXML
    private Hyperlink contact;
    @FXML
    private ImageView idlogo;
    @FXML
    private ImageView exitgv;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         consult.setOnAction(event->{
             try {
                 Pagination pp = new Pagination("/fxml/ConsulterClub.fxml");
             } catch (IOException ex) {
                 Logger.getLogger(AccueilClubController.class.getName()).log(Level.SEVERE, null, ex);
             }
        ((Node)(event.getSource())).getScene().getWindow().hide();
                       
        });
            bestclub.setOnAction(event->{
             try {
                 Pagination pp = new Pagination("/fxml/BestClub.fxml");
             } catch (IOException ex) {
                 Logger.getLogger(AccueilClubController.class.getName()).log(Level.SEVERE, null, ex);
             }
        ((Node)(event.getSource())).getScene().getWindow().hide();
                       
        });
            contact.setOnAction(event->{
             try {
                 Pagination pp = new Pagination("/fxml/Mailing.fxml");
             } catch (IOException ex) {
                 Logger.getLogger(AccueilClubController.class.getName()).log(Level.SEVERE, null, ex);
             }
        ((Node)(event.getSource())).getScene().getWindow().hide();
                       
        });
                
        // TODO
    }    

    @FXML
    private void sendMail(ActionEvent event) {
        
      //  final String fromEmail = "arousyassmine02@gmail.com "; //requires valid gmail id
      //   final String password = "yassmounaar95 "; // correct password for gmail id
	 final String toEmail = "yassmine.arous@esprit.tn"; // can be any email id 
		
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
				return new PasswordAuthentication(Email.getText(),password.getText());
			}
		};
		Session session = Session.getInstance(props, auth);
		
		Mailing.sendEmail(session, toEmail,"Reclamation Membre",mail.getText());
    }

    @FXML
    private void BtnExit(MouseEvent event) {
          try {
           Service.Pagination p = new Service.Pagination("/fxml/accueilUser.fxml");
           ((Node)(event.getSource())).getScene().getWindow().hide();
           
       } catch (IOException ex) {
           System.out.println(ex.getMessage());
       }
    } }