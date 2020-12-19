package Service;

import Entity.User;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.ObservableList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Dell
 */
public interface IUserService {
    
    public void ajouterUser(User user);
    public void modifierUser(User user);
    public void supprimerUser(User user);
    public ArrayList<User> AfficherUser();
    public User get(String username) throws SQLException ;
    public User gett(String email) throws SQLException ;
    public void modifierUserPass (User user );
    public String mdpO (String email) throws ClassNotFoundException, SQLException;
public ObservableList<User> loadProduit() throws SQLException ;
}
