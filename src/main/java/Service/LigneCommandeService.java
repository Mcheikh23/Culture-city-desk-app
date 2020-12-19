/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entity.Commande;
import Entity.LigneCommande;
import Iservice.ILigneCommande;
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
 * @author PC
 */
public class LigneCommandeService  implements ILigneCommande{
    Connection C = ConnexionBD.getInstancebd().getConnexionBD();
    Statement ste;
     public  LigneCommandeService() {
          try {
            ste = C.createStatement();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
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
        }return false;
}

    @Override
    public void creerLigneCommande(LigneCommande l) {
      
         try {
             
         
            String req1="INSERT INTO `lignecommande` "
                    + "(`idarticle`,`quantite`,`iduser`) "
                    + "VALUES ( '"+l.getIdarticle()+"','"+l.getQte()+"','"+3+"');";   
            ste.executeUpdate(req1);
            System.out.println("L'ajout  de la ligne de commande a été effectué avec succée");
        } catch (SQLException ex) {
            Logger.getLogger(CatégorieService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void modifierLigneCommande(LigneCommande l) {
    String req="UPDATE `lignecommande` SET `quantite`=? where idarticle="+l.getIdarticle();
       
         PreparedStatement ps;
    try {
                ps = C.prepareStatement(req);
                
               
                ps.setInt(1, l.getQte());
                ps.executeUpdate();
                int rowsUpdated=ps.executeUpdate();
                if (rowsUpdated>0){
                    System.out.println("La modification de la commande a été éffectué avec succée ");
                }}
            
            
            catch (SQLException ex) {
                Logger.getLogger(CatégorieService.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    @Override
    public void supperimerLigneCommande(LigneCommande l) {
     try {
                String req1="delete from lignecommande where"
                        + " idarticle=?";
                
                PreparedStatement ps = C.prepareStatement(req1);
                ps.setInt(1, l.getIdarticle());
                ps.executeUpdate();
                System.out.println("L'annulation de la commande a été effectué avec succée");
                
                
            } catch (SQLException ex) {
                Logger.getLogger(CatégorieService.class.getName()).log(Level.SEVERE, null, ex);
            }
            
    }

    @Override
    public List<LigneCommande> afficherLigneCommande() {
         List<LigneCommande> lignes = new ArrayList<>();
            LigneCommande lc= null ;
            String req2="select * from lignecommande ";
            try {
                
                
                ResultSet res=  ste.executeQuery(req2);
                while (res.next()) {
                    lc=new LigneCommande();
                   
                    lc.setIdarticle(res.getInt("idarticle") );
                    lc.setQte(res.getInt("quantite"));
                   lignes.add(lc);
                }
                
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
            
            return lignes;
       
        
        
       
    }
          
         public int calcul(LigneCommande l) {
       
       int prix=0;
       int prixtotal=0;
        
        try{
    String req="select `idarticle`,`quantite`  from lignecommande ";
     ResultSet res=  ste.executeQuery(req);
     while (res.next()){
     
     String req1="select `prixarticle` from article where idarticle="+res.getInt("idarticle");
            ResultSet res1=  ste.executeQuery(req1);
             
       
            while(res1.next()){
                prix+=res1.getInt("prixarticle")*res.getInt("quantite");
                
           
            
             }
          //  prixtotal=prix*l.getQte();
     }
      System.out.println("le montant est"+prix);
        } catch (SQLException ex) {
            Logger.getLogger(CatégorieService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return prix;
        
    
}
         /*public  XYChart.Series<String, Integer>  toparticle() {
         XYChart.Series<String, Integer> series = new XYChart.Series<String, Integer>();
          try {
             
              String req="SELECT Sum(quantite) as qqq,nomarticle FROM ligne_commande l inner join article a on a.idarticle=l.idarticle GROUP BY(idarticle) order by qqq desc limit 5";
              Statement ste=C.createStatement();
              ResultSet res=ste.executeQuery(req);
               while (res.next()){
                series.getData().add(new XYChart.Data<>(res.getString("nomarticle"), res.getInt("qqq")));
            }
               
             
          } catch (SQLException ex) {
              Logger.getLogger(ArticleService.class.getName()).log(Level.SEVERE, null, ex);
          }
       return series;
    }*/



}
