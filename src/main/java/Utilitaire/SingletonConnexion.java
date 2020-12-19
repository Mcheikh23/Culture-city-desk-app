/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilitaire;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dell
 */
public class SingletonConnexion {
    
        
    private static Connection connect;
    
    private String url="jdbc:mysql://127.0.0.1:3306/ccv1";
    private String login="root";
    private String password="";

        private static SingletonConnexion instance;
        
    private SingletonConnexion() {
        
        try {
            connect=DriverManager.getConnection(url, login, password);
            System.out.println("Connexion etablie");   
        } catch (SQLException ex) {
            System.out.println("Connexion echouée");
        }
    
    }
    
        
    public static SingletonConnexion getInstance(){
        if(instance==null)
            instance=new SingletonConnexion();
        return instance;  
    }
    
    public static Connection getConn()
    {
       return connect;
    }
    
     
}