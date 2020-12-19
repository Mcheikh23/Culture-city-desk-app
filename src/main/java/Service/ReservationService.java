package Service;


import Entity.Reservation;
import Iservice.IReservationService;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.ConnexionBD;


public class ReservationService implements IReservationService{
  Connection c = ConnexionBD.getInstancebd().getConnexionBD();
    Statement ste;
    PreparedStatement pst;
    private static SeanceService instance;
    public ReservationService() {
        try {
            ste = c.createStatement();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
           
    
    
    
    public void creerReservation(Reservation r) {
        try {
            String req1="INSERT INTO `reservation` "
                    + "(`nombre_reservation`, `id_utilisateur`, `id_seance`) "
                    + "VALUES ( '"+r.getNombre_reservation()+"', '"
                    +r.getUtilisateur().getId_utilisateur()+"','"+r.getSeance().getId_seance()+"');";
            
            
         
            ste.executeUpdate(req1);
        } catch (SQLException ex) {
            Logger.getLogger(SeanceService.class.getName()).log(Level.SEVERE, null, ex);
        }

        
        
        
    }
     private static java.sql.Date convertUtilToSql(java.util.Date uDate) {
        java.sql.Date sDate = new java.sql.Date(uDate.getTime());
        return sDate;
    }

    public void modifierReservation(Reservation r) {
        String sql = "UPDATE seance SET nombre_reservation=?, =?, id_utilisateur=? where id_seance="+r.getId_reservation();
 
        PreparedStatement ps;
        try {
            ps = c.prepareStatement(sql);
        
        
        ps.setInt(1, r.getNombre_reservation());
         ps.setInt(2, r.getUtilisateur().getId_utilisateur());
          ps.setInt(3, r.getSeance().getId_seance());
        
         


        int rowsUpdated = ps.executeUpdate();
          if (rowsUpdated > 0) {
            System.out.println("La reservation a été modifier avec succès");
          }
          } catch (SQLException ex) {
                     System.out.println("Erreur"+ex.getMessage());
 
          }
    }
    
    
    

    public void supprimerReservation(Reservation r) {
        try {
            String req1="delete from reservation where"
                    + " id_reservation=?";
       
      PreparedStatement ps = c.prepareStatement(req1);
            ps.setInt(1, r.getId_reservation());
            ps.executeUpdate();
         
           
        } catch (SQLException ex) {
            Logger.getLogger(SeanceService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public List<Reservation> afficherReservation() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
  
    

   
}
