/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.espol.proyectopoo2;

import com.espol.proyectopoo2.data.CraterData;
import com.espol.proyectopoo2.data.RoverData;
import com.espol.proyectopoo2.modelo.Crater;
import com.espol.proyectopoo2.modelo.Rover;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * FXML Controller class
 *
 * @author Carlos user
 */
public class VistaExplorarController implements Initializable {

    @FXML
    private TextField comandoIngresado;
    @FXML
    private TextArea textASalida;
    @FXML
    private ComboBox<String> cboxRover;
    @FXML
    private Label lsalidaMinerales;
    @FXML
    private Pane panelSuperficie;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<Rover> rovers = RoverData.cargarRovers();
    
        for(Rover r: rovers)
           cboxRover.getItems().add(r.getNombre());
       cargarCrateres();
       
    }

    private void cargarCrateres(){
        List<Crater> crateres = CraterData.cargarCrateres();
        
        for (Crater cr:  crateres)
            System.out.println(cr.getRadio());
        
        
        System.out.println(crateres);
        for(Crater c: crateres){
            Circle circulo = new Circle(c.getRadio(),Color.RED);
            
                //circulo.getStyleClass().add("c-nocensado");
            circulo.setLayoutX(c.getUbicacion().getLongitud()/2.0/*(panelSuperficie.getPrefWidth()/1372.0)*/);
            circulo.setLayoutY(c.getUbicacion().getLatitud()/2.0/*(panelSuperficie.getPrefHeight()/997.0)*/);
            panelSuperficie.getChildren().add(circulo);
            
        }
        Circle o = new Circle(20.0,Color.GREEN);
        o.setLayoutX(panelSuperficie.getPrefWidth());
        o.setLayoutY(panelSuperficie.getPrefHeight());
        panelSuperficie.getChildren().add(o);
    }

    @FXML
    private void regresarMenu(MouseEvent event) {
        App.cambioVista("VistaInicial");
    }
    
   /**public static Rover roverActual(){
       return cboxRover.getValue();
       
   }**/
}
