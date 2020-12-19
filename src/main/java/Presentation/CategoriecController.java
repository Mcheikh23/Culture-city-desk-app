/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;


import Entity.categoriec;
import Service.CategoriecService;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author user
 */
public class CategoriecController implements Initializable {

    @FXML
    private Button btnEnregistrer;
   

    /**
     * Initializes the controller class.
     */
    CategoriecService cats=new CategoriecService();
    categoriec c;
    private Button btnAnnuler;
    @FXML
    private Hyperlink addcat;
    @FXML
    private Hyperlink listcl;
    @FXML
    private Hyperlink stat;
    @FXML
    private Hyperlink listcat;
    @FXML
    private Hyperlink addcl;
    @FXML
    private ImageView idlogo;
    @FXML
    private TextField nom;
    @FXML
    private ImageView exitgv;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        CategoriecService Cats = new CategoriecService();
           btnEnregistrer.setOnAction(xx -> {
              if(nom.getText().isEmpty()){
             alert.setContentText("Veuillez Saisir le nom de la catégorie");
            alert.showAndWait();
           
    }            
        
                  
        /*  else 
           if(Cats.RechercherCategorieCByNom(Nom_categoriec.getText())!= null){
            alert.setContentText("ce nom existe déjà");
            alert.showAndWait();
             System.out.println(Cats.RechercherCategorieCByNom(Nom_categoriec.getText()));
            
    }  */
            else {     
            c = new categoriec(nom.getText());
            Cats.creerCategoriec(c);
           nom.setText("");   
            
          }
            
         });
                    addcat.setOnAction(event->{
             try {
                 Pagination p = new Pagination("/fxml/Categoriec.fxml");
             } catch (IOException ex) {
                 Logger.getLogger(AccueilClubController.class.getName()).log(Level.SEVERE, null, ex);
             }
        ((Node)(event.getSource())).getScene().getWindow().hide();
                       
        });
            listcat.setOnAction(event->{
             try {
                 Pagination p = new Pagination("/fxml/AfficherCategoriec.fxml");
             } catch (IOException ex) {
                 Logger.getLogger(AccueilClubController.class.getName()).log(Level.SEVERE, null, ex);
             }
        ((Node)(event.getSource())).getScene().getWindow().hide();
                       
        });
            addcl.setOnAction(event->{
             try {
                 Pagination p = new Pagination("/fxml/Club.fxml");
             } catch (IOException ex) {
                 Logger.getLogger(AccueilClubController.class.getName()).log(Level.SEVERE, null, ex);
             }
        ((Node)(event.getSource())).getScene().getWindow().hide();
                       
        });
                   listcl.setOnAction(event->{
             try {
                 Pagination p = new Pagination("/fxml/AffichClub.fxml");
             } catch (IOException ex) {
                 Logger.getLogger(AccueilClubController.class.getName()).log(Level.SEVERE, null, ex);
             }
        ((Node)(event.getSource())).getScene().getWindow().hide();
                       
        });
                   
                             stat.setOnAction(event->{
             try {
                 Pagination p = new Pagination("/fxml/ParticipationStatistique.fxml");
             } catch (IOException ex) {
                 Logger.getLogger(AccueilClubController.class.getName()).log(Level.SEVERE, null, ex);
             }
        ((Node)(event.getSource())).getScene().getWindow().hide();
                       
        });
            
             
    }    

    @FXML
    private void BtnExit(MouseEvent event) {
         System.exit(0);
    }
    
}
