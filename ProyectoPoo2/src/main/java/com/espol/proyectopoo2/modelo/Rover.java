/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.espol.proyectopoo2.modelo;

/**
 * Clase que representa el rover
 * @author Dome user
 */
public abstract class Rover {
    /**
     * Nombre del Rover
     */
    String nombre ;
    //Ubicacion ubicacion;
    String image_path;
    //Double carga;
    
    /**
     * Carga el rover
     */
    public abstract void cargar();
    
}
