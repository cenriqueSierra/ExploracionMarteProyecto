/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.espol.proyectopoo2.modelo;

import static com.espol.proyectopoo2.data.Constantes.factor;

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
    public Ubicacion(double longitud, double latitud) {
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
    
    /**
     * Imprime la ubicacion.
     * @return Posicion x, posicion y.
     */
    @Override
    public String toString(){
        return "Longitud "+ longitud/factor+" ,Latitud "+latitud/factor;
    }
    
    public double distancia(Ubicacion u){
        double x_diff = u.getLongitud() - longitud;
        double y_diff = u.getLatitud() -latitud;
        return Math.sqrt(Math.pow(x_diff,2)+Math.pow(y_diff,2));   
    }
    
}
