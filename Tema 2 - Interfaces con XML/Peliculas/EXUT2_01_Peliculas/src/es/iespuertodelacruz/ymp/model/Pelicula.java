/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.iespuertodelacruz.ymp.model;

import java.util.Date;

/**
 *
 * @author Yared
 */
public class Pelicula {
    
    Integer id;
    String title;
    String release_date;
    Integer duracion;
    String cover_url;
    String descripcion;

    public Pelicula(Integer id, String title, String release_date, Integer duracion, String cover_url, String descripcion) {
        this.id = id;
        this.title = title;
        this.release_date = release_date;
        this.duracion = duracion;
        this.cover_url = cover_url;
        this.descripcion = descripcion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public Integer getDuracion() {
        return duracion;
    }

    public void setDuracion(Integer duracion) {
        this.duracion = duracion;
    }

    public String getCover_url() {
        return cover_url;
    }

    public void setCover_url(String cover_url) {
        this.cover_url = cover_url;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public String reduceInfo(){
        
        String respuesta = "";
        
        respuesta += "Titulo: " + title + " Fecha lanzamiento: " + release_date +
                " Duracion: " + duracion;
        
        return respuesta;
        
    }

    @Override
    public String toString() {
        return "Pelicula{" + "id=" + id + ", title=" + title + ", release_date=" 
                + release_date + ", duracion=" + duracion + ", cover_url=" + 
                cover_url + ", descripcion=" + descripcion + '}';
    }
    
    public String getPeliculaFile(){
        
        return id + ";" + title + ";" + release_date + ";" + duracion + ";" + cover_url + ";" + descripcion;
        
    }
    

    
}
