/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author user
 */
public class categoriec {
    private int id_categorieC;
    private String nom_categorieC;

    public categoriec() {
    }

    public categoriec(int id_categorieC, String nom_categorieC) {
        this.id_categorieC = id_categorieC;
        this.nom_categorieC = nom_categorieC;
    }

    public categoriec(String nom_categorieC) {
        this.nom_categorieC = nom_categorieC;
    }

    public int getId_categorieC() {
        return id_categorieC;
    }

    public String getNom_categorieC() {
        return nom_categorieC;
    }

    public void setId_categorieC(int id_categorieC) {
        this.id_categorieC = id_categorieC;
    }

    public void setNom_categorieC(String nom_categorieC) {
        this.nom_categorieC = nom_categorieC;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.id_categorieC;
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
        final categoriec other = (categoriec) obj;
        if (this.id_categorieC != other.id_categorieC) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "categoriec{" + "id_categorieC=" + id_categorieC + ", nom_categorieC=" + nom_categorieC + '}';
    }
    
    
}