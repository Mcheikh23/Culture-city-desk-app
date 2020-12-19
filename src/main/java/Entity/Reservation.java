
package Entity;
import Entity.Film;
import Entity.Salle;
import Entity.Utilisateur;
import java.sql.Date;
import java.util.Objects;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*package Entities;

import java.util.Objects;

/**
 *
 * @author Mnif
 */
public class Reservation {
    private int id_reservation;
    private Utilisateur Utilisateur;
    private  Seance seance;
    private int nombre_reservation;

    public Reservation() {
    }

    public Reservation(int id_reservation, Utilisateur Utilisateur, Seance seance, int nombre_reservation) {
        this.id_reservation = id_reservation;
        this.Utilisateur = Utilisateur;
        this.seance = seance;
        this.nombre_reservation = nombre_reservation;
    }

    public Reservation(Utilisateur Utilisateur, Seance seance, int nombre_reservation) {
        this.Utilisateur = Utilisateur;
        this.seance = seance;
        this.nombre_reservation = nombre_reservation;
    }

    public int getId_reservation() {
        return id_reservation;
    }

    public Utilisateur getUtilisateur() {
        return Utilisateur;
    }

    public Seance getSeance() {
        return seance;
    }

    public int getNombre_reservation() {
        return nombre_reservation;
    }

    public void setId_reservation(int id_reservation) {
        this.id_reservation = id_reservation;
    }

    public void setUtilisateur(Utilisateur Utilisateur) {
        this.Utilisateur = Utilisateur;
    }

    public void setSeance(Seance seance) {
        this.seance = seance;
    }

    public void setNombre_reservation(int nombre_reservation) {
        this.nombre_reservation = nombre_reservation;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + this.id_reservation;
        hash = 61 * hash + Objects.hashCode(this.Utilisateur);
        hash = 61 * hash + Objects.hashCode(this.seance);
        hash = 61 * hash + this.nombre_reservation;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Reservation other = (Reservation) obj;
        if (this.id_reservation != other.id_reservation) {
            return false;
        }
        if (!Objects.equals(this.Utilisateur, other.Utilisateur)) {
            return false;
        }
        if (!Objects.equals(this.seance, other.seance)) {
            return false;
        }
        if (this.nombre_reservation != other.nombre_reservation) {
            return false;
        }
        return true;
    }

    
    @Override
    public String toString() {
        return "Reservation{" + "id_reservation=" + id_reservation + ", Utilisateur=" + Utilisateur + ", seance=" + seance + ", nombre_reservation=" + nombre_reservation + '}';
    }



    
    


   
}
