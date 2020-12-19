package Presentation;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author user
 */
public class AccueilUserController implements Initializable {

    @FXML
    private Hyperlink consult;
    @FXML
    private Hyperlink bestclub;
    @FXML
    private Hyperlink contact;
    @FXML
    private ImageView idlogo;
    @FXML
    private ImageView imclub;
    @FXML
    private Hyperlink stat1;
    @FXML
    private ImageView exitgv;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     
         consult.setOnAction(event->{
             try {
                 Pagination p = new Pagination("/fxml/ConsulterClub.fxml");
             } catch (IOException ex) {
                 Logger.getLogger(AccueilClubController.class.getName()).log(Level.SEVERE, null, ex);
             }
        ((Node)(event.getSource())).getScene().getWindow().hide();
                       
        });
            bestclub.setOnAction(event->{
             try {
                 Pagination p = new Pagination("/fxml/BestClub.fxml");
             } catch (IOException ex) {
                 Logger.getLogger(AccueilClubController.class.getName()).log(Level.SEVERE, null, ex);
             }
        ((Node)(event.getSource())).getScene().getWindow().hide();
                       
        });
            contact.setOnAction(event->{
             try {
                 Pagination p = new Pagination("/fxml/Mailing.fxml");
             } catch (IOException ex) {
                 Logger.getLogger(AccueilClubController.class.getName()).log(Level.SEVERE, null, ex);
             }
        ((Node)(event.getSource())).getScene().getWindow().hide();
                       
        });
                
    }    

    @FXML
    private void BtnExit(MouseEvent event) {
         try {
           Service.Pagination p = new Service.Pagination("/fxml/AcceuilCC.fxml");
           ((Node)(event.getSource())).getScene().getWindow().hide();
           
       } catch (IOException ex) {
           System.out.println(ex.getMessage());
       }
        
    }
    
}
