/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Entity.Evennement;
import Service.EvennementService;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Dell
 */
public class ModifierevController implements Initializable {

    @FXML
    private AnchorPane bp;
    @FXML
    private TextField imageEmm1;
    @FXML
    private TextField Titremm1;
    @FXML
    private TextField empmm1;
    @FXML
    private TextField DESCmm1;
    @FXML
    private DatePicker datf1;
    @FXML
    private DatePicker datd1;
    @FXML
    private Button save1;
    @FXML
    private TextField categmm1;
    @FXML
    private Spinner nbr_place_E11;
    @FXML
    private Button upli;
      @FXML
    private Label labelmode;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         SpinnerValueFactory<Integer> grade = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 50);
        nbr_place_E11.setValueFactory(grade);
        
 DESCmm1.setText(ModifierEController.De);
 Titremm1.setText(ModifierEController.TI);
 empmm1.setText(ModifierEController.Em);
 categmm1.setText(ModifierEController.ca);
 imageEmm1.setText(ModifierEController.Im);
 datd1.setValue(ModifierEController.dad);
 datf1.setValue(ModifierEController.daf);
 nbr_place_E11.getValueFactory().setValue(ModifierEController.nbrp);
 
 //
        
        
    }    

    @FXML
    private void save1(ActionEvent event) throws IOException, SQLException {
         EvennementService es = new EvennementService();
         Evennement e1 = ModifierEController.modifev;
         Evennement e2 = new Evennement();
         e2=e1;
         e2.setCategorie_Event(categmm1.getText());
         e2.setDATED_EVENT(datd1.getValue());
         e2.setDATEF_EVENT(datf1.getValue());
         e2.setDescr_Event(DESCmm1.getText());
         e2.setEMPLACEMENT(empmm1.getText());
         e2.setNbr_place_E((int) nbr_place_E11.getValueFactory().getValue());
         e2.setImage_Event(imageEmm1.getText());
         e2.setTitre_Event(Titremm1.getText());

         
 
  if (datd1.getValue()==null){
           labelmode.setText(" date debut NULL");
           
      }
     else if (datf1.getValue()==null){
           labelmode.setText(" date fin NULL");
           
      }
     //  else if (!"not found".equals(es.recherchertitre(Titremm1.getText()))){
      //  labelmode.setText(" titre Evennement deja existe");
       //} 
       
       else if (datd1.getValue().isAfter(datf1.getValue()))
       {
           labelmode.setText("date fin ne peut pas etre avant date debut");
       }
       else if (DESCmm1.getText().equals("")){
       labelmode.setText("champ des est vide");
       }
        else if (imageEmm1.getText().equals("")){
        labelmode.setText("champ Image est vide");
       }
       else if (Titremm1.getText().equals("")){
        labelmode.setText("champ Titre evenement est vide");
       }
        else if (empmm1.getText().equals("")){
        labelmode.setText("champ Emplacement evenement est vide");
       }
        else if (categmm1.getText().equals("")){
        labelmode.setText("champ categorie_Event evenement est vide");
       }
        else if ((int) nbr_place_E11.getValue() == 0 ){
        labelmode.setText("nombre de place est 0");
       } 
       
       ////////
       else
       {
         labelmode.setText("Evennement modifié avec succé");
 es.modifierRole(e2);
 
  Stage stage = (Stage) bp.getScene().getWindow(); 
                System.err.println("bbb2");
           // Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("fxml/ModifierE.fxml"));
       AnchorPane newLoadedPane = FXMLLoader.load(getClass().getResource("/fxml/ModifierE.fxml"));
				bp.getChildren().clear();
				bp.getChildren().add(newLoadedPane);
       }
  
  
    
    
    
 //////
    
        
    }

    @FXML
    private void uploadm(ActionEvent event) {
        
          Stage primary = new Stage();
        
        FileChooser filechooser = new FileChooser();
        filechooser.setTitle("Upload");
        filechooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
        File file = filechooser.showOpenDialog(primary);
        String path ="C:\\wamp64\\www";
      imageEmm1.setVisible(true);
        imageEmm1.setText(file.getName());
        System.out.println(imageEmm1.getText());
        if(file!=null)
        {
            try {             Files.copy(file.toPath(),new File(path+"\\"+file.getName()).toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
    
}
