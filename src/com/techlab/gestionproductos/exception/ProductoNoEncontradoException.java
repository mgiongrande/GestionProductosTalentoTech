package com.techlab.gestionproductos.exception;

public class ProductoNoEncontradoException extends RuntimeException {
    public ProductoNoEncontradoException(String mensaje) {
        super("Producto no encontrado con ID: " + mensaje);
    }
    
}
