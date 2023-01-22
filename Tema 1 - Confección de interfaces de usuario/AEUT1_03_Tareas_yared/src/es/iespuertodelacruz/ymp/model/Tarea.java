/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.iespuertodelacruz.ymp.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Yared
 */
public class Tarea implements Serializable{
    
    String description;
    Date date;
    boolean realizada;
    
    
    public Tarea(String description, Date date){
        
        this.description = description;
        this.date = date;
        this.realizada = false;
        
    }

    public String getDescription() {
        return description;
    }

    public String getFormatDate() {
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String formatDate = sdf.format(date);
        
        return formatDate;
        
    }

    public Date getDate() {
        return date;
    }
    
    
    
    public String toString(){
        
        if(realizada){
           return "- " + "[✓] " + description + " ["
                        + getFormatDate() + "]\n"; 
        }else{
        
        return "- " + "[✘] " + description + " ["
                        + getFormatDate() + "]\n";
        }
    }

    public void setRealizada(boolean realizada) {
        this.realizada = realizada;
    }
    
    public void modTarea(String descripcion, Date fecha){
        
        this.description = descripcion;
        this.date = fecha;
        
    }

    public boolean isRealizada() {
        return realizada;
    }
    
    
    
    
    
}
