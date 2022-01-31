package com.espol.proyectopoo2.modelo;

import java.io.IOException;
import javafx.scene.control.Alert;

/**
 * Representa el rover de tipo eolico.
 * @author Dome
 */
public class RoverEolico extends Rover {
    /**
     * Angulo considerado Norte
     */
    private double anguloNorte;
    /**
     * Crea un rover de tipo eolico.
     * @param nombre Nombre del rover.
     * @param ubicacion Ubicacion del rover.
     * @param image_path Ruta de la imagen del rover.
     * @param carga Carga que posee el rover.
     */
    public RoverEolico(
            String nombre, 
            Ubicacion ubicacion, 
            String image_path, 
            double carga) throws IOException{
        super(nombre, ubicacion, image_path,carga);
    }
    
    /**
     * Carga el rover en direccion donde hay mas viento el norte.
     */
    @Override
    public void cargar() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Notificacion");
        alert.setHeaderText("Acciones");
        alert.setContentText("Despliegue de molinos");
        alert.showAndWait();
        anguloNorte = 90d;
        double anguloGirado = super.getAnguloGrados();
        double movimiento = 360 - Math.abs(anguloGirado - anguloNorte);
        girar(movimiento);       
        setCarga(100);
    }   
}