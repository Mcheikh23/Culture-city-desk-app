/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Entity.Article;
import Entity.Catégorie;
import Entity.Commande;
import Entity.LigneCommande;
import Service.ArticleService;
import Service.CommandeService;
import Service.LigneCommandeService;
import java.io.IOException;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class ListedesarticlesController implements Initializable {

    @FXML
    private TableColumn<Article, String> nomart;
    @FXML
    private TableColumn<Article, String> prixart;
    @FXML
    private ImageView imgart;
    @FXML
    private Button btncommande;
    @FXML
    private Button btnconsl;
     ArticleService ArticleService = new ArticleService();
    private ObservableList<Article> data ;
    @FXML
    private TableView<Article> tableart;
    Article art;
    ImageView img;
    @FXML
    private Label idart;
    @FXML
    private TextField qte;
   
       LocalDate currentTime=LocalDate.now();
        LocalDate date1=currentTime.withDayOfMonth(25).withYear(2023).withMonth(12);
          Commande c ;
    @FXML
    private Button idreturn;
       
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        idart.setVisible(false);
       
        
       
          data = FXCollections.observableArrayList();
        loadDataFromDatabase();
        setsCllTable();
        selected_prop();
        btncommande.setOnAction(xx -> {
         ajoutercommande();
         });
        btnconsl.setOnAction(event->{try {
                 Pagination p = new Pagination("/fxml/detailCommande.fxml");
             } catch (IOException ex) {
                 
             }
        ((Node)(event.getSource())).getScene().getWindow().hide();});
        
        // TODO
    } 
    private void loadDataFromDatabase() {
       
      ArrayList<Article> listarticles = (ArrayList<Article> )ArticleService.afficherArticle();
      for (Article a:listarticles){
      System.out.println(a);
      data.add(a);}
      tableart.setItems(data);}
     private void setsCllTable() {
      
      nomart.setCellValueFactory(new PropertyValueFactory<>("NomA"));
       prixart.setCellValueFactory(new PropertyValueFactory<>("Prix"));
    
}
      private void selected_prop()
    {
        tableart .setOnMouseClicked((javafx.scene.input.MouseEvent event) -> {
             art = tableart.getItems().get(tableart.getSelectionModel().getSelectedIndex());
            
             
                 int id=art.getIdA();
               idart.setText(Integer.toString(art.getIdA()));
               idart.setVisible(false);
                 imgart.setVisible(true);
                  
                imgart.setImage(new Image("http://localhost/codetest/web/images/"+art.getImg()));
                });
    }
    public void ajoutercommande(){
        LigneCommande ligneCommande = new LigneCommande();
        ligneCommande.setIdarticle(Integer.parseInt(idart.getText()));
         ligneCommande.setQte(Integer.parseInt(qte.getText()));
         LigneCommandeService lcs=new LigneCommandeService();
         lcs.creerLigneCommande(ligneCommande);
    
    
    
    }

    @FXML
    private void retour(ActionEvent event) throws IOException {
        Pagination p = new Pagination("/fxml/AcceuilCC.fxml");
          ((Node)(event.getSource())).getScene().getWindow().hide();
    }
  
    
    }


