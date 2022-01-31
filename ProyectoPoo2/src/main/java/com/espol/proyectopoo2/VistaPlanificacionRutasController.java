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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
<<<<<<< HEAD
=======
import java.util.Set;
import java.util.Stack;
import java.util.stream.Collectors;
>>>>>>> 79675d950a2dc86fd5c4733976ed39ef2d03ed99
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
     *
     * @param event
     */
    @FXML
    private void buscarRutas(KeyEvent event) {
        ArrayList<Crater> crateresVisitar = new ArrayList<>();
        
        if (event.getCode() != event.getCode().ENTER) {
            return;
        }
        
       try{
            paneNombres.getChildren().clear();

            if (cbRover.getValue() == null) 
                throw new NullPointerException("Seleccione un Rover");
            
            if (crateresIngresados.getText().isBlank()) 
                throw new NullPointerException("Ingrese crateres");
            

            Ubicacion u0 = roverSeleccionado.getUbicacion();

            ArrayList<String> crateresNombresIngresados = new ArrayList<>(
                    Arrays.asList(crateresIngresados.getText()
                            .toLowerCase().split(",")));

            List<String> crateresNoEncontrados = new ArrayList<>();
            List<Crater> crateres = CraterData.cargarCrateres();

            List<String> nombreCrater = crateres.stream()
                    .map(it -> it.getNombre().toLowerCase()).collect(Collectors.toList());
            for (String nombre : crateresNombresIngresados) {
                int idx = nombreCrater.indexOf(nombre.trim());
                if (idx >= 0) {
                    crateresVisitar.add(crateres.get(idx));
                } else {
                    crateresNoEncontrados.add(nombre);
                }
            }

            if (!crateresNoEncontrados.isEmpty()) {
                a.setAlertType(Alert.AlertType.WARNING);
                a.setContentText("Nombre de crateres no encontrados: " + crateresNoEncontrados
                        + "\n no es posible planificar la ruta.");
                a.show();
                return;
            }

            GridPane orden = new GridPane();
            orden.gridLinesVisibleProperty().set(true);

            Deque<Crater> tmp = new LinkedList<>(crateresVisitar);
            List<Crater> last_dist = new ArrayList<>();

            //System.out.println("ORDEN");
            //System.out.println("Distacia desde Rover: u0 = " + u0 + "\f" + roverSeleccionado);
            while (last_dist.size() < crateresVisitar.size()) {

                double dist_min = Double.MAX_VALUE;
                Crater minDistCrater = null;

                while (!tmp.isEmpty()) {
                    Crater t = tmp.poll();
                    double ndist = u0.distancia(t.getUbicacion()).get(0);
                    if (ndist < dist_min) {
                        dist_min = ndist;
                        minDistCrater = t;
                    }
                    //System.out.println("\t[crater] " + t + "\f dist: " + ndist);
                }
                //System.out.println("Distacia desde crater: " + minDistCrater);

                if (minDistCrater == null) {
                    break;
                }

                last_dist.add(minDistCrater);
                u0 = minDistCrater.getUbicacion();
                for (Crater c : crateresVisitar) {
                    if (!last_dist.contains(c)) {
                        tmp.add(c);
                    }
                }
            }
            //System.out.println("LAST DIST LIST: " + last_dist);
            int i = 1;
            for (Crater c : last_dist) {
                Label l = new Label(i++ + " " + c.getNombre());
                l.setPadding(new Insets(10));
                orden.addRow(i, l);
            }

            if (!last_dist.isEmpty()) 
                paneNombres.getChildren().add(orden);
            

        }catch(NullPointerException ex){
                a.setContentText(ex.getMessage());
                ex.printStackTrace();
                a.show();
        }
    }

    /**
     * Regresa a la vista inicial
     *
     * @param event
     */
    @FXML
    private void volverMenu(MouseEvent event) {
        App.cambioVista("VistaInicial");

    }

    /**
     * Actualiza el rover seleccionado
     *
     * @param event
     */
    @FXML
    private void seleccionar(ActionEvent event) {
        roverSeleccionado = cbRover.getValue();
        paneNombres.getChildren().clear();
        crateresIngresados.setText("");
    }

}
