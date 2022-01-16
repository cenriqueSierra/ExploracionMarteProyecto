package com.espol.proyectopoo2.hilos;

import com.espol.proyectopoo2.modelo.Rover;

/**
 * Hilo creado para el comando girar
 * @author josek
 */
public class HiloGirar extends Thread{
    /**
     * Rover en cuestion
     */
    Rover rover;
    /**
     * Angulo a girar el rover
     */
    double angulo;
    /**
     * Constructor del hilo
     * @param rover Rover a comandar
     * @param angulo Angulo a girar el rover
     */
    public HiloGirar(Rover rover, double angulo){
        super();
        this.rover=rover;
        this.angulo=angulo;        
    }
    /**
     * Metodo run del hilo
     */
    @Override
    public void run(){
        rover.girar(angulo);
    }
}
