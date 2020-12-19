/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilitaire;

import java.io.IOException;
/**
 *
 * @author arafe
 */
public class copyImages {

    public static void deplacerVers(String path, String copyTo) {
        
            try {
                String[] args = { "CMD", "/C", "COPY", "/Y", path, copyTo };
                Process p = Runtime.getRuntime().exec(args);
                p.waitFor();
                System.out.println("executé");
            } catch (IOException | InterruptedException e) { System.out.println(e.getMessage());
            }
            
    }
    
}
