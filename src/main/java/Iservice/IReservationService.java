/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Iservice;

import Entity.Reservation;
import Entity.Salle;
import java.util.List;

/**
 *
 * @author Mnif
 */
public interface IReservationService {
    public void creerReservation(Reservation r);

 public void modifierReservation(Reservation r);
 public void supprimerReservation(Reservation r);
 public List<Reservation> afficherReservation();
}
