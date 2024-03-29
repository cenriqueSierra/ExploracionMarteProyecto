package com.espol.proyectopoo2.modelo;

import java.io.IOException;
import javafx.application.Platform;
import javafx.scene.control.Alert;

/**
 * Representa el rover de tipo solar.
 * @author Dome
 */
public class RoverSolar extends Rover {
    
     /**
     * Crea un rover de tipo eolico.
     * @param nombre Nombre del rover.
     * @param ubicacion Ubicacion del rover.
     * @param image_path Ruta de la imagen del rover.
     * @param carga Carga que posee el rover.
     * @throws java.io.IOException
     */
    public RoverSolar(
            String nombre, 
            Ubicacion ubicacion, 
            String image_path, 
            double carga) throws IOException{
        super(nombre, ubicacion, image_path,carga);
    }
    
    /**
     * Carga el rover en la posicion donde haya mas sol.
     */
    @Override
    public void cargar() {
        tareas.cola.add(()->{
            desplazarse(new Ubicacion(100,100), true);
            setCarga(100);
            Platform.runLater(()->{Alert a = new Alert(Alert.AlertType.WARNING);
                setCarga(100);
                a.setTitle("Notificacion");
                a.setHeaderText("Acciones");
                a.setContentText("Desplegando paneles solares");
                a.show();});
            });

    }  
}