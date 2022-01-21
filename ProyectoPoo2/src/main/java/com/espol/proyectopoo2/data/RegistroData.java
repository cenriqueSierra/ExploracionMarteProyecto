/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.espol.proyectopoo2.data;

import static com.espol.proyectopoo2.data.CraterData.rutaCrater;
import com.espol.proyectopoo2.modelo.Crater;
import com.espol.proyectopoo2.modelo.Registro;
import com.espol.proyectopoo2.modelo.Ubicacion;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 *Clase que se encarga de la lectura de los crateres explotados
 * @author Carlos user
 */
public class RegistroData {
    /**
     * Ruta para archivo que contiene la informacion de los minerales
     */
    public static String rutaReporte = Constantes.ARCHIVOS+"/reporteSensado.dat";
    
    public static List<Registro> reportes;
    
    public static void addReporte(Registro reporte){
        reportes.add(reporte);
    }
    /**
     * Metodo que crear un archivo binario que almacena 
     * una lista de objetos Reporte
     * 
     * @param reportes Lista de reportes generada cuando el rover sensa el crater
     */
    public static void guardarReporte(List<Registro> reportes){
        
        try(ObjectOutputStream outStream = new ObjectOutputStream(new FileOutputStream(rutaReporte,true))){        
            outStream.writeObject(reportes); 
            
        
        }catch(FileNotFoundException fex){
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Alerta de Error");
            alert.setHeaderText("Estado");
            alert.setContentText("Archivo de reportes no existe");

            alert.showAndWait();
            
        }catch(IOException ioex){
            ioex.printStackTrace();  
        }
    }
    
    /**
     * Metodo para leer la informacion de los crateres que 
     * han sido sensados
     * @return Lista de objetos reporte
     */
    public static List<Registro> cargarRegistro(){
        List<Registro> reporteSenso = null;
        
        try(ObjectInputStream oinStream = new ObjectInputStream(new FileInputStream(rutaReporte))){
            reporteSenso = (ObservableList<Registro>) oinStream.readObject(); 
            
        }catch(FileNotFoundException fex){
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Alerta de Error");
            alert.setHeaderText("Estado");
            alert.setContentText("Archivo de reportes no existe");

            alert.showAndWait();
            
        }catch(ClassNotFoundException ex){
            
        }
        catch(IOException ioex){
            ioex.printStackTrace();  
        }
        return reporteSenso;
    }
    
}
