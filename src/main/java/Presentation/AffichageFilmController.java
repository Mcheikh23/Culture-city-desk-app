/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Entity.Film;
import Entity.Salle;
import Service.FilmService;
import Utilitaire.copyImages;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;



/**
 * FXML Controller class
 *
 * @author Mnif
 */
public class AffichageFilmController implements Initializable {
    Film film;
    FilmService filmService= new FilmService();
    @FXML
    private TableView<Film> tableFilm;
    @FXML
    private TableColumn<Film, String> listNom;
    @FXML
    private TableColumn<Film, String> listCategorie;
    @FXML
    private TableColumn<Film, String> listDescription;
    @FXML
    private TextField nomFilm;
    @FXML
    private Button BtnAjoutFilm;
    @FXML
    private Button BtnSupprimerFilm;
    @FXML
    private Button BtnModifierFilm;
    private Button BtinQuitterFilm;
    @FXML
    private TextField CategorieFilm;
    @FXML
    private Label nomLab;
    @FXML
    private Label categorieLab;
    private ObservableList<Film> data;
    @FXML
    private TextArea DescriptionFilm;
    @FXML
    private Button buttonPhoto ;
    String name="";
    @FXML
    private TableColumn<Film, String> listImage;
    @FXML
    private Label descriptionLab;
    @FXML
    private TextField pathImgArt;
    @FXML
    private Hyperlink lienSalle;
    @FXML
    private Hyperlink lienfilm;
    @FXML
    private Hyperlink lienseance;
    @FXML
    private ImageView exitbtn;
    @FXML
    private ImageView retourBtn;
    @FXML
    private TextField recherchetext;
    @FXML
    private ImageView titre1;
    @FXML
    private ImageView img;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
      Alert alert = new Alert(Alert.AlertType.WARNING);
      FilmService fs = new FilmService();
      Film f = new Film();
      data = FXCollections.observableArrayList();
      loadDataFromDatabase();
       setsCllTable();
       setcellValue();
       
       BtnSupprimerFilm.setOnAction(xx->{
          supprimer();
       });
        
        BtnModifierFilm.setOnAction((event) -> {
         Modifier();
       });
        BtnAjoutFilm.setOnAction((event)->{
       if(nomFilm.getText().isEmpty() || DescriptionFilm.getText().isEmpty() || CategorieFilm.getText().isEmpty() ){
         alert.setContentText("Please complete all fields!");
         alert.showAndWait();    
    }
    else if(fs.rechercheFilmParNom(nomFilm.getText())!= null)
    {
    alert.setContentText("This film already exists");
    alert.showAndWait();                     
    System.out.println(fs.rechercheFilmParNom(nomFilm.getText()));

    }   
    

    else 
        try {
            Ajouter(event);

    } catch (IOException ex) {
        Logger.getLogger(AffichageFilmController.class.getName()).log(Level.SEVERE, null, ex);
    }
});
         /////////// recherche 
       FilteredList<Film> FilteredData= new FilteredList<>(data , e-> true);
       recherchetext.setOnKeyReleased(e->{
           recherchetext.textProperty().addListener((ObservableValue, oldValue, newValue)->{
                   FilteredData.setPredicate((Predicate<? super Film>) film -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    if ((film.getNom_film().contains(newValue)) || (film.getDescription_film().toLowerCase().contains(newValue))){
                        return true;
                    }
                    else if(film.getCategorie_film().toLowerCase().contains(lowerCaseFilter)){
                        return true ;
                    }
                   
                  
                    return false;

           });
       });
           SortedList<Film> SortedData = new SortedList<>(FilteredData)  ; 
                    SortedData.comparatorProperty().bind(tableFilm.comparatorProperty());
                    tableFilm.setItems(SortedData);
 }); ///////////////////////////fin recherche
       
            lienSalle.setOnAction(event->{
             try {
                 Pagination p = new Pagination("/fxml/AffichageSalle.fxml");
             } catch (IOException ex) {
              Logger.getLogger(AffichageFilmController.class.getName()).log(Level.SEVERE, null, ex);
          }
        ((Node)(event.getSource())).getScene().getWindow().hide();
                       
        });
            
            lienfilm.setOnAction(event->{
             try {
                 Pagination p = new Pagination("/fxml/AffichageFilm.fxml");
             } catch (IOException ex) {
              Logger.getLogger(AffichageFilmController.class.getName()).log(Level.SEVERE, null, ex);
          }
        ((Node)(event.getSource())).getScene().getWindow().hide();
                       
        });
            lienseance.setOnAction(event->{
             try {
                 Pagination p = new Pagination("/fxml/AfficheSeance.fxml");
             } catch (IOException ex) {
              Logger.getLogger(AffichageFilmController.class.getName()).log(Level.SEVERE, null, ex);
          }
        ((Node)(event.getSource())).getScene().getWindow().hide();
                       
        });
      
      } 

    private void loadDataFromDatabase() {
       List<Film> lisFilm =filmService.afficherFilm();

        for(Film e:lisFilm){
            System.out.println(e);
            data.add(e);
        }
           tableFilm.setItems(data);
    }

    private void setsCllTable() {
        
        listNom.setCellValueFactory(new PropertyValueFactory<>("nom_film"));
         listCategorie.setCellValueFactory(new PropertyValueFactory<>("categorie_film"));
         listDescription.setCellValueFactory(new PropertyValueFactory<>("description_film"));
       //  listImage.setCellValueFactory(new PropertyValueFactory<>("image_film"));
    }

    private void setcellValue() {
        /*
        tableFilm.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent event) {    
                film = tableFilm.getItems().get(tableFilm.getSelectionModel().getSelectedIndex());
               //  nomLab.setText(String.valueOf(cart.getId()));
                nomFilm.setText(film.getNom_film());
                CategorieFilm.setText(film.getCategorie_film());
                DescriptionFilm.setText(film.getDescription_film());
                
                BtnSupprimerFilm.setVisible(true);
                BtnModifierFilm.setVisible(true);
            }
        });
        */
         tableFilm.setOnMouseClicked((javafx.scene.input.MouseEvent event) -> {
             film = tableFilm.getItems().get(tableFilm.getSelectionModel().getSelectedIndex());
            
             
              nomFilm.setText(film.getNom_film());
              CategorieFilm.setText(film.getCategorie_film());
              DescriptionFilm.setText(film.getDescription_film());
                 
                 //changer.setVisible(true);
                 //img.setVisible(true);
                  
                 img.setImage(new Image("http://localhost/codetest/web/uploads/images/"+film.getImage_film()));
              nomFilm.setVisible(true);
               CategorieFilm.setVisible(true);
                DescriptionFilm.setVisible(true);
                
                
                 img.setVisible(true);
             
         });
    }

    private void supprimer() {
        filmService.supprimerFilm(film);
        nomFilm.setText("");
        CategorieFilm.setText("");
        DescriptionFilm.setText("");
        buttonPhoto.setText("");
       
         BtnSupprimerFilm.setVisible(false);
       
        BtnModifierFilm.setVisible(false);
        data.clear();
        loadDataFromDatabase();
    }

   /* private void Modifier() {
        film=new Film(nomFilm.getText(),CategorieFilm.getText(),DescriptionFilm.getText(),pathImgArt.getText());
        filmService.modifierFilm(film);
        nomFilm.setText("");
        CategorieFilm.setText("");
        DescriptionFilm.setText("");
       // pathImgArt.setText("");
        data.clear();
        loadDataFromDatabase();
    }*/
    
    private void Modifier() {
        film.setNom_film(nomFilm.getText());
        film.setCategorie_film(CategorieFilm.getText());
        film.setDescription_film(DescriptionFilm.getText());
        film.setImage_film(pathImgArt.getText());
        filmService.modifierFilm(film);
       // nomFilm.setText("");
       // CategorieFilm.setText("");
       // DescriptionFilm.setText("");
      //  pathImgArt.setText("");
        data.clear();
        loadDataFromDatabase();
    }
    
    public void Ajouter(ActionEvent event) throws IOException{
        film= new Film();
        film.setNom_film(nomFilm.getText());
        film.setDescription_film(DescriptionFilm.getText());
        film.setCategorie_film(CategorieFilm.getText());
        //buttonPhoto.setText(film.getImage_film());
        //film.setImage_film(selectedFile.getName());
        film.setImage_film(name);
        filmService.creerFilm(film);
        
        
        nomFilm.setText("");
        CategorieFilm.setText("");
        DescriptionFilm.setText("");
        //buttonPhoto.setText("");

          Pagination p = new Pagination("/fxml/AffichageFilm.fxml");
           ((Node)(event.getSource())).getScene().getWindow().hide();
    
    }
    
    
    
   // FileChooser fc=new FileChooser();
  //  File selectedFile=new File("");
    @FXML
    private void uploadImage(ActionEvent event) throws FileNotFoundException, IOException {
       /* File dest=new File("C:\\wamp64\\www\\upload");
        fc.setInitialDirectory(new File("C:\\wamp64\\www\\image"));
        selectedFile = fc.showOpenDialog(null);
        FileUtils.copyFileToDirectory(selectedFile, dest);
        System.out.println(selectedFile.getName());*/
        
        FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg")
         );
        
            File choix = fileChooser.showOpenDialog(null);
            if (choix != null) {
               
                pathImgArt.setText(choix.getPath());
                copyImages.deplacerVers(pathImgArt.getText(),"C:\\wamp64\\www\\codetest\\web\\uploads\\images\\");
                name=choix.getName();
             } else {
                System.out.println("Image introuvable");
            }
    


    }

    @FXML
    private void exitbtn(MouseEvent event) {
           Stage stage = (Stage) retourBtn.getScene().getWindow();
           stage.close();
    }

    @FXML
    private void retourBtn(MouseEvent event) throws IOException {
          Pagination p = new Pagination("/fxml/Cinema.fxml");
          ((Node)(event.getSource())).getScene().getWindow().hide();
    }
       

   
         
     
    

        
         

    

  
    
}
