/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Entity.Evennement;
import Service.EvennementService;
import facebook4j.Facebook;
import facebook4j.FacebookException;
import facebook4j.FacebookFactory;
import facebook4j.auth.AccessToken;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import Utilitaire.SingletonConnexion;
import javafx.scene.Node;

/**
 * FXML Controller class
 *
 * @author Dell
 */
public class ModifierEController implements Initializable {
 //Evennement E1 = new Evennement( DESCmm.getText(),imageEmm.getText(),Titremm.getText(),datd.getValue(),datf.getValue(),empmm.getText(),12,categmm.getText(),(int) nbr_place_E1.getValueFactory().getValue());     
    public static String De ;
    public static String Im ;
    public static String TI ;
    public static LocalDate dad ;
    public static LocalDate daf ;
    public static String Em ;
    public static String ca;
    public static int nbrp;
    public static Evennement modifev;
    
     Connection c = SingletonConnexion.getInstance().getConn();
    Statement ste;
  
    @FXML
    private AnchorPane bp;
    @FXML
    private TableView<Evennement> TTm;
    @FXML
    private TableColumn<Evennement,String> imageEm;
    @FXML
    private TableColumn<Evennement,String> Titrem;
    @FXML
    private TableColumn<Evennement,String> empm;
    @FXML
    private TableColumn<Evennement,LocalDate> dateDm;
    @FXML
    private TableColumn<Evennement,LocalDate> DATEFm;
    @FXML
    private TableColumn<Evennement,Integer> NBRPm;
    @FXML
    private TableColumn<Evennement,String> DESCm;
    @FXML
    private TableColumn<Evennement,String> CATEGm;
@FXML
    private Button supprimE;
 @FXML
    private Button up;
    private Spinner  nbr_place_E1;
    private Facebook facebook;
    @FXML
    private Button sha;
    @FXML
    private Button rr;
/**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("fxml.ModifierEController");
       
        
 EvennementService  es = new EvennementService();
        System.out.println("fxml.AfficherEController.initialize()");
        
        ////////////////
        
     
        
        
        ///////////////
        ArrayList<Evennement> arrayList = null;
        try {
            arrayList = (ArrayList<Evennement>) es.getAllP1();
        } catch (SQLException ex) {
            Logger.getLogger(ModifierEController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ObservableList obs = FXCollections.observableArrayList(arrayList);
        TTm.setItems(obs);

        imageEm.setCellValueFactory(new PropertyValueFactory<Evennement,String>("Image_Event"));
        Titrem.setCellValueFactory(new PropertyValueFactory<Evennement,String>("Titre_Event"));
        CATEGm.setCellValueFactory(new PropertyValueFactory<Evennement,String>("categorie_Event"));
         empm.setCellValueFactory(new PropertyValueFactory<Evennement,String>("EMPLACEMENT"));
          DESCm.setCellValueFactory(new PropertyValueFactory<Evennement,String>("Descr_Event"));
          NBRPm.setCellValueFactory(new PropertyValueFactory<Evennement,Integer>("nbr_place_E"));
       dateDm.setCellValueFactory(new PropertyValueFactory<Evennement,LocalDate>("DATED_EVENT"));   
       DATEFm.setCellValueFactory(new PropertyValueFactory<Evennement,LocalDate>("DATEF_EVENT"));   
      

// TODO
    }    
    
      @FXML
    void supprimerE(ActionEvent event) throws SQLException {
 Evennement song = TTm.getSelectionModel().getSelectedItem();
 System.out.println(song);
 EvennementService e = new EvennementService();
 TTm.getItems().removeAll(TTm.getSelectionModel().getSelectedItem());
 if (song != null){
   JOptionPane.showMessageDialog(null,"supprimee");   
 e.supprimerEvennement(song);
 }
    
   

    


        
    }
     @FXML
    void up(ActionEvent event) throws IOException {
        
Evennement song = TTm.getSelectionModel().getSelectedItem();
 System.out.println(song);
 modifev = song;
 EvennementService e = new EvennementService();
 if (song != null){
  
 ////////////
    De = song.getDescr_Event();
     Im=song.getImage_Event() ;
     TI =song.getTitre_Event();
     dad = song.getDATED_EVENT();
     daf=song.getDATEF_EVENT() ;
    Em =song.getEMPLACEMENT();
     ca=song.getCategorie_Event();
     nbrp=song.getNbr_place_E();
 /////modifier jdida
   Stage stage = (Stage) bp.getScene().getWindow();
           
       AnchorPane newLoadedPane = FXMLLoader.load(getClass().getResource("/fxml/Modifierev.fxml"));
				bp.getChildren().clear();
				bp.getChildren().add(newLoadedPane);
 
 }
    }
      
    private void sharefa(ActionEvent event) {
       
    }

    @FXML
    private void shar(ActionEvent event) throws FacebookException {
        Evennement song = TTm.getSelectionModel().getSelectedItem();

           facebook = new FacebookFactory().getInstance();
        
        facebook.setOAuthAppId("", "");
        String accessTokenString = "EAANRgqg9GZC4BAKfm7KVeKytsNGCWG0duBhyhmTH6Xh4bT3SIbCXaExyZCYntaOZAolufqfOwdhWsGUplAgs3yL6GfaGsXhEzqd1HmCzpkSZCcZBJZCxxXAojV07CvjUg1bqkGjweiXwZCopRG14zsxYmPIz9X2zX6sDZBsbDNdN8L1FVX5TounplLq9URj8qZB6KRDntLmLmpAZDZD";
        AccessToken accessToken = new AccessToken(accessTokenString);
        //set access token
        facebook.setOAuthAccessToken(accessToken);
        //ResponseList<Account> accounts = facebook.getAccounts();
        //Account pageAccount = accounts.get(0);
       try{
       facebook.postStatusMessage("Le titre de l'évènement est: "+ song.getTitre_Event()+" \n sa categorie est "+song.getCategorie_Event() +" \n et la date de début est "+ song.getDATED_EVENT() + " et la date de fin est " + song.getDATEF_EVENT()+ " le nombre de participant est " + song.getNbr_place_E() + " \n sa description : "+ song.getDescr_Event());
            System.out.println("fxml.ModifierEController.shar()");}
            catch(FacebookException fe){
            Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Evenement deja partagé.");
        alert.setHeaderText(null);
        alert.setContentText("Error");
        alert.showAndWait();
       
    
            }
        
    }

    @FXML
    private void rr(ActionEvent event) {
        try {
            Service.Pagination p = new Service.Pagination("/fxml/login.fxml");
        } catch (IOException ex) {
            Logger.getLogger(InscriptionController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ((Node)(event.getSource())).getScene().getWindow().hide();  
    }

  
      
}
