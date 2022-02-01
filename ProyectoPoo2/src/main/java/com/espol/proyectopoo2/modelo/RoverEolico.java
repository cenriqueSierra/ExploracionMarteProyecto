package com.espol.proyectopoo2.modelo;

import java.io.IOException;
import javafx.application.Platform;
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
     * @throws java.io.IOException
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
        tareas.cola.add(()->{
            
            anguloNorte = 90d;
            girar(-getAngulo()-90);
            Platform.runLater(()->{
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Notificacion");
                alert.setHeaderText("Acciones");
                alert.setContentText("Despliegue de molinos");
                alert.showAndWait();
                setCarga(100);

            });
        });
        

    }   
}