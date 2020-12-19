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
public class Utilisateur {
    private int id_utilisateur;
    private String pseudo_utilisateur;
    private String pass_utilisateur;
    private String nom_utilisateur;
    private String prenom_utilisateur;
    private int tel_utilisateur;
    private String mail_utilisateur;
    private String photo_utilisateur;
    private int statut_utilisateur;

    public Utilisateur() {
    }

    public Utilisateur(String pseudo_utilisateur, String pass_utilisateur, String nom_utilisateur, String prenom_utilisateur, int tel_utilisateur, String mail_utilisateur, String photo_utilisateur, int statut_utilisateur) {
        this.pseudo_utilisateur = pseudo_utilisateur;
        this.pass_utilisateur = pass_utilisateur;
        this.nom_utilisateur = nom_utilisateur;
        this.prenom_utilisateur = prenom_utilisateur;
        this.tel_utilisateur = tel_utilisateur;
        this.mail_utilisateur = mail_utilisateur;
        this.photo_utilisateur = photo_utilisateur;
        this.statut_utilisateur = statut_utilisateur;
    }

    public Utilisateur(int id_utilisateur, String pseudo_utilisateur, String pass_utilisateur, String nom_utilisateur, String prenom_utilisateur, int tel_utilisateur, String mail_utilisateur, String photo_utilisateur, int statut_utilisateur) {
        this.id_utilisateur = id_utilisateur;
        this.pseudo_utilisateur = pseudo_utilisateur;
        this.pass_utilisateur = pass_utilisateur;
        this.nom_utilisateur = nom_utilisateur;
        this.prenom_utilisateur = prenom_utilisateur;
        this.tel_utilisateur = tel_utilisateur;
        this.mail_utilisateur = mail_utilisateur;
        this.photo_utilisateur = photo_utilisateur;
        this.statut_utilisateur = statut_utilisateur;
    }

    public int getId_utilisateur() {
        return id_utilisateur;
    }

    public String getPseudo_utilisateur() {
        return pseudo_utilisateur;
    }

    public String getPass_utilisateur() {
        return pass_utilisateur;
    }

    public String getNom_utilisateur() {
        return nom_utilisateur;
    }

    public String getPrenom_utilisateur() {
        return prenom_utilisateur;
    }

    public int getTel_utilisateur() {
        return tel_utilisateur;
    }

    public String getMail_utilisateur() {
        return mail_utilisateur;
    }

    public String getPhoto_utilisateur() {
        return photo_utilisateur;
    }

    public int getStatut_utilisateur() {
        return statut_utilisateur;
    }

    public void setId_utilisateur(int id_utilisateur) {
        this.id_utilisateur = id_utilisateur;
    }

    public void setPseudo_utilisateur(String pseudo_utilisateur) {
        this.pseudo_utilisateur = pseudo_utilisateur;
    }

    public void setPass_utilisateur(String pass_utilisateur) {
        this.pass_utilisateur = pass_utilisateur;
    }

    public void setNom_utilisateur(String nom_utilisateur) {
        this.nom_utilisateur = nom_utilisateur;
    }

    public void setPrenom_utilisateur(String prenom_utilisateur) {
        this.prenom_utilisateur = prenom_utilisateur;
    }

    public void setTel_utilisateur(int tel_utilisateur) {
        this.tel_utilisateur = tel_utilisateur;
    }

    public void setMail_utilisateur(String mail_utilisateur) {
        this.mail_utilisateur = mail_utilisateur;
    }

    public void setPhoto_utilisateur(String photo_utilisateur) {
        this.photo_utilisateur = photo_utilisateur;
    }

    public void setStatut_utilisateur(int statut_utilisateur) {
        this.statut_utilisateur = statut_utilisateur;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + this.id_utilisateur;
        hash = 89 * hash + Objects.hashCode(this.pseudo_utilisateur);
        hash = 89 * hash + Objects.hashCode(this.pass_utilisateur);
        hash = 89 * hash + Objects.hashCode(this.nom_utilisateur);
        hash = 89 * hash + Objects.hashCode(this.prenom_utilisateur);
        hash = 89 * hash + this.tel_utilisateur;
        hash = 89 * hash + Objects.hashCode(this.mail_utilisateur);
        hash = 89 * hash + Objects.hashCode(this.photo_utilisateur);
        hash = 89 * hash + this.statut_utilisateur;
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
        final Utilisateur other = (Utilisateur) obj;
        if (this.id_utilisateur != other.id_utilisateur) {
            return false;
        }
        if (!Objects.equals(this.pseudo_utilisateur, other.pseudo_utilisateur)) {
            return false;
        }
        if (!Objects.equals(this.pass_utilisateur, other.pass_utilisateur)) {
            return false;
        }
        if (!Objects.equals(this.nom_utilisateur, other.nom_utilisateur)) {
            return false;
        }
        if (!Objects.equals(this.prenom_utilisateur, other.prenom_utilisateur)) {
            return false;
        }
        if (this.tel_utilisateur != other.tel_utilisateur) {
            return false;
        }
        if (!Objects.equals(this.mail_utilisateur, other.mail_utilisateur)) {
            return false;
        }
        if (!Objects.equals(this.photo_utilisateur, other.photo_utilisateur)) {
            return false;
        }
        if (this.statut_utilisateur != other.statut_utilisateur) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Utilisateur{" + "id_utilisateur=" + id_utilisateur + ", pseudo_utilisateur=" + pseudo_utilisateur + ", pass_utilisateur=" + pass_utilisateur + ", nom_utilisateur=" + nom_utilisateur + ", prenom_utilisateur=" + prenom_utilisateur + ", tel_utilisateur=" + tel_utilisateur + ", mail_utilisateur=" + mail_utilisateur + ", photo_utilisateur=" + photo_utilisateur + ", statut_utilisateur=" + statut_utilisateur + '}';
    }

   
}
