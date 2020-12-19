/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Iservice;

import Entity.participation;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author user
 */
public interface IparticipationService {
    public void participer(participation p) throws SQLException;
    public void annulerparticiper(participation p) throws SQLException;
     public List<participation> afficherParticipation();
    
}
