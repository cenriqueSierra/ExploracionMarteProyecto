/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.espol.proyectopoo2;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

/**
 * FXML Controller class
 *
 * @author Carlos user
 */
public class VistaPlanificacionRutasController implements Initializable {

    @FXML
    private TextField crateres;
    
    private String[] crateresVisitar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void buscarRutas(KeyEvent event) {
        
        if (event.getCode() == event.getCode().ENTER){
             crateresVisitar = crateres.getText().split(",");
             
        }
    }
    
}
