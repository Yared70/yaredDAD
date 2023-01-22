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
    
    public String leerFichero(){
        
        File archivo = new File("/tmp/peliculas.txt");
        
        String texto = null;
        
        try(
                BufferedReader br = Files.newBufferedReader(archivo.toPath());
                ){
            texto = br.lines()
                    .collect(Collectors.joining());
          
        
        
    }catch (IOException ex){}
    
        return texto;
    }
    
    
    
    public void escribirFichero(String texto){
        
        File archivo = new File("/tmp/peliculas.txt");
        
                try ( BufferedWriter bw = Files.newBufferedWriter(archivo.toPath(),
                StandardOpenOption.APPEND,
                StandardOpenOption.CREATE,
                StandardOpenOption.WRITE
        );) {

            bw.write(texto);

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        
    }

        
}
