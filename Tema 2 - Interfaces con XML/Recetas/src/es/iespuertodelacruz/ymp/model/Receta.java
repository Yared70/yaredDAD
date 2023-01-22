package es.iespuertodelacruz.ymp.model;

import java.util.ArrayList;
import java.util.List;

public class Receta {

    String titulo;
    String autor;
    String tipo;
    List<Ingrediente> ingredientes;
    List<Paso> pasos;
    Integer numeroComensales;
    Integer tiempoPreparacion;
    Integer calorias;
    String imagenPath;

    public Receta(String titulo, String autor, String tipo, List<Ingrediente> ingredientes, List<Paso> pasos, Integer numeroComensales, Integer tiempoPreparacion, Integer calorias, String imagenPath) {

        this.titulo = titulo;
        this.autor = autor;
        this.tipo = tipo;
        this.numeroComensales = numeroComensales;
        this.tiempoPreparacion = tiempoPreparacion;
        this.calorias = calorias;
        this.imagenPath = imagenPath;
        this.ingredientes = ingredientes;
        this.pasos = pasos;

    }
    
    public String getRecetaSimplificada(){
        
        return "Nombre: " + titulo + 
                " Autor: " + autor + 
                " Tipo: " + tipo + 
                " Tiempo de preparacion: " + tiempoPreparacion + " minutos" + 
                " Calorias: " + calorias;
               
        
    }
    
    public String getRecetaFileFormat(){
        
        String respuesta = "";
        respuesta+= titulo + ";" + autor + ";" + tipo + ";";
        for (Ingrediente ingrediente : ingredientes) {
            respuesta += ingrediente.getNombre() + ",";
        }
        respuesta += "\b;";
        for (Paso paso : pasos) {
            respuesta += paso.getDescripcion() + ",";
        }
        respuesta += "\b;";
        respuesta += numeroComensales + ";" + tiempoPreparacion + ";" + calorias + ";" + imagenPath;
        
        return respuesta;
        
    }
    
    public String getIngredientesView(){
        
        String respuesta = "";
        
        for (Ingrediente ingrediente : ingredientes) {
            respuesta += ingrediente.getNombre() + "\n";
        }
        
        return respuesta;
    }
    
    public String getPasosView(){
        
        String respuesta = "";
        
        for (Paso paso : pasos) {
            respuesta += paso.getDescripcion() + "\n";
            
        }
        
        return respuesta;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public List<Ingrediente> getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(List<Ingrediente> ingredientes) {
        this.ingredientes = ingredientes;
    }

    public List<Paso> getPasos() {
        return pasos;
    }

    public void setPasos(List<Paso> pasos) {
        this.pasos = pasos;
    }

    public Integer getNumeroComensales() {
        return numeroComensales;
    }

    public void setNumeroComensales(Integer numeroComensales) {
        this.numeroComensales = numeroComensales;
    }

    public Integer getTiempoPreparacion() {
        return tiempoPreparacion;
    }

    public void setTiempoPreparacion(Integer tiempoPreparacion) {
        this.tiempoPreparacion = tiempoPreparacion;
    }

    public Integer getCalorias() {
        return calorias;
    }

    public void setCalorias(Integer calorias) {
        this.calorias = calorias;
    }

    public String getImagenPath() {
        return imagenPath;
    }

    public void setImagenPath(String imagenPath) {
        this.imagenPath = imagenPath;
    }

    @Override
    public String toString() {
        return "Receta{" + "titulo=" + titulo + ", autor=" + autor + ", tipo=" 
                + tipo + ", ingredientes=" + ingredientes + ", pasos=" + pasos 
                + ", numeroComensales=" + numeroComensales + ", "
                + "tiempoPreparacion=" + tiempoPreparacion + ", calorias=" 
                + calorias + ", imagenPath=" + imagenPath + '}';
    }
    
    
    
}
