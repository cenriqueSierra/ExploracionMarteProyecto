/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.espol.proyectopoo2.modelo;

import com.espol.proyectopoo2.App;
import com.espol.proyectopoo2.data.CraterData;
import com.espol.proyectopoo2.data.ReporteData;
import com.espol.proyectopoo2.interfaces.AccionesRover;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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
    private ImageView imagen;
    
    /**
     * Carga del rover.
     */
    private double carga;
    
    /**
     * Angulo en radianes donde esta viendo el rover
     */
    private double angulo;
    
   /**
    * Crea un objeto de tipo rover
    * @param nombre Nombre del rover
    * @param ubicacion Ubicacion del rover
    * @param image_path Path de la imagen del river
    * @param carga Carga del rover
    */ 
   public Rover(String nombre, Ubicacion ubicacion, String image_path, double carga) throws IOException{
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        Image img = new Image(App.class.getResourceAsStream(image_path),60,60,false,true);
        this.imagen = new ImageView(img);
        imagen.setLayoutX(ubicacion.getLongitud());
        imagen.setLayoutY(ubicacion.getLatitud());
        this.carga = carga;
        angulo=0;
        
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
    public ImageView getImagen() {
        return imagen;
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
//        double posX = ubicacion.getLongitud();
//        double posY = ubicacion.getLatitud();
//        //angulo
//        
//        //sacar las componentes rectangulares
//        double compX = 10*Math.cos(angulo);
//        double compY = 10*Math.sin(angulo);     
//       
//         
//        //settear
//        posX+=compX;
//        posY+=compY;
//        System.out.println("-------AVANZAAAAR--------");
//        System.out.println("x:"+ posX+"y: "+posY);
//        
          setUbicacion(posicionNueva());
          moverImgRover(ubicacion.getLongitud(),ubicacion.getLatitud());
    }
    
    @Override
    public void girar(double grados){
        grados *= Math.PI/180;
        angulo+=grados;
        moverImgRover(ubicacion.getLongitud(),ubicacion.getLatitud());

    }
    
    @Override
    public void desplazarse(Ubicacion ubicacion){
        
        
        
        //final
        moverImgRover(ubicacion.getLongitud(),ubicacion.getLatitud());

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

    /**
     * @return el angulo del rover en grados.
     */
    public double getAngulo() {
        return angulo*180/Math.PI;
    }
    
    public void moverImgRover(double x, double y){
        imagen.setLayoutX(x);
        imagen.setLayoutY(y);
        imagen.setRotate(getAngulo());
    }
    
    public Ubicacion posicionNueva(){
        double posX = ubicacion.getLongitud();
        double posY = ubicacion.getLatitud();
        //angulo
        
        //sacar las componentes rectangulares
        double compX = 10*Math.cos(angulo);
        double compY = 10*Math.sin(angulo);     
       
         
        //settear
        posX+=compX;
        posY+=compY;
        
        return new Ubicacion(posX,posY);
    }
}
