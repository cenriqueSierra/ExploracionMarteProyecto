/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */
package com.espol.proyectopoo2.modelo;

/**
 *
 * @author Dome
 */
public class ComandoInvalidoException extends RuntimeException {

    /**
     * Crea una instancia de ComandoInvalido sin mensaje de detalles
     */
    public ComandoInvalidoException() {
    }

    /**
     * Construye una instancia de <Code>ComandoInvalido</Code> con el mensaje
     * de detalle especificado
     *
     * @param msg mensaje de detalle
     */
    public ComandoInvalidoException(String msg) {
        super(msg);
    }
}
