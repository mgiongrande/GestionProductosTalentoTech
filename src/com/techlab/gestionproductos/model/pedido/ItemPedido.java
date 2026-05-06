package com.techlab.gestionproductos.model.pedido;

import com.techlab.gestionproductos.model.producto.Producto;

public class ItemPedido {
    private ProductoPedido producto;
    private int cantidad = 0;
    private double total = 0;

    public ItemPedido(Producto producto, int cantidad) {
        this.producto = new ProductoPedido(producto);
        this.cantidad = cantidad;
        this.total = producto.getPrecio() * cantidad;
    }

    public ProductoPedido getProducto() {
        return producto;
    }

    public double getTotal() {
        return total;
    }

    public int getCantidad() {
        return cantidad;
    }

}
