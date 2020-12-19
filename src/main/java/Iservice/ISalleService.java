/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Iservice;

import Entity.Salle;
import java.util.List;



/**
 *
 * @author PC
 */
public interface ISalleService {
 public void creerSalle(Salle s);
 public void modifierSalle(Salle s,String num);
 public void supprimerSalle(Salle s);
 public List<Salle> afficherSalle();
    
}


