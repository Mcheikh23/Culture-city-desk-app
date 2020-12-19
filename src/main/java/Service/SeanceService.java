package Service;


import Entity.Film;
import Entity.Salle;
import Entity.Seance;
import Iservice.ISeanceService;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.ConnexionBD;

public class SeanceService implements ISeanceService{
  Connection c = ConnexionBD.getInstancebd().getConnexionBD();
Statement ste;
    PreparedStatement pst;
   
   // private static SeanceService instance;
    
    public SeanceService() {
        try {
            ste = c.createStatement();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
           
    
    
    
   @Override
    public void creerSeance(Seance s) {
        try {
            String req1="INSERT INTO seance (id_film,numero_salle,date_seance) VALUES ('"+s.getFilm()+"','" +s.getSalle()+"','"+s.getDate_seance()+"');";
            
            
         
            ste.executeUpdate(req1);
        } catch (SQLException ex) {
            Logger.getLogger(SeanceService.class.getName()).log(Level.SEVERE, null, ex);
        }

        
        
        
    }
   /*  private static java.sql.Date convertUtilToSql(java.util.Date uDate) {
        java.sql.Date sDate = new java.sql.Date(uDate.getTime());
        return sDate;
    }
*/
  /*  public void modifierSeance(Seance s) {
        String sql = "UPDATE seance SET id_film=?, numero_salle=?, date_seance=? where id_seance="+s.getId_seance();
 
        PreparedStatement ps;
        try {
            ps = c.prepareStatement(sql);
        
        
        ps.setInt(1, s.getFilm().getId_film());
         ps.setInt(2, s.getSalle().getNumero_salle());
         ps.setDate(3, s.getDate_seance());
      // Date date1 = Date.from(s.getDate_seance().atStartOfDay(ZoneId.systemDefault()).toInstant());
         
 //ps.setDate(3, convertUtilToSql(date1));

        int rowsUpdated = ps.executeUpdate();
          if (rowsUpdated > 0) {
            System.out.println("La seance a été modifier avec succès");
          }
          } catch (SQLException ex) {
                     System.out.println("Erreur"+ex.getMessage());
 
          }
    }
    
    
    */

    public void supprimerSeance(Seance s) {
        try {
            String req1="delete from Seance where"
                    + " id_seance=?";
       
      PreparedStatement ps = c.prepareStatement(req1);
            ps.setInt(1, s.getId_seance());
            ps.executeUpdate();
         
           
        } catch (SQLException ex) {
            Logger.getLogger(SeanceService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public List<Seance> afficherSeance() {
      
      ArrayList<Seance> Seance = new ArrayList<>();
      Film film;
      Salle salle;
      //Seance s = null ;
      String req2="select * from seance order by(date_seance) desc";
      try {
         
         
          ResultSet res=  ste.executeQuery(req2);
          while (res.next()) { 
                    //  s = new Seance();
                int id_seance = res.getInt("id_seance");
                Date date_seance= res.getDate("date_seance");
                Film f = new Film();
                Salle s = new Salle();
                int id_film=res.getInt("id_film");
                int numero_salle=res.getInt("numero_salle");
                FilmService fs= new FilmService();
                SalleService ss= new SalleService();
                film = fs.rechercherFilmCById(id_film);
                salle = ss.rechercherSalleCById(numero_salle);
                Seance seance = new Seance(id_seance,film,salle,date_seance);
                Seance.add(seance);
               
          }
          
      } catch (SQLException ex) {
          System.out.println(ex.getMessage());
      } 
        
     return Seance;
    }
    /*
      public Film rechercheSeanceParSalle(String id_salle1) {
   
           
        

        try { 
            Statement ps=c.createStatement();
            ResultSet res;
            
            res=ps.executeQuery("select * from 	seance where id_salle like '%"+id_salle1+"%' ");
            while(res.next())
            {
             int id_seance = res.getInt("id_seance");
                String id_film=res.getString("id_film");
                String numero_salle=res.getString("numero_salle");
                Date date_seance=res.getDate("date_seance"); 
               
              
                System.out.println("seance trouvé  ");
                return  new  Seance (id_seance, id_film, numero_salle, date_seance);   
                
            }
            
        } catch (SQLException ex) {
            System.out.println(""+ex.getMessage());
        }
        System.out.println("film non trouvé ");
        return null;
    }
    
  */  

}