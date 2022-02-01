/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.espol.proyectopoo2;

import com.espol.proyectopoo2.data.RegistroData;
import static com.espol.proyectopoo2.data.RegistroData.rutaReporte;
import com.espol.proyectopoo2.modelo.Registro;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;

/**
 * FXML Controlador ver reportes.
 *
 * @author Carlos user
 */
public class VistaVerReportesController implements Initializable {
    
    /**
     * Texto de la fecha inicio ingresada.
     */
    @FXML
    private TextField fInicioIngresada;
    /**
     *  Texto de la fecha fin ingresada.
     */
    @FXML
    private TextField fFinIngresada;
    /**
     *  Texto del material ingresado.
     */
    @FXML
    private TextField mineralIngresado;
    /**
     * Combo box que contiene las opciones.
     */
    @FXML
    private ComboBox<String> cbxSeleccion;
    /**
     * Reportes
     */
    private ObservableList<Registro> reporte = FXCollections.observableArrayList();
    /**
     * Tabla donde se presentaran los datos.
     */
    @FXML
    private TableView<Registro> tableProof;
    /**
     * Columana de las fechas.
     */
    @FXML
    private TableColumn<Registro,LocalDate> fechaC;
    /**
     * Columnna de los registros.
     */
    @FXML
    private TableColumn<Registro,String> nCraterC;
   /**
    * Columna de los minerales.
    */
    @FXML
    private TableColumn<Registro,List<String>> nMineralE;
    @FXML
    private StackPane stkTable;
    private Alert alert;
    /**
     * Inicia clase controladora y carga la informacion
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            cbxSeleccion.getItems().add("Nombre");
            cbxSeleccion.getItems().add("Fecha");
            tableProof.autosize();
            reporte.addAll(RegistroData.leerReporte());
            stkTable.getChildren().clear();
            System.out.println("Esta es la lista general:\n"+reporte);
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        alert = new Alert(Alert.AlertType.WARNING);

    }    
    
    
    /**
     * Selecciona la manera en que se ordenaran los reportes.
     * @param event 
     */
    @FXML
    private void seleccionarOrden(ActionEvent event) {
        
        switch(cbxSeleccion.getValue()){
            case("Nombre"):
                Comparator<Registro> com1 = (Registro r1, Registro r2) -> {
                    return r2.getNombreCrater().compareToIgnoreCase(r1.getNombreCrater()); //r2 primero y r1 despues ordena de forma descendente
                };

                creacionTabla(reporte, com1);
                break;

            case("Fecha"):
                Comparator<Registro> com2 = (Registro r1, Registro r2) -> {
                    return r2.getFecha().compareTo(r1.getFecha());
                };

                creacionTabla(reporte, com2);
                break;

            default:
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
        ObservableList<Registro> registrosPresentar = 
                filtrarCampos(fInicioIngresada, fFinIngresada, mineralIngresado);

        System.out.println("\nLista Ordenada\n"+registrosPresentar);
        stkTable.getChildren().clear(); //Limpia el pane
        registrosPresentar.sort(com); //Ordena por medio del comparador
        fechaC.setCellValueFactory(
                        new PropertyValueFactory<>("fecha"));
        
        nCraterC.setCellValueFactory(
                new PropertyValueFactory<> ("nombreCrater"));

        nMineralE.setCellValueFactory(
                new PropertyValueFactory <> ("minerales"));

        tableProof.setItems(registrosPresentar);
        stkTable.getChildren().addAll(tableProof);
    }
    
    
    /**
     * Metodo para filtrar la lista de registros por medio de los campos
     * ingresados por el usuario: fechas, mineral
     * @param fi Fecha de inicio de la exploracion
     * @param ffin Fecha de fin de la exploracion
     * @param mineralIngresado Mineral que dese
     * @return Una lista de registro dentro de ese intervalo de fecha con el mineral deseado
     */
    public ObservableList<Registro> filtrarCampos(TextField fi, TextField ffin, TextField mineralIngresado){
        ObservableList<Registro> registrosPresentar = FXCollections.observableArrayList();

        try{
            LocalDate inicioLDate = LocalDate.parse(fi.getText());
            LocalDate finLDate = LocalDate.parse(ffin.getText());
            String mineralI = mineralIngresado.getText();
             if(inicioLDate.compareTo(finLDate) >=0){
                 
                    alert = new Alert(Alert.AlertType.ERROR); 
                    alert.setContentText("Ingrese correctamente el orden de las fechas");
                    alert.show();
                    return FXCollections.observableArrayList();
                }   

            for(Registro r: reporte){
                int respuestaInicio = inicioLDate.compareTo(r.getFecha()); // Si la fechaInicio ingresada es menor o igual a la del registro saldrá menor a cero o igual a cero
                int respuestaFin = finLDate.compareTo(r.getFecha()); //Si la fechaFin ingresada es mayor o igual a la del registro
                System.out.println("Este registro: "+r);

                if(respuestaInicio <= 0 && respuestaFin >= 0){ 
                    if(r.getMinerales().contains(mineralI.toLowerCase()))
                        registrosPresentar.add(r);
                }
         
            }
            if(registrosPresentar.isEmpty()){
              alert = new Alert(Alert.AlertType.INFORMATION); //En caso que el mineral no este, muestre un mensaje
                    alert.setTitle("Comunicado");
                    alert.setHeaderText("Estado");
                    alert.setContentText("El mineral: "+mineralI+" no ha sido encontrado");
                    alert.show();
                    System.out.println("NO ENCONTRADOO");
        }
        }catch(DateTimeParseException ex){
            System.out.println("Errores tecnicos.");
            alert = new Alert(Alert.AlertType.ERROR); //En caso que el mineral no este, muestre un mensaje
            alert.setTitle("Error");
            alert.setHeaderText("Formato fecha");
            alert.setContentText("El formato de fecha ingresada es incorrecto");
            alert.show();
        }
        System.out.println("\nLista filtrada: "+registrosPresentar);       
        return registrosPresentar;

    }
    
   
    /**
     * Metodo que permite regresar al menu principal
     * @param event Click al boton regresar
     */
    @FXML
    private void regresarMenu(MouseEvent event) {
        App.cambioVista("VistaInicial");
    }

    /**
     * Elimana el archivo del reporte.
     * @param event 
     */
    @FXML
    private void accionEliminarReporte(MouseEvent event) {
        File fichero = new File(rutaReporte);
        fichero.delete();
        System.out.println("Archivo Borrado");
    }
}