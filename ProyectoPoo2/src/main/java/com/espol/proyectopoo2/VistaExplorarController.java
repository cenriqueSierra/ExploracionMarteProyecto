/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.espol.proyectopoo2;

import com.espol.proyectopoo2.data.CraterData;
import com.espol.proyectopoo2.data.RegistroData;
import com.espol.proyectopoo2.data.RoverData;
import com.espol.proyectopoo2.modelo.ComandoInvalidoException;
import com.espol.proyectopoo2.modelo.Crater;
import com.espol.proyectopoo2.modelo.Registro;
import com.espol.proyectopoo2.modelo.Rover;
import com.espol.proyectopoo2.modelo.Ubicacion;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
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
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

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
    
    private Rover roverSeleccionado;
    
    private Alert a;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Rectangle r = new Rectangle(panelSuperficie.getWidth(), panelSuperficie.getHeight());
        r.heightProperty().bind(panelSuperficie.heightProperty());
        r.widthProperty().bind(panelSuperficie.widthProperty());
        panelSuperficie.setClip(r);
        a = new Alert(Alert.AlertType.ERROR);
       cboxRover.getItems().addAll(RoverData.cargarRovers());
       cargarCrateres();
       
       
    }
    
    /**
     * Muestra los circulos que representan los craters.
     */
    private void cargarCrateres(){
        List<Crater> crateres = CraterData.cargarCrateres();
        //StackPane st = new StackPane();
        //st.setPrefSize(panelSuperficie.getPrefWidth(), panelSuperficie.getPrefHeight());
        for (Crater cr:  crateres)
            System.out.println(cr.getRadio());
                
        for(Crater c: crateres){
            Circle circulo = new Circle(c.getRadio());
            if(c.isExplorado())
                circulo.getStyleClass().add("relleno");
            circulo.getStyleClass().add("contorno");
           
           
            
            circulo.setLayoutX(c.getUbicacion().getLongitud());
            /*(panelSuperficie.getPrefWidth()/1372.0)*/
            circulo.setLayoutY(c.getUbicacion().getLatitud()/*(panelSuperficie.getPrefHeight()/997.0)*/);
            
            panelSuperficie.getChildren().add(circulo);
            //st.getChildren().add(circulo);
            
            circulo.setOnMouseClicked((MouseEvent ev)-> {
                infoCrater.getChildren().clear();
                Label craterInfo = new Label(c.toString());
                infoCrater.getChildren().add(craterInfo);
                /*List<Registro> registros = RegistroData.reportes;
                    //int validacion = r.getCrater().getId().compareTo(c.getId());&& validacion == 0
                    if( c.isExplorado() ){
                        List<String> minerales = new ArrayList<>();
                        for(Registro r: registros){
                            if(r.getCrater().equals(c)){
                               minerales.addAll(r.getMinerales());
                            }
                        
                        }

                    }*/
                
     
            });
        }
         //panelSuperficie.getChildren().add(st);


 
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
                       
       try{
           if(roverSeleccionado ==null){
               throw new NullPointerException("Seleccione un Rover");
           }
           
           
            if (event.getCode() == event.getCode().ENTER){
                
                String comando = comandoIngresado.getText().toString();
                String[] comandoSeparado = comando.trim().split(":");
                
                if(!comando.isBlank())
                    comandosIngresados.getItems().add(comando);
                
                comandoIngresado.clear();
                switch(comandoSeparado[0].toLowerCase() ){
                case("avanzar"):
                    Ubicacion u = roverSeleccionado.posicionNueva();
                    
                    if (dentroLimites( u.getLongitud(),u.getLatitud()))
                        roverSeleccionado.avanzar();
                    else 
                        throw new NullPointerException("El Rover no puede avanzar mas");
                    break;
                case("girar"):
                    roverSeleccionado.girar(Double.parseDouble(comandoSeparado[1]));                     
                    break;
                case("desplazarse"):
                    String[] ubicacion = comandoSeparado[1].trim().split(",");                    
                    Double x = Double.parseDouble(ubicacion[0]);
                    System.out.println(x);
                    Double y = Double.parseDouble(ubicacion[1]);
                    System.out.println(y);
                    
                    if (dentroLimites(x, y))
                        roverSeleccionado.desplazarse(new Ubicacion(x,y),false);
                    

                    break;
                case("sensar"):
                    roverSeleccionado.sensar();
                    break;
                case("cargar"):
                    roverSeleccionado.cargar();
                    a.setAlertType(Alert.AlertType.INFORMATION);
                    a.setContentText("Abriendo paneles");
                    a.show();
                    break;
                default:
                    throw new ComandoInvalidoException();
                }
        }
            
       }
       catch(NullPointerException ex){
           comandoIngresado.clear();
           a.setContentText(ex.getMessage());
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

    @FXML
    private void seleccionarRover(ActionEvent event) {
        if(roverSeleccionado!=null)
            panelSuperficie.getChildren().remove(roverSeleccionado.getImagen());
        
        roverSeleccionado = cboxRover.getValue();
        if(roverSeleccionado!= null)
            panelSuperficie.getChildren().add(roverSeleccionado.getImagen());
        
    }
    
 
    
    private boolean dentroLimites(double x , double y){

        double limitY = panelSuperficie.getPrefHeight()-60;
        double limitX = panelSuperficie.getPrefWidth()-60;
        System.out.println("FIT Y");
        System.out.println(-roverSeleccionado.getImagen().getFitHeight());
        System.out.println("FIT X");
        System.out.println(-roverSeleccionado.getImagen().getFitWidth());
        
        return 0<=x && x<=limitX && 0<=y && y<= limitY ;
            
    }
}
