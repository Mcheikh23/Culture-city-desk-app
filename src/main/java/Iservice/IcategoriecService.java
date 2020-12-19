/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Iservice;

import Entity.categoriec;
import java.util.List;

/**
 *
 * @author user
 */
public interface IcategoriecService {
     public void creerCategoriec(categoriec Categoriec);
    public void modifierCategoriec(categoriec Categoriec);
    public void supprimerCategoriec(categoriec Categoriec);
    public List<categoriec> afficherCategoriec();
    public categoriec RechercherCategorieCById(int id_categorieC);
    public categoriec RechercherCategorieCByNom(String nom_categorieC);
    
}