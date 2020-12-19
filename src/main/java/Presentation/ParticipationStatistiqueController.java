/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Service.ClubService;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.Window;
import static jdk.nashorn.internal.objects.Global.print;
import utils.ConnexionBD;

/**
 * FXML Controller class
 *
 * @author user
 */
public class ParticipationStatistiqueController implements Initializable {

    @FXML
    private AnchorPane idBorder;
    @FXML
    private BarChart<String, Integer> barChart;
     Connection c = ConnexionBD
           .getInstancebd()
           .getConnexionBD();
    Statement ste;
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
    private Button print;
    @FXML
    private ImageView exitgv;
     public ParticipationStatistiqueController() {
        try {
            ste = c.createStatement();
        } catch (SQLException ex) {
            System.out.println(ex);
        }}

    /**
     * Initializes the controller class.
     */
 /*   @Override
    public void initialize(URL url, ResourceBundle rb) {
        ClubService cls = new ClubService();
 String req =" SELECT nom_club,max(nbr_participants) as y from club group by (nom_club) order by y desc limit 7 ";
        XYChart.Series<String, Integer> series = new XYChart.Series<String, Integer>();
        try {
             PreparedStatement ste = (PreparedStatement) c.prepareStatement(req);
            ResultSet rs = ste.executeQuery();
            while (rs.next()){
                series.getData().add(new XYChart.Data<>(rs.getString("nom_club"), rs.getInt("y")));
            }
            barChart.getData().add(series);
        } catch (SQLException ex) {
            Logger.getLogger(ParticipationStatistiqueController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
            private BarChart<String, Integer> idstatart;*/

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
           ClubService p=new ClubService();
        XYChart.Series<String, Integer> series = p.topclub();
        barChart.setData(FXCollections.observableArrayList(series));
         
                            addcat.setOnAction(event->{
             try {
                 Pagination pa = new Pagination("/fxml/Categoriec.fxml");
             } catch (IOException ex) {
                 Logger.getLogger(AccueilClubController.class.getName()).log(Level.SEVERE, null, ex);
             }
        ((Node)(event.getSource())).getScene().getWindow().hide();
                       
        });
            listcat.setOnAction(event->{
             try {
                 Pagination pa = new Pagination("/fxml/AfficherCategoriec.fxml");
             } catch (IOException ex) {
                 Logger.getLogger(AccueilClubController.class.getName()).log(Level.SEVERE, null, ex);
             }
        ((Node)(event.getSource())).getScene().getWindow().hide();
                       
        });
            addcl.setOnAction(event->{
             try {
                 Pagination pa = new Pagination("/fxml/Club.fxml");
             } catch (IOException ex) {
                 Logger.getLogger(AccueilClubController.class.getName()).log(Level.SEVERE, null, ex);
             }
        ((Node)(event.getSource())).getScene().getWindow().hide();
                       
        });
                   listcl.setOnAction(event->{
             try {
                 Pagination pa = new Pagination("/fxml/AffichClub.fxml");
             } catch (IOException ex) {
                 Logger.getLogger(AccueilClubController.class.getName()).log(Level.SEVERE, null, ex);
             }
        ((Node)(event.getSource())).getScene().getWindow().hide();
                       
        });
                   
                             stat.setOnAction(event->{
             try {
                 Pagination pa = new Pagination("/fxml/ParticipationStatistique.fxml");
             } catch (IOException ex) {
                 Logger.getLogger(AccueilClubController.class.getName()).log(Level.SEVERE, null, ex);
             }
        ((Node)(event.getSource())).getScene().getWindow().hide();
                       
        });
         
        
        // TODO
    }

   
    

    @FXML
    private void imprimerAction(ActionEvent event) {
   
         

 System.out.println("To Printer!");
         PrinterJob job = PrinterJob.createPrinterJob();
           if(job != null){
    Window primaryStage = null;
           job.showPrintDialog(primaryStage); 
            
    Node root = this.barChart;
           job.printPage(root);
           job.endJob();
            
       

    }

   
    }   

    @FXML
    private void BtnExit(MouseEvent event) {
         System.exit(0);
    }
}   
    
        // TODO
       
    

