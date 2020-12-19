/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entity.categoriec;
import Iservice.IcategoriecService;
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

/**
 *
 * @author user
 */
public class CategoriecService implements IcategoriecService {
    Connection c = ConnexionBD
           .getInstancebd()
           .getConnexionBD();
    Statement ste;
     private static CategoriecService instance;
   
    
       public static CategoriecService getInstance(){
        if(instance==null) 
            instance=new CategoriecService();
        return instance;}


    public CategoriecService() {
        try {
            ste = c.createStatement();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    @Override
    public void creerCategoriec(categoriec Categoriec) {
         try {
      String req1c="INSERT INTO `categoriec` "
                    + "(`nom_categorieC`)"
                    + "VALUES ( '"+Categoriec.getNom_categorieC()+"');";
            
            
         
            ste.executeUpdate(req1c);
        } catch (SQLException ex) {
            Logger.getLogger(CategoriecService.class.getName()).log(Level.SEVERE, null, ex);
    }

    }

    @Override
    public void modifierCategoriec(categoriec Categoriec) {
         String sql = "UPDATE categoriec SET nom_categorieC=?  where id_categorieC="+Categoriec.getId_categorieC();
 
        PreparedStatement ps;
        try {
            ps = c.prepareStatement(sql);
        
        
        ps.setString(1, Categoriec.getNom_categorieC());

        int rowsUpdated = ps.executeUpdate();
          if (rowsUpdated > 0) {
            System.out.println("La categorie a été modifier avec succès");
          }
          } catch (SQLException e) {
                     System.out.println("Erreur"+e.getMessage());
 
          }
    }

    @Override
    public void supprimerCategoriec(categoriec Categoriec) {
         try {
            String req3c="delete from categoriec where"
                    + " id_categorieC=?";
       
      PreparedStatement ct = c.prepareStatement(req3c);
            ct.setInt(1,Categoriec.getId_categorieC());
            ct.executeUpdate();
         
           
        } catch (SQLException ex) {
            Logger.getLogger(CategoriecService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<categoriec> afficherCategoriec() {
          List<categoriec> Categoriesc = new ArrayList<>();
      categoriec Categoriec = null ;
      String req4c="select * from Categoriec";
      try {
         
         
          ResultSet res=  ste.executeQuery(req4c);
          while (res.next()) { 
              Categoriec = new categoriec();
                      Categoriec.setId_categorieC( res.getInt("id_categorieC"));
                      Categoriec.setNom_categorieC( res.getString("nom_categorieC") );
                      
              Categoriesc.add(Categoriec);
          }
          
      } catch (SQLException ex) {
          System.out.println(ex.getMessage());
      } 
        
     return Categoriesc;
    }

    @Override
    public categoriec RechercherCategorieCById(int id_categorieC) {
          categoriec Categoriec = new categoriec ();
        
            try
        {
            Statement ps = c.createStatement();
            ResultSet res;
            
            res=ps.executeQuery("select * from 	categoriec where id_categorieC="+id_categorieC);
            while(res.next())
            {
                int id_categorieCt= res.getInt("id_categorieC");
                String nom_categorieC=res.getString("nom_categorieC");
                Categoriec.setId_categorieC(id_categorieC);
                Categoriec.setNom_categorieC(nom_categorieC);
                
             }
            
            return Categoriec;
        }catch(SQLException ex)
        {
          System.out.println("Introuvable"+ex.getMessage());
        
        } 
            
        return null;
           
    }
    @Override
    public categoriec RechercherCategorieCByNom(String nom_categorieC1) {
         categoriec Categoriec= new categoriec();
         try { 
            Statement ps=c.createStatement();
            ResultSet res;
            
            res=ps.executeQuery("select * from 	categoriec where nom_categorieC like '%"+nom_categorieC1+"%' ");
            while(res.next())
            {
                int id_categorieC = res.getInt("id_categorieC");
                String nom_categorieC=res.getString("nom_categorieC");
                 Categoriec.setId_categorieC(id_categorieC);
                Categoriec.setNom_categorieC(nom_categorieC);
               
            }
        }catch(Exception e)
        {
           System.out.println(""+e.getMessage());
        } 
        return Categoriec;
    }
    
}
