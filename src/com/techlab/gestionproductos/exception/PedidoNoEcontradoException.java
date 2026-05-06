package com.techlab.gestionproductos.exception;

public class PedidoNoEcontradoException extends RuntimeException {
    public PedidoNoEcontradoException(String mensaje) {
        super("Pedido no encontrado con ID: " + mensaje);
    }
}
