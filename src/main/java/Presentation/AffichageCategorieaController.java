/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Entity.Article;
import Entity.Catégorie;
import Service.CatégorieService;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class AffichageCategorieaController implements Initializable {
    Catégorie cart;
    CatégorieService Cats= new CatégorieService();
     @FXML
    private TableView<Catégorie> tabCata;

    @FXML
    private TableColumn<Catégorie, String> listecate;
    @FXML
    private TextField Cnom;
    @FXML
    private Button BtnSupp;
    @FXML
    private Button BtnModif;
    @FXML
    private Label lab;
     private ObservableList<Catégorie> data;
    @FXML
    private Button BtnAjoutcatart;
    @FXML
    private Button BtnQuiitcatart;
    @FXML
    private Label labid;
    @FXML
    private Hyperlink rtracceuilvente;
    @FXML
    private Hyperlink rtrart;
    @FXML
    private Hyperlink btnrtrcmd;
     

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         data = FXCollections.observableArrayList();
        
        loadDataFromDatabase();
        setsCllTable();
        setcellValue();
       BtnSupp.setOnAction(xx->{
          Supprimer();
       });
        
        BtnModif.setOnAction((event) -> {
         Modifier();
       });
        BtnAjoutcatart.setOnAction((event)->{try {
            Ajouter( event);
             } catch (IOException ex) {
                 Logger.getLogger(AffichageCategorieaController.class.getName()).log(Level.SEVERE, null, ex);
             }
});
        BtnQuiitcatart.setOnAction((event)-> {System.exit(0);});
        rtracceuilvente.setOnAction(event -> {try {
                 Pagination p = new Pagination("/fxml/AcceuilVente.fxml");
             } catch (IOException ex) {
                 
             }
        ((Node)(event.getSource())).getScene().getWindow().hide();});
        
        
        
        
    } 
     private void loadDataFromDatabase() {
        List<Catégorie> lisCat =Cats.afficherCatégorie();

        for(Catégorie e:lisCat){
            System.out.println(e);
            data.add(e);
        }
           tabCata.setItems(data);

    }
    private void setsCllTable() {
        listecate.setCellValueFactory(new PropertyValueFactory<>("NomC"));
        

    }
    private void setcellValue() {
        tabCata.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent event) {    
                cart = tabCata.getItems().get(tabCata.getSelectionModel().getSelectedIndex());
                 labid.setText(String.valueOf(cart.getId()));
                Cnom.setText(cart.getNomC());
                labid.setVisible(false);
                
               
                
               BtnSupp.setVisible(true);
                
                BtnModif.setVisible(true);
            }
        });
 }
        // TODO
       

     @FXML
    private void Supprimer() {
 Cats.supperimerCatégorie(cart);
   Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Etes vous sur de supprimer l'article "+cart.getNomC()+"?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
        labid.setText("");
        Cnom.setText("");
         BtnSupp.setVisible(true);
         BtnModif.setVisible(true);
        data.clear();
        loadDataFromDatabase();
    }}

    @FXML
    private void Modifier() {
        if (Cnom.getText().isEmpty()){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setContentText("Please complete all fields!");
         alert.showAndWait(); }else{  
 cart=new Catégorie(Integer.parseInt(labid.getText()), Cnom.getText());
        Cats.modifierCatégorie(cart);
        labid.setText("");
        Cnom.setText("");
        

        data.clear();
        loadDataFromDatabase();}}
    public void Ajouter(ActionEvent event) throws IOException{
          if (Cnom.getText().isEmpty()){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setContentText("Please complete all fields!");
         alert.showAndWait(); }else{ 
    
     cart= new Catégorie(Cnom.getText());
           Cats.creerCatégorie(cart);
            Cnom.setText("");
                Pagination p = new Pagination("/fxml/AffichageCategoriea.fxml");
            ((Node)(event.getSource())).getScene().getWindow().hide();
    
    }}

    @FXML
    private void Retourpacatart(MouseEvent event) {
        try {
                 Pagination p = new Pagination("/fxml/consulterArticle.fxml");
             } catch (IOException ex) {
                 
             }
        ((Node)(event.getSource())).getScene().getWindow().hide();}
        
        
    

    @FXML
    private void Retourcmdpage(ContextMenuEvent event) {
        try {
                 Pagination p = new Pagination("/fxml/ConsulterCommande.fxml");
             } catch (IOException ex) {
                 
             }
        ((Node)(event.getSource())).getScene().getWindow().hide();}
        

    }
  
   

    
    

