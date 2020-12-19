/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entity.Article;
import Entity.Catégorie;
import Iservice.IArticle;
import java.sql.Connection;
import java.sql.Date;
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
public class ArticleService implements IArticle {
      Connection C = ConnexionBD.getInstancebd().getConnexionBD();
    Statement ste;
     public ArticleService() {
          try {
            ste = C.createStatement();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void creerArticle(Article a) {
      try{
      
   String req4="INSERT INTO `article` "
    + "( `nomarticle`,`prixarticle`, `quantitearticle`, `imagearticle`, `idcategorie`) "
    + "VALUES ( '"+a.getNomA()+"',"+"'"+a.getPrix()+"','"+a.getQte()+"','"+a.getImg()+"',"+a.getIdC().getId()+");"; 
                        ste.executeUpdate(req4); 
          System.out.println("L'article a été ajouté avec succée! ");
          
          
          
    }   catch (SQLException ex) {
            Logger.getLogger(CatégorieService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void modifierArticle(Article a) {
         String req3="UPDATE `article` SET `nomarticle`=?,`prixarticle`=?,`quantitearticle`=?,`imagearticle`=? WHERE idarticle="+a.getIdA();
   PreparedStatement ps;
        try {
            ps = C.prepareStatement(req3);
            ps.setString(1, a.getNomA());
             
              ps.setFloat(2, a.getPrix());
               ps.setInt(3, a.getQte());
                ps.setString(4, a.getImg());
                   
            ps.executeUpdate();
            int rowsUpdated=ps.executeUpdate();
            if (rowsUpdated>0){
            System.out.println("La modification de l'article " +a.getIdA()+" a été éffectué avec succée ");
            }}
        catch (SQLException ex) {
            Logger.getLogger(CatégorieService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void supperimerArticle(Article a) {
          try {
            String req1="delete from article where"
                    + " idarticle=?";
       
      PreparedStatement ps = C.prepareStatement(req1);
            ps.setInt(1, a.getIdA());
            ps.executeUpdate();
            System.out.println("La suppression a été effectué avec succée");
         
           
        } catch (SQLException ex) {
            Logger.getLogger(CatégorieService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Article> afficherArticle() {
         List<Article> Articles = new ArrayList<>();
      Article a1 = null ;
      String req2="select * from article";
      try {
         
         
          ResultSet res=  ste.executeQuery(req2);
          while (res.next()) { 
             a1=new Article();
              a1.setIdA( res.getInt("idarticle"));
                      a1.setNomA( res.getString("nomarticle") );
                       
                         a1.setPrix( res.getInt("prixarticle"));
                      a1.setQte( res.getInt("quantitearticle") );
                        a1.setImg( res.getString("imagearticle") );
                      
                   
                    
              Articles.add(a1);
          }
          
      } catch (SQLException ex) {
          System.out.println(ex.getMessage());
      } 
        
     return Articles;
    }
          public boolean RechercheArticle(String nom) {
         boolean test=false;
           String req = "SELECT * from article where nom_art='"+nom+"'";
            
     
        try {
           
            ResultSet stp=ste.executeQuery(req);
            stp.last();
            if(stp.getRow()!=0)
            {
                test=true;
                System.out.println("Article trouveé");
            }
            else
            {
                test=false;
                System.out.println("Article non trouveé");
            }
        }
            catch (SQLException ex) {
            Logger.getLogger(CatégorieService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return test;}
            public List<Article> rechercherArticle(String Nom) {
         ArrayList<Article> listeArticle = new ArrayList<Article>();
        
        try
        {
            Statement ps = C.createStatement();
            ResultSet res;
            
            res = ps.executeQuery("select * from article where nomarticle like '%"+Nom+"%'");
            while(res.next())
            {
                int id = res.getInt("idarticle");
                String nom=res.getString("nomarticle");
                float prix  = res.getFloat("prixarticle");
                int qte = res.getInt("quantitearticle"); 
                String img=res.getString("imagearticle");
                Article art = new Article(id,nom , prix,qte ,img);
                listeArticle.add(art);
            }
         
        }
          catch(SQLException e)
        {
           System.out.println("Erreur d'affichage "+e.getMessage());
        } 
        return listeArticle;
    }
              public  XYChart.Series<String, Integer>  toparticle() {
         XYChart.Series<String, Integer> series = new XYChart.Series<String, Integer>();
          try {
             
              String req="SELECT Sum(quantite) as qqq,nomarticle FROM lignecommande l inner join article a on a.idarticle=l.idarticle GROUP BY(l.idarticle) order by qqq desc limit 5";
              Statement ste=C.createStatement();
              ResultSet res=ste.executeQuery(req);
               while (res.next()){
                series.getData().add(new XYChart.Data<>(res.getString("nomarticle"), res.getInt("qqq")));
            }
               
             
          } catch (SQLException ex) {
              Logger.getLogger(ArticleService.class.getName()).log(Level.SEVERE, null, ex);
          }
       return series;
    }


        
}
