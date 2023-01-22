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
public class Reserva {
    
    String sala;
    String nombre;
    String telefono;
    Date fecha;
    String tipoEvento;
    int numeroPersonas;
    String tipoCocina;
    int numeroJornadas;
    boolean habitacionesAsistentes;
    int numeroHabitaciones;

    public Reserva(String sala, String nombre, String telefono, Date fecha,
            String tipoEvento, int numeroPersonas, String tipoCocina) {
        this.sala = sala;
        this.nombre = nombre;
        this.telefono = telefono;
        this.fecha = fecha;
        this.tipoEvento = tipoEvento;
        this.numeroPersonas = numeroPersonas;
        this.tipoCocina = tipoCocina;

    }

    public Reserva(String sala, String nombre, String telefono, Date fecha,
            String tipoEvento, int numeroPersonas, String tipoCocina,
            int numeroJornadas, boolean habitacionesAsistentes, 
            int numeroHabitaciones) {
        this.sala = sala;
        this.nombre = nombre;
        this.telefono = telefono;
        this.fecha = fecha;
        this.tipoEvento = tipoEvento;
        this.numeroPersonas = numeroPersonas;
        this.tipoCocina = tipoCocina;
        this.numeroJornadas = numeroJornadas;
        this.habitacionesAsistentes = habitacionesAsistentes;
        this.numeroHabitaciones = numeroHabitaciones;
    }
    
    
    
    
    
    
    
    
}
