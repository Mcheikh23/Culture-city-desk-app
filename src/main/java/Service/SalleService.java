package Service;

import Entity.Film;
import Entity.Salle;
import Iservice.ISalleService;
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
public class SalleService implements ISalleService{

Connection c = ConnexionBD.getInstancebd().getConnexionBD();
Statement ste;
    PreparedStatement pst;
    public SalleService() {
          try {
            ste = c.createStatement();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
   
@Override
    public void creerSalle(Salle s) {
        try {
            String req1="INSERT INTO `salle` "
                    + "(`nombrePlace_salle`, `nom_salle` ) "
                    + "VALUES ( '"+s.getNombrePlace_salle()+"', '"
                    +s.getNom_salle()+"');";
            
            
         
            ste.executeUpdate(req1);
        } catch (SQLException ex) {
            Logger.getLogger(FilmService.class.getName()).log(Level.SEVERE, null, ex);
        }

        
        
        
    }

    public void modifierSalle(Salle s,String num) {
       
           
    
String sql = "UPDATE salle SET nombrePlace_salle=?, nom_salle=?  where nom_salle='"+num+"'";
 
        PreparedStatement ps;
        try {
            ps = c.prepareStatement(sql);
        
        
        ps.setInt(1, s.getNombrePlace_salle());
         ps.setString(2, s.getNom_salle());

        int rowsUpdated = ps.executeUpdate();
       //   if (rowsUpdated > 0) {
         //   System.out.println("La salle a été modifier avec succès");
          //}
          } catch (SQLException e) {
                     System.out.println("Erreur"+e.getMessage());
 
          }
    }

@Override
    public void supprimerSalle(Salle s) {
        try {
            String req1="delete from salle where"
                    + " numero_salle=?";
       
      PreparedStatement ps = c.prepareStatement(req1);
            ps.setInt(1, s.getNumero_salle());
            ps.executeUpdate();
         
           
        } catch (SQLException ex) {
            Logger.getLogger(FilmService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public List<Salle> afficherSalle() {
      
      List<Salle> Salle = new ArrayList<>();
      Salle s = null ;
      String req2="select * from salle";
      try {
         
         
          ResultSet res=  ste.executeQuery(req2);
          while (res.next()) { 
              s = new Salle();
                      s.setNumero_salle(res.getInt("numero_salle"));
                      s.setNombrePlace_salle(res.getInt("nombrePlace_salle") );
                  s.setNom_salle(res.getString("nom_salle") );
              Salle.add(s);
          }
          
      } catch (SQLException ex) {
          System.out.println(ex.getMessage());
      } 
        
     return Salle;
    }
    
      public Salle rechercheSalleParNom(String nom_salle1) {
   
           
        

        try { 
            Statement ps=c.createStatement();
            ResultSet res;
            
            res=ps.executeQuery("select * from 	salle where nom_salle like '%"+nom_salle1+"%' ");
            while(res.next())
            {
             int numero_salle = res.getInt("numero_salle");
                String nom_salle=res.getString("nom_salle");
                int nombrePlace_salle=res.getInt("nombrePlace_salle"); 
             
                
                
                
              
           
               
               
              
                System.out.println("salle trouvé  ");
                return  new  Salle(numero_salle,nombrePlace_salle,nom_salle);   
                
            }
            
        } catch (SQLException ex) {
            System.out.println(""+ex.getMessage());
        }
        System.out.println("salle non trouvé ");
        return null;
    }
      
      
      
        public Salle rechercherSalleCById(int numero_salle) {
      Salle Salle = new Salle ();
        
            try
        {
            Statement ps = c.createStatement();
            ResultSet res;
            
            res=ps.executeQuery("select * from 	salle where numero_salle="+numero_salle);
            while(res.next())
            {
               // int id_film1= res.getInt("id_film");
                
                int nombrePlace_salle=res.getInt("nombrePlace_salle");
                String nom_salle=res.getString("nom_salle");
                System.out.println("salle trouvé  ");
                return  new  Salle(numero_salle ,nombrePlace_salle, nom_salle);   
             }
         
          
        }catch(SQLException ex)
        {
           System.out.println("Introuvable"+ex.getMessage());
        } 
                        System.out.println("film non trouvé  ");  
 
        return null;
           
    }
    
    

}