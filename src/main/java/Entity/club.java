/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.util.Objects;


public class club {
    private int id_club;
    private String nom_club;
    private String description_club;
    
    private String logo_club;
    private categoriec Categoriec;
    private int nbr_participants;

    public club() {
    }

    public club(int id_club, String nom_club, String description_club, String logo_club, categoriec Categoriec, int nbr_participants) {
        this.id_club = id_club;
        this.nom_club = nom_club;
        this.description_club = description_club;
        this.logo_club = logo_club;
        this.Categoriec = Categoriec;
        this.nbr_participants = nbr_participants;
    }

    public club(int id_club, String nom_club, String description_club, String logo_club, categoriec Categoriec) {
        this.id_club = id_club;
        this.nom_club = nom_club;
        this.description_club = description_club;
        this.logo_club = logo_club;
        this.Categoriec = Categoriec;
    }

    public club(String nom_club, String description_club, String logo_club, categoriec Categoriec) {
        this.nom_club = nom_club;
        this.description_club = description_club;
        this.logo_club = logo_club;
        this.Categoriec = Categoriec;
    }

    public int getId_club() {
        return id_club;
    }

    public String getNom_club() {
        return nom_club;
    }

    public String getDescription_club() {
        return description_club;
    }

    public String getLogo_club() {
        return logo_club;
    }

    public categoriec getCategoriec() {
        return Categoriec;
    }

    public int getNbr_participants() {
        return nbr_participants;
    }

    public void setId_club(int id_club) {
        this.id_club = id_club;
    }

    public void setNom_club(String nom_club) {
        this.nom_club = nom_club;
    }

    public void setDescription_club(String description_club) {
        this.description_club = description_club;
    }

    public void setLogo_club(String logo_club) {
        this.logo_club = logo_club;
    }

    public void setCategoriec(categoriec Categoriec) {
        this.Categoriec = Categoriec;
    }

    public void setNbr_participants(int nbr_participants) {
        this.nbr_participants = nbr_participants;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final club other = (club) obj;
        if (this.id_club != other.id_club) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "club{" + "id_club=" + id_club + ", nom_club=" + nom_club + ", description_club=" + description_club + ", logo_club=" + logo_club + ", Categoriec=" + Categoriec + ", nbr_participants=" + nbr_participants + '}';
    }

  
    

   

  



     public String valid() {
            if(nom_club.equals("")) return "nom_club";
            if(description_club.equals("")) return "description_club";
            if(logo_club.equals("")) return "logo_club";
        return null;
    }


 
    
}