/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Entity.User;
import Service.UserService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;
import Service.Pagination;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * FXML Controller class
 *
 * @author Dell
 */
public class InscriptionController implements Initializable {

    UserService user = new UserService();

    @FXML
    private Label btn_exit;
    @FXML
    private JFXButton reset;
    @FXML
    private ImageView btnR;
    @FXML
    private JFXButton retour;

    @FXML
    private void handleButtonAction(MouseEvent event) {
        Stage stage = (Stage) btn_exit.getScene().getWindow();
        stage.close();
    }
    @FXML
    private JFXTextField email;
    @FXML
    private JFXPasswordField password;
    @FXML
    private JFXTextField username;
    @FXML
    private JFXTextField nom;
    @FXML
    private JFXTextField prenom;
    @FXML
    private JFXButton ajouter;
    @FXML
    private JFXTextField confemail;
    @FXML
    private JFXPasswordField confpassword;
    User u;
    @FXML
    private ImageView image;
    @FXML
    private ImageView imagee;

    @FXML
    private void back(MouseEvent event) throws IOException {
        Pagination p = new Pagination("/fxml/login.fxml");
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Image im = new Image("http://localhost/pidev/web/uploads/images/logo.png");
        image.setImage(im);
        Image imm = new Image("http://localhost/pidev/web/uploads/images/Cit-de-la-culture.png");
        imagee.setImage(imm);
        Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

        
        String role = "a:0:{}";
        u = new User();
        ajouter.setOnAction(xx -> {
          

            u.setUserName(username.getText());
            u.setEmail(email.getText());
            u.setPassword(password.getText());
            String myName = u.getPassword();
            char[] myNameChars = myName.toCharArray();
//            myNameChars[2] = 'y';
            myName = String.valueOf(myNameChars);
            u.setPassword(myName);
            u.setRoles(role);
            u.setNom(nom.getText());
            u.setUsername_canonical(username.getText());
            u.setPrenom(prenom.getText());
            u.setEmail_canonical(email.getText());
            
            
            if (!(email.getText().equals(confemail.getText()) && password.getText().equals(confpassword.getText()))) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Email or password don't match");
                alert.setHeaderText("Email or password don't match");
                alert.setContentText("Email or password don't match");
                alert.showAndWait();
            } else if (!VALID_EMAIL_ADDRESS_REGEX.matcher(email.getText()).find()) {
                System.out.println("mail invalide");
            } else if (username.getText().equals("") || email.getText().equals("")
                    || password.getText().equals("")
                    || nom.getText().equals("") || prenom.getText().equals("")
                    || confemail.getText().equals("")
                    || confpassword.getText().equals("")) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Tous les champs sont obligatoires");
                alert.setHeaderText("Tous les champs sont obligatoires");
                alert.setContentText("Tous les champs sont obligatoires");
                alert.showAndWait();
            } else {
                user.ajouterUser(u);
                username.setText("");
                email.setText("");
                confemail.setText("");
                password.setText("");
                confpassword.setText("");
                nom.setText("");
                prenom.setText("");
                ajouter.setVisible(false);

            }
        });

    }

    FileChooser fc = new FileChooser();
    File selectedFile = new File("");

    @FXML
    private void image(ActionEvent event) throws FileNotFoundException, IOException {

        
        File dest = new File("C:\\wamp64\\www\\PIDEVWEB\\web\\uploads\\images");
        //Bureau

        fc.setInitialDirectory(new File("C:\\Users\\Hachem\\Desktop"));
        selectedFile = fc.showOpenDialog(null);
        FileUtils.copyFileToDirectory(selectedFile, dest);
        u.setImage(selectedFile.getName());

    }

    @FXML
    private void reset(ActionEvent event) {

        username.clear();
        email.clear();
        confemail.clear();
        password.clear();
        confpassword.clear();
        nom.clear();
        prenom.clear();

    }

    @FXML
    private void retour(ActionEvent event) {
        try {
            Pagination p = new Pagination("/fxml/login.fxml");
        } catch (IOException ex) {
            Logger.getLogger(InscriptionController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ((Node)(event.getSource())).getScene().getWindow().hide();  
    }
}
