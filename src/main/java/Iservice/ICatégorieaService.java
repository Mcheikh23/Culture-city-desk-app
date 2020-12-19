/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Iservice;

import Entity.Article;
import Entity.Catégorie;
import Entity.Commande;
import Entity.LigneCommande;
import java.util.List;

/**
 *
 * @author PC
 */
public interface ICatégorieaService {
    public void creerCatégorie(Catégorie c);
    public void modifierCatégorie(Catégorie c);
    public void supperimerCatégorie(Catégorie c);
    public List <Catégorie> afficherCatégorie( );
    
}
