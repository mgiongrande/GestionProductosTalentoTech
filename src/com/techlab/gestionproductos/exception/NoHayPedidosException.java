package com.techlab.gestionproductos.exception;

public class NoHayPedidosException extends RuntimeException {
    public NoHayPedidosException() {
        super("No hay pedidos disponibles.");
    }
    
}
