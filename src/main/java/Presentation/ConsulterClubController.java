package Presentation;

import Entity.club;
import Entity.participation;
import Service.ClubService;
import Service.ParticipationService;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author user
 */
public class ConsulterClubController implements Initializable {

    @FXML
    private Button annuler;
    @FXML
    private Button participer;
    @FXML
    private TableView<club> listClub;
    @FXML
    private TableColumn<club, String> NomClub;
    @FXML
    private TableColumn<club, String> DescriptionClub;
    @FXML
    private TableColumn<club, String> CatégorieClub;
    @FXML
    private TextField textRechercher;
    @FXML
    private ImageView img;
    @FXML
    private Label image1;
    private ObservableList<club> data;
    
    
    List<club> lstclub;
   
    participation pa;
    ParticipationService parts=new ParticipationService();
        club cl;
    ClubService Cls = new ClubService();
    @FXML
    private Label nom1;
    @FXML
    private Label nom;
    @FXML
    private Label Description1;
    private Label catégorie1;
    private TextField catégorie;
    @FXML
    private Label description;
     static int id;
    @FXML
    private Label nbr;
    @FXML
    private Hyperlink consult;
    @FXML
    private Hyperlink bestclub;
    @FXML
    private Hyperlink contact;
    @FXML
    private ImageView idlogo;
    @FXML
    private ImageView exitgv;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       nom1.setVisible(false);
       nom.setVisible(false);
       image1.setVisible(false);
       description.setVisible(false);
       Description1.setVisible(false);
       annuler.setVisible(false);
        participer.setVisible(false);
        img.setVisible(false);
         data = FXCollections.observableArrayList();
        loadDataFromDatabase();
        setsCllTable();
        selected_prop();
        //recherche avancée
         consult.setOnAction(event->{
             try {
                 Pagination p = new Pagination("/fxml/ConsulterClub.fxml");
             } catch (IOException ex) {
                 Logger.getLogger(AccueilClubController.class.getName()).log(Level.SEVERE, null, ex);
             }
        ((Node)(event.getSource())).getScene().getWindow().hide();
                       
        });
            bestclub.setOnAction(event->{
             try {
                 Pagination p = new Pagination("/fxml/ParticipationStatique.fxml");
             } catch (IOException ex) {
                 Logger.getLogger(AccueilClubController.class.getName()).log(Level.SEVERE, null, ex);
             }
        ((Node)(event.getSource())).getScene().getWindow().hide();
                       
        });
            contact.setOnAction(event->{
             try {
                 Pagination p = new Pagination("/fxml/Mailing.fxml");
             } catch (IOException ex) {
                 Logger.getLogger(AccueilClubController.class.getName()).log(Level.SEVERE, null, ex);
             }
        ((Node)(event.getSource())).getScene().getWindow().hide();
                       
        });
                
    
        
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
       
    
    }    
      private void loadDataFromDatabase() {
        ArrayList<club> listclubs = (ArrayList<club>) Cls.afficherClub();

        for(club a:listclubs){
            System.out.println(a);
            data.add(a);
        }
           listClub.setItems(data);


    }
       private void setsCllTable() {
        NomClub.setCellValueFactory(new PropertyValueFactory<>("nom_club"));
        DescriptionClub.setCellValueFactory(new PropertyValueFactory<>("description_club"));
        CatégorieClub.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCategoriec().getNom_categorieC()));
        
       

    }
    private void selected_prop()
    {
         listClub.setOnMouseClicked((javafx.scene.input.MouseEvent event) -> {
             cl = listClub.getItems().get(listClub.getSelectionModel().getSelectedIndex());
             if(cl.valid()==null)
             {
                 id=cl.getId_club();
                 // System.out.println(id);
                // club id1 = Cls.RechercherClubCById(id);
                // participation pa = new participation(id1);
                 //try {
                  //   parts.participer(pa);
                 //    nbr.setText(Integer.toString(cl.getNbr_participants()));
                // } catch (SQLException ex) {
                 //    Logger.getLogger(ConsulterClubController.class.getName()).log(Level.SEVERE, null, ex);
               //  }
                 nom.setText(cl.getNom_club());
                description.setText(cl.getDescription_club());
               
              nom1.setVisible(true);
              nom.setVisible(true);
              image1.setVisible(true);
              description.setVisible(true);
              Description1.setVisible(true);
              annuler.setVisible(true);
              participer.setVisible(true);
              
                 img.setVisible(true);
                 img.setImage(new Image("http://localhost/codetest/web/images/"+cl.getLogo_club()));
                 image1.setVisible(true);
                 participer.setVisible(true);
                 annuler.setVisible(true);
                
       
             }
             
         });
    }

    @FXML
    private void annule(ActionEvent event) throws SQLException { 
//         id=pa.getId_participation();
      //   System.out.println(id);
         if(cl.valid()==null)
             {
                 id=cl.getId_club();
                 // System.out.println(id);
                 club id1 = Cls.RechercherClubCById(id);
                 participation pa = new participation(id1);
                 try {
                     parts.annulerparticiper(pa);
                  //   nbr.setText(Integer.toString(cl.getNbr_participants()));
                 } catch (SQLException ex) {
                     Logger.getLogger(ConsulterClubController.class.getName()).log(Level.SEVERE, null, ex);
                 }
                 

    }
     annuler.setVisible(false);}
    @FXML
    private void participe(ActionEvent event) throws SQLException {
         if(cl.valid()==null)
             {
                 id=cl.getId_club();
                 // System.out.println(id);
                 club id1 = Cls.RechercherClubCById(id);
                 participation pa = new participation(id1);
                 try {
                     parts.participer(pa);
                 //    nbr.setText(Integer.toString(cl.getNbr_participants()));
                 } catch (SQLException ex) {
                     Logger.getLogger(ConsulterClubController.class.getName()).log(Level.SEVERE, null, ex);
                 }
                 
           participer.setVisible(false);
     // participation paa = new participation();
       // ParticipationService ps = new ParticipationService();
      
        
       
        
        
       // parts.participer(pa);
       
       // data.clear();
       // loadDataFromDatabase();   
      
       

       
   
    }
    }

    @FXML
    private void BtnExit(MouseEvent event) {
          try {
           Service.Pagination p = new Service.Pagination("/fxml/AcceuilCC.fxml");
           ((Node)(event.getSource())).getScene().getWindow().hide();
           
       } catch (IOException ex) {
           System.out.println(ex.getMessage());
       }
    }
}
