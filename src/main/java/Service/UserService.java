/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;


import Entity.User;
import Utilitaire.SingletonConnexion;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.swing.JOptionPane;

/**
 *
 * @author Dell
 */
public class UserService implements IUserService{
    
    SingletonConnexion connect =SingletonConnexion.getInstance();
    Connection conn=connect.getConn();
    
    @Override
    public void ajouterUser (User user)
    {
        String req="INSERT INTO user (userName,username_canonical,email,email_canonical,enabled,password,roles,nom,prenom,image) VALUES(?,?,?,?,1,?,?,?,?,?)" ; 
        
        PreparedStatement ps;
    
        try { 
            ps =conn.prepareStatement(req); 
            ps.setString(1,user.getUserName()) ; 
            ps.setString(2,user.getUserName()) ; 
            ps.setString(3,user.getEmail()) ; 
            ps.setString(4,user.getEmail()) ; 

            ps.setString(5,user.getPassword());
            ps.setString(6,user.getRoles());
            ps.setString(7,user.getNom());
            ps.setString(8,user.getPrenom());
            ps.setString(9,user.getImage());
            ps.executeUpdate() ; 
            
        } catch (SQLException ex) {
           System.out.println("Impossible d'ajouter un utilisateur"+ex.getMessage());
        } 
    
    }
    @Override
    public String mdpO (String email) throws ClassNotFoundException, SQLException{
         Class.forName("com.mysql.jdbc.Driver");
            Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://127.0.0.1/pidev?autoReconnect=true&useSSL=false", "root", "");
            String query = "select password from user where email LIKE '" + email + "'";
            PreparedStatement statmnt = con.prepareStatement(query);
            ResultSet result = statmnt.executeQuery();
            if (result.next()) {
                String fetchedPassword = result.getString("password");
                return fetchedPassword;
    }
            return null;
    }
    
    @Override
     public void modifierUser (User user )
    {
    String req="UPDATE user SET id=?, userName=?,email=?,password=?,roles=?,nom=?,prenom=?, image=? WHERE id = "+user.getId() ; 
        
                PreparedStatement ps;
        try { 
            ps = conn.prepareStatement(req);
            ps.setInt(1,user.getId()) ;
            ps.setString(2,user.getUserName()) ; 
            ps.setString(3,user.getEmail()) ; 
            ps.setString(4,user.getPassword());
            ps.setString(5,user.getRoles());
            ps.setString(6,user.getNom());
            ps.setString(7,user.getPrenom());
            ps.setString(8,user.getImage());
            ps.executeUpdate() ;    
        } catch (SQLException ex) {
        }
    }
     
     @Override
     public void modifierUserPass (User user ){
    String req="UPDATE user SET password=? WHERE id = "+user.getId() ; 
                PreparedStatement ps;
        try { 
            ps = conn.prepareStatement(req);
            ps.setString(1,user.getPassword());
            ps.executeUpdate() ; 
        } catch (SQLException ex) {
        }
    }
     
       @Override
    public void supprimerUser(User user){
         try {
            Statement ps = conn.createStatement();
            ps.executeUpdate("delete from commentaire where user = '"+user.getId()+"'");
            ps.executeUpdate("delete from user where id = '"+user.getId()+"'");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Impossible de supprimer une categorie\n"+ex.getMessage());
        }  
    }
    
    
    @Override
    public ArrayList<User> AfficherUser(){
        ArrayList<User> listeUser = new ArrayList<User>();
        
        try
        {
            Statement ps = conn.createStatement();
            ResultSet res;
            
            res = ps.executeQuery("select * from user");
            while(res.next())
            {
                int id = res.getInt("id");
                String userName=res.getString("userName");
                String email=res.getString("email"); 
                String password=res.getString("password"); 
                String roles=res.getString("roles"); 
                String nom=res.getString("nom"); 
                String prenom=res.getString("prenom");
                String image=res.getString("image"); 
                User user = new User(id, userName, email, password, roles, nom, prenom, image);
                listeUser.add(user);
            }
        }
          catch(Exception e)
        {
           System.out.println("Erreur d'affichage"+e.getMessage());
        } 
                return listeUser;
    }
 
    @Override
    public User get(String username) throws SQLException {
       
      String req = "Select * from user where username = ?";
        PreparedStatement st = conn.prepareStatement(req);
        st.setString(1, username);
        ResultSet rs = st.executeQuery();
        

        if (rs.next()) {
            User obj = new User();
            obj.setId(rs.getInt("id"));
            obj.setUserName(rs.getString("username"));
            obj.setEmail(rs.getString("email"));
            obj.setPassword(rs.getString("password"));
            obj.setRoles(rs.getString("roles"));
            obj.setNom(rs.getString("nom"));
            obj.setPrenom(rs.getString("prenom"));
            obj.setImage(rs.getString("image"));

           
            return obj;
        }
        return null;
    }
    
    @Override
    public User gett(String email) throws SQLException {
       
      String req = "Select * from user where email = ?";
        PreparedStatement st = conn.prepareStatement(req);
        st.setString(1, email);
        ResultSet rs = st.executeQuery();
        

        if (rs.next()) {
            User obj = new User();
            obj.setId(rs.getInt("id"));
            obj.setUserName(rs.getString("username"));
            obj.setEmail(rs.getString("email"));
            obj.setPassword(rs.getString("password"));
            obj.setRoles(rs.getString("roles"));
            obj.setNom(rs.getString("nom"));
            obj.setPrenom(rs.getString("prenom"));
            obj.setImage(rs.getString("image"));

           
            return obj;
        }
        return null;
    }
    
          @Override
      public ObservableList<User> loadProduit() throws SQLException {
      ObservableList<User> items = FXCollections.observableArrayList();
        PreparedStatement pt = conn.prepareStatement("SELECT user.username, user.image   from user");        
        ResultSet resultat = pt.executeQuery();
        while (resultat.next()) {
            String nom = resultat.getString("username");
            String img=resultat.getString("image");
            User p=new User(nom,img);
            items.add(p);
        }
         return items;        
    }

    public User RechercheUser(User obj) throws SQLException {
       
        String req = "Select * from user where id ="+obj.getId();
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(req);
        

        if (rs.next()) {
            obj.setId(rs.getInt("id"));
            obj.setUserName(rs.getString("username"));
            obj.setEmail(rs.getString("email"));
            obj.setPassword(rs.getString("password"));
            obj.setRoles(rs.getString("roles"));
            obj.setNom(rs.getString("nom"));
            obj.setPrenom(rs.getString("prenom"));
            obj.setImage(rs.getString("image"));

           
            return obj;
        }
        return null;
    }

}
    
   
    

