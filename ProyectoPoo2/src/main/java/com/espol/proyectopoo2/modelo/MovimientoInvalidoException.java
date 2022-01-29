/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.espol.proyectopoo2.modelo;

/**
 * Exception para movimientos invalidos, hereda de RuntimeException
 * @author josek
 */
public class MovimientoInvalidoException extends RuntimeException{

    /**
     * Crea una instancia de MovimientoInvalidoException con un mensaje
     * @param str el mensaje
     */
    public MovimientoInvalidoException(String str){
        super(str);
    }   
}