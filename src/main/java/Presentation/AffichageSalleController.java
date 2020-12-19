/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Entity.Film;
import Entity.Salle;
import Presentation.AffichageFilmController;
import Presentation.Pagination;
import Service.FilmService;
import Service.SalleService;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import jdk.nashorn.internal.runtime.JSType;


/**
 * FXML Controller class
 *
 * @author Mnif
 */
public class AffichageSalleController implements Initializable {
    
    Salle cart;
    SalleService Cats= new SalleService();
    @FXML
    private TableView<Salle> tableSalle;
    @FXML
    private TableColumn<Salle, String> listNom;
    @FXML
    private TableColumn<Salle, String> listNombre;
    @FXML
    private TextField nomSalle;
    @FXML
    private Button BtnAjoutFilm;
    @FXML
    private Button BtnSupprimerFilm;
    @FXML
    private Button BtnModifierFilm;
  
    @FXML
    private TextField NombreSalle;
    @FXML
    private Label nomLab;
     
     private ObservableList<Salle> data;
    @FXML
    private Label categorieLab;
    @FXML
    private Hyperlink lienfilm;
    @FXML
    private Hyperlink liensalle;
    @FXML
    private Hyperlink lienseance;
     @FXML
    private ImageView BtinQuitterFilm;
private Salle salle;
    Salle s;
    SalleService salleService = new SalleService();
    @FXML
    private ImageView retourbtn;
    @FXML
    private TextField recherchetext;
    /**
     * Initializes the controller class.
     */String x1;
    @FXML
    private ImageView titre1;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
         Alert alert = new Alert(Alert.AlertType.WARNING);
      SalleService ss = new SalleService();
      
    
            data = FXCollections.observableArrayList();
        
        loadDataFromDatabase();
        setsCllTable();
        setcellValue();
       BtnSupprimerFilm.setOnAction(xx->{
          supprimer();
       });
        
        BtnModifierFilm.setOnAction((event) -> {
         Modifier();
       });
        BtnAjoutFilm.setOnAction((event)->{
                if(nomSalle.getText().isEmpty() || NombreSalle.getText().isEmpty() ){
             alert.setContentText("Please complete all fields!");
            alert.showAndWait();
           
    }
                
                 
                        
                        else if(ss.rechercheSalleParNom(nomSalle.getText())!= null)
                        {
                        alert.setContentText("This theater already exists");
                        alert.showAndWait();                     
                        System.out.println(ss.rechercheSalleParNom(nomSalle.getText()));
                        
                        }
                        
                        
                        
                        
                        //     else{
                        //     Ajouter(event);}
                        //   final TrayNotification tray = new TrayNotification("féliceitatiton", "votre ajout est fait avec succés", NotificationType.SUCCESS);
                        //    tray.showAndDismiss(Duration.seconds(5));   }*/
                
                else 
                    try {
                        Ajouter(event );
                      
                } catch (IOException ex) {
                    Logger.getLogger(AffichageSalleController.class.getName()).log(Level.SEVERE, null, ex);
                }
});
        /////////// recherche 
       FilteredList<Salle> FilteredData= new FilteredList<>(data , e-> true);
       recherchetext.setOnKeyReleased(e->{
           recherchetext.textProperty().addListener((ObservableValue, oldValue, newValue)->{
                   FilteredData.setPredicate((Predicate<? super Salle>) salle -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    if ((salle.getNom_salle().contains(newValue))){
                        return true;
                    }
                       if(JSType.toString(salle.getNombrePlace_salle()).toLowerCase().contains(lowerCaseFilter)){
                        return true ;}
                 
                  
                    return false;

           });
       });
           SortedList<Salle> SortedData = new SortedList<>(FilteredData)  ; 
                    SortedData.comparatorProperty().bind(tableSalle.comparatorProperty());
                    tableSalle.setItems(SortedData);
 }); ///////////////////////////fin recherche
          liensalle.setOnAction(event->{
             try {
                 Pagination p = new Pagination("/fxml/AffichageSalle.fxml");
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
                 Pagination p = new Pagination("/fxml/AfficheSeance.fxml");
             } catch (IOException ex) {
              Logger.getLogger(AffichageFilmController.class.getName()).log(Level.SEVERE, null, ex);
          }
        ((Node)(event.getSource())).getScene().getWindow().hide();
                       
        });
      
    }    

    private void loadDataFromDatabase() {
       List<Salle> lisCat =Cats.afficherSalle();

        for(Salle e:lisCat){
            System.out.println(e);
            data.add(e);
        }
           tableSalle.setItems(data);
    }

    private void setsCllTable() {
      listNom.setCellValueFactory(new PropertyValueFactory<>("nom_salle"));
         listNombre.setCellValueFactory(new PropertyValueFactory<>("nombrePlace_salle"));
      
    }

    private void setcellValue() {
         tableSalle.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent event) {    
                cart = tableSalle.getItems().get(tableSalle.getSelectionModel().getSelectedIndex());
               //  nomLab.setText(String.valueOf(cart.getId()));
                nomSalle.setText(cart.getNom_salle());
                NombreSalle.setText(String.valueOf(cart.getNombrePlace_salle()));
                x1=cart.getNom_salle();
          
                
                
               
                
               BtnSupprimerFilm.setVisible(true);
                
                BtnModifierFilm.setVisible(true);
            }
        });
    }

    private void supprimer() {
        Cats.supprimerSalle(cart);
        nomSalle.setText("");
        NombreSalle.setText("");
        
        
       
         BtnSupprimerFilm.setVisible(false);
       
        BtnModifierFilm.setVisible(false);
        data.clear();
        loadDataFromDatabase();
    }

    private void Modifier() {
        cart=new Salle(Integer.parseInt(NombreSalle.getText()),nomSalle.getText());
         salle.setNom_salle(nomSalle.getText());
        salle.setNombrePlace_salle(Integer.parseInt(NombreSalle.getText()));
        System.out.println(cart.getNumero_salle());
        Cats.modifierSalle(cart,salle.getNom_salle());
        nomSalle.setText("");
        NombreSalle.setText("");
       
     
        

        data.clear();
        loadDataFromDatabase();
              System.out.println(salle);
    //    programmee = prg.get(titre.getText());
           //  Salle salle = null;
       
  
        
        s = salle;
        System.out.println(s);
        //salleService.modifierSalle(salle);
    }

    private void Ajouter(ActionEvent event) throws IOException {
            cart= new Salle(Integer.parseInt(NombreSalle.getText()),nomSalle.getText());
           Cats.creerSalle(cart);
             nomSalle.setText("");
        NombreSalle.setText("");
     
 
                Pagination p = new Pagination("/fxml/AffichageSalle.fxml");
            ((Node)(event.getSource())).getScene().getWindow().hide();
            
    }

    @FXML
    private void BtinQuitterFilm(MouseEvent event) {
         Stage stage = (Stage) BtinQuitterFilm.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void retourbtn(MouseEvent event) throws IOException {
         Pagination p = new Pagination("/fxml/Cinema.fxml");
          ((Node)(event.getSource())).getScene().getWindow().hide();
    }
    

    
}
