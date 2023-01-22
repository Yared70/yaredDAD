/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.iespuertodelacruz.ymp.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import static java.util.stream.Collectors.toList;

/**
 *
 * @author Yared
 */
public class GestorPeliculas {
    
    ArrayList<Pelicula> peliculas;

    public GestorPeliculas() {
        this.peliculas = new ArrayList<Pelicula>();
    }

    public ArrayList<Pelicula> getPeliculas() {
        List<Pelicula> collect = peliculas.stream().sorted((a, b) -> a.getTitulo().compareTo(b.getTitulo())).collect(toList());
        return (ArrayList<Pelicula>) collect;
    }

    @Override
    public String toString() {
        String respuesta = "";
        
        for (Pelicula pelicula : peliculas) {
            respuesta += pelicula + "\n"; 
        }
        
        return respuesta;
        
    }
    
    public void addPelicula(Pelicula pelicula){
        
        peliculas.add(pelicula);
        
    }
    
    public void eliminarPelicula(Pelicula pelicula){
        
        peliculas.remove(pelicula);
        
    }
    
    public void modPelicula(Pelicula peliculaAnterior, Pelicula PeliculaNueva){
        
        peliculas.remove(peliculaAnterior);
        peliculas.add(PeliculaNueva);
        
    }
    
    public Pelicula findPelicula(String titulo){
        
        for (Pelicula pelicula : peliculas) {
            if(pelicula.getTitulo().equals(titulo)){
                return pelicula;
            }
        }
        
        return null;
        
    }
    
    
    
    
    
    
            
    
}
