/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.util.Objects;

/**
 *
 * @author Mnif
 */
public class Favoris {
    private int id_favoris; 
    private Utilisateur utilisateur;
     private Film film;

    public Favoris() {
    }

    public Favoris(int id_favoris, Utilisateur utilisateur, Film film) {
        this.id_favoris = id_favoris;
        this.utilisateur = utilisateur;
        this.film = film;
    }

    public Favoris(Utilisateur utilisateur, Film film) {
        this.utilisateur = utilisateur;
        this.film = film;
    }

   

    public int getId_favoris() {
        return id_favoris;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public Film getFilm() {
        return film;
    }

    public void setId_favoris(int id_favoris) {
        this.id_favoris = id_favoris;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + this.id_favoris;
        hash = 67 * hash + Objects.hashCode(this.utilisateur);
        hash = 67 * hash + Objects.hashCode(this.film);
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
        final Favoris other = (Favoris) obj;
        if (this.id_favoris != other.id_favoris) {
            return false;
        }
        if (!Objects.equals(this.utilisateur, other.utilisateur)) {
            return false;
        }
        if (!Objects.equals(this.film, other.film)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Favoris{" + "id_favoris=" + id_favoris + ", utilisateur=" + utilisateur + ", film=" + film + '}';
    }

   
     

}