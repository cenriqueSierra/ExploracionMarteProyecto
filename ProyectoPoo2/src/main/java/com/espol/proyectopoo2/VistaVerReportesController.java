/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.espol.proyectopoo2;

import com.espol.proyectopoo2.data.RegistroData;
import com.espol.proyectopoo2.modelo.Registro;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
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
    private ComboBox<String> cbxSeleccion;
    
    private ObservableList<Registro> reporte = FXCollections.observableArrayList();

    /**
     * Inicia clase controladora y carga la informacion
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            cbxSeleccion.getItems().add("Nombre");
            cbxSeleccion.getItems().add("Fecha");
            System.out.println("Va a leer");
            reporte.addAll(RegistroData.leerReporte());
            System.out.println("Leido");
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }    
    
    
    /**
     * 
     * @param event 
     */
    @FXML
    private void seleccionarOrden(ActionEvent event) {
        
        switch(cbxSeleccion.getValue()){
            case("Nombre"):
                vboxTable.getChildren().clear();
                Comparator<Registro> com1 = (Registro r1, Registro r2) -> {
                    return r2.getNombreCrater().compareToIgnoreCase(r1.getNombreCrater()); //r2 primero y r1 despues ordena de forma descendente
                };

                creacionTabla(reporte, com1);
                break;

            case("Fecha"):
                vboxTable.getChildren().clear();
                Comparator<Registro> com2 = (Registro r1, Registro r2) -> {
                    return r2.getFecha().compareTo(r1.getFecha());
                };

                creacionTabla(reporte, com2);
                break;

            default:
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Notificacion");
                alert.setHeaderText("Estado");
                alert.setContentText("Seleccion invalida");

                alert.showAndWait();
        }
    }
    
    /**
     * Metodo que crea un tableView 
     * @param registro Lista de registros con crateres sensados 
     * @param com Comparador que permite ordenar de forma descendente por campo
     */
    public void creacionTabla(ObservableList<Registro> registro, Comparator<Registro> com){
        System.out.println(registro);
        
        registro.sort(com); //Ordena por medio del comparador
        TableView<Registro> table = new TableView();
        table.setItems(registro);
        
        //TableColumn<Movie, String> colTitle = new TableColumn<Movie, String>("Title");
        TableColumn<Registro,LocalDate> colFecha = new TableColumn<Registro,LocalDate> ("Fecha de exploracion");
        colFecha.setMinWidth(100);
        colFecha.setCellValueFactory(
                new PropertyValueFactory<Registro,LocalDate>("Fecha de exploracion"));
        
        
        TableColumn<Registro,String> colNombre = new TableColumn<Registro,String> ("Nombre de Crater");
        colNombre.setMinWidth(100);
        colNombre.setCellValueFactory(
                new PropertyValueFactory<Registro,String> ("Nombre de Crater"));
        
        TableColumn<Registro,List<String>> colMinerales = new TableColumn<Registro,List<String>> ("Minerales Encontrados");
        colMinerales.setMinWidth(100);
        colMinerales.setCellValueFactory(
                new PropertyValueFactory <Registro,List<String>> ("Minerales Encontrados"));
        
        //vboxTable.getChildren().clear(); //Limpia el pane
        table.getColumns().addAll(colFecha,colNombre,colMinerales);
        vboxTable.getChildren().addAll(table);
    }
    
    
    /**
     * Metodo que permite regresar al menu principal
     * @param event Click al boton regresar
     */
    @FXML
    private void regresarMenu(MouseEvent event) {
        App.cambioVista("VistaInicial");
    }
}