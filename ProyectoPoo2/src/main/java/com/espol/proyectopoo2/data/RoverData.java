package com.espol.proyectopoo2.data;

import com.espol.proyectopoo2.VistaExplorarController;
import static com.espol.proyectopoo2.data.Constantes.factor;
import com.espol.proyectopoo2.modelo.Rover;
import com.espol.proyectopoo2.modelo.RoverEolico;
import com.espol.proyectopoo2.modelo.RoverSolar;
import com.espol.proyectopoo2.modelo.Ubicacion;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
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
     * Guarda los rover en el archivo rovers-1.txt
     * @param rovers Rover a guardar
     */
    public static void guardarRovers(List<Rover> rovers){
      
        try(BufferedWriter bw =
                new BufferedWriter(new FileWriter(ruta))){
            String tipo;
            for(Rover r : rovers){
                if(r instanceof RoverSolar)
                    tipo = "solar";
                else
                    tipo = "eolico";                            
                bw.write(   r.getNombre()+","+
                            r.getUbicacion().getLongitud()+","+
                            r.getUbicacion().getLatitud()+","+
                            tipo);
                bw.newLine();
                bw.flush();
            }
        } catch (IOException ex) {
            System.out.println("Problemas tecnicos");
            //ex.printStackTrace();
        }
    }

}