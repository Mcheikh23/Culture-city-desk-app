/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entity.Article;
import Entity.Commande;
import Entity.LigneCommande;
import Iservice.ICommande;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.ConnexionBD;

/**
 *
 * @author PC
 */
public class CommandeService  implements ICommande{
     Connection C = ConnexionBD.getInstancebd().getConnexionBD();
    Statement ste;
     public CommandeService() {
          try {
            ste = C.createStatement();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
   

    @Override
    public void creerCommande(Commande cm) {
        Article a =null;
       
        
           try{
           
       
         String sql1="INSERT INTO `commande`( `datecommande`, `Montant`, `iduser`) VALUES ( '"+cm.getDate()+"',"+"'"+cm.getMontant()+"','"+3+"');" ;             
             ste.executeUpdate(sql1);
        System.out.println("insertion effectué avec succée");
       
       
        
        }   
  
            catch (SQLException ex) {
            Logger.getLogger(CatégorieService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
       
    }

    @Override
    public void modifierCommande(Commande cm) {
        
        
         String req2="UPDATE `commande` SET `datecommande`=? ,`montant`=? WHERE idcommande="+cm.getIdCM();
    PreparedStatement ps;
     Date date1 = Date.from(cm.getDate().atStartOfDay(ZoneId.systemDefault()).toInstant());
    try {
                ps = C.prepareStatement(req2);
                ps.setDate(1, cm.convertUtilToSql(date1));
                ps.setFloat(2, cm.getMontant());
                ps.executeUpdate();
                int rowsUpdated=ps.executeUpdate();
                if (rowsUpdated>0){
                    System.out.println("La modification de la commande"+cm.getIdCM()+" a été éffectué avec succée ");
                }}
            
            
            catch (SQLException ex) {
                Logger.getLogger(CatégorieService.class.getName()).log(Level.SEVERE, null, ex);
            }
      
    }

    @Override
    public void supperimerCommande(Commande cm) {
         try {
                String req1="delete from commande where"
                        + " idcommande=?";
                
                PreparedStatement ps = C.prepareStatement(req1);
                ps.setInt(1, cm.getIdCM());
                ps.executeUpdate();
                System.out.println("L'annulation de la commande a été effectué avec succée");
                
                
            } catch (SQLException ex) {
                Logger.getLogger(CatégorieService.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    @Override
    public List<Commande> afficherCommande() {
            List<Commande> Commandes = new ArrayList<>();
            Commande cm = null ;
            String req2="select * from commande";
            try {
                
                
                ResultSet res=  ste.executeQuery(req2);
                while (res.next()) {
                    cm=new Commande();
                    cm.setIdCM( res.getInt("idcommande"));
                    Date d1=res.getDate("datecommande");
                    Instant instant = Instant.ofEpochMilli(d1.getTime());
                     LocalDate date = instant.atZone(ZoneId.systemDefault()).toLocalDate();
                      System.out.println(date);
                       cm.setDate(date);
                         cm.setMontant (res.getInt("montant"));
                    Commandes.add(cm);
                }
                
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
                        
            return Commandes;

        
        
    }
     /* public void updateQte(Article a){
      
        LigneCommande l=null;
      int q=0;
        
    
        try {
            String req1="Select quantite from lignecommande where idarticle="+a.getIdA();
            ResultSet res=ste.executeQuery(req1);
            while (res.next()){
                System.out.println("la quantité de ligne commande "+res.getInt("quantite"));
              String req2="select quantitearticle from article where idarticle="+a.getIdA();
            ResultSet res2=ste.executeQuery(req2);
            while (res2.next()){
                 System.out.println("la quantité de l'article "+res2.getInt("quantitearticle"));
                 q=res2.getInt("quantitearticle")-res.getInt("quantite");
                 System.out.println("la quantité est"+q);
            String req3="UPDATE `article` SET  `quantitearticle` =? WHERE `idarticle`="+a.getIdA();
            PreparedStatement ps;
        
         
               ps = C.prepareStatement(req3);
            ps.setInt(1,q);
            ps.executeUpdate();
            int rowsUpdated=ps.executeUpdate();
            if (rowsUpdated>0){
            System.out.println("La modification de l'article " +a.getIdA()+" a été éffectué avec succée ");
            }}}}
        catch (SQLException ex) {
            Logger.getLogger(CatégorieService.class.getName()).log(Level.SEVERE, null, ex);
        }
    
   
          
           }
         public void calcul(Commande c) {
       LigneCommande l=null;
       int prix=0;
        
        try{
    String req="select `idarticle` from lignecommande where idcommande="+c.getIdCM();
     ResultSet res=  ste.executeQuery(req);
     while (res.next()){
     
     String req1="select `prixarticle` from article where idarticle="+res.getInt("idarticle");
            ResultSet res1=  ste.executeQuery(req1);
             
       
            while(res1.next()){
                prix+=res1.getInt("prixarticle");
                
           
            
             }}
      System.out.println("le montant est"+prix);
        } catch (SQLException ex) {
            Logger.getLogger(CatégorieService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
   
        
       
    
}*/}
