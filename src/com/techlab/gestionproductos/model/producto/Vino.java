package com.techlab.gestionproductos.model.producto;

import com.techlab.gestionproductos.enums.TipoVarietal;

public class Vino extends Producto {
    private int cosecha;
    private TipoVarietal tipoVarietal;

    public Vino(int id, String nombre, double precio, int cantidad, int cosecha, TipoVarietal tipoVarietal) {
        super(id, nombre, precio, cantidad);
        this.cosecha = cosecha;
        this.tipoVarietal = tipoVarietal;
    }

    public TipoVarietal getTipoVarietal() {
        return tipoVarietal;
    }

    public int getCosecha() {
        return cosecha;
    }

    @Override
    public String listar() {
        return this.getId() + " - " + this.getNombre() + " " +  this.tipoVarietal.getDescripcion() + " " + this.cosecha + " | Precio: $ " + this.getPrecio() + " | Stock: " + this.getCantidad();
    }

    @Override
    public String listarParaPedido() {
        return this.getNombre() + " " +  this.tipoVarietal.getDescripcion() + " " + this.cosecha;
    }
    
}
