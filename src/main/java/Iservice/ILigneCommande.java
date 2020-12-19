/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Iservice;

import Entity.Commande;
import Entity.LigneCommande;
import java.util.List;

/**
 *
 * @author PC
 */
public interface ILigneCommande {
       public void creerLigneCommande(LigneCommande l);
    public void modifierLigneCommande(LigneCommande l);
    public void supperimerLigneCommande(LigneCommande l);
    public List <LigneCommande> afficherLigneCommande();
    
    
}
