/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entity.categoriec;
import Entity.club;
import Iservice.IclubService;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.chart.XYChart;
import utils.ConnexionBD;

/**
 *
 * @author user
 */
public class ClubService implements IclubService {
    CategoriecService categoriecService = new CategoriecService ();
   
    Connection c = ConnexionBD
           .getInstancebd()
           .getConnexionBD();
    Statement ste;
            private static ClubService instance;
   
    
       public static ClubService getInstance(){
        if(instance==null) 
            instance=new ClubService();
        return instance;}

    public ClubService() {
        try {
            ste = c.createStatement();
        } catch (SQLException ex) {
            System.out.println(ex);
        }}

    @Override
    public void creerClub(club Cl) {
           try {
            String req1c;
                req1c = "INSERT INTO `club` "
                        + "(`nom_club`, `description_club`, `logo_club`,`id_categorieC`,`nbr_participants`) "
                        + "VALUES ( '"
                        +Cl.getNom_club()+"' ,'"+Cl.getDescription_club()+"', '"+Cl.getLogo_club()+"', "+Cl.getCategoriec().getId_categorieC()+","+Cl.getNbr_participants()+");";
            
            
         
            ste.executeUpdate(req1c);
        } catch (SQLException ex) {
            Logger.getLogger(ClubService.class.getName()).log(Level.SEVERE, null, ex);
    }
    }

    @Override
    public void modifierClub(club Cl) {
        String req2c = "UPDATE club SET  nom_club=?, description_club=?, logo_club=?  where id_club="+Cl.getId_club();
 
        PreparedStatement ps;
        try {
            ps = c.prepareStatement(req2c);
        
         ps.setString(1, Cl.getNom_club());
         ps.setString(2, Cl.getDescription_club());
        ps.setString(3, Cl.getLogo_club());
                      



        int rowsUpdated = ps.executeUpdate();
          if (rowsUpdated > 0) {
            System.out.println("Le club a été modifier avec succès");
          }
          } catch (SQLException e) {
                   System.out.println("Erreur"+e.getMessage());

           }
    }

    @Override
    public void supprimerClub(club Cl) {
       String req3c="delete from Club where id_club=?";
                    PreparedStatement preparedStatement;

        try{
                       preparedStatement = c.prepareStatement(req3c);

           preparedStatement.setInt(1, Cl.getId_club());
           preparedStatement.executeUpdate();
        }catch(SQLException er){
            System.out.println("delete erreur club");
        }
    }

    @Override
    public List<club> afficherClub() {
         ArrayList<club> listClub = new ArrayList<>();
        categoriec Categoriec ;
        
        try
        {
            Statement ps = c.createStatement();
            ResultSet res;
            
            res = ps.executeQuery("select * from club");
            while(res.next())
            {
                int id_club = res.getInt("id_club");
                String nom_club=res.getString("nom_club");
                String description_club=res.getString("description_club"); 
                String logo_club=res.getString("logo_club");
                
            
                
           

                 categoriec CategorieC= new categoriec();
                
                int id_categoriec=res.getInt("id_categoriec");
                CategoriecService categoriecService = new CategoriecService();
                Categoriec = categoriecService.RechercherCategorieCById(id_categoriec);
                int nbr_participants=res.getInt("nbr_participants");
 
                club Club = new club(id_club, nom_club, description_club, logo_club,Categoriec,nbr_participants); 
             listClub.add(Club);
                
            }
         
        }
          catch(Exception e)
        {
           System.out.println("Erreur d'affichage"+e.getMessage());
        } 
        return listClub;  
    }

    @Override
    public club rechercheClubParNom(String nom_club1) {
        categoriec Categoriec ;
           
        

        try { 
            Statement ps=c.createStatement();
            ResultSet res;
            
            res=ps.executeQuery("select * from 	club where nom_club like '%"+nom_club1+"%' ");
            while(res.next())
            {
             int id_club = res.getInt("id_club");
                String nom_club=res.getString("nom_club");
                String description_club=res.getString("description_club"); 
                String logo_club=res.getString("logo_club");
                
                
                
                
                
                int id_categorieC=res.getInt("id_categorieC");
                Categoriec = categoriecService.RechercherCategorieCById(id_categorieC);
                 int nbr_participants=res.getInt("nbr_participants");
                
               
               
              
                System.out.println("Club trouvé  ");
                return  new  club(id_club,nom_club,description_club,logo_club,Categoriec);   
                
            }
            
        } catch (SQLException ex) {
            System.out.println(""+ex.getMessage());
        }
        System.out.println("Club non trouvé ");
        return null;
    }

    @Override
    public club RechercherClubCById(int id_club) {
        club Club = new club ();
          categoriec Categoriec ;
        
            try
        {
            Statement ps = c.createStatement();
            ResultSet res;
            
            res=ps.executeQuery("select * from 	club where id_club="+id_club);
            while(res.next())
            {
                int id_club1 = res.getInt("id_club");
                String nom_club=res.getString("nom_club");
                String description_club=res.getString("description_club"); 
                String logo_club=res.getString("logo_club");
                int id_categorieC=res.getInt("id_categorieC");
                Categoriec = categoriecService.RechercherCategorieCById(id_categorieC);
                int nbr_participants=res.getInt("nbr_participants");
               
               
              
                System.out.println("Club trouvé  ");
                return  new  club(id_club,nom_club,description_club,logo_club,Categoriec);   
                
            }
            
        } catch (SQLException ex) {
            System.out.println(""+ex.getMessage());
        }
        System.out.println("Club non trouvé ");
        return null;
    }
    
         public  XYChart.Series<String, Integer>  topclub() {
         XYChart.Series<String, Integer> series = new XYChart.Series<String, Integer>();
          try {
             
              String req="SELECT nom_club,nbr_participants  FROM club ";
              Statement ste=c.createStatement();
              ResultSet res=ste.executeQuery(req);
               while (res.next()){
                series.getData().add(new XYChart.Data<>(res.getString(1), res.getInt(2)));
            }
               
             
          } catch (SQLException ex) {
              Logger.getLogger(ClubService.class.getName()).log(Level.SEVERE, null, ex);
          }
       return series;
    }
    }
    

