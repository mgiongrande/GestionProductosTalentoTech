package com.techlab.gestionproductos.enums;

public enum TipoProducto {
    GASEOSA("Gaseosa"),
    VINO("Vino");

    private String descripcion;

    TipoProducto(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return this.descripcion;
    }
}
