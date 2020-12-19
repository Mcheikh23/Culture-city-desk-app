/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.sql.*;
import java.text.SimpleDateFormat;

/**
 *
 * @author hp
 */
public class Sujet {

    private int id_f;
    private int id_user;
    private int Etat;
    private String description_f;
    private String date;
    private int nbre_jaime;

    public Sujet() {
    }

    public Sujet(int id_user, String description_f) {
        this.id_user = id_user;
        this.description_f = description_f;
    }

    public Sujet(int id_f, int id_user, String description_f, String date) {
        this.id_f = id_f;
        this.id_user = id_user;
        this.description_f = description_f;
        this.date = date;
    }

    public Sujet(int id_f, int id_user, int Etat, String description_f, String date, int nbre_jaime) {
        this.id_f = id_f;
        this.id_user = id_user;
        this.Etat = Etat;
        this.description_f = description_f;
        this.date = date;
        this.nbre_jaime = nbre_jaime;
    }

    public int getId_f() {
        return id_f;
    }

    public void setId_f(int id_f) {
        this.id_f = id_f;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getEtat() {
        return Etat;
    }

    public void setEtat(int Etat) {
        this.Etat = Etat;
    }

    public String getDescription_f() {
        return description_f;
    }

    public void setDescription_f(String description_f) {
        this.description_f = description_f;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getNbre_jaime() {
        return nbre_jaime;
    }

    public void setNbre_jaime(int nbre_jaime) {
        this.nbre_jaime = nbre_jaime;
    }

    

    
    
}
