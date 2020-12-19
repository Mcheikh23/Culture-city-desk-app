package Service;

import Entity.Utilisateur;
import Iservice.IUtilisateurService;
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
public class UtilisateurService implements IUtilisateurService{

 Connection c = ConnexionBD.getInstancebd().getConnexionBD();
Statement ste;
    PreparedStatement pst;
   
    private static SeanceService instance;
    public UtilisateurService() {
          try {
            ste = c.createStatement();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
   

    public void creerUtilisateur(Utilisateur u) {
        try {
            String req1="INSERT INTO `utilisateur` "
                    + "(`pseudo_utilisateur`, `pass_utilisateur`, `nom_utilisateur`, `prenom_utilisateur`, `tel_utilisateur`, `mail_utilisateur`, `photo_utilisateur`, `statut_utilisateur` )"
                   
                    + "VALUES ( '"+u.getPseudo_utilisateur()+"', '"
                    +u.getPass_utilisateur()+"', '"
                    +u.getNom_utilisateur()+"', '"
                    +u.getPrenom_utilisateur()+"', '"
                    +u.getTel_utilisateur()+"', '"
                    +u.getMail_utilisateur()+"', '"
                    +u.getPhoto_utilisateur()+"', '"
                    +u.getStatut_utilisateur()+"');";
                    
            
            
         
            ste.executeUpdate(req1);
        } catch (SQLException ex) {
            Logger.getLogger(FilmService.class.getName()).log(Level.SEVERE, null, ex);
        }

        
        
        
    }

    public void modifierUtilisateur(Utilisateur u) {
       
           
    
String sql = "UPDATE utilisateur SET pseudo_utilisateur=?, pass_utilisateur=?, nom_utilisateur=?, prenom_utilisateur=?, tel_utilisateur=?, mail_utilisateur=?,  photo_utilisateur=?, statut_utilisateur=? where id_utilisateur="+u.getId_utilisateur();
 
        PreparedStatement ps;
        try {
            ps = c.prepareStatement(sql);
        
        
        ps.setString(1, u.getPseudo_utilisateur());
        ps.setString(2, u.getPass_utilisateur());
        ps.setString(3, u.getNom_utilisateur());
        ps.setString(4, u.getPrenom_utilisateur());
        ps.setInt(5, u.getTel_utilisateur());
        ps.setString(6, u.getMail_utilisateur());
        ps.setString(7, u.getPhoto_utilisateur());
        ps.setInt(8, u.getStatut_utilisateur());         
                 
             
               
        
                  
                   
                  
                       

        int rowsUpdated = ps.executeUpdate();
          if (rowsUpdated > 0) {
            System.out.println("L'utilisateur a été modifier avec succès");
          }
          } catch (SQLException e) {
                     System.out.println("Erreur"+e.getMessage());
 
          }
    }
    
    public void supprimerUtilisateur(Utilisateur u) {
        try {
            String req1="delete from utilisateur where"
                    + " id_utilisateur=?";
       
      PreparedStatement ps = c.prepareStatement(req1);
            ps.setInt(1, u.getId_utilisateur());
            ps.executeUpdate();
         
           
        } catch (SQLException ex) {
            Logger.getLogger(FilmService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

@Override
    public List<Utilisateur> afficherUtilisateur() {
      
      List<Utilisateur> Utilisateur = new ArrayList<>();
      Utilisateur u = null ;
      String req2="select * from utilisateur";
      try {
         
         
          ResultSet res=  ste.executeQuery(req2);
          while (res.next()) { 
              u = new Utilisateur();
                      u.setId_utilisateur(res.getInt("id_utilisateur"));
                      u.setNom_utilisateur(res.getString("nom_utilisateur") );
                      u.setPrenom_utilisateur(res.getString("prenom_utilisateur"));
                        u.setPseudo_utilisateur(res.getString("pseudo_utilisateur"));
                            u.setMail_utilisateur(res.getString("mail_utilisateur"));
                             u.setPhoto_utilisateur(res.getString("photo_utilisateur"));
                    
                      u.setTel_utilisateur(res.getInt("tel_utilisateur") );
                      u.setStatut_utilisateur(res.getInt("statut_utilisateur"));
                          u.setPass_utilisateur(res.getString("pass_utilisateur"));
                                   
              Utilisateur.add(u);
          }
          
      } catch (SQLException ex) {
          System.out.println(ex.getMessage());
      } 
        
     return Utilisateur;
    }

  
    
    
}
