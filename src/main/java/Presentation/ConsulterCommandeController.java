/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Entity.Article;
import Entity.Commande;
import Service.ArticleService;
import Service.CommandeService;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class ConsulterCommandeController implements Initializable {

    @FXML
    private TableView<Commande> tablecommande;
    @FXML
    private TableColumn<Commande, String> idcmd;
    @FXML
    private TableColumn<Commande, String> datecmd;
    @FXML
    private TableColumn<Commande, String> montantcmd;
    @FXML
    private Button confcmd;
     ArticleService ArticleService = new ArticleService();
    private ObservableList<Commande> data ;
    CommandeService cmdser=new CommandeService();
    Commande cmd;
   
    @FXML
    private Hyperlink btnrtrartman;
    @FXML
    private Hyperlink btnrtrcatman;
    @FXML
    private Hyperlink rtrpagadmin;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          data = FXCollections.observableArrayList();
          loadDataFromDatabase();
          setsCllTable();
          selected_prop();
          confcmd.setOnAction(event->{ confirmerCommande();});
          rtrpagadmin.setOnAction(event->{try {
                 Pagination p = new Pagination("/fxml/AcceuilVente.fxml");
             } catch (IOException ex) {
                 
             }
        ((Node)(event.getSource())).getScene().getWindow().hide();});
        
    
         
          
         
        
    }
 private void loadDataFromDatabase() {
       
      ArrayList<Commande> listcmd = (ArrayList<Commande> )cmdser.afficherCommande();
      for (Commande lcmd:listcmd){
      System.out.println(lcmd);
      data.add(lcmd);}
     tablecommande.setItems(data);}
   private void setsCllTable() {
      
       idcmd.setCellValueFactory(new PropertyValueFactory<>("IdCM"));
       datecmd.setCellValueFactory(new PropertyValueFactory<>("Date"));
        montantcmd.setCellValueFactory(new PropertyValueFactory<>("Montant"));
               
         }
      private void selected_prop()
    {
        tablecommande.setOnMouseClicked((javafx.scene.input.MouseEvent event) -> {
             cmd = tablecommande.getItems().get(tablecommande.getSelectionModel().getSelectedIndex());
                int  id=cmd.getIdCM();
                 System.out.println(id);});
    
}
      public void confirmerCommande(){
      Alert alert = new Alert(AlertType.INFORMATION);
alert.setTitle("Commande confirmée");
alert.setHeaderText(null);
alert.setContentText("votre confirmation"+cmd.getIdCM() +"a été envoyeé au client");

alert.showAndWait();
      
      
      }

    private void returnesadmin(MouseEvent event) {
        {try {
                 Pagination p = new Pagination("/fxml/AcceuilVente.fxml");
             } catch (IOException ex) {
                 
             }
        ((Node)(event.getSource())).getScene().getWindow().hide();}
        
    }

    @FXML
    private void retourartman(MouseEvent event) {
        try {
                 Pagination p = new Pagination("/fxml/consulterArticle.fxml");
             } catch (IOException ex) {
                 
             }
        ((Node)(event.getSource())).getScene().getWindow().hide();}
        
    

    @FXML
    private void rtrcatman(MouseEvent event) {
        try {
                 Pagination p = new Pagination("/fxml/AffichageCategoriea.fxml");
             } catch (IOException ex) {
                 
             }
        ((Node)(event.getSource())).getScene().getWindow().hide();}
        
    }
      





