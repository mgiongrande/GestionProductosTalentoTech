package com.techlab.gestionproductos.enums;

public enum EstadoPedido {
    EN_PROCESO("En Proceso"),
    CONFIRMADO("Confirmado"),
    ENVIADO("Enviado"),
    ENTREGADO("Entregado"),
    CANCELADO("Cancelado");


    private final String descripcion;

    EstadoPedido(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return this.descripcion;
    }
}
