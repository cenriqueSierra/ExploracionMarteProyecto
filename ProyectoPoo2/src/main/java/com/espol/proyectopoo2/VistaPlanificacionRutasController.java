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
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author Carlos user
 */
public class VistaPlanificacionRutasController implements Initializable {

    private TextField crateres;
    
//    private ArrayList<Crater> crateresVisitar;
    
    private List<Double> crateresDistancia;
//    
    
    @FXML
    private ComboBox<Rover> cbRover;
    
    private Rover roverSeleccionado;
    
    
    private Alert a;
    @FXML
    private TextField crateresIngresados;
    @FXML
    private GridPane paneNombres;
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

    @FXML
    private void buscarRutas(KeyEvent event) {
        List<String> nombresCra = null;
        List<Crater> crateresVisitar = new ArrayList<>();
        //Queue<Double> dist = new ArrayDeque<>();
        Ubicacion u0 = roverSeleccionado.getUbicacion();
        if (event.getCode() == event.getCode().ENTER){
            try{
                if(cbRover.getValue()==null)
                    throw new NullPointerException("Seleccione un Rover");
                
            String[] crateresNombres = crateres.getText().split(",");
            
            for(Crater c : CraterData.cargarCrateres()){
                for(String nombreCrater : crateresNombres){
                    if(c.getNombre().equalsIgnoreCase(nombreCrater.trim())){
                        //dist.add(c.getUbicacion().distancia(u0));
                        crateresVisitar.add(c);
                    }
                    
                }
            }
            
            
          
            Deque<Crater> tmp = new LinkedList<>(crateresVisitar);
            List<Crater> last_dist = new ArrayList<>();
            
            
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
                }
                
                if (minDistCrater == null)
                    break;
                
                last_dist.add(minDistCrater);
                paneNombres.getChildren().add(new Label(minDistCrater.getNombre()));
                u0 = minDistCrater.getUbicacion();
                
                for(Crater c : crateresVisitar) {
                    if (!last_dist.contains(c))
                        tmp.add(c);
                }
                
            }
            
                    
            
            }catch(NullPointerException ex){
                a.setContentText("Selecciones un rover");
            }
        }
    }

    @FXML
    private void volverMenu(MouseEvent event) {
    }

    
}
