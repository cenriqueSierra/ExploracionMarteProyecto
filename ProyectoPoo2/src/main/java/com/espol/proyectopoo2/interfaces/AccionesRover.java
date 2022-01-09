package com.espol.proyectopoo2.interfaces;

/**
 * Interfaz que contiene las acciones que puede realizar el rover
 * @author josek
 */
public interface AccionesRover {
    /**
     * Mueve al rover N pixeles adelante en la direccion que encara
     */
    public void avanzar();
    /**
     * Gira al rover los grados pasados como argumento en sentido de las
     * manecillas del reloj. Si el argumento es negativo, gira contrario.
     * @param grados Grados a girar el rover. En caso de ser negativo 
     * gira contrario a las manecillas del reloj.
     */
    public void girar(double grados);
    /**
     * Desplaza el rover hacia la ubicacion pasada.
     * @param ubicacion Ubicacion donde se quiere mover el rover.
     */
    //public void desplazarse(Ubicacion ubicacion);
    /**
     * Sensa el crater por minerales.
     * @return String de minerales encontrados.
     */
    public String sensar();     
    
}