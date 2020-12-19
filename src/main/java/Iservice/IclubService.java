/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Iservice;

import Entity.club;
import java.util.List;

/**
 *
 * @author user
 */
public interface IclubService {
    public void creerClub(club Cl);
    public void modifierClub(club Cl);
    public void supprimerClub(club Cl);
    public List<club> afficherClub();
    public club rechercheClubParNom(String nom_club1);
    public club RechercherClubCById(int id_club);
    
}
