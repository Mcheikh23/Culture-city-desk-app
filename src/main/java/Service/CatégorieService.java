/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entity.Article;
import Entity.Catégorie;

import Entity.Commande;
import Entity.LigneCommande;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Instant;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.ConnexionBD;
import java.time.LocalDate;
import Iservice.ICatégorieaService;

/**
 *
 * @author PC
 */
public class CatégorieService implements ICatégorieaService {
    Connection C = ConnexionBD.getInstancebd().getConnexionBD();
    Statement ste;
     public CatégorieService() {
          try {
            ste = C.createStatement();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
     private static CatégorieService instance;
   
    
       public static CatégorieService getInstance(){
        if(instance==null) 
            instance=new CatégorieService();
        return instance;
    }

    @Override
    public void creerCatégorie(Catégorie c) {
       try {
            String req1="INSERT INTO `categoriea` "
                    + "(`nomcategorie`) "
                    + "VALUES ( '"+c.getNomC()+"');";   
            ste.executeUpdate(req1);
            System.out.println("L'ajout  de la catégorie a été effectué avec succée");
        } catch (SQLException ex) {
            Logger.getLogger(CatégorieService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    

    @Override
    public void modifierCatégorie(Catégorie c) {
       String req2="UPDATE `categoriea` SET `nomcategorie`=? WHERE idcategorie="+c.getId();
       PreparedStatement ps;
        try {
            ps = C.prepareStatement(req2);
            ps.setString(1, c.getNomC());
              ps.executeUpdate();
            int rowsUpdated=ps.executeUpdate();
            if (rowsUpdated>0){
            System.out.println("La modification de la catégorie"+c.getId()+" a été éffectué avec succée ");
            }}
        catch (SQLException ex) {
            Logger.getLogger(CatégorieService.class.getName()).log(Level.SEVERE, null, ex);
        }}
            
    @Override
    public void supperimerCatégorie(Catégorie c) {
         try {
            String req1="delete from categoriea where"
                    + " idcategorie=?";
       
      PreparedStatement ps = C.prepareStatement(req1);
            ps.setInt(1, c.getId());
            ps.executeUpdate();
            System.out.println("La supression a été effectué avec succée");
         
           
        } catch (SQLException ex) {
            Logger.getLogger(CatégorieService.class.getName()).log(Level.SEVERE, null, ex);
        }

        

    }

    @Override
    public List<Catégorie> afficherCatégorie() {
          List<Catégorie> Catégories = new ArrayList<>();
      Catégorie p = null ;
      String req2="select * from Categoriea";
      try {
         
         
          ResultSet res=  ste.executeQuery(req2);
          while (res.next()) { 
             p=new Catégorie();
                      p.setId( res.getInt("idcategorie"));
                      p.setNomC( res.getString("nomcategorie") );
                       
                    
              Catégories.add(p);
          }
          
      } catch (SQLException ex) {
          System.out.println(ex.getMessage());
      } 
        
     return Catégories;
    }    
     
    public boolean RechercheCatégorie(String nom) {
        boolean test=false;
           String req = "SELECT * from categoriea where nom='"+nom+"'";
            
     
        try {
           
            ResultSet stp=ste.executeQuery(req);
            stp.last();
            if(stp.getRow()!=0)
            {
                test=true;
                System.out.println("categorie trouveé");
            }
            else
            {
                test=false;
                System.out.println("categorie non trouveé");
            }
        }
            catch (SQLException ex) {
            Logger.getLogger(CatégorieService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return test;
        
    }
                

 
    
    
  
     
      
       
      
       
    
    public boolean verif(int qte) {
       
        boolean test=false;
       
        try{
         String req2="select quantitearticle from article";
          ResultSet res=  ste.executeQuery(req2);
          while (res.next()) {
              if ((res.getInt("quantitearticle"))> qte){
              
              test=true;
              }
              else{
             test= false;}}
          } catch (SQLException ex) {
            Logger.getLogger(CatégorieService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    
    return test;
    }
     public Catégorie rechercheCatégorieArtByNom (String Nomcatart) {
           
          
        try { 
            Statement ps=C.createStatement();
            ResultSet res;
            
            res=ps.executeQuery("select * from 	categoriea where nomcategorie like '%"+Nomcatart+"%' ");
            while(res.next())
            {
                int idcatart = res.getInt("idcategorie");
                String NomCatart=res.getString("nomcategorie");
              
              // System.out.println("L'id est "+idcatart+" nom"+NomCatart);
               
                
                return  new Catégorie(idcatart, NomCatart); 
                
                
            }
        } catch (SQLException ex) {
            System.out.println(""+ex.getMessage());
        }
        return null;
}
   public List<Catégorie> rechercherNomcat(String Nom) {
         ArrayList<Catégorie> listeArticle = new ArrayList<Catégorie>();
        
        try
        {
            Statement ps = C.createStatement();
            ResultSet res;
            
            res = ps.executeQuery("select * from categoriea where nomcategorie like '%"+Nom+"%'");
            while(res.next())
            {
                int id = res.getInt("idcategorie");
                String nom=res.getString("nomcategorie");
               
                Catégorie cartt = new Catégorie(id,nom);
                listeArticle.add(cartt);
            }
         
        }
          catch(SQLException e)
        {
           System.out.println("Erreur d'affichage "+e.getMessage());
        } 
        return listeArticle;
    }}
  
    
  

  
    
         
            
            
         
            
          
                
        
            
            
            
           
          
            
                
                
           

    
         
                      
      

    
