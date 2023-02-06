/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.iespuertodelacruz.ymp.model;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author yared
 */
public class UsuarioDTV {

    SimpleStringProperty nombre;
    SimpleStringProperty pass;
    SimpleStringProperty rol;

    public UsuarioDTV(SimpleStringProperty nombre, SimpleStringProperty pass, SimpleStringProperty rol) {
        this.nombre = nombre;
        this.pass = pass;
        this.rol = rol;
    }

    public String getNombre() {
        return nombre.get();
    }

    public String getPass() {
        return pass.get();
    }

    public String getRol() {
        return rol.get();
    }

    @Override
    public String toString() {
        return "UsuarioDTV{" + "nombre=" + nombre + ", pass=" + pass + ", rol=" + rol + '}';
    }

}
