package com.espol.proyectopoo2.data;

import com.espol.proyectopoo2.modelo.Registro;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * Clase que se encarga de la lectura de los crateres explotados
 * @author Carlos user
 */
public class RegistroData {
    
    /**
     * Ruta relativa al archivo que contiene la informacion de los reporte
     */
    public static String rutaReporte = Constantes.ARCHIVOS+"/reporteSensado.txt";
    
    /**
     * Lista de reportes del programa
     */
    public static List<Registro> reportes = new ArrayList<>();
    
    /**
     * Metodo para guardar los datos de los crateres que han sido sensados
     * @param reporte Ingresa un objeto registro a guardar
     */
    public static void guardarReporte(Registro reporte){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(rutaReporte,true))){
            List<String> m = reporte.getMinerales();
            String line = String.valueOf(
                        reporte.getFecha())+";"+
                        reporte.getNombreCrater()+";"+
                        String.join(",", m)+"\n";                
            writer.write(line);  
        } catch (IOException ex) {
            System.out.println("Problemas tecnicos");
        }        
    }
    
    /**
     * Metodo para obtener los datos de los crateres explotados
     * @return Lista de
     * @throws java.io.FileNotFoundException
     * @throws java.io.IOException
     */
    public static List<Registro> leerReporte() throws FileNotFoundException, IOException{        
        try(BufferedReader lector = new BufferedReader(new FileReader(rutaReporte))){
            String line;
            reportes.clear();
            while((line = lector.readLine()) != null){
                String[] parts = line.split(";");
                List<String> minerales = new ArrayList<>(); //Se guarda un solo string con los minerales
                minerales.addAll(Arrays.asList(parts[2].strip().split(",")));
                reportes.add(new Registro(
                                    LocalDate.parse(parts[0]), 
                                    (ArrayList<String>) minerales, 
                                    parts[1]));                
            }            

        }catch (FileNotFoundException ex) {
            
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Alerta de Error");
            alert.setHeaderText("Estado");
            alert.setContentText("Archivo de reportes no existe");
            alert.showAndWait();
        } catch (IOException ex) {
            System.out.println("Problemas tecnicos");
        }        
        return reportes;
    }    
}