/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.espol.proyectopoo2.modelo;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;

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
     * @param nombreCrater
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

    //Comparator<Reporte> com1 = ne
    
    
    
}
