/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author Hudson
 */
public class Membre {
   
      private int id_user ;
    private String login ;
    private String mdp;
    private String mail;
    private String prenom;
    private String nom;
    private String cin;
    private String dateNaissance;
    private int numTel;
    private String photoProfil ;
    private String role;
    public static String session;
   

    public Membre(String mail, String prenom, String nom, String cin, String dateNaissance, int numTel, String login) {
        this.mail = mail;
        this.prenom = prenom;
        this.nom = nom;
        this.cin = cin;
        this.dateNaissance = dateNaissance;
        this.numTel = numTel;
        this.login=login;
        this.role="membre";

    }

    
    

    public Membre( int id_user, String login, String mdp, String mail, String prenom, String nom, String cin, String dateNaissance, int numTel, String photoProfil) {
        this.id_user = id_user;
        this.login = login;
        this.mdp = mdp;
        this.mail = mail;
        this.prenom = prenom;
        this.nom = nom;
        this.cin = cin;
        this.dateNaissance = dateNaissance;
        this.numTel = numTel;
        this.photoProfil = photoProfil;
        role="membre";
    }

    public Membre( String login, String mdp, String mail, String prenom, String nom, String cin, String dateNaissance, int numTel) {
         this.login = login;
        this.mdp = mdp;
        this.mail = mail;
        this.prenom = prenom;
        this.nom = nom;
        this.cin = cin;
        this.dateNaissance = dateNaissance;
        this.numTel = numTel;
   //    this.photoProfil = "";
        role="membre";
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public String getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public int getNumTel() {
        return numTel;
    }

    public void setNumTel(int numTel) {
        this.numTel = numTel;
    }

    public String getPhotoProfil() {
        return photoProfil;
    }

    public void setPhotoProfil(String photoProfil) {
        this.photoProfil = photoProfil;
    }

    public static String getSession() {
        return session;
    }

    public static void setSession(String session) {
        Membre.session = session;
    }

    

    public String getRole() {
        return role;
    }

    @Override
    public String toString() {
        return "Membre{" + "id_user=" + id_user + ", login=" + login + ", mdp=" + mdp + ", mail=" + mail + ", prenom=" + prenom + ", nom=" + nom + ", cin=" + cin + ", dateNaissance=" + dateNaissance + ", numTel=" + numTel + ", photoProfil=" + photoProfil + ", role=" + role + '}';
    }
    
  
    
    
    
}
