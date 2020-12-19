/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Entity.Evennement;
import Service.EvennementService;
import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Dell
 */
public class AjouterEvntController implements Initializable {

    
    @FXML 
    private DatePicker DATEF_EVENT;
    @FXML 
    private DatePicker DATED_EVENT;
    
     @FXML
    private TextField Titre_Event;
    @FXML
    private TextField Image_Event;
    @FXML
    private TextField EMPLACEMENT;
    @FXML
    private TextField categorie_Event;         
             @FXML
    private TextField Descr_Event;
            @FXML
    private Spinner nbr_place_E;
            @FXML
    private Button ajte;
                @FXML
    private Button rt;
            @FXML
    private AnchorPane bp;
             @FXML
    private Button uploade;
    @FXML
    private ImageView image;
    @FXML
    private Label labelec;
    @FXML
    private Button mapiig;
    /**
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        SpinnerValueFactory<Integer> grade = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 50);
    
    nbr_place_E.setValueFactory(grade);
//    EMPLACEMENT.setText(AjouterPointCollectController.longitude);
    
    }    
    
    
    
     @FXML
    private void ajouE(ActionEvent event) throws SQLException, IOException {
        //Preferences userPreferences = Preferences.userRoot();
              
        System.err.println("bbb");
        
         EvennementService es = new EvennementService();

        LocalDate currentTime = LocalDate.now(); 
        LocalDate date1 ;
        Titre_Event.getText();
        /*int years = (DATED_EVENT.getValue().getYear());
        int day = (DATED_EVENT.getValue().getDayOfMonth());
        int month = (DATED_EVENT.getValue().getMonth());*/
        LocalDate date2= DATED_EVENT.getValue();
        LocalDate date3= DATEF_EVENT.getValue();
       Integer a = (Integer) nbr_place_E.getValue();
       
       
       Evennement E = new Evennement( Descr_Event.getText(),Image_Event.getText(),Titre_Event.getText(),date2,date3,EMPLACEMENT.getText(),12,categorie_Event.getText()	, (int) nbr_place_E.getValue());
       ////cont1rol saisie
         //System.out.println(es.recherchertitre(Titre_Event.getText()));
       
 
       if (date2==null){
           labelec.setText(" date debut NULL");
           
      }
     else if (date3==null){
           labelec.setText(" date fin NULL");
           
      }
       else if (!"not found".equals(es.recherchertitre(Titre_Event.getText()))){
        labelec.setText(" titre Evennement deja existe");
       } 
       
       else if (date2.isAfter(date3))
       {
           labelec.setText("date fin ne peut pas etre avant date debut");
       }
       else if (Descr_Event.getText().equals("")){
        labelec.setText("champ des est vide");
       }
        else if (Image_Event.getText().equals("")){
        labelec.setText("champ Image est vide");
       }
       else if (Titre_Event.getText().equals("")){
        labelec.setText("champ Titre evenement est vide");
        
       }
        else if (EMPLACEMENT.getText().equals("")){
        labelec.setText("champ Emplacement evenement est vide");
       }
        else if (EMPLACEMENT.getText().equals("")){
        labelec.setText("champ categorie_Event evenement est vide");
       }
        else if ((int) nbr_place_E.getValue() == 0 ){
        labelec.setText("nombre de place est 0");
       } 
       
       ////////
       else
       {
         labelec.setText("Evennement ajouter avec succé");
 es.creerEvennement(E);
       }
 
 
 
        }
     @FXML
    void rt(ActionEvent event) throws IOException {
     Stage stage = (Stage) bp.getScene().getWindow();

     
     AnchorPane newLoadedPane = FXMLLoader.load(getClass().getResource("/fxml/AfficherE.fxml"));
				bp.getChildren().clear();
				bp.getChildren().add(newLoadedPane);
          
            //Scene scene = new Scene(root);
            //stage.setScene(scene);
            //stage.show();
        
        
    }
 @FXML
    void uploade(ActionEvent event) {
        Stage primary = new Stage();
        
        FileChooser filechooser = new FileChooser();
        filechooser.setTitle("Upload");
        filechooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
        File file = filechooser.showOpenDialog(primary);
        String path ="C:\\wamp64\\www\\pidev\\web\\uploads\\images";
      Image_Event.setVisible(true);
        Image_Event.setText(file.getName());
        System.out.println(Image_Event.getText());
        if(file!=null)
        {
            try {             Files.copy(file.toPath(),new File(path+"\\"+file.getName()).toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}
