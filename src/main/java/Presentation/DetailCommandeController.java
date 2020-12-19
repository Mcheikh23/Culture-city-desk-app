/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Entity.Article;
import Entity.Commande;
import Entity.LigneCommande;
import Service.CommandeService;
import Service.LigneCommandeService;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import javafx.print.PrinterJob;
import java.io.File;
import java.io.FileOutputStream;



import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javafx.scene.image.Image;
import javafx.stage.Window;




/**
 * FXML Controller class
 *
 * @author PC
 */
public class DetailCommandeController implements Initializable {

    @FXML
    private TableView<LigneCommande> tablelignecommande;
    @FXML
    private TableColumn<LigneCommande, String> idartc;
    @FXML
    private TableColumn<LigneCommande, String> qteartc;
    @FXML
    private Button supprimerartc;
    @FXML
    private Button Modifierartc;
    @FXML
    private Button confartc;
    @FXML
    private Label montant;
    @FXML
    private TextField quantitéartc;
  
     private ObservableList<LigneCommande> data ;
     LigneCommandeService ligneCommandeService =new LigneCommandeService();
     LigneCommande lc;
     int prix;
    private Label idart;
    @FXML
    private Button acmontant;
    Commande c;
    @FXML
    private Button btn_imprimcmd;
     ActionEvent event;
     PrinterJob job = PrinterJob.createPrinterJob();
     final TextArea textArea = new TextArea();
     Stage app_stage;
     ActionEvent eventt;
     MouseEvent event1;
     Article art;
    @FXML
    private ImageView rtrconus;
     

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        data = FXCollections.observableArrayList();
        loadDataFromDatabase();
        setsCllTable();
        montant();
        
        selected_prop();
        Modifierartc.setOnAction(event ->{edit();});
        supprimerartc.setOnAction(event ->{delete();});
       acmontant.setOnAction(event ->{montant();});
       confartc.setOnAction(event ->{valider();});
       btn_imprimcmd.setOnAction(event ->{pdf();});
       
      
     
       
       
      
       
        
        
    }
    private void loadDataFromDatabase() {
       
     ArrayList<LigneCommande> listligne = (ArrayList<LigneCommande> )ligneCommandeService.afficherLigneCommande();
      for (LigneCommande lg:listligne){
      System.out.println(lg);
      data.add(lg);}
    tablelignecommande.setItems(data);}
      private void setsCllTable() {
      
        idartc.setCellValueFactory(new PropertyValueFactory<>("Idarticle"));
       qteartc.setCellValueFactory(new PropertyValueFactory<>("Qte"));}
       
     
      
        private void selected_prop()
    {
         tablelignecommande.setOnMouseClicked((javafx.scene.input.MouseEvent event) -> {
             lc = tablelignecommande.getItems().get(tablelignecommande.getSelectionModel().getSelectedIndex());
            
             
                 int id=lc.getIdarticle();
                 System.out.println(id);
                // idart.setText(Integer.toString(id));
                 // imgarticlecommandé.setVisible(true);
                  
              //  imgarticlecommandé.setImage(new Image("http://localhost/PidevWeb/images/"+art.getImg()));
               
                
                 quantitéartc.setText(Integer.toString(lc.getQte()));});
         
       
      
     
    
}
        public void montant(){
        
               montant.setText(Integer.toString(ligneCommandeService.calcul(lc)));
         
        
        
        }
            public void edit()
    {
        lc.setQte(Integer.parseInt(quantitéartc.getText()));
        //lc.setIdarticle(Integer.parseInt(idart.getText()));
        
        
       ligneCommandeService.modifierLigneCommande(lc);
       
        
       
     
        data.clear();
        loadDataFromDatabase();
        System.out.println("edit");
        }
             private void delete()
    {
         ligneCommandeService.supperimerLigneCommande(lc);
         quantitéartc.setText("");
      
         
      
        data.clear();
        loadDataFromDatabase();   
        }
             
             
             
             public void valider (){
                Commande c =new Commande();
                
                 CommandeService cmdcrv=new CommandeService();
                 c.setDate(LocalDate.now());
                 c.setMontant(Integer.parseInt(montant.getText()));
                 cmdcrv.creerCommande(c);
             
             
             
             }
             /*  public void printSetup(Node node, Stage owner) 
    {
        // Create the PrinterJob        
        PrinterJob job = PrinterJob.createPrinterJob();
     
        if (job == null) 
        {
            return;
        }
 
        // Show the print setup dialog
        boolean proceed = job.showPrintDialog(owner);
         
        if (proceed) 
        {
            print(job, node);
        }
    }
      public void print(PrinterJob job, Node node) 
    {
        // Set the Job Status Message
        idart.textProperty().bind(job.jobStatusProperty().asString());
        
        // Print the node
        boolean printed = job.printPage(node);
     
        if (printed) 
        {
            job.endJob();
        }
    }
    /* @FXML
    private void imprimerAction() {
      printSetup(textArea, app_stage);
      pdfDownload(event1);
      

    }
    private void pdfDownload(ActionEvent event) {}
      
 
    
 
 
    private void pdfDownload(MouseEvent event) {

         Stage app_stage= (Stage) ((Node) event.getSource()).getScene().getWindow();

                   
                    FileChooser fc= new FileChooser();
                    
                    fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF File", ".pdf"));
                    fc.setTitle("Enregistrer votre ticket ");
                    fc.setInitialFileName("fichier.pdf");
                    File file = fc.showSaveDialog(app_stage);
                    if (file!=null){
                        String str = file.getAbsolutePath();
                        try {
                            FileOutputStream fos = new FileOutputStream(str);
                            PDF pdf = new PDF(fos);
                           Page page = new Page(pdf, Letter.LANDSCAPE);
                           pdf.close();
                          
                           fos.close();
                           System.out.println("PDF file was saved to:"+file.getAbsolutePath().toString());
                           
                           
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

    }
}*/
    void pdf() {
 System.out.println("To Printer!");
         PrinterJob job = PrinterJob.createPrinterJob();
           if(job != null){
    Window primaryStage = null;
           job.showPrintDialog(primaryStage); 
            
    Node root = this.tablelignecommande ;
    
           job.printPage(root);
           job.endJob();
            
       

  }}

    @FXML
    private void retourart(MouseEvent event) {
        try {
                 Pagination p = new Pagination("/fxml/AcceuilCC.fxml");
             } catch (IOException ex) {
                 
             }
        ((Node)(event.getSource())).getScene().getWindow().hide();}
        
     
        
    
    


    



}
