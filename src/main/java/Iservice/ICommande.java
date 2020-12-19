/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Iservice;

import Entity.Commande;
import java.util.List;

/**
 *
 * @author PC
 */
public interface ICommande {
     public void creerCommande(Commande cm);
    public void modifierCommande(Commande cm);
    public void supperimerCommande(Commande cm);
    public List <Commande> afficherCommande( );
    
}
