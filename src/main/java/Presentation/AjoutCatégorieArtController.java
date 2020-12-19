/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Entity.Catégorie;
import Service.CatégorieService;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class AjoutCatégorieArtController implements Initializable {

    @FXML
    private TextField Nom_catArt;
    @FXML
    private Button Btn_ajoutCat;
    @FXML
    private Button Btn_rtrcatart;
    @FXML
    private Label BTN_esxitcatart;
    CatégorieService catart=new CatégorieService();
    Catégorie catarta;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         Btn_ajoutCat.setOnAction(xx -> {
           catarta= new Catégorie(Nom_catArt.getText());
           catart.creerCatégorie(catarta);
            Nom_catArt.setText("");
           
        });
        // TODO
    }    

    @FXML
    private void Retourart(ActionEvent event) throws IOException {
            Pagination p = new Pagination("/fxml/AffichageGestionCatégorieArt.fxml");
            ((Node)(event.getSource())).getScene().getWindow().hide();
    }

    @FXML
    private void handleButtonAction(MouseEvent event) {
         Stage stage = (Stage) BTN_esxitcatart.getScene().getWindow();
        stage.close();
    }
    
}
