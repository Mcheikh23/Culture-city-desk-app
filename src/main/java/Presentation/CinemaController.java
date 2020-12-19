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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Mnif
 */
public class CinemaController implements Initializable {
    @FXML
    private ImageView exitbtn;
    @FXML
    private ImageView retourBtn;
    @FXML
    private Hyperlink lienfilm;
    @FXML
    private Hyperlink lienseance;
    @FXML
    private Hyperlink lienSalle;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         lienSalle.setOnAction(event->{
             try {
                 Pagination p = new Pagination("/fxml/AfficheSeance.fxml");
             } catch (IOException ex) {
              Logger.getLogger(AffichageFilmController.class.getName()).log(Level.SEVERE, null, ex);
          }
        ((Node)(event.getSource())).getScene().getWindow().hide();
                       
        });
            
            lienfilm.setOnAction(event->{
             try {
                 Pagination p = new Pagination("/fxml/AffichageFilm.fxml");
             } catch (IOException ex) {
              Logger.getLogger(AffichageFilmController.class.getName()).log(Level.SEVERE, null, ex);
          }
        ((Node)(event.getSource())).getScene().getWindow().hide();
                       
        });
            lienseance.setOnAction(event->{
             try {
                 Pagination p = new Pagination("/fxml/AffichageSalle.fxml");
             } catch (IOException ex) {
              Logger.getLogger(AffichageFilmController.class.getName()).log(Level.SEVERE, null, ex);
          }
        ((Node)(event.getSource())).getScene().getWindow().hide();
                       
        });
      
    }    



    @FXML
    private void exitbtn(MouseEvent event) {
               try {
           Service.Pagination p = new Service.Pagination("/fxml/login.fxml");
           ((Node)(event.getSource())).getScene().getWindow().hide();
           
       } catch (IOException ex) {
           System.out.println(ex.getMessage());
       } 
        
    }

    @FXML
    private void retour(MouseEvent event) {
          try {
           Service.Pagination p = new Service.Pagination("/fxml/AdminHome.fxml");
           ((Node)(event.getSource())).getScene().getWindow().hide();
           
       } catch (IOException ex) {
           System.out.println(ex.getMessage());
       }
    }
    
}
