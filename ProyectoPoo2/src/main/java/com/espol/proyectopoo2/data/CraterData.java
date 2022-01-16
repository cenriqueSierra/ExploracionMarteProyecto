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
import java.util.Random;

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
    
    public static String mineralAleatorio(){
        Random random = new Random();
        List<String> minerales = new ArrayList(cargarMinerales());
        int indice = random.nextInt(minerales.size()-1);
        return minerales.get(indice);
    }
    
    public static String mineralAleatorioRepetido(){
        int repeticion = new Random().nextInt(6);
        String minerales = null;
        for(int i = 0; i<repeticion ;i++){
            String mineral = mineralAleatorio();
            minerales += mineral;
        }
        return minerales.substring(0,minerales.length());        
    }
    
    public static Crater isUbicacionInCrater(Ubicacion ubicacion){
        List<Crater> crateres = new ArrayList(cargarCrateres());
        
        for(Crater crater : crateres){
            if(isInCrater(crater,ubicacion))
                return crater;
        }
        return null;
    }
    
    public static boolean isInCrater(Crater crater, Ubicacion ubicacion){
        double posXCrater=crater.getUbicacion().getLongitud();
        double posYCrater=crater.getUbicacion().getLatitud();
        double radio=crater.getRadio();
        double limiteInferiorX = posXCrater - radio;
        double limiteSuperiorX = posXCrater + radio;
        double limiteInferiorY = posYCrater - radio;
        double limiteSuperiorY = posYCrater + radio;
        boolean isInX = limiteInferiorX<=ubicacion.getLongitud() &&
                        ubicacion.getLongitud()<=limiteSuperiorX;
        boolean isInY = limiteInferiorY<=ubicacion.getLatitud() &&
                        ubicacion.getLatitud()<=limiteSuperiorY;
        return isInX && isInY;
    }
    
    
}
