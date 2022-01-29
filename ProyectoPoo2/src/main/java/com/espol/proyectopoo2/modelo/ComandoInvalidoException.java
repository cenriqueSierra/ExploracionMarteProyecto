/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */
package com.espol.proyectopoo2.modelo;

/**
 * Exception para comandos invalidos, hereda de RuntimeException
 * @author Dome
 */
public class ComandoInvalidoException extends RuntimeException {
   
    /**
     * Crea una instancia de ComandoInvalidoException con un mensaje
     * @param str el mensaje
     */
    public ComandoInvalidoException(String msg) {
        super(msg);
    }
}