package Presentation;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Entity.Film;
import Entity.Salle;
import Entity.Seance;
import Service.SeanceService;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
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
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
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
import javafx.stage.Window;
import static jdk.nashorn.internal.objects.Global.print;
import jdk.nashorn.internal.runtime.JSType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import utils.ConnexionBD;


/**
 * FXML Controller class
 *
 * @author Mnif
 */
public class AffichageSeanceController implements Initializable {
    
   
  
   SeanceService seanceService= new SeanceService();
   Seance seance = new Seance();
   
    Connection c = ConnexionBD.getInstancebd().getConnexionBD();
    Statement ste;
    @FXML
    private TableView<Seance> tableSeance;
    @FXML
    private TableColumn<Seance, String> listFilm;
    @FXML
    private TableColumn<Seance, String> listSalle;
    @FXML
    private TableColumn<Seance, String> listDate;
    @FXML
    private Button BtnAjoutFilm;
    @FXML
    private Button BtnSupprimerFilm;
    @FXML
    private Button BtnModifierFilm;
    @FXML
    private Label nomLab;
    @FXML
    private Label categorieLab;
    @FXML
    private Label descriptionLab;
    private ImageView BtinQuitterFilm;
    @FXML
    private ComboBox  <String> fIlmSeance;
    @FXML
    private ComboBox <String> salleSeance;
    @FXML
    private DatePicker DateSeance;
    private ObservableList<Seance> data;  
    final TextArea textArea = new TextArea();
    Stage app_stage;
     
  
  
     //     List<Film> listFilm = filmService.afficherFilm();
  
     ObservableList<Film> listeFilm = FXCollections.observableArrayList();
     ObservableList<Salle> listeSalle = FXCollections.observableArrayList();
    @FXML
    private Hyperlink lienFilm;
    @FXML
    private Hyperlink lienSalle;
    @FXML
    private Hyperlink lienSeance;
    @FXML
    private TextField recherchetext;
    @FXML
    private Button btn_cmd_imprimer;
    @FXML
    private Button btn_excel;
    @FXML
    private ImageView titre1;
    @FXML
    private ImageView exitbtn;
    @FXML
    private ImageView retourBtn;
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
      data = FXCollections.observableArrayList();
 
     loadDataFromDatabase();
     setsCllTable();
     setcellValue();
      BtnSupprimerFilm.setOnAction(xx->{

          supprimer();
       });
     
      
       lienSalle.setOnAction(event->{
             try {
                 Pagination p = new Pagination("/fxml/AffichageSalle.fxml");
             } catch (IOException ex) {
              Logger.getLogger(AffichageFilmController.class.getName()).log(Level.SEVERE, null, ex);
          }
        ((Node)(event.getSource())).getScene().getWindow().hide();
                       
        });
            
           lienFilm.setOnAction(event->{
             try {
                 Pagination p = new Pagination("/fxml/AffichageFilm.fxml");
             } catch (IOException ex) {
              Logger.getLogger(AffichageFilmController.class.getName()).log(Level.SEVERE, null, ex);
          }
        ((Node)(event.getSource())).getScene().getWindow().hide();
                       
        });
            lienSeance.setOnAction(event->{
             try {
                 Pagination p = new Pagination("/fxml/AfficheSeance.fxml");
             } catch (IOException ex) {
              Logger.getLogger(AffichageFilmController.class.getName()).log(Level.SEVERE, null, ex);
          }
        ((Node)(event.getSource())).getScene().getWindow().hide();
                       
        });
        
            
               /////////// recherche 
            /*
       FilteredList<Seance> FilteredData= new FilteredList<>(data , e-> true);
       recherchetext.setOnKeyReleased(e->{
           recherchetext.textProperty().addListener((ObservableValue, oldValue, newValue)->{
                   FilteredData.setPredicate((Predicate<? super Seance>) seance -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    if ((seance.getFilm().toLowerCase().contains(newValue)) ){
                        return true;
                    }
                    else if(seance.getSalle().toLowerCase().contains(lowerCaseFilter)){
                        return true ;
                    }
                   if(JSType.toString(seance.getDate_seance()).toLowerCase().contains(lowerCaseFilter)){
                        return true ;}
                  
                  
                    return false;

           });
       });
           SortedList<Seance> SortedData = new SortedList<>(FilteredData)  ; 
                    SortedData.comparatorProperty().bind(tableSeance.comparatorProperty());
                    tableSeance.setItems(SortedData);
 }); */
                    ///////////////////////////fin recherche

      try {

            String req = " select * from Film ";
            Statement ste = c.createStatement();
            ResultSet res = ste.executeQuery(req);

            
            while (res.next()) {
                Film f = new Film();
              //  Salle s = new Salle();
                
                f.setNom_film(res.getString("nom_film"));
                listeFilm.add(f);
                }

                System.out.println(listFilm); 
            
            
        } catch (SQLException ex) {
            System.out.println(ex);
        }

        for (Film f : listeFilm) {
            fIlmSeance.getItems().addAll(f.getNom_film());
        }
          try {

            String req = " select * from Salle ";
            Statement ste = c.createStatement();
            ResultSet res = ste.executeQuery(req);

            
            while (res.next()) {
                Salle s = new Salle();
              //  Salle s = new Salle();
                
                s.setNom_salle(res.getString("nom_salle"));
                listeSalle.add(s);
                }
           
                System.out.println(listFilm); 
            
            
        } catch (SQLException ex) {
            System.out.println(ex);
        }

        for (Salle s : listeSalle) {
            salleSeance.getItems().addAll(s.getNom_salle());
        }
        
        

                
            
    }
/*
   @FXML
    private void btn_ajout(ActionEvent event) throws IOException {
        
        // seance = new Seance();
     //    serviceSeance = new SeanceService();
           if (fIlmSeance.getValue()==null || salleSeance.getValue()==null || DateSeance.getValue()==null   ){
                 Alert alert = new Alert(Alert.AlertType.WARNING);
             alert.setContentText("Please complete all fields!");
            alert.showAndWait();}
            if(DateSeance.getValue().compareTo(LocalDate.now()) < 0) {
                 Alert alert1 = new Alert(Alert.AlertType.WARNING);
            alert1.setContentText("Date est déjà passée merci de choisir autre date ");
            alert1.showAndWait();
        }
        
           else {
          seance.setFilm(fIlmSeance.getValue());
          seance.setSalle(salleSeance.getValue());
          seance.setDate_seance(Date.valueOf(DateSeance.getValue()));
          System.out.println(seance);
          SeanceService r=null;
          r = new SeanceService();
          r.creerSeance(seance);
             Pagination p = new Pagination("/fxml/AfficheSeance.fxml");
           ((Node)(event.getSource())).getScene().getWindow().hide();}
           
    }
    */

    private void loadDataFromDatabase() {
        List<Seance> listSeance =seanceService.afficherSeance();
        for(Seance e:listSeance){
            System.out.println(e);
            data.add(e);
        }
           tableSeance.setItems(data);
    }

    private void setsCllTable() {
    
       listFilm.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFilm().getNom_film()));
       listSalle.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getSalle().getNom_salle()));
       
       listDate.setCellValueFactory(new PropertyValueFactory<>("date_seance"));

          
    }

    private void setcellValue() {
          tableSeance.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent event) { 
                
                
                
                
                
                seance = tableSeance.getItems().get(tableSeance.getSelectionModel().getSelectedIndex());
             
                fIlmSeance.setValue(seance.getFilm().getNom_film());
                salleSeance.setValue(seance.getSalle().getNom_salle());
             
          
         
     
                BtnSupprimerFilm.setVisible(true);
                BtnModifierFilm.setVisible(true);
            }
        });
   
}
    
    private void supprimer() {
       seanceService.supprimerSeance(seance);
        fIlmSeance.setValue("");
        salleSeance.setValue("");
      // seance.setDate_seance(Date.valueOf(DateSeance.getValue()));
         BtnSupprimerFilm.setVisible(false);
       
        BtnModifierFilm.setVisible(false);
        data.clear();
        loadDataFromDatabase();
    }

    private void BtinQuitterFilm(MouseEvent event) {
         Stage stage = (Stage) BtinQuitterFilm.getScene().getWindow();
        stage.close();
    }

    private void retourbtn(MouseEvent event) throws IOException {
          Pagination p = new Pagination("/fxml/Cinema.fxml");
          ((Node)(event.getSource())).getScene().getWindow().hide();
    }

    @FXML
    private void imprimerAction(MouseEvent event) {
       //  printSetup(textArea, app_stage);
        System.out.println("To Printer!");
         PrinterJob job = PrinterJob.createPrinterJob();
           if(job != null){
    Window primaryStage = null;
           job.showPrintDialog(primaryStage); 
            
    Node root = this.tableSeance;
           job.printPage(root);
           job.endJob();
    }
 
        
    }

   
    private void Excel(File file) throws FileNotFoundException, IOException , SQLException {
     
             FileOutputStream fileOut;
            fileOut = new FileOutputStream(file);
           XSSFWorkbook workbook = new XSSFWorkbook();
           XSSFSheet workSheet = workbook.createSheet("Datatypes in Java");
        

     
            Row row1 = workSheet.createRow((short) 0);
            
            workSheet.autoSizeColumn(7);
            row1.createCell(0).setCellValue("film");
            row1.createCell(1).setCellValue("salle");
            row1.createCell(2).setCellValue("date_seance");
            Row row2;

            String req = "SELECT * from seance ";
            
            PreparedStatement ps = c.prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int a = rs.getRow();
                row2 = workSheet.createRow((short) a); 
               
               row2.createCell(0).setCellValue(rs.getString(2));
               row2.createCell(1).setCellValue(rs.getString(3));
               row2.createCell(2).setCellValue(rs.getDate(4).toString());
                System.out.println(rs.getDate(4).toString() +""+ rs.getString(2) +""+a);
            
            }
            
            
            try{
            workbook.write(fileOut);
            fileOut.flush();
            fileOut.close();
            rs.close();

        
        } catch (SQLException e) {
            e.getStackTrace();
            System.out.println("Presentation.AfficheSeanceController.ExcelAction()"); 

        }
    }

    @FXML
    private void excelAction(MouseEvent event) throws FileNotFoundException, IOException, SQLException {
          FileChooser chooser = new FileChooser();
        // Set extension filter
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Excel Files(*.xls)", "*.xls");
        chooser.getExtensionFilters().add(filter);
        // Show save dialog
        File file = chooser.showSaveDialog(btn_excel.getScene().getWindow());
        if (file != null) {
            Excel(file);
        }
        
    
    }

    @FXML
    private void exitbtn(MouseEvent event) {
         Stage stage = (Stage) retourBtn.getScene().getWindow();
           stage.close();
    }

    @FXML
    private void retourBtn(MouseEvent event) throws IOException {
         Pagination p = new Pagination("/fxml/Cinema.fxml");
          ((Node)(event.getSource())).getScene().getWindow().hide();
    }

    @FXML
    private void btn_ajout(ActionEvent event) {
    }



  

   

  

 
}
