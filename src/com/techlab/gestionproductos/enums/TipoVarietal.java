package com.techlab.gestionproductos.enums;

public enum TipoVarietal {
    MALBEC("Malbec"),
    CABERNET_SAUVIGNON("Cabernet Sauvignon"),
    CABERNET_FRANC("Cabernet Franc"),
    MERLOT("Merlot"),
    BLEND("Blend");

    private String descripcion;

    TipoVarietal(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return this.descripcion;
    }
}
