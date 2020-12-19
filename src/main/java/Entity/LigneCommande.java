/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author PC
 */
public class LigneCommande {
    private int idlc;
    private int iduser;
    
   
    private int idarticle;
    private int qte;

    @Override
    public String toString() {
        return "LigneCommande{"  + ", idarticle=" + idarticle + ", qte=" + qte + '}';
    }

    public LigneCommande(int idlc, int iduser, int idarticle, int qte) {
        this.idlc = idlc;
        this.iduser = iduser;
        this.idarticle = idarticle;
        this.qte = qte;
    }

    public LigneCommande(int idlc, int idarticle, int qte) {
        this.idlc = idlc;
        this.idarticle = idarticle;
        this.qte = qte;
    }
    

    public int getIdlc() {
        return idlc;
    }

    public void setIdlc(int idlc) {
        this.idlc = idlc;
    }

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }
    

   
    public int getIdarticle() {
        return idarticle;
    }

    public void setIdarticle(int idarticle) {
        this.idarticle = idarticle;
    }

    public int getQte() {
        return qte;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }

    public LigneCommande() {
    }

    public LigneCommande( int idarticle, int qte) {
        
        this.idarticle = idarticle;
        this.qte = qte;
    }
    
}
