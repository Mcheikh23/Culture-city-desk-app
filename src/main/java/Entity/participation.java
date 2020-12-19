/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;
public class participation {
    private int id_participation;
    private club Club ;

    public participation(int id_participation, club Club) {
        this.id_participation = id_participation;
       
        this.Club = Club;
    }

    public participation(club Club) {
       
        this.Club = Club;
    }

  

    public participation() {
       
    }

    public int getId_participation() {
        return id_participation;
    }

   
    public club getClub() {
        return Club;
    }

    public void setId_participation(int id_participation) {
        this.id_participation = id_participation;
    }

  

    public void setClub(club Club) {
        this.Club = Club;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + this.id_participation;
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
        final participation other = (participation) obj;
        if (this.id_participation != other.id_participation) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "participation{" + "id_participation=" + id_participation + ", Club=" + Club + '}';
    }
}


   