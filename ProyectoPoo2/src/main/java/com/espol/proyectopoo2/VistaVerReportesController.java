/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.espol.proyectopoo2;

import com.espol.proyectopoo2.data.ReporteData;
import com.espol.proyectopoo2.modelo.Reporte;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Carlos user
 */
public class VistaVerReportesController implements Initializable {

    @FXML
    private TextField fInicioIngresada;
    @FXML
    private TextField fFinIngresada;
    @FXML
    private TextField mineralIngresado;
    @FXML
    private VBox vboxTable;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TableView<Reporte> table = new TableView((ObservableList) ReporteData.cargarReporte());
        
        vboxTable.setAlignment(Pos.CENTER);
        vboxTable.getChildren().addAll(table);
    }    
    
    
}
