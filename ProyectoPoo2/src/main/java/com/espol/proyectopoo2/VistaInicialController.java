/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.espol.proyectopoo2;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controlador Vista Inicial 
 *
 * @author Carlos user
 */
public class VistaInicialController implements Initializable {
    /**
     * Vista asociada.
     */
    private String vistaAsociada;
    /**
     * Boton salir.
     */
    @FXML
    private Button btnSalir;

    /**
     * Se inicia el controlador de la vistaInicial
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
    /**
     * Cambia a la vista explorar.
     * @param event 
     */
    @FXML
    private void entrarExplorar(MouseEvent event) {
        vistaAsociada = "VistaExplorar";
        App.cambioVista(vistaAsociada);
    }
    
    /**
     * Cambia a la vista Planificacion Rutas.
     * @param event 
     */
    @FXML
    private void hacerPlanificacion(MouseEvent event) {
        vistaAsociada = "VistaPlanificacionRutas";
        App.cambioVista(vistaAsociada);
    }
    /**
     * Cambia a la vista Ver Reportes.
     * @param event 
     */
    @FXML
    private void entrarVerReportes(MouseEvent event) {
        vistaAsociada = "VistaVerReportes";
        App.cambioVista(vistaAsociada);
    }

    /**
     * Cierre del programa.
     * @param event 
     */
    private void salirVentana(MouseEvent event) {
        Stage stage = (Stage) btnSalir.getScene().getWindow();
        stage.close();
    }

    
}
