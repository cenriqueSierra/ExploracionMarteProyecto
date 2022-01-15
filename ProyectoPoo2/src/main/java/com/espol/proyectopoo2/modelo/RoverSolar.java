/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.espol.proyectopoo2.modelo;

/**
 * Representa el rover de tipo solar.
 * @author Dome
 */
public class RoverSolar extends Rover {
    
     /**
     * Crea un rover de tipo eolico.
     * @param nombre Nombre del rover.
     * @param ubicacion Ubicacion del rover.
     * @param image_path Ruta de la imagen del rover.
     * @param carga Carga que posee el rover.
     */
    public RoverSolar(String nombre, Ubicacion ubicacion, String image_path, double carga){
        super(nombre, ubicacion, image_path,carga);
    }
    
    /**
     * Carga el rover en la posicion donde haya mas sol.
     */
    @Override
    public void cargar() {
        
    }
    
    
}
