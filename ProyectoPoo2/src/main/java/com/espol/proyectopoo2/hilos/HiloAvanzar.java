package com.espol.proyectopoo2.hilos;

import com.espol.proyectopoo2.modelo.Rover;

/**
 * Hilo creado para el comando avanzar
 * @author josek
 */
public class HiloAvanzar extends Thread {
    /**
     * Rover en cuestion
     */
    Rover rover;
    /**
     * Constructor del hilo
     * @param rover Rover a comandar
     */
    public HiloAvanzar(Rover rover){
        super();
        this.rover=rover;
    }
    /**
     * Metodo run del hilo
     */
    @Override
    public void run(){
        rover.avanzar();
    }
    
}
