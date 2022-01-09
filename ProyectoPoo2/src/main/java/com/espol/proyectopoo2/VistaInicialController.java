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
 * FXML Controller class
 *
 * @author Carlos user
 */
public class VistaInicialController implements Initializable {
    private String vistaAsociada;
    @FXML
    private Button btnSalir;

    /**
     * Se inicia el controlador de la vistaInicial
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void entrarExplorar(MouseEvent event) {
        vistaAsociada = "VistaExplorar";
        App.cambioVista(vistaAsociada);
    }

    @FXML
    private void hacerPlanificacion(MouseEvent event) {
        vistaAsociada = "VistaPlanificacionRutas";
        App.cambioVista(vistaAsociada);
    }

    @FXML
    private void entrarVerReportes(MouseEvent event) {
        vistaAsociada = "VistaVerReportes";
        App.cambioVista(vistaAsociada);
    }

    @FXML
    private void salirVentana(MouseEvent event) {
        Stage stage = (Stage) btnSalir.getScene().getWindow();
        stage.close();
    }

    
}
