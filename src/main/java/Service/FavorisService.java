package Service;

import Entity.Favoris;
import Iservice.IFavorisService;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
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
public class FavorisService implements IFavorisService{

Connection c = ConnexionBD.getInstancebd().getConnexionBD();
    Statement ste;

    public FavorisService() {
          try {
            ste = c.createStatement();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
   

@Override
    public void creerFavoris(Favoris f) {
        
            String req1="INSERT INTO `favoris` "
                    + "(`id_film`, `id_utilisateur` ) "
                    + "VALUES ( '"+f.getFilm().getId_film()+"', '"
                    + f.getUtilisateur().getId_utilisateur()+"');";
            
            
          try {
            ste.executeUpdate(req1);
        } catch (SQLException ex) {
            Logger.getLogger(FavorisService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void supprimerFavoris(Favoris f) {
        try {
            String req1="delete from favoris where"
                    + " id_favoris=?";
       
      PreparedStatement ps = c.prepareStatement(req1);
            ps.setInt(1, f.getId_favoris());
            ps.executeUpdate();
         
           
        } catch (SQLException ex) {
            Logger.getLogger(FavorisService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    

    }

    

