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
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author yared
 */
public class GestorFichero {
    
    public GestorFichero(){}
    
    public List<String> leerFichero(){
        
        File archivo = new File("src/es/iespuertodelacruz/ymp/assets/recetas.txt");
        
        List<String> recetas = null;
        
        try(
                BufferedReader br = Files.newBufferedReader(archivo.toPath());
                ){
            recetas = br.lines()
                    .collect(Collectors.toList());
          
        
        
    }catch (IOException ex){}
    
        return recetas;
    }
    
    
    
    public void escribirFichero(String texto){
        
        File archivo = new File("src/es/iespuertodelacruz/ymp/assets/recetas.txt");
        
                try ( BufferedWriter bw = Files.newBufferedWriter(archivo.toPath(),
                StandardOpenOption.TRUNCATE_EXISTING,
                StandardOpenOption.CREATE,
                StandardOpenOption.WRITE
        );) {

            bw.write(texto);

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        
    }
    
}
