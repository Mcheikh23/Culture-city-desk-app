/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Entity.User;
import Service.UserService;
import Utilitaire.UserSession;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Dell
 */
public class DivUtController implements Initializable {

    @FXML
    private AnchorPane ap;
    @FXML
    private Pane sq;
    @FXML
    private Label txtitre;
    @FXML
    private Label id;
    @FXML
    private Rectangle rectangle;
    @FXML
    private Button shwprod;

    /**
     * Initializes the controller class.
     */
     ListView<User> list=new ListView<>();
    ObservableList<User> items = FXCollections.observableArrayList();
    private ObservableList<User> data;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        UserService ps = new UserService();
          List <User> data= ps.AfficherUser();
          for(User p : data){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("divUt.fxml"));
            txtitre.setText(p.getUserName());
            Image im = new Image("http://localhost/PIDEVWEB/web/uploads/images/"+p.getImage());
            rectangle.setFill(new ImagePattern(im));
    }    
}
     public void LoadValues(User e) throws IOException {
        txtitre.setText(e.getUserName());
        Image im = new Image("http://localhost/PIDEVWEB/web/uploads/images/"+e.getImage());
        rectangle.setFill(new ImagePattern(im));
      }
    @FXML
    private void ShowProduct(MouseEvent event) throws SQLException {
        UserService psp = new UserService();
        User wal = new User ();
        User wall = new User ();
       wal = psp.get(txtitre.getText());
       String a = UserSession.getInstance(null, null, null, null, null, null, null).getUserName();
       wall = psp.get(a);
        if (!(wall.getId()==wal.getId())){
         FXMLLoader Loader = new FXMLLoader();
        Loader.setLocation(getClass().getResource("/fxml/profilUt.fxml"));
        try {
            Loader.load();
            } catch (IOException ex) {
            }              
            ProfilUtController aff= Loader.getController();
            User prog = new User();
            prog= psp.get(txtitre.getText());
            aff.setUser(prog);
            Parent p = Loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(p));
            stage.show();
        }
        else {
             FXMLLoader Loader = new FXMLLoader();
        Loader.setLocation(getClass().getResource("monProfil.fxml"));
        try {
            Loader.load();
            } catch (IOException ex) {
            }              
            MonProfilController afff= Loader.getController();
            User prog = new User();
            prog= psp.get(txtitre.getText());
            afff.setUser(prog);
            Parent p = Loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(p));
            stage.show();
        }
    }
    
}
