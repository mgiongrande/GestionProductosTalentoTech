package com.techlab.gestionproductos.enums;

public enum TipoGaseosa {
    ORIGINAL("Original"),
    ZERO("Zero"),
    LIGHT("Light");

    private String descripcion;

    TipoGaseosa(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return this.descripcion;
    }
}
