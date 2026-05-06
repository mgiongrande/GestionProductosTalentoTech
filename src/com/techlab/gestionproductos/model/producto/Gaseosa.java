package com.techlab.gestionproductos.model.producto;

import com.techlab.gestionproductos.enums.TipoGaseosa;

public class Gaseosa extends Producto {
    private int volumen;
    private TipoGaseosa tipo;

    public Gaseosa(int id, String nombre, double precio, int cantidad, int volumen, TipoGaseosa tipo) {
        super(id, nombre, precio, cantidad);
        this.volumen = volumen;
        this.tipo = tipo;
    }

    public int getVolumen() {
        return volumen;
    }

    public TipoGaseosa getTipo() {
        return tipo;
    }

    @Override
    public String listar() {
        return this.getId() + " - " + this.getNombre() + " " + this.getTipo().getDescripcion() + " | Precio: $ " + this.getPrecio() + " | Stock: " + this.getCantidad() + " | Volumen: " + this.volumen + " ml";
    }

    @Override
    public String listarParaPedido() {
        return this.getNombre() + " " + this.getTipo().getDescripcion();
    }
}   