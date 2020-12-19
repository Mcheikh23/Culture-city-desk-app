/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entity.Commentaire;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.DataSource;

/**
 *
 * @author hp
 */
public class CommentaireService {
   private Connection con = DataSource.getInstance().getConnection();
    private Statement ste;

    public CommentaireService() {
        try {
            ste=con.createStatement();
        } catch (SQLException ex) {
            System.out.println(ex);  
        }
    }
    public void ajouterCommentaire(Commentaire c) throws SQLException {
         java.util.Date date1= new java.util.Date();  
        String temp_commentaire = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date1);
        c.setDate_com(temp_commentaire);
        String req1 = "INSERT INTO `commentaire` (`id_f`, `id_user`,`date_com`, `description_com`) VALUES ('" + c.getId_f() + "', '" + c.getId_user()+ "', '" + c.getDate_com()+ "', '" + c.getDescription_com() + "');";
        ste.executeUpdate(req1);
        System.out.println("elment inseré");

    }
    
    public List<Commentaire> readAll(int id_sujet) throws SQLException
    {
    List<Commentaire> list=new ArrayList<>();
    ResultSet res=ste.executeQuery("select * from commentaire WHERE id_f='"+id_sujet+"'");
    Commentaire com=null;
    while (res.next()) {            
      com=new Commentaire(res.getInt(1),res.getInt(2),res.getInt(3),res.getString(4),res.getString(5));
      list.add(com);
        }
    return list;
    } 
    
   public void supprimerCommentaire(Commentaire c) {
            
        
        try { 
            String req="DELETE FROM `commentaire` WHERE `commentaire`.`id_com` = ?";
            PreparedStatement ste = con.prepareStatement(req);
            ste.setInt(1, c.getId_com());
            ste.executeUpdate();
            System.out.println("element supprimer");
         
        } catch (SQLException ex) {
            Logger.getLogger(CommentaireService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
   
   public void modifierCommentaire(Commentaire c) {      
   String sql="UPDATE commentaire SET `Id_Sujet`=?,`Id_User`=?,`Contenu_Commentaire`=?,`Temps_Commentaire`=? WHERE Id_Commentaire="+c.getId_com();
   PreparedStatement ste;
        try {
            ste =con.prepareStatement(sql);
            ste.setInt(1, c.getId_f());
            ste.setInt(2, c.getId_user());
            
            ste.setString(3, c.getDescription_com());
            ste.setString(4, c.getDate_com());
            
                
            ste.executeUpdate();
            int rowsUpdated=ste.executeUpdate();
            if (rowsUpdated>0){
            System.out.println("La modification de produit"+c.getId_com()+" a été éffectué avec succée ");
            }}
        catch (SQLException ex) {
            Logger.getLogger(CommentaireService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
    
}
