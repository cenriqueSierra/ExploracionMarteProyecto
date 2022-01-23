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
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
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
    public static String rutaReporte = Constantes.ARCHIVOS+"/reporteSensado.txt";
    
    
    /**
     * Metodo para guardar los datos de los crateres que han sido sensados
     * @param reporte Ingresa un objeto registro 
     */
    public static void guardarReporte(Registro reporte){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(rutaReporte,true))){
            List<String> m = reporte.getMinerales();
            String line = String.valueOf(reporte.getFecha())+","+
                        reporte.getNombreCrater()+","+String.join(",", m);
                
                writer.write(line);  
        } catch (IOException ex) {
            
        }
        
    }
    
    /**
     * Metodo para obtener los datos de los crateres explotados
     * @return 
     */
    public static List<Registro> leerReporte(){
        List<Registro> reporte = null;
        try(BufferedReader lector = new BufferedReader(new FileReader(rutaReporte))){
            String line;
            
            while((line = lector.readLine()) != null){
                String[] parts = line.split(",");
                List<String> minerales = new ArrayList<>();
                minerales = Arrays.asList(parts[2]);
                reporte.add(new Registro(LocalDateTime.parse(parts[0]), (ArrayList<String>) minerales,parts[1]));
                
            }
        } catch (FileNotFoundException ex) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Alerta de Error");
            alert.setHeaderText("Estado");
            alert.setContentText("Archivo de reportes no existe");

            alert.showAndWait();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return reporte;
    }
    
}
