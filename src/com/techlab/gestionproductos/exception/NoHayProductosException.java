package com.techlab.gestionproductos.exception;

public class NoHayProductosException extends RuntimeException {
    public NoHayProductosException() {
        super("No hay productos disponibles.");
    }
    
}
