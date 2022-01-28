package com.espol.proyectopoo2.data;

import static com.espol.proyectopoo2.data.Constantes.factor;
import com.espol.proyectopoo2.modelo.Rover;
import com.espol.proyectopoo2.modelo.RoverEolico;
import com.espol.proyectopoo2.modelo.RoverSolar;
import com.espol.proyectopoo2.modelo.Ubicacion;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que contiene la informacion de los rovers
 * @author Carlos user
 */
public class RoverData {
    /**
     * Ruta relativa al archivo de rovers
     */
    public static String ruta = Constantes.ARCHIVOS+"/rovers-1.txt";
    
    /**
     * Obtiene lista de los rovers a partir del archivo
     * @return Lista de los rovers
     */
    public static List<Rover> cargarRovers(){
        List<Rover> rovers = new ArrayList<>();
        try( BufferedReader bf =
                new BufferedReader(new FileReader(ruta))){
            String linea;
            while((linea = bf.readLine())!=null){
                System.out.println(linea);
                String[] roverAtributos = linea.split(",");
                String nombre =roverAtributos[0];
                Ubicacion ubicacion = new Ubicacion(
                        Double.parseDouble(roverAtributos[1])*factor,
                        Double.parseDouble(roverAtributos[2])*factor);
                String tipo = roverAtributos[3];                
                if(tipo.equals("solar")){
                    RoverSolar rover = new RoverSolar(
                            nombre,
                            ubicacion,
                            nombre+".png",
                            100);
                    rovers.add(rover);
                }else{
                    RoverEolico rover = new RoverEolico(
                            nombre,
                            ubicacion,
                            nombre+".png",
                            100);
                    rovers.add(rover);
                }   
            }                           
        }catch (IOException ex) {
            ex.printStackTrace();
        }        
        return rovers;    
    }
    
    /**
     * Busca al rover en la lista de rovers obtenida del archivo
     * @param nombreRover Nombre de rover a buscar
     * @return Rover cuyo nombre se ha pasado, null si no lo encuentra
     */
    public Rover buscarRover(String nombreRover){
        Rover roverBuscado = null ;        
        for(Rover r: cargarRovers())
            if(r.getNombre().equals(nombreRover))
                roverBuscado = r;        
        return roverBuscado;
    }    
}