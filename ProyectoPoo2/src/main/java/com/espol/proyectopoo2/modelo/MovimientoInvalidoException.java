/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.espol.proyectopoo2.modelo;

/**
 *
 * @author josek
 */
public class MovimientoInvalidoException extends RuntimeException{
    /**
     * Crea una instancia de MovimientoInvalido sin detalles
     */
    public MovimientoInvalidoException(){
        super();
    }
    /**
     * Crea una instancia de MovimientoInvalido con un mensaje
     * @param str el mensaje
     */
    public MovimientoInvalidoException(String str){
        super(str);
    }
    
}
