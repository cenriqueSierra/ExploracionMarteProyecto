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
    public static String ruta = Constantes.ARCHIVOS+"crateres_info.txt";
    
    /**
     * Metodo para leer la informacion de los crateres que está en un archivo de formato txt
     * @return 
     */
    public static List<Crater> cargarCrateres(){
        List<Crater> crateres = new ArrayList<>();
        
        try(BufferedReader lector = new BufferedReader(new FileReader(ruta))){
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
        }catch(FileNotFoundException fnex){
            
        }catch(IOException ioex){
            
        }
        return crateres;
        
    }
    
}
