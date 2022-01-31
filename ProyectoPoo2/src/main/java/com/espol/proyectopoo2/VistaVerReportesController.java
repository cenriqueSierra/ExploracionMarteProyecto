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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
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
    private ComboBox<String> cbxSeleccion;
    
    private ObservableList<Registro> reporte = FXCollections.observableArrayList();
    
    //private TableView<Registro> table = new TableView<>(); //Table creado aqui mismo dentro del codigo
    @FXML
    private TableView<Registro> tableProof = new TableView<>();
    @FXML
    private TableColumn<Registro,LocalDate> fechaC;
    @FXML
    private TableColumn<Registro,String> nCraterC;
    @FXML
    private TableColumn<Registro,List<String>> nMineralE;
    @FXML
    private StackPane stkTable;
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
            stkTable.getChildren().clear();
            System.out.println("Leido");
            System.out.println("Esta es la lista general:\n"+reporte);
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
        ObservableList<Registro> registrosPresentar = 
                filtrarCampos(fInicioIngresada, fFinIngresada, mineralIngresado);

        System.out.println("\nLista Ordenada\n"+registrosPresentar);
        stkTable.getChildren().clear(); //Limpia el pane
        registrosPresentar.sort(com); //Ordena por medio del comparador
        //double anchoT =tableProof.getWidth();
        //double anchoColumn = anchoT/3;
        //fechaC.setPrefWidth(anchoColumn);
        fechaC.setCellValueFactory(
                        new PropertyValueFactory<>("fecha"));
        
        //nCraterC.setPrefWidth(anchoColumn);
        nCraterC.setCellValueFactory(
                new PropertyValueFactory<> ("nombreCrater"));

        //nMineralE.setPrefWidth(anchoColumn);
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
        LocalDate inicioLDate = LocalDate.parse(fi.getText());
        LocalDate finLDate = LocalDate.parse(ffin.getText());
        String mineralI = mineralIngresado.getText();
        
        ObservableList<Registro> registrosPresentar = FXCollections.observableArrayList();
        
        for(Registro r: reporte){
            int respuestaInicio = inicioLDate.compareTo(r.getFecha()); // Si la fechaInicio ingresada es menor o igual a la del registro saldrá menor a cero o igual a cero
            int respuestaFin = finLDate.compareTo(r.getFecha()); //Si la fechaFin ingresada es mayor o igual a la del registro
            System.out.println("Este registro: "+r);
            System.out.println("\nRespuestaIniio: "+respuestaInicio);
            
            if(respuestaInicio <= 0 && respuestaFin >= 0){
                System.out.println("Necesito saber si funciona");
                if(r.getMinerales().contains(mineralI.toLowerCase())){
                    registrosPresentar.add(r);
                    
                }else{
                    Alert alert = new Alert(Alert.AlertType.INFORMATION); //En caso que el mineral no este, muestre un mensaje
                    alert.setTitle("Comunicado");
                    alert.setHeaderText("Estado");
                    alert.setContentText("El mineral: "+mineralI+" no ha sido encontrado");
                
                }      
            }   
        }
        /*
        if(registrosPresentar.isEmpty()){
             Alert alert = new Alert(Alert.AlertType.INFORMATION); //En caso que el mineral no este, muestre un mensaje
                    alert.setTitle("Comunicado");
                    alert.setHeaderText("Estado");
                    alert.setContentText("El mineral: "+mineralI+" no ha sido encontrado");
        }*/
        
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
}