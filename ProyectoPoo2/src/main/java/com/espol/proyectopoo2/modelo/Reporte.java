/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.espol.proyectopoo2.modelo;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * Clase que representa la exploracion de un crater.
 * @author Dome
 */
public class Reporte implements Serializable{
    /**
     * Fecha de la exploracion.
     */
    private LocalDateTime fecha;
    /**
     * Arreglo de minerales encontrados.
     */
    private ArrayList<String> minerales;
    /**
     * Crater explorado.
     */
    private Crater crater;
    
    
    /**
     * Crea un objeto exploracion.
     * @param fecha Fecha en la que se realiza la exploracion.
     * @param minerales Minerales encontrados.
     * @param crater Crater explorado.
     */
    public Reporte( LocalDateTime fecha, ArrayList<String> minerales,Crater crater){
        this.fecha= fecha;
        this.minerales = minerales;
        this.crater = crater;
    }

    /**
     * @return fecha de exploracion.
     */
    public LocalDateTime getFecha() {
        return fecha;
    }

    /**
     * @return arreglo de minerales encontrados.
     */
    public ArrayList<String> getMinerales() {
        return minerales;
    }

    /**
     * @return El crater explorado.
     */
    public Crater getCrater() {
        return crater;
    }

    //Comparator<Reporte> com1 = ne
    
    
    
}
