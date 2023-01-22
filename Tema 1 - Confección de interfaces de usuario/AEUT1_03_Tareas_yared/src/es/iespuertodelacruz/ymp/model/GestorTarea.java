/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.iespuertodelacruz.ymp.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Yared
 */
public class GestorTarea implements Serializable{

    List<Tarea> tareas;

    public GestorTarea() {

        tareas = new ArrayList<Tarea>();

    }

    public void addTarea(Tarea tarea) {

        tareas.add(tarea);

    }

    @Override
    public String toString() {

        String respuesta = "";

        for (Tarea tarea : tareas) {
            respuesta += "- " + tarea.getDescription() + " ["
                    + tarea.getFormatDate() + "]\n";
        }

        return respuesta;

    }

    public String showHistorial() {

        String respuesta = "";

        for (Tarea tarea : tareas) {

            if (tarea.date.getTime() < new Date().getTime() || tarea.isRealizada()) {

                respuesta += tarea.toString();
            }
        }

        return respuesta;

    }
    
    public List<Tarea> tareasSinFinalizar(){
        
        List<Tarea> tareasRes = new ArrayList<>();

        for (Tarea tarea : tareas) {

            if (tarea.date.getTime() > new Date().getTime() && !tarea.isRealizada()) {

                tareasRes.add(tarea);
            }
        }

        return tareasRes;
        
    }

    public List<Tarea> getTareas() {
        return tareas;
    }
    
    
    public Tarea findTask(String descripcion){
        
        for (Tarea tarea : tareas) {
            if(tarea.getDescription().equals(descripcion)){
                return tarea;
            }
        }
        
        return null;
        
        
        
    }
    
    public Tarea findTaskDate(String date){
        
        for (Tarea tarea : tareas) {
            if(tarea.getFormatDate().equals(date.toString())){
                return tarea;
            }
        }
        return null;
        
    }
    
    
    public void modTarea(Tarea tarea, String descripcion, Date fecha){
        
        tarea.modTarea(descripcion, fecha);
        
        
    }
    
    public void eliminarTarea(String descripcion){
        
        Iterator<Tarea> iterator = tareas.iterator();
        while(iterator.hasNext()){
            if ( iterator.next().getDescription().equals(descripcion)){
                iterator.remove();
            }
            
        }
        
        
    }
    
    public void completarTarea(Tarea tarea){
        
        tarea.setRealizada(true);
        
        
    }
    
   
    
    

}
