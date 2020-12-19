/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Iservice;

import Entity.Utilisateur;
import java.util.List;



/**
 *
 * @author PC
 */
public interface IUtilisateurService {
 public void creerUtilisateur(Utilisateur u);
 public void modifierUtilisateur(Utilisateur u);
 public void supprimerUtilisateur(Utilisateur u);
 public List<Utilisateur> afficherUtilisateur();
    
}
