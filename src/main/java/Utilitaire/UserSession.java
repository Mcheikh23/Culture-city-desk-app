/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilitaire;

import java.util.HashSet;
import java.util.Set;

public final class UserSession {

    private static UserSession instance;
    
    
    private String userName;
    private String email;
    private String password;
    private String roles;
    private String nom;
    private String prenom;
    private String image;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

   

  
    private UserSession(String userName,String email,String password,String roles,String nom,String prenom,String image) {
        this.userName = userName;
        this.email=email;
        this.password=password;
        this.roles = roles;
        this.nom=nom;
        this.prenom=prenom;
        this.image=image;
        
    }

    public static UserSession getInstance(String userName,String email,String password, String roles,String nom,String prenom,String image) {
        if(instance == null) {
            System.out.println("okiiiii");
            instance = new UserSession(userName,email,password, roles,nom,prenom,image);
        }
        System.out.println("NOOO");
        return instance;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getUserName() {
        return userName;
    }

    public String getRoles() {
        return roles;
    }

    public void cleanUserSession() {
        userName = "";
        email="";
        password="";
        roles = "";
        nom="";
        prenom="";
        image="";
        instance=null;
    }

    @Override
    public String toString() {
        return "UserSession{" + "userName=" + userName + ", email=" + email + ", password=" + password + ", roles=" + roles + ", nom=" + nom + ", prenom=" + prenom + ", image=" + image + '}';
    }

   
 

    

    
}
