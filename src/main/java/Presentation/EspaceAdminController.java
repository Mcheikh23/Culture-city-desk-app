package Presentation;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Mnif
 */
public class EspaceAdminController implements Initializable {
    @FXML
    private ImageView exitbtn;
    @FXML
    private ImageView retourBtn;
    @FXML
    private Hyperlink lienClub;
    @FXML
    private Hyperlink lienVente;
    @FXML
    private Hyperlink lienBiblio;
    @FXML
    private Hyperlink lienForum;
    @FXML
    private Hyperlink lienCinema;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lienVente.setOnAction(event->{try {
                 Pagination p = new Pagination("/fxml/AcceuilVente.fxml");
             } catch (IOException ex) {
                 
             }
        ((Node)(event.getSource())).getScene().getWindow().hide();});
    lienCinema.setOnAction(event->{try {
                 Pagination p = new Pagination("/fxml/Cinema.fxml");
             } catch (IOException ex) {
                 
             }
        ((Node)(event.getSource())).getScene().getWindow().hide();});}
        // TODO
        

    @FXML
    private void exitbtn(MouseEvent event) {
        System.exit(0);
    }

    @FXML
    private void retourBtn(MouseEvent event) {
    }
}
