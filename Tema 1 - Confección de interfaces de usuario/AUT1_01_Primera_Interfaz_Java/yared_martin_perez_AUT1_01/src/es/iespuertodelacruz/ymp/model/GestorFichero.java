/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.iespuertodelacruz.ymp.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.stream.Collectors;

/**
 *
 * @author yared
 */
public class GestorFichero {
        
    public GestorFichero(){}
    
    public String leerFichero(File archivo){
        
        String texto = null;
        
        try(
                BufferedReader br = Files.newBufferedReader(archivo.toPath());
                ){
            texto = br.lines()
                    .collect(Collectors.joining(System.lineSeparator()));
        
        
    }catch (IOException ex){}
    
        return texto;
    }
    
    
    public void escribirFichero(File archivo, String texto){
        
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
