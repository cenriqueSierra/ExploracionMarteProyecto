/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.espol.proyectopoo2;

import com.espol.proyectopoo2.data.RegistroData;
import com.espol.proyectopoo2.modelo.Registro;
import java.net.URL;
import java.util.Comparator;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
    private ComboBox<String> cbxSeleccion;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cbxSeleccion.getItems().add("Nombre");
        cbxSeleccion.getItems().add("Fecha");
        
    }    
    
    
    /**
     * Metodo que actua frente a la seleccion de 'Ordenar por:', inmediatamente 
     * se crea el hilo que carga la informacion y se carga de 
     * 
     * @param event 
     */
    @FXML
    private void seleccionarOrden(ActionEvent event) {
        Thread t = new Thread(new RegistroRunnable());
        //t.start();
    }
    
    
    /**
     * Metodo que crea un tableView 
     * @param registro Lista de registros con crateres sensados 
     * @param com Comparador que permite ordenar de forma descendente por campo
     */
    public void creacionTabla(ObservableList<Registro> registro, Comparator<Registro> com){
        registro.sort(com); //Ordena por medio del comparador
        vboxTable.getChildren().clear(); //Limpia el pane
        TableView<Registro> tableNombre = new TableView(registro);
        vboxTable.getChildren().addAll(tableNombre);
    }
        
    
    /**
     * Clase que permite cargar los registros del archivo binario
     * y modifica la interfaz creando un tableView con la información del archivo
     */
    class RegistroRunnable implements Runnable{

        @Override
        public void run() {
            //Cargo la lista
            ObservableList<Registro> registro = (ObservableList<Registro>) RegistroData.cargarRegistro();
            
            //Debes validar que lo que se presentará está dentro del rango de fechas y sea el nombre de crater correcto
            
            //Bloque de codigo que modifica la interfaz
            Platform.runLater(()->{
                
                switch(cbxSeleccion.getValue()){
                    case("Nombre"):
                        vboxTable.getChildren().clear();
                        Comparator<Registro> com1 = (Registro r1, Registro r2) -> {
                            return r2.getCrater().getNombre().compareToIgnoreCase(r1.getCrater().getNombre()); //r2 primero y r1 despues ordena de forma descendente
                        };

                        creacionTabla(registro, com1);
                        break;

                    case("Fecha"):
                        vboxTable.getChildren().clear();
                        Comparator<Registro> com2 = (Registro r1, Registro r2) -> {
                            return r2.getFecha().compareTo(r1.getFecha());
                        };

                        creacionTabla(registro, com2);
                        break;

                    default:
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Notificacion");
                        alert.setHeaderText("Estado");
                        alert.setContentText("Seleccion invalida");

                        alert.showAndWait();
                }
            });

        }

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
