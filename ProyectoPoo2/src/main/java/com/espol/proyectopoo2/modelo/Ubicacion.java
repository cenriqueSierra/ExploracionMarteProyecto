/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.espol.proyectopoo2.modelo;

/**
 *
 * @author Carlos user
 */
public class Ubicacion {
    private double latitud;
    private double longitud;

    /**
     * Ceacion del constructor 
     * @param latitud
     * @param longitud 
     */
    public Ubicacion(double latitud, double longitud) {
        this.latitud = latitud;
        this.longitud = longitud;
    }

    /**
     * @return latitud asociada a una ubicacion
     */
    public double getLatitud() {
        return latitud;
    }
    
    /**
     * @return la longitud asociada a una ubicacion
     */
    public double getLongitud() {
        return longitud;
    }
    
    
    
    
}
