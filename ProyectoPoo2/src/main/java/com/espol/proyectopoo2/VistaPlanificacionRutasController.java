/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.espol.proyectopoo2;

import com.espol.proyectopoo2.data.CraterData;
import com.espol.proyectopoo2.data.RoverData;
import com.espol.proyectopoo2.modelo.Crater;
import com.espol.proyectopoo2.modelo.Rover;
import com.espol.proyectopoo2.modelo.Ubicacion;
import java.net.URL;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

/**
 * FXML Controller Planificacion de Rutas
 *
 * @author Dome user
 */
public class VistaPlanificacionRutasController implements Initializable {

   /**
    * Texto de los crateres ingresados
    */
    @FXML
    private TextField crateresIngresados;
    
    /**
     * Opciones de los rovers.
     */
    @FXML
    private ComboBox<Rover> cbRover;
    
    /**
     * Rover seleccionado
     */
    private Rover roverSeleccionado;
    /**
     * Alerta del programa.
     */
    private Alert a;
    
    /**
     * Panel con los nombres de los crateres ordenados segun la ruta.
     */
    @FXML
    private Pane paneNombres;
    
    /**
     * Boton para volver al menu.
     */
    @FXML
    private Button volverMenu;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        a = new Alert(Alert.AlertType.ERROR);
        cbRover.getItems().addAll(RoverData.cargarRovers());
        
    }    
    
    /**
     * Busca la ruta más optima para visitar los crateres.
     * @param event 
     */
    @FXML
    private void buscarRutas(KeyEvent event) {
        List<Crater> crateresVisitar = new ArrayList<>();
        
        if (event.getCode() == event.getCode().ENTER){
            paneNombres.getChildren().clear();
        try{
            
                if(cbRover.getValue()==null)
                    throw new NullPointerException("Seleccione un Rover");
                if (crateresIngresados.getText().isBlank())
                        throw new NullPointerException("Ingrese crateres");
                        
                        
            Ubicacion u0 = roverSeleccionado.getUbicacion();

            String[] crateresNombres = crateresIngresados.getText().split(",");
            System.out.println("Ingresados: "+crateresNombres);
            List<String> crateresNoEncontrados = new ArrayList<>();
            
            for(String nombreCrater : crateresNombres){
                boolean find = false;
                for(Crater c : CraterData.cargarCrateres())
                    if(c.getNombre().equalsIgnoreCase(nombreCrater.trim())){
                        crateresVisitar.add(c);
                        find = true;
                        System.out.println("A visitar"+c);
                    }
                if(!find && !crateresNoEncontrados.contains(nombreCrater))
                    crateresNoEncontrados.add(nombreCrater);
            }
            
            if(!crateresNoEncontrados.isEmpty()){
                a.setAlertType(Alert.AlertType.WARNING);
                a.setContentText("Nombre de crateres no encontrados: "+crateresNoEncontrados
                                  +"\n los cuales no se agregaran a la ruta");
                a.show();
            }
                
            
            GridPane orden = new GridPane();
            orden.gridLinesVisibleProperty().set(true);
            
            paneNombres.getChildren().add(orden);
            Deque<Crater> tmp = new LinkedList<>(crateresVisitar);
            List<Crater> last_dist = new ArrayList<>();
            
                System.out.println("ORDEN");
                System.out.println("Distacia desde Rover: u0 = " + u0 + "\f" + roverSeleccionado);
            while (last_dist.size() < crateresVisitar.size()){
                
                double dist_min = Double.MAX_VALUE;
                Crater minDistCrater = null;
                
                while(!tmp.isEmpty()){
                    Crater t = tmp.poll();
                    double ndist = u0.distancia(t.getUbicacion());
                    if (ndist < dist_min) {
                        dist_min = ndist;
                        minDistCrater = t;                    
                    }
                    System.out.println("\t[crater] " + t + "\f dist: " + ndist);
                }
                System.out.println("Distacia desde crater: " + minDistCrater);
                

                if (minDistCrater == null)
                    break;
                
                last_dist.add(minDistCrater);
                int i =last_dist.size();
                System.out.println(i+" "+minDistCrater.getNombre());

                u0 = minDistCrater.getUbicacion();
                Label l = new Label(i+" "+minDistCrater.getNombre());
                l.setPadding(new Insets(10));
                orden.addRow(i, l);
                for(Crater c : crateresVisitar) 
                    if (!last_dist.contains(c))
                        tmp.add(c);
                
                
            }
        
            
        }catch(NullPointerException ex){
                a.setContentText(ex.getMessage());
                ex.printStackTrace();
                a.show();
            }
      }
    }

    
    /**
     * Regresa a la vista inicial
     * @param event 
     */
    @FXML
    private void volverMenu(MouseEvent event) {
       App.cambioVista("VistaInicial");

        
    }
 
    /**
     * Actualiza el rover seleccionado
     * @param event 
     */
    @FXML
    private void seleccionar(ActionEvent event) {
            roverSeleccionado = cbRover.getValue();
            paneNombres.getChildren().clear();
            crateresIngresados.setText("");
    }

    
}
