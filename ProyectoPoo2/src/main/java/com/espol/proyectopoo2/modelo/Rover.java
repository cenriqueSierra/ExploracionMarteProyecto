/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.espol.proyectopoo2.modelo;

import com.espol.proyectopoo2.data.CraterData;
import com.espol.proyectopoo2.data.ReporteData;
import com.espol.proyectopoo2.interfaces.AccionesRover;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

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
     * 
     * @return 
     */
    public Ubicacion getUbicacion(){
        return ubicacion;
    }
    /**
     * @param carga la carga del rover.
     */
    public void setCarga(double carga) {
        this.carga = carga;
    }
    /**
     * 
     * @param ubicacion 
     */
    public void setUbicacion(Ubicacion ubicacion){
        this.ubicacion=ubicacion;
    }
    
    
    @Override
    public void avanzar(){
        double posX = ubicacion.getLongitud();
        double posY = ubicacion.getLatitud();
        //angulo
        
        //sacar las componentes rectangulares
        double compX = 10*Math.cos(angulo);
        double compY = 10*Math.sin(angulo);     
                
        //settear
        posX+=compX;
        posY+=compY;
        setUbicacion(new Ubicacion(posX,posY)); 
    }
    
    @Override
    public void girar(double grados){
        angulo+=grados;
    }
    
    @Override
    public void desplazarse(Ubicacion ubicacion){
        
    }
    
    @Override
    public String sensar(){
        //Esta en el crater
        Crater crater = CraterData.isUbicacionInCrater(ubicacion);
        if(null!=crater){
            crater.setExplorado(true);
            String minerales = CraterData.mineralAleatorioRepetido();
            ArrayList<String> mineralesReporte = new ArrayList();
            mineralesReporte.addAll(Arrays.asList(minerales.split(";")));
            Reporte reporte = new Reporte(LocalDateTime.now(),
                                    mineralesReporte,
                                    crater);            
            ReporteData.addReporte(reporte);            
            return minerales;
        }
        return null;
    }
    
    @Override
    public String toString(){
        return nombre;
    }
}
