/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.espol.proyectopoo2;

import com.espol.proyectopoo2.data.ReporteData;
import com.espol.proyectopoo2.modelo.Reporte;
import java.net.URL;
import java.util.Comparator;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
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
    @FXML
    private Button btnFecha;
    @FXML
    private ComboBox<String> cbxSeleccion;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cbxSeleccion.getItems().add("Nombre");
        cbxSeleccion.getItems().add("Fecha");
        
        ObservableList<Reporte> registro = (ObservableList<Reporte>) ReporteData.cargarReporte();
        
        switch(cbxSeleccion.getValue()){
            case("Nombre"):
                vboxTable.getChildren().clear();
                Comparator<Reporte> com1 = (Reporte r1, Reporte r2) -> {
                    return r1.getCrater().getNombre().compareToIgnoreCase(r2.getCrater().getNombre());
                };
                registro.sort(com1);
                vboxTable.getChildren().clear();
                TableView<Reporte> tableNombre = new TableView(registro);
                vboxTable.getChildren().addAll(tableNombre);
                break;
                
            case("Fecha"):
                vboxTable.getChildren().clear();
                Comparator<Reporte> com2 = (Reporte r1, Reporte r2) -> {
                    return r1.getFecha().compareTo(r2.getFecha());
                };
                registro.sort(com2);
                vboxTable.getChildren().clear();
                TableView<Reporte> tableDate = new TableView(registro);
                vboxTable.getChildren().addAll(tableDate);
                break;
            
            default:
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Notificacion");
                alert.setHeaderText("Estado");
                alert.setContentText("Seleccion invalida");
                
                alert.showAndWait();
            
                
        }
                
        TableView<Reporte> table = new TableView((ObservableList) ReporteData.cargarReporte());
        
        vboxTable.setAlignment(Pos.CENTER);
        vboxTable.getChildren().addAll(table);
    }    

    @FXML
    private void ordenarAsc(MouseEvent event) {
    }

    @FXML
    private void ordenarDesc(MouseEvent event) {
    }
    
}
