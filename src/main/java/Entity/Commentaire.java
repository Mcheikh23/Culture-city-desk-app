/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.sql.*;

/**
 *
 * @author hp
 */
public class Commentaire {

    private int id_com;
    private int id_f;
    private int id_user;
    private String date_com;
    private String description_com;

    public Commentaire() {
    }

    public Commentaire(int id_com, int id_f, int id_user, String description_com) {
        this.id_com = id_com;
        this.id_f = id_f;
        this.id_user = id_user;
        this.description_com = description_com;
    }

    public Commentaire(int id_f, int id_user, String description_com) {
        this.id_f = id_f;
        this.id_user = id_user;
        this.description_com = description_com;
    }

    public Commentaire(int id_com, int id_f, int id_user, String date_com, String description_com) {
        this.id_com = id_com;
        this.id_f = id_f;
        this.id_user = id_user;
        this.date_com = date_com;
        this.description_com = description_com;
    }

    public int getId_com() {
        return id_com;
    }

    public void setId_com(int id_com) {
        this.id_com = id_com;
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

    public String getDate_com() {
        return date_com;
    }

    public void setDate_com(String date_com) {
        this.date_com = date_com;
    }

    public String getDescription_com() {
        return description_com;
    }

    public void setDescription_com(String description_com) {
        this.description_com = description_com;
    }

  
}

