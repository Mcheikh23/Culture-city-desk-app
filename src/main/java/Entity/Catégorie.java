/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.sql.PreparedStatement;

/**
 *
 * @author PC
 */
public class Catégorie {
    private int id_cat;
    private String nom_cat;
  
     public Catégorie(String nom ){
    this.nom_cat=nom;
    
    }
        public Catégorie (int id, String nom) {
        this.id_cat = id;
          this.nom_cat=nom;
          
     
    }

    public Catégorie() {
     
    }
      public int getId() {
        return id_cat;
    }

    public void setId(int id) {
        this.id_cat = id;
    }
      public String getNomC(){
        return nom_cat;
    }

    public void setNomC(String nom) {
        this.nom_cat= nom;
    }
   
      @Override
    public String toString() {
        return "Catégorie{" + "id=" + id_cat + ", nom=" + nom_cat +"}";
    }
      @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Catégorie other = (Catégorie) obj;
        if (this.id_cat != other.id_cat) {
            return false;
        }
        return true;
    }

   
    
    
}
