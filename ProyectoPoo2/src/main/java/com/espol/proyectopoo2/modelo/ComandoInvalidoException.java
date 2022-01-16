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
     * Creates a new instance of <code>ComandoInvalido</code> without detail
     * message.
     */
    public ComandoInvalidoException() {
    }

    /**
     * Constructs an instance of <code>ComandoInvalido</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public ComandoInvalidoException(String msg) {
        super(msg);
    }
}
