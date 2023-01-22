/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.iespuertodelacruz.ymp.model;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 *
 * @author yared
 */
public class GestorFichero {
        
    public GestorFichero(){}
    
    public GestorTarea leerFichero(File archivo){
        
        GestorTarea listaTareas = new GestorTarea();
        
        try(
                FileInputStream fis = new FileInputStream(archivo);
                BufferedInputStream bis = new BufferedInputStream(fis);
                ObjectInputStream ois = new ObjectInputStream(bis);
                ){
        
            boolean finDeFichero = false;
            do{
                try{
                    listaTareas = (GestorTarea)ois.readObject(); 
                }catch(EOFException ex){
                    finDeFichero = true;
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            }while(!finDeFichero);
        
    }catch (FileNotFoundException ex){} catch (IOException ex) {
            ex.printStackTrace();
        }
    
        return listaTareas;
    }
    
    
    
    public void escribirFichero(File archivo, GestorTarea gt){
        
        GestorTarea listaTareas = gt;
        
        try(
                FileOutputStream fos = new FileOutputStream(archivo);
                BufferedOutputStream bos = new BufferedOutputStream(fos);
                ObjectOutputStream oos = new ObjectOutputStream(bos);
                
                ){
            oos.writeObject(listaTareas);
            oos.close();
    
        
        }catch(FileNotFoundException ex){
            ex.printStackTrace();
        }catch(IOException ex){
            ex.printStackTrace();
        }
        
    }
        
}
