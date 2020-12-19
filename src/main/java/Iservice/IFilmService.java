/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Iservice;

import Entity.Film;
import java.util.List;



/**
 *
 * @author PC
 */
public interface IFilmService {
 public void creerFilm(Film f);
 public void modifierFilm(Film f);
 public void supprimerFilm(Film f);
 public List<Film> afficherFilm();
    
}

