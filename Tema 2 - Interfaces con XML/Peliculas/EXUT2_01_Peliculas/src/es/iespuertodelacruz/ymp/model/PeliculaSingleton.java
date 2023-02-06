/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.iespuertodelacruz.ymp.model;

/**
 *
 * @author yared
 */
public class PeliculaSingleton {
    
  private static PeliculaSingleton instance;
  private Pelicula pelicula;

  private PeliculaSingleton() { }

  public static PeliculaSingleton getInstance() {
    if (instance == null) {
      instance = new PeliculaSingleton();
    }
    return instance;
  }
  public void setPelicula(Pelicula pelicula) {
    this.pelicula = pelicula;
  }
  public Pelicula getPelicula() {
    return pelicula;
  }
  
}
