package es.iespuertodelacruz.ymp.model;

public class Paso {

    String descripcion;

    public Paso(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "Paso{" + "descripcion=" + descripcion + '}';
    }
    
}
