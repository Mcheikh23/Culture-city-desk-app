/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.sql.Date;
import java.time.LocalDate;

/**
 *
 * @author PC
 */
public class Commande {
    private int iduser;
    
    private int idCM;
     private LocalDate date;
    private float montant;

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    public Commande(int iduser, int idCM, LocalDate date, float montant) {
        this.iduser = iduser;
        this.idCM = idCM;
        this.date = date;
        this.montant = montant;
    }
    

    public int getIdCM() {
        return idCM;
    }

    public void setIdCM(int idCM) {
        this.idCM = idCM;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public float getMontant() {
        return montant;
    }

    public void setMontant(float montant) {
        this.montant = montant;
    }

    public Commande(int idCM, LocalDate date, float montant) {
        this.idCM = idCM;
        this.date = date;
        this.montant = montant;
    }

    public Commande(LocalDate date, float montant) {
        this.date = date;
        this.montant = montant;
    }

    public Commande() {
    }

    public Date convertUtilToSql(java.util.Date uDate) {
       java.sql.Date sDate = new java.sql.Date(uDate.getTime());
        return sDate;
    }
   
    
}