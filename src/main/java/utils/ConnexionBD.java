/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author PC
 */
public class ConnexionBD {
     private  String url="jdbc:mysql://localhost:3306/ccv2";
  private  String login="root";
   private  String pwd="";
   private  Connection C;
   static ConnexionBD instancebd;
   private ConnexionBD(){
           try {
           C=(Connection) DriverManager.getConnection(url,login,pwd);
           System.out.println("connexion établie");
    }catch (SQLException ex) {
           System.out.println(ex.getMessage());
          
       }

   }
   public static  ConnexionBD getInstancebd(){
    if(instancebd==null)
          if (instancebd == null)
            instancebd = new ConnexionBD();
            return  instancebd;
        
   
   }
    public Connection getConnexionBD(){
        return C;
    
    }

  
}
    

