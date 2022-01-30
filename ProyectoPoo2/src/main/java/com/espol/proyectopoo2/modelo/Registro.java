package com.espol.proyectopoo2.modelo;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Clase que representa la exploracion de un crater.
 * @author Dome
 */
public class Registro {
    
    /**
     * Fecha de la exploracion.
     */
    private LocalDate fecha;
    
    /**
     * Arreglo de minerales encontrados.
     */
    private ArrayList<String> minerales;
    
    /**
     * Crater explorado.
     */
    private String nombreCrater; 
    
    /**
     * Crea un objeto exploracion.
     * @param fecha Fecha en la que se realiza la exploracion.
     * @param minerales Minerales encontrados.
     * @param nombreCrater nombre del crater explorado
     */
    public Registro( LocalDate fecha, 
                    ArrayList<String> minerales, 
                    String nombreCrater ){
        this.fecha= fecha;
        this.minerales = minerales;
        this.nombreCrater = nombreCrater;
    }

    /**
     * @return fecha de exploracion.
     */
    public LocalDate getFecha() {
        return fecha;
    }

    /**
     * @return arreglo de minerales encontrados.
     */
    public ArrayList<String> getMinerales() {
        return minerales;
    }

    /**
     * @return El nombre del crater explorado.
     */
    public String getNombreCrater() {
        return nombreCrater;
    }  

    /**
     * Metodo para visualizar el contenido de cada Registro
     * @return 
     */
    @Override
    public String toString() {
        return "Registro{" + "fecha=" + fecha + ", minerales=" + minerales + ", nombreCrater=" + nombreCrater + '}';
    }
    
    
}