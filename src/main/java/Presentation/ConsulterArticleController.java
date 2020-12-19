/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Entity.Article;
import Service.ArticleService;
import Utilitaire.copyImages;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import utils.ConnexionBD;


/**
 * FXML Controller class
 *
 * @author PC
 */
public class ConsulterArticleController implements Initializable {
    Connection C = ConnexionBD.getInstancebd().getConnexionBD();
    Statement ste;

    @FXML
    private TableView<Article> articles;
    @FXML
    private TableColumn<Article, String> NomArt;
    @FXML
    private TableColumn<Article, String> Prixart;
    @FXML
    private TableColumn<Article, String> quantité;
    @FXML
    private ImageView img;
    @FXML
    private Button changer;
    private TextField ttitre;
    private TextArea tdescription;
    private Label imgs;
    @FXML
    private TextField recherche;
    @FXML
    private Button Ajoutart;
    @FXML
    private Button ModifArt;
    ArticleService ArticleService = new ArticleService();
    private ObservableList<Article> data ;
    Article art;
    static int id;
    @FXML
    private TextField Nomartt;
    @FXML
    private TextField tprix;
    @FXML
    private TextField tqte;
    @FXML
    private Button BtnSuppart;
  
    @FXML
    private Button affexcel;
    @FXML
    private Hyperlink pagcatart;
    @FXML
    private Hyperlink pagcmd;
    @FXML
    private Hyperlink rtrpageadm;
    @FXML
    private Hyperlink paginstat;
  
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         img.setVisible(false);
        Nomartt.setVisible(true);
        tprix.setVisible(true);
         tqte.setVisible(true);
          
        
       
        data = FXCollections.observableArrayList();
        loadDataFromDatabase();
        setsCllTable();
        selected_prop();
        Ajoutart.setOnAction(event -> { add();});
        BtnSuppart.setOnAction(event ->{delete();});
        ModifArt.setOnAction(event-> {
            edit();});
        
        affexcel.setOnAction(event->{try {
            excelAction();
             } catch (IOException ex) {
                 Logger.getLogger(ConsulterArticleController.class.getName()).log(Level.SEVERE, null, ex);
             } catch (SQLException ex) {
                 Logger.getLogger(ConsulterArticleController.class.getName()).log(Level.SEVERE, null, ex);
             }
        
        });
        rtrpageadm.setOnAction(event -> {try {
                 Pagination p = new Pagination("/fxml/AcceuilVente.fxml");
             } catch (IOException ex) {
                 
             }
        ((Node)(event.getSource())).getScene().getWindow().hide();});
        
        
        
        
        
    }
        
     
     private void loadDataFromDatabase() {
       
      ArrayList<Article> listarticles = (ArrayList<Article> )ArticleService.afficherArticle();
      for (Article a:listarticles){
      System.out.println(a);
      data.add(a);}
      articles.setItems(data);}
      private void setsCllTable() {
      
        NomArt.setCellValueFactory(new PropertyValueFactory<>("NomA"));
       Prixart.setCellValueFactory(new PropertyValueFactory<>("Prix"));
        quantité.setCellValueFactory(new PropertyValueFactory<>("Qte"));
               
         }
        private void selected_prop()
    {
         articles.setOnMouseClicked((javafx.scene.input.MouseEvent event) -> {
             art = articles.getItems().get(articles.getSelectionModel().getSelectedIndex());
            
             
                 id=art.getIdA();
                 System.out.println(id);
                 Nomartt.setText(art.getNomA());
                 tprix.setText(Float.toString(art.getPrix()));
                  tqte.setText(Integer.toString(art.getQte()));
       
                 
                 changer.setVisible(true);
                 img.setVisible(true);
                  
                 img.setImage(new Image("http://localhost/codetest/web/images/"+art.getImg()));
                 Nomartt.setVisible(true);
                 tprix.setVisible(true);
                 tqte.setVisible(true);
                
                
             
         });
    }

    @FXML
    private void change(ActionEvent event) {
          FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg")
         );
        
            File choix = fileChooser.showOpenDialog(null);
            if (choix != null) {
                System.out.println(choix.getAbsolutePath());
                copyImages.deplacerVers(choix.getPath(),"C:\\wamp64\\www\\codetest\\web\\images");
                art.setImg(choix.getName());
                ArticleService.modifierArticle(art);
                data.clear();
                loadDataFromDatabase();
                img.setVisible(false);
                changer.setVisible(false);
             } else {
                System.out.println("Image introuvable");
            }
    }
    private void add()
    {
            try { 
            
            Pagination p = new Pagination("/fxml/addArticle.fxml");
            Stage stage = (Stage) articles.getScene().getWindow();
            stage.close();
        } catch (IOException ex) {
        } 
    }


    @FXML
    private void rechercher(KeyEvent event) {
          if(recherche.getText().equals(""))
         {
            data.clear();
            loadDataFromDatabase();
         }
        else{
              ArrayList<Article> listarticles = (ArrayList<Article>) ArticleService.rechercherArticle(recherche.getText());
              data.clear();
              data = FXCollections.observableArrayList(listarticles);           
           articles.setItems(data);
        
        
       Nomartt.setVisible(true);
       tprix.setVisible(true);
       tqte.setVisible(true);
                
        changer.setVisible(true);
        img.setVisible(true);
         }
    }
     public void edit()
    { if (NomArt.getText().isEmpty()||tprix.getText().isEmpty() ||tqte.getText().isEmpty()){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setContentText("Please complete all fields!");
         alert.showAndWait(); }else{  
        art.setNomA(Nomartt.getText());
         art.setPrix(Float.parseFloat(tprix.getText()));
        art.setQte(Integer.parseInt(tqte.getText()));
       
        
        ArticleService.modifierArticle(art);
     
        data.clear();
        loadDataFromDatabase();
        System.out.println("edit");
        }}
       
     private void delete()
    {
          ArticleService.supperimerArticle(art);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Etes vous sur de supprimer l'article "+art.getNomA()+" ?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
         Nomartt.setText("");
        tprix.setText("");
         tqte.setText("");
        img.setVisible(false);
       
         BtnSuppart.setVisible(true);
       
         ModifArt.setVisible(true);
        data.clear();
        loadDataFromDatabase();   
        }}

    private void Ajouterarttt(MouseEvent event) {
            try { 
            
            Pagination p = new Pagination("/fxml/addArticle.fxml");
            Stage stage = (Stage) articles.getScene().getWindow();
            stage.close();
        } catch (IOException ex) {
        } 
    }
      private void Excel(File file) throws FileNotFoundException, IOException , SQLException {
     
             FileOutputStream fileOut;
            fileOut = new FileOutputStream(file);
           XSSFWorkbook workbook = new XSSFWorkbook();
           XSSFSheet workSheet = workbook.createSheet("Datatypes in Java");
        

        //HSSFFont fontBold = workbook.createFont();
        //fontBold.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        //HSSFCellStyle styleBold = workbook.createCellStyle();
        //styleBold.setFont(fontBold);         
            Row row1 = workSheet.createRow((short) 0);
            
            workSheet.autoSizeColumn(7);
            row1.createCell(0).setCellValue("nom");
            row1.createCell(1).setCellValue("prix");
          //  row1.createCell(2).setCellValue("quantite");
            
            Row row2;

            String req = "SELECT nomarticle,prixarticle,quantitearticle from article ";
            
            PreparedStatement ps = C.prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int a = rs.getRow();
                row2 = workSheet.createRow((short) a); 
               
               row2.createCell(0).setCellValue(rs.getString(2));
               row2.createCell(1).setCellValue(rs.getInt(3));
               //row2.createCell(2).setCellValue(rs.getInt(4));
              //  System.out.println(rs.getInt(4)+""+ rs.getString(2) +""+a);
             //  row2.createCell(2).setCellValue(rs.getString(3));         
            }
            
            
            try{
            workbook.write(fileOut);
            fileOut.flush();
            fileOut.close();
            rs.close();

        
        } catch (SQLException e) {
            e.getStackTrace();
            System.out.println("Presentation.ConsulterArticleController.ExcelAction()"); 

        }
    }
        private void excelAction() throws FileNotFoundException, IOException, SQLException {
          FileChooser chooser = new FileChooser();
        // Set extension filter
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Excel Files(*.xls)", "*.xls");
        chooser.getExtensionFilters().add(filter);
        // Show save dialog
        File file = chooser.showSaveDialog(affexcel.getScene().getWindow());
        if (file != null) {
            Excel(file);
        }
        
    
    }

    @FXML
    private void retourgescatart(MouseEvent event) {
        try {
                 Pagination p = new Pagination("/fxml/AffichageCategoriea.fxml");
             } catch (IOException ex) {
                 
             }
        ((Node)(event.getSource())).getScene().getWindow().hide();}
        
    

    @FXML
    private void retourcmd(MouseEvent event) {
        try {
                 Pagination p = new Pagination("/fxml/ConsulterCommande.fxml");
             } catch (IOException ex) {
                 
             }
        ((Node)(event.getSource())).getScene().getWindow().hide();}

    @FXML
    private void paginationstat(MouseEvent event) {
         
          try {
                 Pagination p = new Pagination("/fxml/Stat.fxml");
             } catch (IOException ex) {
                 
             }
        ((Node)(event.getSource())).getScene().getWindow().hide();}

    }

    

