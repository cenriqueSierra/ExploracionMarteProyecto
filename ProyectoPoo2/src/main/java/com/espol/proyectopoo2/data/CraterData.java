/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.espol.proyectopoo2.data;

import com.espol.proyectopoo2.modelo.Crater;
import com.espol.proyectopoo2.modelo.Ubicacion;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Carlos user
 */
public class CraterData {
    
    /**
     * Ruta para archivo que contiene la informacion de los crateres
     */
    public static String rutaCrater = Constantes.ARCHIVOS+"/crateres_info.txt";
    
    /**
     * Ruta para archivo que contiene la informacion de los minerales
     */
    public static String rutaMinerals = Constantes.ARCHIVOS+"/minerales.txt";
    
    /**
     * Metodo para leer la informacion de los crateres que 
     * está en un archivo de formato txt
     * @return Lista de crateres
     */
    public static List<Crater> cargarCrateres(){
        List<Crater> crateres = new ArrayList<>();
        
        try(BufferedReader lector = new BufferedReader(new FileReader(rutaCrater))){
            String line;
            while((line = lector.readLine()) != null ){
                String[] parts = line.split(",");
                String id = parts[0];
                String nombre = parts[1];
                double latitud = Double.parseDouble(parts[2]);
                double longitud = Double.parseDouble(parts[3]);
                double radio = Double.parseDouble(parts[4]);
                
                crateres.add(new Crater(id,nombre,radio, new Ubicacion(latitud, longitud)));
            }
        }catch(FileNotFoundException ex){
            ex.printStackTrace();
        }catch(IOException ioex){
            ioex.printStackTrace();  
        }
        return crateres;
        
    }
    
    /**
     * Metodo para leer la informacion de los minerales que están
     * en un archivo .txt
     * @return Lista de minerales
     */
    public static List<String> cargarMinerales(){
        List<String> minerales = new ArrayList<>();
        
        try(BufferedReader lector = new BufferedReader(new FileReader(rutaCrater))){
            String line;
            while((line = lector.readLine()) != null ){
                String m = line.strip();
                minerales.add(m);
                                
            }
        }catch(FileNotFoundException ex){
            ex.printStackTrace();

        }catch(IOException ex){
            ex.printStackTrace();

            
        }
        return minerales;
        
    }
    
}
