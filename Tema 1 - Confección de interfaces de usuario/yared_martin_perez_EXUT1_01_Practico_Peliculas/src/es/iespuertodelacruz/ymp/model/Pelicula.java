/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.iespuertodelacruz.ymp.model;

import java.util.ArrayList;

/**
 *
 * @author Yared
 */
public class Pelicula {
    
    String titulo;
    String director;
    ArrayList<Actor> actores;
    ArrayList<Premio> premios;
    int numeroSalas;
    String duracion;

    public Pelicula(String titulo, String director, ArrayList<Actor> actores, ArrayList<Premio> premios, int numeroSalas, String duracion) {
        this.titulo = titulo;
        this.director = director;
        this.actores = actores;
        this.premios = premios;
        this.numeroSalas = numeroSalas;
        this.duracion = duracion;
    }

    public Pelicula() {
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDirector() {
        return director;
    }

    public ArrayList<Actor> getActores() {
        return actores;
    }

    public ArrayList<Premio> getPremios() {
        return premios;
    }

    public int getNumeroSalas() {
        return numeroSalas;
    }

    public String getDuracion() {
        return duracion;
    }
    
    public String getActoresStr(){
        
        String respuesta = "";
        
        for (Actor actor : actores) {
            respuesta += actor.getNombre() + ",\n";
        }
        
        return respuesta;
        
    }
    
        public String getActoresArchivo(){
        
        String respuesta = "";
        
        for (Actor actor : actores) {
            respuesta += actor.getNombre() + ",";
        }
        respuesta = respuesta.substring(0, respuesta.length()-1);
        
        return respuesta;
        
    }
    
    public String getPremiosStr(){
        
        String respuesta = "";
        
        for (Premio premio : premios) {
            respuesta += premio.getNombre() + ", \n";
        }
        
        return respuesta;
        
        
    }
    
    public String getPremiosArchivo(){
        
        String respuesta = "";
        
        for (Premio premio : premios) {
            respuesta += premio.getNombre() + ",";
        }
        respuesta = respuesta.substring(0, respuesta.length()-1);
        
        return respuesta;
        
        
    }

    @Override
    public String toString() {
        return "Pelicula{" + "titulo=" + titulo + ", director=" + director + ", duracion=" + duracion + '}';
    }
    
    

    
    
}
