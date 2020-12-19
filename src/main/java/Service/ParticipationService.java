/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entity.club;
import Entity.participation;
import Iservice.IparticipationService;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utils.ConnexionBD;

/**
 *
 * @author user
 */
public class ParticipationService implements IparticipationService{
Connection c = ConnexionBD
           .getInstancebd()
           .getConnexionBD();


club cl;
  Statement ste;
    @Override
    public void participer(participation p) throws SQLException {
       String req = "INSERT INTO participation(id_club) VALUES(?)";
          String req0 = "SELECT nbr_participants FROM club WHERE id_club=?";
          String req1 = "UPDATE club SET nbr_participants=? WHERE id_club=?";
          
          
          PreparedStatement pste = c.prepareStatement(req); 
         
          pste.setInt(1, p.getClub().getId_club());
        
         
          pste.executeUpdate();
          
          PreparedStatement pste0 = c.prepareStatement(req0);
          pste0.setInt(1, p.getClub().getId_club());
          ResultSet r0 = pste0.executeQuery();
          r0.next();
          int a = r0.getInt("nbr_participants");
          int b = a+1 ; 
            System.out.println(b); 
          
          PreparedStatement pste1 = c.prepareStatement(req1); 
          pste1.setInt(1, b);
          pste1.setInt(2,p.getClub().getId_club());
          pste1.executeUpdate();
    }

    @Override
    public void annulerparticiper(participation p) throws SQLException {
         String req = "INSERT INTO participation(id_club) VALUES(?)";
          String req0 = "SELECT nbr_participants FROM club WHERE id_club=?";
          String req1 = "UPDATE club SET nbr_participants=? WHERE id_club=?";
          
          
          
          PreparedStatement pste = c.prepareStatement(req);
          pste.setInt(1, p.getClub().getId_club());
      
          pste.executeUpdate();
          
          PreparedStatement pste0 = c.prepareStatement(req0);
          pste0.setInt(1, p.getClub().getId_club());
          ResultSet r0 = pste0.executeQuery();
          r0.next();
          int a = r0.getInt("nbr_participants");
          int b = a-1 ; 
          
          PreparedStatement pste1 = c.prepareStatement(req1); 
         pste1.setInt(1, b);
          pste1.setInt(2,p.getClub().getId_club());
           pste1.executeUpdate();
    }

    @Override
    public List<participation> afficherParticipation() {
        List<participation> participation = new ArrayList<>();
      participation pa = null  ;
      club Club;
      
     
      String req2="select * from participation";
      try {
         
         
          ResultSet res=  ste.executeQuery(req2);
          while (res.next()) { 
              pa = new participation();
                pa.setId_participation(res.getInt("id_participation"));             
                int id_club=res.getInt("id_club");
                ClubService clubService = new ClubService();
                Club = clubService.RechercherClubCById(id_club);
                pa.setClub(Club);  
           
              participation.add(pa);
          }
          
      } catch (SQLException ex) {
          System.out.println(ex.getMessage());
      } 
        
     return participation;
    }}