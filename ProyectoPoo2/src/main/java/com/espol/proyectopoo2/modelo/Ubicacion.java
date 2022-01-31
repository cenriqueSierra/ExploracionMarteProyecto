package com.espol.proyectopoo2.modelo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Objeto ubicacion
 * @author Carlos user
 */
public class Ubicacion {
    /**
     * Coordenada de latitud de la ubicacion
     */
    private double latitud;
    
    /**
     * Coordenada de longitud de la ubicacion
     */
    private double longitud;

    /**
     * Crea una ubicacion
     * @param latitud coordenada de latitud
     * @param longitud coordenada de longitud
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
     * Provee longitud y latitud de la ubicacion en String
     * @return Posicion x, posicion y.
     */
    @Override
    public String toString(){
        return longitud+","+latitud;
    }
    
    /**
     * Encuentra la distancia y angulo entre un punto y otro
     * @param ubicacion ubicacion a comparar
     * @return distancia
     */
    public ArrayList<Double> distancia(Ubicacion ubicacion){
        double x_diff = ubicacion.getLongitud() - longitud;
        double y_diff = ubicacion.getLatitud() -latitud;
        double distancia =Math.sqrt(Math.pow(x_diff,2)+Math.pow(y_diff,2));   
        double angulo = Math.atan2(y_diff, x_diff);
        ArrayList<Double> valores = new ArrayList<>();
        valores.add(distancia);
        valores.add(angulo);
        return valores;
        
    }    
}