/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.espol.proyectopoo2;

import com.espol.proyectopoo2.data.CraterData;
import com.espol.proyectopoo2.data.RoverData;
import com.espol.proyectopoo2.modelo.ComandoInvalidoException;
import com.espol.proyectopoo2.modelo.Crater;
import com.espol.proyectopoo2.modelo.Rover;
import com.espol.proyectopoo2.modelo.Ubicacion;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * FXML Controller class
 *
 * @author Carlos user
 */
public class VistaExplorarController implements Initializable {

    @FXML
    private TextField comandoIngresado;
    @FXML
    private ComboBox<Rover> cboxRover;
    @FXML
    private Pane panelSuperficie;
    @FXML
    private ListView<String> comandosIngresados;
    @FXML
    private VBox infoCrater;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<Rover> rovers = RoverData.cargarRovers();
        for(Rover r: rovers)
           cboxRover.getItems().add(r);
        
        cboxRover.getSelectionModel().selectedItemProperty().addListener((o, old, newvalue) -> {
           comandosIngresados.getItems().clear();
            Rover rover = newvalue;
            imagenRover(newvalue);
       });
        
       cargarCrateres();
       
    }
    
    /**
     * Muestra los circulos que representan los craters.
     */
    private void cargarCrateres(){
        List<Crater> crateres = CraterData.cargarCrateres();
        
        for (Crater cr:  crateres)
            System.out.println(cr.getRadio());
                
        for(Crater c: crateres){
            Circle circulo = new Circle(c.getRadio()/2,Color.RED);
            
                //circulo.getStyleClass().add("c-nocensado");
            circulo.setLayoutX(c.getUbicacion().getLongitud()/2.0/*(panelSuperficie.getPrefWidth()/1372.0)*/);
            circulo.setLayoutY(c.getUbicacion().getLatitud()/2.0/*(panelSuperficie.getPrefHeight()/997.0)*/);
            panelSuperficie.getChildren().add(circulo);
            circulo.setOnMouseClicked((MouseEvent ev)-> {
                Label craterInfo = new Label(c.toString());
                infoCrater.getChildren().add(craterInfo);
                if(c.isExplorado()){
                    //
                }
                    
            });
        }
 
    }
    
    private void imagenRover(Rover rover){
        panelSuperficie.getChildren().removeIf((t) -> {
               return t instanceof ImageView;
           });
        if(rover!= null){
                Image img;
                ImageView imgview = null;
                 try{
                     InputStream input = App.class.getResource(rover.getImage_path()).openStream();
                     img = new Image(input,60,60,true,true);
                     imgview = new ImageView(img);
                     panelSuperficie.getChildren().add(imgview);
                     imgview.setX(rover.getUbicacion().getLongitud());
                     imgview.setY(rover.getUbicacion().getLatitud());
                     imgview.setRotate(rover.getAngulo());
                 }catch (IOException | RuntimeException ex ) {
                     ex.printStackTrace();
                 }
        }
    }
    
    /**
     * Regresea a la vista inicial.
     * @param event 
     */
    @FXML
    private void regresarMenu(MouseEvent event) {
        App.cambioVista("VistaInicial");
    }
    
    /**
     * Ejecuta el comando ingesado
     * @param event 
     */
    @FXML
    private void ingresoComando(KeyEvent event) {
        
       Alert a = new Alert(Alert.AlertType.ERROR);
               
       try{
           Rover rover = cboxRover.getValue();
           if(rover ==null){
               throw new NullPointerException();
           }
           
           
            if (event.getCode() == event.getCode().ENTER){
                
                String comando = comandoIngresado.getText().toString();
                String[] comandoSeparado = comando.trim().split(":");
                
                if(!comando.isBlank())
                    comandosIngresados.getItems().add(comando);
                
                comandoIngresado.clear();
                switch(comandoSeparado[0].toLowerCase() ){
                case("avanzar"):
                    System.out.println("VIEJOOO \n"+rover.getUbicacion());
                    System.out.println("Angulo old "+rover.getAngulo());
                    rover.avanzar();
                    System.out.println("NUEVAAA \n"+rover.getUbicacion());
                    System.out.println("Angulo new "+rover.getAngulo());
                    System.out.println("-------------------------------");
                    imagenRover(rover);
                    break;
                case("girar"):
                    rover.girar(Double.parseDouble(comandoSeparado[1]));
                    imagenRover(rover);

                    break;
                case("desplazarse"):
                    String[] ubicacion = comandoSeparado[1].trim().split(",");
                    
                    Double longitud = Double.parseDouble(ubicacion[0]);
                    Double latitud = Double.parseDouble(ubicacion[1]);
                    
                    rover.desplazarse(new Ubicacion(longitud,latitud));
                    imagenRover(rover);

                    break;
                case("sensar"):
                    rover.sensar();
                    break;
                case("cargar"):
                    rover.cargar();
                    break;
                default:
                    throw new ComandoInvalidoException();
                }
        }
            
       }
       catch(NullPointerException ex){
           comandoIngresado.clear();
           a.setContentText("Seleccione un rover");
           a.show();
       }catch(NumberFormatException ex){
           a.setContentText("Ingrese un comando correcto");
           a.show();
       }catch(IndexOutOfBoundsException ex){
           a.setContentText("Comando incompleto");
           a.show();
       }catch(ComandoInvalidoException ex){
           a.setContentText("Comando ingresado no existe");
           a.show();
       }
    }
    
 
    
   /** static Rover roverActual(){
       return cboxRover.getValue();
       
   }**/
}
