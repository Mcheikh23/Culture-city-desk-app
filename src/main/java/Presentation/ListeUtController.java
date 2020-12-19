/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Entity.User;
import Service.UserService;
import Utilitaire.UserSession;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;
import javafx.util.Duration;
import Service.Pagination;

/**
 * FXML Controller class
 *
 * @author Dell
 */
public class ListeUtController implements Initializable {

    @FXML
    private ScrollPane pane;
    @FXML
    private Label btn_exit;
    @FXML
    private ImageView monimage;
    UserService userService = new UserService();
    User u = new User();
    ListView<User> list=new ListView<>();
    ObservableList<User> items = FXCollections.observableArrayList();
    private ObservableList<User> data;
    @FXML
    private JFXButton retour;
    @FXML
    private void handleButtonAction(MouseEvent event) {
        Stage stage = (Stage) btn_exit.getScene().getWindow();
        stage.close();
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String arg = (UserSession.getInstance(null, null, null, null, null, null, null).getUserName());
        try {
            u = userService.get(arg);
            javafx.scene.image.Image im = new javafx.scene.image.Image("http://localhost/PIDEVWEB/web/uploads/images/" + u.getImage());
            System.out.println("http://localhost/PIDEVWEB/web/uploads/images/"+ u.getImage()+"aaaaa");
            monimage.setImage(im);
        } catch (SQLException ex) {
        }
         try {
            TilePane b = new TilePane();
            b.setPadding(new javafx.geometry.Insets(30));
            TilePane c = new TilePane();
            FadeTransition ft = new FadeTransition(Duration.millis(1500));
           data =     userService.loadProduit();

            for ( User d : data) {                
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/divUt.fxml"));
                    Parent root = (Pane) loader.load();
                    DivUtController DHC = loader.getController();
                    DHC.LoadValues(d);
                   c.getChildren().removeAll();
                   c.getChildren().add(root);
                } catch (IOException ex) {
                }
            }
            c.setPrefColumns(3);
            c.setPadding(new javafx.geometry.Insets(0));
            c.setHgap(10);
            c.setVgap(80);
            b.getChildren().add(c);
            b.setPrefWidth(1000);
            pane.setContent(b);    
                }catch (SQLException ex) {
                }
    }    

 
    @FXML
    private void monimage(MouseEvent event) throws IOException {
        Pagination p = new Pagination("/fxml/monProfil.fxml");
        ((Node)(event.getSource())).getScene().getWindow().hide();  
    }

    @FXML
    private void retour(ActionEvent event) throws IOException {
        Pagination p = new Pagination("/fxml/AdminHome.fxml");
        ((Node)(event.getSource())).getScene().getWindow().hide();  
    }
    
}
