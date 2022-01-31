/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.espol.proyectopoo2.data;

import static com.espol.proyectopoo2.data.Constantes.factor;
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
 * Clase que contiene la informacion de los crateres
 * @author Carlos user
 */
public class CraterData {
    
    /**
     * Ruta relativa del archivo que contiene la informacion de los crateres
     */
    public static String rutaCrater = Constantes.ARCHIVOS+"/crateres_info.txt";
    
    /**
     * Ruta relativa del archivo que contiene la informacion de los minerales
     */
    public static String rutaMinerals = Constantes.ARCHIVOS+"/minerales.txt";
    
    /**
     * Metodo para leer la informacion de los crateres que 
     * se encuentra en un txt y los devuelve en una lista
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
                double latitud = Double.parseDouble(parts[2])* factor;
                double longitud = Double.parseDouble(parts[3])*factor;
                double radio = Double.parseDouble(parts[4])*factor;
                
                crateres.add(new Crater(id,nombre,radio, new Ubicacion(latitud, longitud)));
            }
        }catch(FileNotFoundException ex){
            System.out.println("Problemas tecnicos, estamos resolviendo.");
            //ex.printStackTrace();
        }catch(IOException ioex){
            System.out.println("Problemas tecnicos, estamos resolviendo.");
            //ioex.printStackTrace();  
        }
        return crateres;        
    }
    
    /**
     * Metodo para leer la informacion de los minerales que 
     * se encuentra en un txt y los devuelve en una lista
     * @return Lista de minerales
     */
    public static List<String> cargarMinerales(){
        List<String> minerales = new ArrayList<>();
        
        try(BufferedReader lector = new BufferedReader(new FileReader(rutaMinerals))){
            String line;
            while((line = lector.readLine()) != null ){
                String m = line.strip();
                minerales.add(m);                                
            }
        }catch(FileNotFoundException ex){
            System.out.println("Problemas tecnicos con el archivo, estamos resolviendo.");
            //ex.printStackTrace();
        }catch(IOException ex){
            System.out.println("Problemas tecnicos, estamos resolviendo.");
            //ex.printStackTrace();
        }
        return minerales;        
    }
    
    /**
     * Selecciona un mineral aleatorio de la lista de minerales pasada
     * @param minerales Lista de minerales a seleccionar
     * @return Mineral aleatorio
     */
    public static String mineralAleatorio(List<String> minerales){
        Random random = new Random();
        int indice = random.nextInt(minerales.size()-1);
        return minerales.get(indice);
    }
    
    /**
     * Selecciona un numero aleatorio de minerales del archivo que tiene la
     * infomacion de minerales. El numero de minerales dependera del archivo.
     * @return Lista de minerales aleatorios
     */
    public static String mineralAleatorioRepetido(){
        List<String> mineralesLectura = cargarMinerales();
        int repeticion = new Random().nextInt(mineralesLectura.size()-1);   //Genera un numero de acuerdo al numero de minerales en el archivo
        List<String> minerales = new ArrayList<>();        
        for(int i = 0; i<repeticion ;i++){
            String mineral=mineralAleatorio(mineralesLectura);
            if(!minerales.contains(mineral))
                minerales.add(mineral);
        }
        if(minerales.isEmpty())
            minerales.add("Ninguno");
        return String.join((","), minerales);
    }
    
    /**
     * Revisa si la ubicacion pasada esta dentro de algun crater de la lista
     * pasada y lo retorna
     * @param ubicacion Ubicacion a comparar
     * @param crateres Lista de crateres a comparar
     * @return Crater que coincida con la ubicacion 
     */
    public static Crater isUbicacionInCrater(Ubicacion ubicacion,List<Crater> crateres){      
        for(Crater crater : crateres){
            if(isInCrater(crater,ubicacion)){
                return crater;
            }
        }
        return null;
    }
    
    /**
     * Revisa si la ubicacion pasada de argumento coincide con el area del 
     * crater pasado
     * @param crater Crater a comparar
     * @param ubicacion Ubicacion a comparar
     * @return Verdadero si la ubicacion coincide con el crater, falso si no
     */
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