package Presentation;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import Entity.categoriec;
import Service.CategoriecService;
import Service.ClubService;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
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
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


 
public class AfficherCategoriecController implements Initializable {

    @FXML
    private TableView<categoriec> categcTable;
    private Button btnAjouterCatc;
    @FXML
    private Button btnSupprimerCat;

    /**
     * Initializes the controller class.
     */
    categoriec c;
    CategoriecService Cats= new CategoriecService();
    @FXML
    private TableColumn<categoriec, String> listCat;
     private ObservableList<categoriec> data;
    
    private TextField nom_categoriec;
    private ImageView btnRetour;
     private ObservableList<categoriec> listdata = FXCollections.observableArrayList(); 
    //private Button btnEnregistrer;
    private TextField Nom_categoriec;
    @FXML
    private TextField textRechercher;
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
         Alert alert = new Alert(Alert.AlertType.WARNING);
      
    
        
        
        categcTable.getSelectionModel().setSelectionMode(
    SelectionMode.MULTIPLE
);
            CategoriecService Cats = CategoriecService.getInstance();
      
        categcTable.setItems(listdata);
          listCat.setCellValueFactory(new PropertyValueFactory<>("nom_categorieC"));         
        
         categcTable.setEditable(true);
        
         
 
        listCat.setCellFactory(TextFieldTableCell.forTableColumn());
        
    
       
    
  data = FXCollections.observableArrayList();

      loadDataFromDatabase();
    
           
        btnSupprimerCat.setOnAction(xx->{
             alert.setContentText("do you really want to delete");
           alert.showAndWait();
            
           supprimerCategoriec();
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
         
        

        //Recherche avancé
        FilteredList<categoriec> FilteredData= new FilteredList<>(data , e-> true);
       textRechercher.setOnKeyReleased(e->{
           textRechercher.textProperty().addListener((ObservableValue, oldValue, newValue)->{
                   FilteredData.setPredicate((Predicate<? super categoriec>) categorie -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    if ((categorie.getNom_categorieC().contains(newValue)) || (categorie.getNom_categorieC().toLowerCase().contains(newValue))){
                        return true;
                    }
                    else 
                   
                    return false;

           });
       });
           SortedList<categoriec> SortedData = new SortedList<>(FilteredData)  ; 
                    SortedData.comparatorProperty().bind(categcTable.comparatorProperty());
                    categcTable.setItems(SortedData);
 });
       
       
       
         
        
        
    }
     private void loadDataFromDatabase() {
        List<categoriec> listeCat =Cats.afficherCategoriec();

        for(categoriec c:listeCat){
            System.out.println(c);
            data.add(c);
        }
           categcTable.setItems(data);

    }
    
    

 private void supprimerCategoriec() {
        ObservableList<categoriec> selectedRows, allCategorie ;
        allCategorie = categcTable.getItems();
        
        //this gives us the rows that were selected
        selectedRows = categcTable.getSelectionModel().getSelectedItems();
        
        
        //loop over the selected rows and remove the Person objects from the table
        for (categoriec c: selectedRows)
        {
             allCategorie.remove(c);
             CategoriecService Cats =CategoriecService.getInstance();
             Cats.supprimerCategoriec(c);
        
             }
 }
     
   
    

    @FXML
    private void changeCategorieCellEvent(TableColumn.CellEditEvent edittedCell) {
      
        categoriec c =  categcTable.getSelectionModel().getSelectedItem(); 
        c.setNom_categorieC(edittedCell.getNewValue().toString());   
        CategoriecService Cats = CategoriecService.getInstance();
        Cats.modifierCategoriec(c);
        System.out.println("genre modif"); 
    }

    @FXML
    private void BtnExit(MouseEvent event) {
         System.exit(0);
    }


    

  
    }


       

