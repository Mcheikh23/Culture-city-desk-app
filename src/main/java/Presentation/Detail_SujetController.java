/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Entity.Commentaire;
import Service.CommentaireService;
import com.mycompany.projetman.MainApp;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class Detail_SujetController implements Initializable {

    @FXML
    private ScrollPane x;
    @FXML
    private VBox msget;
   @FXML
    private JFXButton aff;
 @FXML
    private Button id_ajout_Comment;

    @FXML
    private TextArea text_comment;

     @FXML
    private HBox id_hbox;
    /**
     * Initializes the controller class.
     */
   
   
    private HBox wrap;
    private VBox wrap1;
    VBox content2 = new VBox();
 Label textField[] = new Label[150000];
   int i =0;
   Button button[] = new Button[150000];
      Button button1[] = new Button[150000];

   int j=0;
    CommentaireService commentaireService = new CommentaireService();
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            afficher_liste_commentaires();
        } catch (SQLException ex) {
            Logger.getLogger(Detail_SujetController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }    

    
     private void afficher_liste_commentaires() throws SQLException{
        clearContent(content2);
            
           
            List<Commentaire> ff=  commentaireService.readAll(AffichageForumController.getSujet_à_ouvrir().getId_f());
            ff.forEach(e->{
              //  String thrd = e.getContent();
                //thread = Integer.parseInt(thrd);
                
                textField[i] = new Label();
                button[j]= new Button();
                 button1[j]= new Button();
                textField[i].setText(" " + e.getDescription_com() +" \n"+ e.getDate_com()+ "\n");
               textField[i].setTranslateX(10);
               textField[i].setAlignment(Pos.TOP_LEFT);
               wrap1 = new VBox();
                wrap1.setPrefWidth(id_hbox.getPrefWidth()-80);
                wrap1.setAlignment(Pos.TOP_LEFT);
              wrap1.getChildren().add(textField[i]);
           if(e.getId_user()==3) { textField[i].setStyle("-fx-text-fill: red;") ;
          
           textField[i].setTranslateX(70);
           button[j].setTranslateX(600);
           button[j].setTranslateY(-30);
            button[j].setText("Modifier");
            button[j].setStyle("-fx-text-fill: green;");         
            button[j].setAlignment(Pos.TOP_LEFT);
            
            button1[j].setTranslateX(680);
           button1[j].setTranslateY(-55);
            button1[j].setText("Supprimer");
            button1[j].setStyle("-fx-text-fill: green;");         
            button1[j].setAlignment(Pos.TOP_LEFT);
            
wrap1.getChildren().add(button[j]);
wrap1.getChildren().add(button1[j]);

           j=j+1;
          
           }
           
           // textField[i].setTranslateX(40);
                wrap = new HBox();
                wrap.setPrefWidth(msget.getPrefWidth()-80);
                wrap.setAlignment(Pos.TOP_LEFT);
                
                
//textField[i].getStyleClass().add("recumsg");




wrap.getChildren().add(wrap1);
wrap.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
content2.getChildren().add(wrap);
content2.setPrefHeight(content2.getPrefHeight()+88 + textField[i].getPrefHeight()+88);
i = i + 1;

x.setContent(content2);
x.setVvalue(1.0d); 
            });
        
     }
     
     
      @FXML
    void ajoutComment(ActionEvent event) throws SQLException {
        Commentaire commentaire = new Commentaire(AffichageForumController.getSujet_à_ouvrir().getId_f(),MainApp.user.getId(), text_comment.getText());
        commentaireService.ajouterCommentaire(commentaire);
        text_comment.clear();
        afficher_liste_commentaires();
       
    }
    @FXML
    private void goReceive(MouseEvent event) {
    }
    
      private void clearContent(Pane container) {
        container.getChildren().clear();
    }
      @FXML
    void isWriting(KeyEvent event) {

    }
}
