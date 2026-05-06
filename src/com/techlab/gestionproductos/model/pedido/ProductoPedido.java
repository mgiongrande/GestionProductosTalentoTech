package com.techlab.gestionproductos.model.pedido;

import com.techlab.gestionproductos.model.producto.Producto;

public class ProductoPedido {
    private int id;
    private String nombre;
    private double precio;

    public ProductoPedido(Producto producto) {
        this.id = producto.getId();
        this.nombre = producto.listarParaPedido();
        this.precio = producto.getPrecio();
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }
}
