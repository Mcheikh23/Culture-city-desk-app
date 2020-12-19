/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Service;

import Entity.Evennement;
import Entity.User;
import Presentation.LoginController;
//import Controller.AuthentificationController;

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
import Utilitaire.SingletonConnexion;

/**
 *
 * @author Dell
 */
public class EvennementService {

   Connection c = SingletonConnexion.getInstance().getConn();
    Statement ste;
  
    
      public EvennementService() {
        try {
            ste = c.createStatement();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
      public void select () throws SQLException{
           String req2 = "select roles from user  ";
    ResultSet  res =  ste.executeQuery(req2);
      }
      
      public String rechercherparrole(int id) {
        String test = null;
        String req = "SELECT roles from user where id='"+id+"'";
             
        try {
           
            ResultSet stp=ste.executeQuery(req);
            stp.last();
            if(stp.getRow()!=0)
            {
               test= stp.getString(1);
                System.out.println(test);
            }
            else
            {
                test="not found";
                System.out.println(test);
            }
        }
            catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return test;
        
    }
      ////co
       public String recherchertitre(String titre) {
        String test = null;
        String req = "SELECT Titre_Event from evenement where Titre_Event='"+titre+"'";
             
        try {
           
            ResultSet stp=ste.executeQuery(req);
            stp.last();
            if(stp.getRow()!=0)
            {
               test= stp.getString(1);
                System.out.println(test);
            }
            else
            {
                test="not found";
                System.out.println(test);
            }
        }
            catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return test;
        
    }
      /////
  
      public void creerEvennement(Evennement u) throws SQLException{
         
   //   String req2 = "select roles from user where id = '"+LoginController.test+"'  ";
    //ResultSet  res =  ste.executeQuery(req2);
    //while(res.next()){
   //System.out.println(res.getString(1)); 
//if ("admin".equals(res.getString(1)) ||"membre".equals(res.getString(1)) )  {
    String req1="INSERT INTO `evenement` (`Descr_Event`, `Image_Event`,"
              + " `Titre_Event`, `DATED_EVENT`, `DATEF_EVENT`,`EMPLACEMENT`,"
              + "`id`,`categorie_Event`,`nbr_place_E`) VALUES ('"+u.getDescr_Event()+"', "
              + "'"+u.getImage_Event()+"', '"+u.getTitre_Event()+"','"+u.getDATED_EVENT()+"', '"+u.getDATEF_EVENT()+"', '"+u.getEMPLACEMENT()+"', '"+LoginController.test+"', '"+u.getCategorie_Event()+"','"+u.getNbr_place_E()+"');  ";
            ste.executeUpdate(req1);
            System.out.println("elment inste");
//break;
//}
//else 
  //  System.out.println("nnnn");
    //}
         
    }
      
       private static java.sql.Date convertUtilToSql(java.util.Date uDate) {
        java.sql.Date sDate = new java.sql.Date(uDate.getTime());
        return sDate;
    }
       
       
       
       
       public List<Evennement> afficherevenement() {
      
      List<Evennement> Evennement = new ArrayList<>();
      Evennement e = null ;
      String req2="select * from evenement";
      try {
         
         
          ResultSet res=  ste.executeQuery(req2);
          while (res.next()) { 
              e = new Evennement();
                      e.setId_Event(res.getInt("Id_Event"));
                      e.setDescr_Event(res.getString("Descr_Event") );
                      e.setImage_Event(res.getString("Image_Event") );
                      e.setTitre_Event(res.getString("Titre_Event") );
                      e.setEMPLACEMENT(res.getString("EMPLACEMENT") );
                      e.setId_User(res.getInt("id") );
                     
                      e.setCategorie_Event(res.getString("Categorie_Event") );
                      Date d1 =res.getDate("DATED_EVENT"); 
                      Date d2 =res.getDate("DATEF_EVENT"); 
                      Instant instant = Instant.ofEpochMilli(d1.getTime());
                      Instant instant1 = Instant.ofEpochMilli(d2.getTime());
                      LocalDate date = instant.atZone(ZoneId.systemDefault()).toLocalDate();
                      LocalDate date1 = instant1.atZone(ZoneId.systemDefault()).toLocalDate();
              System.out.println(date);

                   
        
                      e.setDATED_EVENT(date);
                      e.setDATEF_EVENT(date1);
              Evennement.add(e);
          }
          
      } catch (SQLException ex) {
          System.out.println(ex.getMessage());
      } 
        
     return Evennement;
    }

   
   
       
       
        
  public ArrayList<Evennement> getAllP() throws SQLException{
        ArrayList<Evennement> retour = new ArrayList<Evennement>();
        Statement stm = c.createStatement();
        Evennement per=null;
        String req = "SELECT * FROM `evenement` order by (nbr_r) desc " ;
        ResultSet resultat = stm.executeQuery(req);
      while (resultat.next()) {   
           Date d1 =resultat.getDate("DATED_EVENT"); 
                      Date d2 =resultat.getDate("DATEF_EVENT"); 
                      Instant instant = Instant.ofEpochMilli(d1.getTime());
                      Instant instant1 = Instant.ofEpochMilli(d2.getTime());
                      LocalDate date = instant.atZone(ZoneId.systemDefault()).toLocalDate();
                      LocalDate date1 = instant1.atZone(ZoneId.systemDefault()).toLocalDate();
                    int a= this.getCount(resultat.getInt(1)); 
                    inserte(resultat.getInt(1),a );
                    
                    
      per=new Evennement(resultat.getInt(1),resultat.getString(2),resultat.getString(3),resultat.getString(4),date,date1,resultat.getString(7),resultat.getString(9),resultat.getInt(10),a);
  
      retour.add(per);
        }
  
  return retour;
  }
  ///////////////////
  public int getCount(int id) throws SQLException {
        int result = -1;
        try (PreparedStatement pstmt = c.prepareStatement("SELECT COUNT(Id_Event) FROM reservation where Id_Event = '"+id+"'")) {
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                result = rs.getInt(1);
            }

            rs.close();
        }

        return result;
    }
  public void inserte(int id,int nbr ){
      
      
      
        
         
        
   String sql="UPDATE evenement SET `nbr_r`=? WHERE Id_Event='" + id + "' ";
   PreparedStatement ps;
        try {
            ps = c.prepareStatement(sql);
           
            ps.setInt(1, nbr);
            
            ps.executeUpdate();
            int rowsUpdated=ps.executeUpdate();
            if (rowsUpdated>0){
           // System.out.println("La modification de "+login+" a été éffectué avec succée ");
            }}
        catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
  
  ///////////////////////////////
  
  public int nbrreser(int id) throws SQLException
  {int test=0;
   Statement stm = c.createStatement();
  String req2 = "select count(*) FROM reservation WHERE Id_Event="+id+"";
  ResultSet res = stm.executeQuery(req2);
   
  return res.getInt(1);
  }
  
  
  
  
  
  
  public ArrayList<Evennement> getnbrr() throws SQLException{
        ArrayList<Evennement> retour = new ArrayList<Evennement>();
        
        Statement stm = c.createStatement();
        Evennement per=null;
        String req = "SELECT Id_Event FROM `evenement` " ;
        ResultSet resultat = stm.executeQuery(req);
      while (resultat.next()) {   
          int ide=resultat.getInt("Id_Event");
          
      per=new Evennement(ide,nbrreser(ide));
  
      retour.add(per);
     
      
        }
  
  return retour;
  }
  //////aaa
   public ArrayList<Evennement> getnbrr1() throws SQLException{

        ArrayList<Evennement> retour = new ArrayList<Evennement>();
            PreparedStatement stmt = c.prepareStatement("SELECT Id_Event FROM `evenement` ");
            
            int a =0;
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                if (rs.getInt(1) <= 0) {
                    stmt = c.prepareStatement("select count(*) FROM reservation WHERE Id_Event="+rs.getInt(1)+"");

                   
         
                    stmt.execute();
                    
                    System.out.println("Insertion faite!");
                } else {
                    System.out.println("Ohh! Article existe déja dans la bd!");
                }
            }
            rs.close();
            stmt.close();
       return retour;
    }

  //////aaaa
      //////////////////////// 
       
         public ArrayList<Evennement> rechercheEvennement(String rech) throws SQLException {

        ArrayList<Evennement> off = new ArrayList<>();
String req = "SELECT * FROM evenement where Titre_Event Like '%"+rech+"%'  ";     
        Statement stm = c.createStatement();
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
                Date d1 =rst.getDate("DATED_EVENT"); 
                      Date d2 =rst.getDate("DATEF_EVENT"); 
                      Instant instant = Instant.ofEpochMilli(d1.getTime());
                      Instant instant1 = Instant.ofEpochMilli(d2.getTime());
                      LocalDate date = instant.atZone(ZoneId.systemDefault()).toLocalDate();
                      LocalDate date1 = instant1.atZone(ZoneId.systemDefault()).toLocalDate();
                      
   Evennement   per=new Evennement(rst.getString(2),rst.getString(3),rst.getString(4),date,date1,rst.getString(7),rst.getString(9),rst.getInt(10));
  

            off.add(per);
        }
        return off;
    }
       
       
  public ArrayList<Evennement> getAllP1() throws SQLException{
        ArrayList<Evennement> retour = new ArrayList<Evennement>();
        Statement stm = c.createStatement();
        Evennement per=null;
        ////////////
        // String req2 = "select roles from user where Id_User = '"+LoginController.test+"'  ";
    //ResultSet  res =  ste.executeQuery(req2);
  //  while(res.next()){
   //System.out.println(res.getString(1)); 
//if ("admin".equals(res.getString(1)) ) {
    
    
      ArrayList<Evennement> retour1 = new ArrayList<Evennement>();
       
        Evennement per1=null;
        String req = "SELECT * FROM `evenement` " ;
        ResultSet resultat = stm.executeQuery(req);
      while (resultat.next()) {   
           Date d1 =resultat.getDate("DATED_EVENT"); 
                      Date d2 =resultat.getDate("DATEF_EVENT"); 
                      Instant instant = Instant.ofEpochMilli(d1.getTime());
                      Instant instant1 = Instant.ofEpochMilli(d2.getTime());
                      LocalDate date = instant.atZone(ZoneId.systemDefault()).toLocalDate();
                      LocalDate date1 = instant1.atZone(ZoneId.systemDefault()).toLocalDate();
                      
      per=new Evennement(resultat.getInt(1),resultat.getString(2),resultat.getString(3),resultat.getString(4),date,date1,resultat.getString(7),resultat.getString(9),resultat.getInt(10));
  
      retour.add(per);
        }
  
  return retour;
    
    
}
        /////////
        String req = "SELECT * FROM `evenement` where id = '"+LoginController.test+"' " ;
   //     ResultSet resultat = stm.executeQuery(req);
     // while (resultat.next()) {   
       //    Date d1 =resultat.getDate("DATED_EVENT"); 
         //             Date d2 =resultat.getDate("DATEF_EVENT"); 
           //           Instant instant = Instant.ofEpochMilli(d1.getTime());
             //         Instant instant1 = Instant.ofEpochMilli(d2.getTime());
               //       LocalDate date = instant.atZone(ZoneId.systemDefault()).toLocalDate();
                 //     LocalDate date1 = instant1.atZone(ZoneId.systemDefault()).toLocalDate();
                      
     // per=new Evennement(resultat.getString(2),resultat.getString(3),resultat.getString(4),date,date1,resultat.getString(7),resultat.getString(9),resultat.getInt(10));
  
      //retour.add(per);
        //}
    //}
  //return retour;
    //    }     
       
       
       
     
        public void supprimerEvennement(Evennement p) {
            
        
        try { 
            String req="DELETE FROM `evenement` WHERE `evenement`.`Titre_Event` = ?";
            PreparedStatement ps = c.prepareStatement(req);
            ps.setString(1, p.getTitre_Event());
            ps.executeUpdate();
            System.out.println("element supprimer");
         
        } catch (SQLException ex) {
            Logger.getLogger(EvennementService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
   
       
       
       
        
       
       
       
       
 /* public ArrayList<Evennement> getAllP() throws SQLException {
        ArrayList<Evennement> retour = new ArrayList<>();
        Statement stm = c.createStatement();
        Evennement per=null;
        String req = "SELECT * FROM `evenement`" ;
        ResultSet resultat = stm.executeQuery(req);
      while (resultat.next()) {    
          
          Instant instant = Instant.ofEpochMilli(d1.getTime());
                      Instant instant1 = Instant.ofEpochMilli(d2.getTime());
      per=new Evennement(resultat.getString(2), resultat.getString(3),resultat.getString(4),resultat.getDate(5),resultat.getDate(6),resultat.getString(7),resultat.getString(9),resultat);
      retour.add(per);
        }}*/
        
     public List<User> readAll(Evennement u) throws SQLException
    {List<User> list=new ArrayList<>();
    ResultSet res=ste.executeQuery("select * from user where `id`='"+u.getId_User()+"' ");
    User per=null;
    while (res.next()) {            
      per=new User(res.getInt(1), res.getString(2),res.getString(3),res.getString(4),res.getString(5),res.getString(6),res.getString(7),res.getString(8),res.getString(9));
      list.add(per);
        }
    return list;
    }

       
       
       
       
       public void modifierevenement(Evennement e) {
        String sql = "UPDATE evenement SET `DATED_EVENT` =? where Id_Event="+e.getId_Event();
 
        PreparedStatement ps;
        try {
            ps = c.prepareStatement(sql);
       
          Date date1 = Date.from(e.getDATED_EVENT().atStartOfDay(ZoneId.systemDefault()).toInstant());
       

        ps.setDate(1, convertUtilToSql(date1));
       
        int rowsUpdated = ps.executeUpdate();
          if (rowsUpdated > 0) {
            System.out.println("L'ev a été modifier avec succès");
          }
          } catch (SQLException ex) {
                     System.out.println("Erreur"+ex.getMessage());
 
          }
    }
    public int idee (String Titre) throws SQLException{
   int a = 0; 
         String req2 = "select Id_Event from evenement where  Titre_Event  ='" + Titre + "'  ";
    ResultSet  res =  ste.executeQuery(req2);
             while (res.next()) {
    a=res.getInt(1);
             }
             return a;
    }
      public void modifierRole(Evennement u) throws SQLException {
        
         
        
   String sql="UPDATE evenement SET `Descr_Event` =? ,`Image_Event` =? ,`Titre_Event` =?, `DATED_EVENT` =?, `DATEF_EVENT` =?,`EMPLACEMENT` =?, `categorie_Event`  =?,`nbr_place_E` =? WHERE  `Id_Event`="+u.getId_Event();
   PreparedStatement ps;
        try {
            ps = c.prepareStatement(sql);

            Date date1 = Date.from(u.getDATED_EVENT().atStartOfDay(ZoneId.systemDefault()).toInstant());
       

        ps.setDate(4, convertUtilToSql(date1));
     Date date2 = Date.from(u.getDATEF_EVENT().atStartOfDay(ZoneId.systemDefault()).toInstant());
       

        ps.setDate(5, convertUtilToSql(date2));

            
            ps.setString(1, u.getDescr_Event());
            ps.setString(2, u.getImage_Event());
            ps.setString(3, u.getTitre_Event());
            
            
            ps.setString(6, u.getEMPLACEMENT());
            ps.setString(7, u.getCategorie_Event());
            ps.setInt(8, u.getNbr_place_E());
            ps.executeUpdate();
            int rowsUpdated=ps.executeUpdate();
            if (rowsUpdated>0){
            System.out.println("La modification  a été éffectué avec succée ");
            }}
        catch (SQLException ex) {
            Logger.getLogger(EvennementService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
       
       
       
      
  
      
      public boolean rechercherparNom(String nom) {
        boolean test=false;
        String req = "SELECT * from evenement where Titre_Event='"+nom+"'";
            
     
        try {
           
            ResultSet stp=ste.executeQuery(req);
            stp.last();
            if(stp.getRow()!=0)
            {
                test=true;
                System.out.println(test+"evenement trouver");
            }
            else
            {
                test=false;
                System.out.println(test+"evenement non trouver");
            }
        }
            catch (SQLException ex) {
            Logger.getLogger(EvennementService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return test;
        
    }
     
      
      
}
