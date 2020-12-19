/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;


import Entity.club;
import Service.CategoriecService;
import javafx.scene.control.Alert;
import Service.ClubService;
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
import java.util.List;
import java.util.Optional;
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
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javax.swing.JOptionPane;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import utils.ConnexionBD;


/**
 * FXML Controller class
 *
 * @author user
 */
public class AffichClubController implements Initializable {
    

    private Button btnRetour;
    @FXML
    private TableView<club> listClub;
    @FXML
    private TableColumn<club, String> NomClub;
    @FXML
    private TableColumn<club, String> DescriptionClub;
    @FXML
    private TableColumn<club, String> CatégorieClub;
    private ObservableList<club> data;
    club cl;
    ClubService Cls = new ClubService();
    private ObservableList<club> listdata = FXCollections.observableArrayList(); 
  
       CategoriecService categoriecService ;
    @FXML
    private TextField textRechercher;
    @FXML
    private ImageView img;
   String name="";
    Connection c = ConnexionBD
           .getInstancebd()
           .getConnexionBD();
    Statement ste;
    

    /**
     * Initializes the controller class.
     */
     static int id;
    @FXML
    private Button changer;
    private TextField nom;
    private TextArea description;
   
    private TextField image;
  
    private Button ajouter;
    @FXML
    private Button supprimer;
    private Button modifier;
    private Label nom1;
    private Label description1;
    private Label image1;
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
    private Label pic;
    @FXML
    private Button excel;
    @FXML
    private ImageView exitgv;
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         Alert alert = new Alert(Alert.AlertType.WARNING);
        changer.setVisible(false);
        img.setVisible(false);
        pic.setVisible(false);
        listClub.getSelectionModel().setSelectionMode(
    SelectionMode.MULTIPLE
);
           ClubService Cls =ClubService.getInstance();
      
        listClub.setItems(listdata);
          NomClub.setCellValueFactory(new PropertyValueFactory<>("nom_club"));         
          DescriptionClub.setCellValueFactory(new PropertyValueFactory<>("description_club")); 
        CatégorieClub.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCategoriec().getNom_categorieC()));
  
  
         listClub.setEditable(true);
        
         
 
        NomClub.setCellFactory(TextFieldTableCell.forTableColumn());
       
      DescriptionClub.setCellFactory(TextFieldTableCell.forTableColumn());
   
       CatégorieClub.setCellFactory(TextFieldTableCell.forTableColumn());

 
             data = FXCollections.observableArrayList();

      loadDataFromDatabase();
      selected();
           
        supprimer.setOnAction(xx->{
           alert.setContentText("do you really want to delete");
           alert.showAndWait();
           supprimerClub();
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
         
        
       
        
// début recherche
    
   FilteredList<club> FilteredData= new FilteredList<>(data , e-> true);
       textRechercher.setOnKeyReleased(e->{
           textRechercher.textProperty().addListener((ObservableValue, oldValue, newValue)->{
                   FilteredData.setPredicate((Predicate<? super club>) Club -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    if ((Club.getNom_club().contains(newValue)) || (Club.getNom_club().toLowerCase().contains(newValue))){
                        return true;
                    }
                    else if(Club.getDescription_club().toLowerCase().contains(lowerCaseFilter)){
                        return true ;
                    }
                   
                    return false;

           });
       });
           SortedList<club> SortedData = new SortedList<>(FilteredData)  ; 
                    SortedData.comparatorProperty().bind(listClub.comparatorProperty());
                    listClub.setItems(SortedData);
 });
    ////fin recherche   
         
        

                       
      
    }
        private void loadDataFromDatabase() {
        List<club> lista =(List<club>) Cls.afficherClub();

        for(club cl:lista){
            System.out.println(cl);
            data.add(cl);
        }
           listClub.setItems(data);

    }
           private void supprimerClub() {
        ObservableList<club> selectedRows, allClub;
        allClub = listClub.getItems();
        
        //this gives us the rows that were selected
        selectedRows = listClub.getSelectionModel().getSelectedItems();
        
        //loop over the selected rows and remove the Person objects from the table
        for (club cl: selectedRows)
        {
             allClub.remove(cl);
             ClubService Cls =ClubService.getInstance();
             Cls.supprimerClub(cl);
        
             }
        
     
    
    }

      private void selected() {
         listClub.setOnMouseClicked((javafx.scene.input.MouseEvent event) -> {
             cl = listClub.getItems().get(listClub.getSelectionModel().getSelectedIndex());
             
             pic.setVisible(true);
             changer.setVisible(true);
             img.setVisible(true);
             Image imag = new Image("http://localhost/codetest/web/images/"+cl.getLogo_club());
             img.setImage(imag);
         });}
        


    @FXML
    private void change(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg")
         );
        
            File choix = fileChooser.showOpenDialog(null);
            if (choix != null) {
                System.out.println(choix.getPath());
                copyImages.deplacerVers(choix.getPath(),"C:\\wamp64\\www\\codetest\\web\\images");
                name=choix.getName();
                cl.setLogo_club(name);
                Cls.modifierClub(cl);
                data.clear();
                loadDataFromDatabase();
                pic.setVisible(false);
                 img.setVisible(false);
                changer.setVisible(false);
             } else {
                System.out.println("Image introuvable");
            }}


    @FXML
    private void changeNameCellEvent(TableColumn.CellEditEvent edittedCell) {
        
        club c =  listClub.getSelectionModel().getSelectedItem(); 
        c.setNom_club(edittedCell.getNewValue().toString());   
        ClubService Cls = ClubService.getInstance();
        Cls.modifierClub(c);
        System.out.println("nomclub modif"); 
    }

    @FXML
    private void changeDescriptionCellEvent(TableColumn.CellEditEvent edittedCell) {
        club c =  listClub.getSelectionModel().getSelectedItem(); 
        c.setDescription_club(edittedCell.getNewValue().toString());   
        ClubService Cls = ClubService.getInstance();
        Cls.modifierClub(c);
        System.out.println("description modif"); 
    }

    @FXML
    private void excell(ActionEvent event)throws FileNotFoundException, IOException, SQLException {
          FileChooser chooser = new FileChooser();
        // Set extension filter
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Excel Files(*.xls)", "*.xls");
        chooser.getExtensionFilters().add(filter);
        // Show save dialog
        File file = chooser.showSaveDialog(excel.getScene().getWindow());
        if (file != null) {
            Excel(file);
        }
         
        
    }

    private void Excel(File file) 
         throws FileNotFoundException, IOException , SQLException {
     
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
            row1.createCell(0).setCellValue("nom_club");
            row1.createCell(1).setCellValue("description_club");
            row1.createCell(2).setCellValue("categoriec");
            Row row2;

            String req = "SELECT * from club ";
            
            PreparedStatement ps = c.prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int a = rs.getRow();
                row2 = workSheet.createRow((short) a); 
               
               row2.createCell(0).setCellValue(rs.getString(2));
               row2.createCell(1).setCellValue(rs.getString(3));
               row2.createCell(2).setCellValue(rs.getString(4));
                System.out.println(rs.getString(4)+""+ rs.getString(2) +""+a);
             //  row2.createCell(2).setCellValue(rs.getString(3));         
            }
            
            
            try{
            workbook.write(fileOut);
            fileOut.flush();
            fileOut.close();
            rs.close();

        
        } catch (SQLException e) {
            e.getStackTrace();
            System.out.println("Presentation.AffichClubController.ExcelAction()"); 

        }
    }

    @FXML
    private void BtnExit(MouseEvent event) {
         System.exit(0);
    }
}     
        
          
              

    
 
    

   


    
