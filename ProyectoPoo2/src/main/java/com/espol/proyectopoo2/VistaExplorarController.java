/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.espol.proyectopoo2;

import com.espol.proyectopoo2.data.*;
import com.espol.proyectopoo2.modelo.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

/**
 * FXML Controlador de la vista explorar
 * Contiene el modelo de lo presentado.
 * @author  user
 */
public class VistaExplorarController implements Initializable {
    /**
     * Texto del comando ingresado.
     */
    @FXML
    private TextField comandoIngresado;
    
    /**
     * Combo box que contiene los rover disponibles.
     */
    @FXML
    private ComboBox<Rover> cboxRover;
    
    /**
     * Panel donde se encuentra la imagen de la superficie
     */
    @FXML
    private Pane panelSuperficie;
    
    /**
     * Lista donde se presentan los comando ingresados.
     */
    @FXML
    private ListView<String> comandosIngresados;
    
    /**
     * Caja donde aparece la informacion del crater.
     */
    @FXML
    private VBox infoCrater;
    
    /**
     * Rover que fue seleccionado por el usuario.
     */
    private Rover roverSeleccionado;
    
    /**
     * Alerta usada en la vista.
     */
    private Alert alerta;
    
    /**
     * Crateres mostrados en pantalla.
     */
    private List<Crater> crateresPantalla;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        crateresPantalla = new ArrayList<>();
        Rectangle r = new Rectangle(panelSuperficie.getWidth(), panelSuperficie.getHeight());
        r.heightProperty().bind(panelSuperficie.heightProperty());
        r.widthProperty().bind(panelSuperficie.widthProperty());
        panelSuperficie.setClip(r);
        alerta = new Alert(Alert.AlertType.ERROR);
        cboxRover.getItems().addAll(RoverData.cargarRovers());
        cargarCrateres();
       
       
    }
    
    /**
     * Muestra los circulos que representan los craters.
     */
    private void cargarCrateres(){
        List<Crater> crateres = CraterData.cargarCrateres();

        for(Crater c: crateres){
            Circle circulo = c.getCirculo();
            circulo.setId(c.getId());

            c.getCirculo().setCenterX(c.getUbicacion().getLongitud());
            c.getCirculo().setCenterY(c.getUbicacion().getLatitud());
            crateresPantalla.add(c);
            panelSuperficie.getChildren().add(circulo);
            
            circulo.setOnMouseClicked((MouseEvent ev)-> {
                infoCrater.getChildren().clear();
                Label craterInfo = new Label(c.toString());
                infoCrater.getChildren().add(craterInfo);
                
                    if( c.isExplorado() ){
                        try {
                            List<Registro> registros = RegistroData.leerReporte();
                            List<String> minerales = new ArrayList<>();
                            for(Registro r: registros){
                                if(r.getNombreCrater().equalsIgnoreCase(c.getNombre())){
                                    minerales.addAll(r.getMinerales());
                                }

                            }
                            Label mensaje = new Label("Minerales encontrados");
                            Label lbminerales = new Label(minerales.toString());
                            infoCrater.getChildren().add(mensaje);
                            infoCrater.getChildren().add(lbminerales);
                            
                        } catch (IOException ex) {
                            System.out.println("Problemas tecnicos. Estamos resolviendo");
                            //ex.printStackTrace();
                            
                        }
                    }
                
     
            });
        }



 
    }
 
    /**
     * Regresea alerta la vista inicial.
     * @param event 
     */
    @FXML
    private void regresarMenu(MouseEvent event) {
        App.cambioVista("VistaInicial");
        RoverData.guardarRovers(cboxRover.getItems());
        
    }
    
    /**
     * Ejecuta el comando ingesado
     * @param event 
     */
    @FXML
    private void ingresoComando(KeyEvent event) {
                       
       try{
           if(roverSeleccionado ==null)
               throw new ComandoInvalidoException("Seleccione un Rover");
           
           
            if (event.getCode() == event.getCode().ENTER){
              
                String comando = comandoIngresado.getText();
                String[] comandoSeparado = comando.trim().split(":");

                if(!comando.isBlank())
                    comandosIngresados.getItems().add(comando);

                comandoIngresado.clear();
                cboxRover.setDisable(true);

                switch(comandoSeparado[0].toLowerCase() ){
                case("avanzar"):
                    System.out.println("Posicion vieja : "+roverSeleccionado.getUbicacion());
                    Ubicacion u = roverSeleccionado.posicionNueva();

                    if (dentroLimites( u.getLongitud(),u.getLatitud())){
                        System.out.println("Debio avanzar: a "+u);
                        roverSeleccionado.avanzar();}
                    else 
                        throw new ComandoInvalidoException("El Rover no puede avanzar mas");
                    System.out.println("Posicion nueva : "+roverSeleccionado.getUbicacion());
                    break;
                case("girar"):
                    roverSeleccionado.girar(Double.parseDouble(comandoSeparado[1]));                     
                    break;
                case("desplazarse"):
                    String[] ubicacion = comandoSeparado[1].trim().split(",");                    
                    Double x = Double.parseDouble(ubicacion[0]);
                    Double y = Double.parseDouble(ubicacion[1]);

                    if (dentroLimites(x, y))
                        roverSeleccionado.desplazarse(new Ubicacion(x,y),false);
                    else
                        throw new ComandoInvalidoException("No es posible desplazarse\ncoordenadas: ("+
                                                            +x+" , "+y+") fuera de los limites");
                    break;

                case("sensar"):
                    String sensado = roverSeleccionado.sensar(crateresPantalla);
                    
                    break;
                case("cargar"):
                    roverSeleccionado.cargar();

                    break;
                default:
                    throw new ComandoInvalidoException("Comando ingresado no existe");
                }
            }
        }catch(NumberFormatException ex){
            alerta.setContentText("Ingrese un comando correcto");
            alerta.show();
        }catch(IndexOutOfBoundsException ex){
            alerta.setContentText("Comando incompleto");
            alerta.show();
        }catch(ComandoInvalidoException ex){
            alerta.setContentText(ex.getMessage());
            alerta.show();
        }catch(Exception ex){
            System.out.println("Error desconocido");
        }
       finally{
            cboxRover.setDisable(false);
        }
    }
    
    /**
     * Selecciona el rover.
     * @param event 
     */
    @FXML
    private void seleccionarRover(ActionEvent event) {
        if(roverSeleccionado!=null){
            panelSuperficie.getChildren().remove(roverSeleccionado.getImagen());
            comandosIngresados.getItems().clear();
            roverSeleccionado.stop();
        }                
        roverSeleccionado = cboxRover.getValue();
        if(roverSeleccionado!= null){
            panelSuperficie.getChildren().add(roverSeleccionado.getImagen());
            roverSeleccionado.start();
        }
    }
    
 
    /**
     * Verifica que las coordenas ingresadas esten dentro de los limites 
     * de la superficie.
     * @param x longitud
     * @param y latitud
     * @return Verdadero si esta dentro de los limites, caso contrario retorna falso.
     */
    private boolean dentroLimites(double x , double y){

        double limitY = panelSuperficie.getPrefHeight()-60;
        double limitX = panelSuperficie.getPrefWidth()-60;
        return 0<=x && x<=limitX && 0<=y && y<= limitY ;
            
    }

}
