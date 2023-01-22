package es.iespuertodelacruz.ymp.model;

public class Ingrediente {

    String nombre;

    public Ingrediente(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Ingrediente{" + "nombre=" + nombre + '}';
    }
    
    
}
