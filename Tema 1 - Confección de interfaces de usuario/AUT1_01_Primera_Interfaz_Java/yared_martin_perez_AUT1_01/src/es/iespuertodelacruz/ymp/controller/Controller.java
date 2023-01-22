/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.iespuertodelacruz.ymp.controller;

import es.iespuertodelacruz.ymp.model.GestorFichero;
import es.iespuertodelacruz.ymp.view.View;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JTextArea;
import javax.swing.JTextField;


/**
 *
 * @author Yared
 */
public class Controller implements ActionListener{
    
    View vista;
    GestorFichero gf;
    File archivo;
    
    public Controller(View vista){
        
        this.vista = vista;
        this.gf = new GestorFichero();
        this.vista.btnEnviar.addActionListener(this);
        this.vista.itemAbrir.addActionListener(this);
        this.vista.itemGuardar.addActionListener(this);

        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource() == this.vista.btnEnviar){
            enviar();
        }else if(e.getSource() == this.vista.itemAbrir){
            abrir();
        }else if(e.getSource() == this.vista.itemGuardar){
            guardar();
        }
        
    }
    
    public void enviar(){
        
        JTextArea txf = this.vista.txf;

        String texto = this.vista.txtSalida.getText();
        
        if (txf.getText().length() < 1){
            txf.setText(texto);
        }else{
            txf.setText(txf.getText() + "\n" + texto);
        }
        
        this.vista.txtSalida.setText("");
        
    }
    
    public void abrir(){
        
        JFileChooser fileChooser = new JFileChooser();
        int seleccion = fileChooser.showOpenDialog(null);
        archivo = fileChooser.getSelectedFile();
        vista.txf.setText(gf.leerFichero(archivo));
        
    }
    
    public void guardar(){
        
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setApproveButtonText("Guardar como...");
        fileChooser.showOpenDialog(null);
        archivo = new File(fileChooser.getSelectedFile() + "");
        gf.escribirFichero(archivo, vista.txf.getText());
        
    }
    
    
    
}
