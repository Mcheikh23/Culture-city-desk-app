/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Service.ArticleService;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Window;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class StatController implements Initializable {

    @FXML
    private BarChart<String, Integer> idstatart;
    @FXML
    private Button btnstatartt;
    @FXML
    private ImageView consart;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            ArticleService p=new ArticleService();
        XYChart.Series<String, Integer> series = p.toparticle();
        idstatart.setData(FXCollections.observableArrayList(series));
         btnstatartt.setOnAction(event-> {pdf();});
        
        // TODO
    }  
     void pdf() {
 System.out.println("To Printer!");
         PrinterJob job = PrinterJob.createPrinterJob();
           if(job != null){
    Window primaryStage = null;
           job.showPrintDialog(primaryStage); 
            
    Node root = this. idstatart ;
    
           job.printPage(root);
           job.endJob();
            
       

  }}

    @FXML
    private void RetourConsulart(MouseEvent event) {
         try {
                 Pagination p = new Pagination("/fxml/consulterArticle.fxml");
             } catch (IOException ex) {
                 
             }
        ((Node)(event.getSource())).getScene().getWindow().hide();}

    }

    

