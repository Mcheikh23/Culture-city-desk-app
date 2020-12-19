/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Entity;

import java.time.LocalDate;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 *
 * @author Dell
 */
public class Evennement {
    
    
    
    @FXML
    private Button showa;
    
    private int Id_Event;
    private String Descr_Event;
    private String Image_Event;
    private String Titre_Event;
  
    
    private LocalDate DATED_EVENT ;
    private LocalDate DATEF_EVENT ;
    private String EMPLACEMENT;
    private int Id_User;
    
   private String categorie_Event;
    private int nbr_place_E;
    private int nbr_r;
    
    public Evennement() {
    }

   

    public Evennement(int Id_Event, LocalDate DATED_EVENT) {
        this.Id_Event = Id_Event;
        this.DATED_EVENT = DATED_EVENT;
    }

    public Evennement(int Id_Event, String Descr_Event, String Image_Event, String Titre_Event, LocalDate DATED_EVENT, LocalDate DATEF_EVENT, String EMPLACEMENT, String categorie_Event, int nbr_place_E, int nbr_r) {
        this.Id_Event = Id_Event;
        this.Descr_Event = Descr_Event;
        this.Image_Event = Image_Event;
        this.Titre_Event = Titre_Event;
        this.DATED_EVENT = DATED_EVENT;
        this.DATEF_EVENT = DATEF_EVENT;
        this.EMPLACEMENT = EMPLACEMENT;
        this.categorie_Event = categorie_Event;
        this.nbr_place_E = nbr_place_E;
        this.nbr_r = nbr_r;
    }

    public Evennement(int Id_Event, String Descr_Event, String Image_Event, String Titre_Event, LocalDate DATED_EVENT, LocalDate DATEF_EVENT, String EMPLACEMENT, String categorie_Event, int nbr_place_E) {
        this.Id_Event = Id_Event;
        this.Descr_Event = Descr_Event;
        this.Image_Event = Image_Event;
        this.Titre_Event = Titre_Event;
        this.DATED_EVENT = DATED_EVENT;
        this.DATEF_EVENT = DATEF_EVENT;
        this.EMPLACEMENT = EMPLACEMENT;
        this.categorie_Event = categorie_Event;
        this.nbr_place_E = nbr_place_E;
    }
    


    public Evennement(int Id_Event) {
        this.Id_Event = Id_Event;
    }

    public Evennement(String Descr_Event, String Image_Event, String Titre_Event, LocalDate DATED_EVENT, LocalDate DATEF_EVENT, String 	EMPLACEMENT, String categorie_Event, int nbr_place_E) {
        this.Descr_Event = Descr_Event;
        this.Image_Event = Image_Event;
        this.Titre_Event = Titre_Event;
        this.DATED_EVENT = DATED_EVENT;
        this.DATEF_EVENT = DATEF_EVENT;
        this.EMPLACEMENT = EMPLACEMENT;
        this.categorie_Event = categorie_Event;
        this.nbr_place_E = nbr_place_E;
    }

    public Evennement(String Descr_Event, String Image_Event, String Titre_Event, LocalDate DATED_EVENT, LocalDate DATEF_EVENT, String 	EMPLACEMENT, String categorie_Event) {
        this.Descr_Event = Descr_Event;
        this.Image_Event = Image_Event;
        this.Titre_Event = Titre_Event;
        this.DATED_EVENT = DATED_EVENT;
        this.DATEF_EVENT = DATEF_EVENT;
        this.EMPLACEMENT = EMPLACEMENT;
        this.categorie_Event = categorie_Event;
    }

    public Evennement(int Id_Event, String Descr_Event) {
        this.Id_Event = Id_Event;
        this.Descr_Event = Descr_Event;
    }

    
    
    
    
    public Evennement(int Id_Event, int Id_User) {
        this.Id_Event = Id_Event;
        this.Id_User = Id_User;
    }

    public Evennement(String Descr_Event, String Image_Event, String Titre_Event, LocalDate DATED_EVENT, LocalDate DATEF_EVENT, String EMPLACEMENT, int Id_User, String categorie_Event, int nbr_place_E) {
        this.Descr_Event = Descr_Event;
        this.Image_Event = Image_Event;
        this.Titre_Event = Titre_Event;
        this.DATED_EVENT = DATED_EVENT;
        this.DATEF_EVENT = DATEF_EVENT;
        this.EMPLACEMENT = EMPLACEMENT;
        this.Id_User = Id_User;
        this.categorie_Event = categorie_Event;
        this.nbr_place_E = nbr_place_E;
    }
    
    
    
    

    

    public Evennement(String Descr_Event, String Image_Event, String Titre_Event, LocalDate DATED_EVENT, LocalDate DATEF_EVENT, String 	EMPLACEMENT, int Id_User, int Id_Article, String categorie_Event,int nbr_place_E) {
        this.Descr_Event = Descr_Event;
        this.Image_Event = Image_Event;
        this.Titre_Event = Titre_Event;
        this.DATED_EVENT = DATED_EVENT;
        this.DATEF_EVENT = DATEF_EVENT;
        this.EMPLACEMENT = EMPLACEMENT;
        this.Id_User = Id_User;
        
       
        this.categorie_Event = categorie_Event;
    this.nbr_place_E=nbr_place_E;
    }

    public Evennement(Button showa, String Descr_Event, String Image_Event, String Titre_Event, LocalDate DATED_EVENT, LocalDate DATEF_EVENT, String EMPLACEMENT, String categorie_Event, int nbr_place_E) {
        this.showa = showa;
        this.Descr_Event = Descr_Event;
        this.Image_Event = Image_Event;
        this.Titre_Event = Titre_Event;
        this.DATED_EVENT = DATED_EVENT;
        this.DATEF_EVENT = DATEF_EVENT;
        this.EMPLACEMENT = EMPLACEMENT;
        this.categorie_Event = categorie_Event;
        this.nbr_place_E = nbr_place_E;
    }

    public Evennement(Button showa, String Descr_Event, String Image_Event, String Titre_Event, LocalDate DATED_EVENT, LocalDate DATEF_EVENT, String EMPLACEMENT, int Id_User, String categorie_Event, int nbr_place_E, int nbr_r) {
        this.showa = showa;
        this.Descr_Event = Descr_Event;
        this.Image_Event = Image_Event;
        this.Titre_Event = Titre_Event;
        this.DATED_EVENT = DATED_EVENT;
        this.DATEF_EVENT = DATEF_EVENT;
        this.EMPLACEMENT = EMPLACEMENT;
        this.Id_User = Id_User;
        this.categorie_Event = categorie_Event;
        this.nbr_place_E = nbr_place_E;
        this.nbr_r = nbr_r;
    }

    public Evennement(String Descr_Event, String Image_Event, String Titre_Event, LocalDate DATED_EVENT, LocalDate DATEF_EVENT, String EMPLACEMENT, String categorie_Event, int nbr_place_E, int nbr_r) {
        this.Descr_Event = Descr_Event;
        this.Image_Event = Image_Event;
        this.Titre_Event = Titre_Event;
        this.DATED_EVENT = DATED_EVENT;
        this.DATEF_EVENT = DATEF_EVENT;
        this.EMPLACEMENT = EMPLACEMENT;
        this.categorie_Event = categorie_Event;
        this.nbr_place_E = nbr_place_E;
        this.nbr_r = nbr_r;
    }

   

    public int getId_Event() {
        return Id_Event;
    }

    public void setId_Event(int Id_Event) {
        this.Id_Event = Id_Event;
    }

    public String getDescr_Event() {
        return Descr_Event;
    }

    public void setDescr_Event(String Descr_Event) {
        this.Descr_Event = Descr_Event;
    }

    public String getImage_Event() {
        return Image_Event;
    }

    public void setImage_Event(String Image_Event) {
        this.Image_Event = Image_Event;
    }

    public String getTitre_Event() {
        return Titre_Event;
    }

    public void setTitre_Event(String Titre_Event) {
        this.Titre_Event = Titre_Event;
    }

    public LocalDate getDATED_EVENT() {
        return DATED_EVENT;
    }

    public void setDATED_EVENT(LocalDate DATED_EVENT) {
        this.DATED_EVENT = DATED_EVENT;
    }

    public LocalDate getDATEF_EVENT() {
        return DATEF_EVENT;
    }

    public void setDATEF_EVENT(LocalDate DATEF_EVENT) {
        this.DATEF_EVENT = DATEF_EVENT;
    }

    public String getEMPLACEMENT() {
        return 	EMPLACEMENT;
    }

    public void setEMPLACEMENT(String 	EMPLACEMENT) {
        this.EMPLACEMENT = EMPLACEMENT;
    }

    public int getId_User() {
        return Id_User;
    }

    public void setId_User(int Id_User) {
        this.Id_User = Id_User;
    }

    public int getNbr_place_E() {
        return nbr_place_E;
    }

    public void setNbr_place_E(int nbr_place_E) {
        this.nbr_place_E = nbr_place_E;
    }

   
   

    public String getCategorie_Event() {
        return categorie_Event;
    }

    public void setCategorie_Event(String categorie_Event) {
        this.categorie_Event = categorie_Event;
    }

    public Button getShowa() {
        return showa;
    }

    public void setShowa(Button showa) {
        this.showa = showa;
    }

    public int getNbr_r() {
        return nbr_r;
    }

    public void setNbr_r(int nbr_r) {
        this.nbr_r = nbr_r;
    }

    @Override
    public String toString() {
        return "Evennement{" + "showa=" + showa + ", Id_Event=" + Id_Event + ", Descr_Event=" + Descr_Event + ", Image_Event=" + Image_Event + ", Titre_Event=" + Titre_Event + ", DATED_EVENT=" + DATED_EVENT + ", DATEF_EVENT=" + DATEF_EVENT + ", EMPLACEMENT=" + EMPLACEMENT + ", Id_User=" + Id_User + ", categorie_Event=" + categorie_Event + ", nbr_place_E=" + nbr_place_E + ", nbr_r=" + nbr_r + '}';
    }

   


  

   
   
    
    
    
    
    
    
}
