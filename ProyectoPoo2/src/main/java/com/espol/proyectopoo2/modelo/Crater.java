package com.espol.proyectopoo2.modelo;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 *
 * @author josek
 */
public class Crater {
    /**
     * Nombre del crater
     */
    private String nombre;
    /**
     * Identificador unico del crater
     */
    private String id;
    /**
     * Ubicacion del crater
     */
    private Ubicacion ubicacion;
    /**
     * Condicion de explorado o no explorado del crater
     */
    private boolean explorado;
    /**
     * Radio del crater
     */
    private double radio;
    
    /**
     * Representacion grafica.
     */
    private Circle circulo;
    
    /**
     * @return el nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @return el id
     */
    public String getId() {
        return id;
    }
    
    /**
     * 
     * @return la ubicacion del crater
     */
    public Ubicacion getUbicacion(){
        return ubicacion;
    }
    
    /**
     * @return si ha sido explorado
     */
    public boolean isExplorado() {
        return explorado;
    }

    /**
     * @param explorado fija el estado de explorado
     */
    public void setExplorado(boolean explorado) {
        this.explorado = explorado;
        circulo.setFill(Color.RED);
    }

    /**
     * @return el radio
     */
    public double getRadio() {
        return radio;
    }
    /**
     * Constructor de la clase
     * @param id id a fijar
     * @param nombre nombre a fijar
     * @param radio radio a fijar
     * @param ubicacion ubicacion a fijar
     */
    public Crater(  String id,
                    String nombre,
                    double radio,
                    Ubicacion ubicacion){
        this.id = id;
        this.nombre = nombre;
        this.explorado = false;
        this.radio = radio;
        this.ubicacion = ubicacion; 
        circulo = new Circle(this.radio,Color.TRANSPARENT);
        
        circulo.setStroke(Color.RED);
    }
    
    /**
     * Imprime la informacion del crater.
     * @return Informacion del crater
     */
    @Override
    public String toString(){
        return "Nombre: "+nombre+"Id:"+id+"Radio: "+ radio
                +"Ubicación: "+ubicacion;
    }

    /**
     * @return the circulo
     */
    public Circle getCirculo() {
        return circulo;
    }
}
