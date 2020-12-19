/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;
//import org.apache.commons.io.FileUtils;

import Entity.categoriec;
import Entity.club;
import Service.CategoriecService;
import Service.ClubService;
import Utilitaire.copyImages;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author user
 */
public class ClubController implements Initializable {

    @FXML
    private TextField nomClub;
    //private TextField imageClub;
    @FXML
    private TextArea descriptionClub;
    @FXML
    private Button insererImage;
    @FXML
    private ComboBox catégoriec;
    @FXML
    private Button ibtnAjout;
    ObservableList data;
    CategoriecService Cats;
        ClubService Clubs;
        club Club;
        String name="";
    @FXML
    private TextField path;
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
    private ImageView exitgv;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         Alert alert = new Alert(Alert.AlertType.WARNING);

        Clubs = new ClubService();
        Cats = new CategoriecService();      
         List<categoriec> lista = Cats.afficherCategoriec();
      data = FXCollections.observableArrayList(); 
              
      for(categoriec s:lista)
                data.add(s.getNom_categorieC());
                catégoriec.setItems(data);

                
                Club=new club();
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
            
        
            ibtnAjout.setOnAction(new EventHandler<ActionEvent>() {
                
             @Override
             public void handle(ActionEvent event) {
                 
                 if(nomClub.getText().isEmpty()){
                     alert.setContentText("Please enter the club name");
                     alert.showAndWait();
                     
                 }
                 else if  (descriptionClub.getText().isEmpty()){
                     alert.setContentText("Please enter the description of your club");
                     alert.showAndWait();}
                 
                  else if  (path.getText().isEmpty()){
                     alert.setContentText("Please enter the picture of your club");
                     alert.showAndWait();}
           
                 
                 
                 else if(Clubs.rechercheClubParNom(nomClub.getText())!= null){
                     alert.setContentText("this name already exists");
                     alert.showAndWait();
                     System.out.println(Clubs.rechercheClubParNom(nomClub.getText()));
                     
                 }
                 
                 
                 
                 
                 else{
                     
                     Club.setNom_club(nomClub.getText());
                     Club.setDescription_club(descriptionClub.getText());
                     
                     Club.setCategoriec(Cats.RechercherCategorieCByNom((String) catégoriec.getValue()));
                     Club.setLogo_club(name);
                      if(Club.valid()==null)
                      {
                     System.out.println(Club);
                     Clubs.creerClub(Club);
                     
                     nomClub.setText("");
                     descriptionClub.setText("");
   
                     path.setText("");
                     
    }
    else
        {
            JOptionPane.showMessageDialog(null,"Le champ "+Club.valid()+" est invalide");
        }
    }
                     
                     
                  
             }
         });
             
                       
           
    }    
      

    
    @FXML
    private void insertImage(ActionEvent event) throws FileNotFoundException, IOException { 
        FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg")
         );
        
            File choix = fileChooser.showOpenDialog(null);
               if (choix != null) {
               
                path.setText(choix.getPath());
                copyImages.deplacerVers(path.getText(),"C:\\wamp64\\www\\codetest\\web\\images");
                name=choix.getName();
             } else {
                System.out.println("Image introuvable");
            }
    }

    @FXML
    private void BtnExit(MouseEvent event) {
         System.exit(0);
    }
           
        
    

    }

  
        
    

          
            

    


    
    


