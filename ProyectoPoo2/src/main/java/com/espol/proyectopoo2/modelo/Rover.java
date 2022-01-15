/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.espol.proyectopoo2.modelo;

import com.espol.proyectopoo2.interfaces.AccionesRover;

/**
 * Clase que representa el rover
 * @author Dome user
 */
public abstract class Rover implements AccionesRover {
    /**
     * Nombre del Rover
     */
    private String nombre ;
    /**
     * Ubicacion del rover;
     */
    Ubicacion ubicacion;
    
    /**
     * Path de la imagen del rover
     */
    private String image_path;
    
    /**
     * Carga del rover.
     */
    private double carga;
    
    /**
     * Angulo donde esta viendo el rover
     */
    private double angulo;
    
   /**
    * Crea un objeto de tipo rover
    * @param nombre Nombre del rover
    * @param ubicacion Ubicacion del rover
    * @param image_path Path de la imagen del river
    * @param carga Carga del rover
    */ 
   public Rover(String nombre, Ubicacion ubicacion, String image_path, double carga){
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.image_path = image_path;
        this.carga = carga;
        angulo=90;
        
    }
    
   
    /**
     * Carga el rover
     */
    public abstract void cargar();

    /**
     * @return  nombre de rover
     */
    public String getNombre() {
        return nombre;
    }


    /**
     * @return el path de la imagen.
     */
    public String getImage_path() {
        return image_path;
    }

    /**
     * @return la carga del rover.
     */
    public double getCarga() {
        return carga;
    }

    /**
     * @param carga la carga del rover.
     */
    public void setCarga(double carga) {
        this.carga = carga;
    }
    
    
    @Override
    public void avanzar(){
        
    }
    
    @Override
    public void girar(double grados){
        
    }
    
    @Override
    public void desplazarse(Ubicacion ubicacion){
        
    }
    
    @Override
    public String sensar(){
        
        return null;
    }

}
