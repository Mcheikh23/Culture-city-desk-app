package Presentation;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import Entity.Article;
import Entity.Catégorie;
import Service.ArticleService;
import Service.CatégorieService;
import Utilitaire.copyImages;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javax.swing.JOptionPane;


/**
 * FXML Controller class
 *
 * @author PC
 */
public class AddArticleController implements Initializable {

    @FXML
    private Button bpath;
    @FXML
    private Button annuler;
    @FXML
    private Button ajout;
    @FXML
    private ComboBox Camboart;
    @FXML
    private TextField NomArt;
    @FXML
    private TextField pathImgArt;
    @FXML
    private TextField PrixArt;
    @FXML
    private TextField QteArt;
     ArticleService articleService = new ArticleService();
    String name="";
    ObservableList data;
    Article article;
Catégorie catart;
CatégorieService csart=new CatégorieService();
    
     ArticleService arts=new ArticleService();
    @FXML
    private ImageView rtrgestionart;
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          Alert alert = new Alert(Alert.AlertType.WARNING);
          CatégorieService csart=new CatégorieService();
          ArticleService arts=new ArticleService();
           List<Catégorie> lista = csart.afficherCatégorie();
           data = FXCollections.observableArrayList();
           for(Catégorie crt:lista)
                data.add(crt.getNomC());
                Camboart.setItems(data);
                article=new Article();
                pathImgArt.setEditable(false);
      
        
        
    }    

    @FXML
    private void chercher(ActionEvent event) {
          FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg")
         );
        
            File choix = fileChooser.showOpenDialog(null);
            if (choix != null) {
               
                pathImgArt.setText(choix.getPath());
                copyImages.deplacerVers(pathImgArt.getText(),"C:\\wamp64\\www\\codetest\\web\\images");
                name=choix.getName();
             } else {
                System.out.println("Image introuvable");
            }
    }

    @FXML
    private void annuler(ActionEvent event) {
         NomArt.setText("");
       PrixArt.setText("");
        pathImgArt.setText("");
    }

    @FXML
    private void ajouter(ActionEvent event) {
         Article article =new Article();
         if (NomArt.getText().isEmpty()||PrixArt.getText().isEmpty() ||QteArt.getText().isEmpty()||pathImgArt.getText().isEmpty()){
         Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setContentText("Please complete all fields!");
         alert.showAndWait(); }else{  
       
        
        
     
        article.setNomA(NomArt.getText());
        article.setPrix(Float.parseFloat( PrixArt.getText()));
        article.setQte(Integer.parseInt(QteArt.getText()));
        article.setImg(name);
        article.setIdC(csart.rechercheCatégorieArtByNom((String) Camboart.getValue()));
        
         
    articleService.creerArticle(article); }}

    @FXML
    private void rtrgestionart(MouseEvent event) {
        {try {
                 Pagination p = new Pagination("/fxml/consulterArticle.fxml");
             } catch (IOException ex) {
                 
             }
        ((Node)(event.getSource())).getScene().getWindow().hide();}
        
    }

   
  
    
       
    
    
    
}
