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
public class AEUT1_02_Componentes {

   
    public static void main(String[] args) {
       
        View vista = new View();
               
        Controller controlador = new Controller(vista);
        
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                vista.setVisible(true);
            }
        });
        
        
    }
    
}
