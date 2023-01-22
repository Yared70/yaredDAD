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
public class RecetaSingleton {
    
  private static RecetaSingleton instance;
  private Receta receta;

  private RecetaSingleton() { }

  public static RecetaSingleton getInstance() {
    if (instance == null) {
      instance = new RecetaSingleton();
    }
    return instance;
  }
  public void setReceta(Receta receta) {
    this.receta = receta;
  }
  public Receta getReceta() {
    return receta;
  }
  
}
