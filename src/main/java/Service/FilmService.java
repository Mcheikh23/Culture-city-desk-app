package Service;

import Entity.Film;
import Iservice.IFilmService;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.ConnexionBD;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author PC
 */
public class FilmService implements IFilmService{

Connection c = ConnexionBD.getInstancebd().getConnexionBD();
    Statement ste;

    public FilmService() {
          try {
            ste = c.createStatement();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
   public static FilmService instance;
   public static FilmService getInstance(){
       if (instance==null)
           instance=new FilmService();
       return instance;
   }
   
@Override
    public void creerFilm(Film f) {
        try {
            String req1="INSERT INTO `film` "
                    + "(`nom_film`, `description_film`, `categorie_film`, `image_film` ) "
                    + "VALUES ( '"+f.getNom_film()+"', '"
                    +f.getDescription_film()+"', '"
                    +f.getCategorie_film()+"', '"
                    +f.getImage_film()+"');";
            
            
         
            ste.executeUpdate(req1);
        } catch (SQLException ex) {
            Logger.getLogger(FilmService.class.getName()).log(Level.SEVERE, null, ex);
        }

        
        
        
    }

    public void modifierFilm(Film f) {
       
           
    
String sql = "UPDATE film SET nom_film=?, description_film=?, categorie_film=?, image_film=? where id_film="+f.getId_film();
 
        PreparedStatement ps;
        try {
        ps = c.prepareStatement(sql);
        ps.setString(1, f.getNom_film());
        ps.setString(2, f.getDescription_film());
        ps.setString(3, f.getCategorie_film());
        ps.setString(4, f.getImage_film());
        int rowsUpdated = ps.executeUpdate();
          if (rowsUpdated > 0) {
            System.out.println("Le film a été modifier avec succès");
          }
          } catch (SQLException e) {
      System.out.println("Erreur"+e.getMessage());
 
          }
    }
    
@Override
    public void supprimerFilm(Film f) {
        try {
            String req1="delete from film where"
                    + " id_film=?";
       
      PreparedStatement ps = c.prepareStatement(req1);
            ps.setInt(1, f.getId_film());
            ps.executeUpdate();
         
           
        } catch (SQLException ex) {
            Logger.getLogger(FilmService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

@Override
    public List<Film> afficherFilm() {
     
      List<Film> Film = new ArrayList<>();
      Film f = null ;
      String req2="select * from film";
      try {
         
         
          ResultSet res=  ste.executeQuery(req2);
          while (res.next()) { 
                      f = new Film();
                      f.setId_film(res.getInt("id_film"));
                      f.setNom_film(res.getString("nom_film") );
                      f.setDescription_film(res.getString("description_film"));
                      f.setCategorie_film(res.getString("categorie_film"));
                      f.setImage_film(res.getString("image_film"));
                      Film.add(f);
          }
          
      } catch (SQLException ex) {
          System.out.println(ex.getMessage());
      } 
        
     return Film;
    }
    
   public Film rechercheFilmParNom(String nom_film1) {
   
           
        

        try { 
            Statement ps=c.createStatement();
            ResultSet res;
            
            res=ps.executeQuery("select * from 	film where nom_FILM like '%"+nom_film1+"%' ");
            while(res.next())
            {
             int id_film = res.getInt("id_film");
                String nom_film=res.getString("nom_film");
                String description_film=res.getString("description_film"); 
                String categorie_film=res.getString("categorie_film");
                String image_film=res.getString("image_film");
                
                
                
              
           
               
               
              
                System.out.println("film trouvé  ");
                return  new  Film(id_film,nom_film,description_film,categorie_film,image_film);   
                
            }
            
        } catch (SQLException ex) {
            System.out.println(""+ex.getMessage());
        }
        System.out.println("film non trouvé ");
        return null;
    }
    


    public Film rechercherFilmCById(int id_film) {
      Film Film = new Film ();
        
            try
        {
            Statement ps = c.createStatement();
            ResultSet res;
            
            res=ps.executeQuery("select * from 	film where id_film="+id_film);
            while(res.next())
            {
                int id_film1= res.getInt("id_film");
                String nom_film=res.getString("nom_film");
                String description_film=res.getString("description_film");
                String categorie_film=res.getString("categorie_film");
                String image_film=res.getString("image_film");
                System.out.println("film trouvé  ");
                return  new  Film(id_film,nom_film,description_film,categorie_film,image_film);   
             }
         
          
        }catch(SQLException ex)
        {
           System.out.println("Introuvable"+ex.getMessage());
        } 
                        System.out.println("film non trouvé  ");  
 
        return null;
           
    }
    
    
}