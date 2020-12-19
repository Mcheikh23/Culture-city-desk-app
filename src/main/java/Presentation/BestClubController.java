package Presentation;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Service.ClubService;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import utils.ConnexionBD;

/**
 * FXML Controller class
 *
 * @author user
 */
public class BestClubController implements Initializable {

    @FXML
    private Hyperlink consult;
    @FXML
    private Hyperlink bestclub;
    @FXML
    private Hyperlink contact;
    @FXML
    private ImageView idlogo;
    @FXML
    private BarChart<String, Integer> barChart;
     Connection c = ConnexionBD
           .getInstancebd()
           .getConnexionBD();
    Statement ste;
    @FXML
    private ImageView exitgv;
     public BestClubController() {
        try {
            ste = c.createStatement();
        } catch (SQLException ex) {
            System.out.println(ex);
        }}
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          ClubService p=new ClubService();
        XYChart.Series<String, Integer> series = p.topclub();
        barChart.setData(FXCollections.observableArrayList(series));
         
                         consult.setOnAction(event->{
             try {
                 Pagination pp = new Pagination("/fxml/ConsulterClub.fxml");
             } catch (IOException ex) {
                 Logger.getLogger(AccueilClubController.class.getName()).log(Level.SEVERE, null, ex);
             }
        ((Node)(event.getSource())).getScene().getWindow().hide();
                       
        });
            bestclub.setOnAction(event->{
             try {
                 Pagination pp = new Pagination("/fxml/BestClub.fxml");
             } catch (IOException ex) {
                 Logger.getLogger(AccueilClubController.class.getName()).log(Level.SEVERE, null, ex);
             }
        ((Node)(event.getSource())).getScene().getWindow().hide();
                       
        });
            contact.setOnAction(event->{
             try {
                 Pagination pp = new Pagination("/fxml/Mailing.fxml");
             } catch (IOException ex) {
                 Logger.getLogger(AccueilClubController.class.getName()).log(Level.SEVERE, null, ex);
             }
        ((Node)(event.getSource())).getScene().getWindow().hide();
                       
        });
                
    }    
    
        // TODO

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
    

