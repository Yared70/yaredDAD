/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.iespuertodelacruz.ymp;

import es.iespuertodelacruz.ymp.controller.Controller;
import es.iespuertodelacruz.ymp.view.View;

/**
 *
 * @author Yared
 */
public class Yared_martin_perez_EXUT1_01_Practico_Peliculas {

  
   public static void main(String args[]) {
       
        View vista = new View();
        Controller controlador = new Controller(vista);
       
       
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                vista.setVisible(true);
            }
        });
    }
    
}
