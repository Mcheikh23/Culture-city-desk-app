/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entity.Categorie;
import Entity.Sujet;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.chart.XYChart;
import utils.DataSource;
/**
 *
 * @author hp
 */
public class SujetService {
    private Connection con = DataSource.getInstance().getConnection();
    private Statement ste;

    public SujetService() {
        try {
            ste=con.createStatement();
        } catch (SQLException ex) {
            System.out.println(ex);  
        }
    }
    
    public void ajouterSujet(Sujet s) throws SQLException {
          java.util.Date date1= new java.util.Date();  
        String Temps_Sujet = new SimpleDateFormat("yyyy-MM-dd").format(date1);
        s.setDate(Temps_Sujet);
        String req1 = "INSERT INTO `sujet` (`id_user`,`description_f`,`date`) VALUES ('" + s.getId_user() + "', '" + s.getDescription_f() + "', '" + s.getDate()+ "');";
        ste.executeUpdate(req1);
        System.out.println("elment inseré");

    }
    
    public List<Sujet> readAllS() throws SQLException
    {List<Sujet> list=new ArrayList<>();
    ResultSet res=ste.executeQuery("select * from sujet");
    Sujet com=null;
    while (res.next()) {            
      com=new Sujet(res.getInt(1),res.getInt(2),res.getString(3),res.getString(4));
      list.add(com);
        }
    return list;
    } 
    
     public List<Sujet> FindSujetByIdUser(int id_user) throws SQLException
    {
        List<Sujet> list1=new ArrayList<>();
    ResultSet res=ste.executeQuery("select * from sujet WHERE id_user='"+id_user+"'");
    Sujet com=null;
    while (res.next()) {            
      com=new Sujet(res.getInt(1),res.getInt(2),res.getString(3),res.getString(4));
      list1.add(com);
        }
    return list1;
    } 
     
   /*   public void FindSujetByIdSujet(int id_sujet) throws SQLException
    {
    
    } 
     */
    
    public void supprimerSujet(Sujet s) {
            
        
        try { 
            String req="DELETE FROM `sujet` WHERE `sujet`.`id_f` = ?";
            PreparedStatement ste = con.prepareStatement(req);
            ste.setInt(1, s.getId_f());
            ste.executeUpdate();
            System.out.println("element supprimer");
         
        } catch (SQLException ex) {
            Logger.getLogger(SujetService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    
    public void modifierSujet(Sujet s) {
        
         
        
   String sql="UPDATE sujet SET `id_user`=?,`description_f`=?,`date`=? WHERE id_usert="+s.getId_f();
   PreparedStatement ste;
        try {
            ste =con.prepareStatement(sql);
            ste.setInt(1, s.getId_user());
            ste.setInt(2, s.getId_f());
            
            ste.setString(3, s.getDate());      
            ste.executeUpdate();
            int rowsUpdated=ste.executeUpdate();
            if (rowsUpdated>0){
            System.out.println("La modification de produit"+s.getId_f()+" a été éffectué avec succée ");
            }}
        catch (SQLException ex) {
            Logger.getLogger(SujetService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
public boolean rechercherparNom(String nom) {
        boolean test=false;
        String req = "SELECT * from sujet where description_f='"+nom+"'";
            
     
        try {
           
            ResultSet stp=ste.executeQuery(req);
            stp.last();
            if(stp.getRow()!=0)
            {
                test=true;
                System.out.println(test+"sujet trouver");
            }
            else
            {
                test=false;
                System.out.println(test+"sujet non trouver");
            }
        }
            catch (SQLException ex) {
            Logger.getLogger(SujetService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return test;
      }

    public  XYChart.Series<String, Integer>  topsujet() {
         XYChart.Series<String, Integer> series = new XYChart.Series<String, Integer>();
          try {
             
              String req="SELECT description_f,nbre_jaime  FROM sujet ";
              Statement ste=con.createStatement();
              ResultSet res=ste.executeQuery(req);
               while (res.next()){
                series.getData().add(new XYChart.Data<>(res.getString(1), res.getInt(2)));
            }
               
             
          } catch (SQLException ex) {
              Logger.getLogger(SujetService.class.getName()).log(Level.SEVERE, null, ex);
          }
       return series;
    }
  
    }
