package com.techlab.gestionproductos.model.pedido;

import java.util.ArrayList;
import com.techlab.gestionproductos.enums.EstadoPedido;
import com.techlab.gestionproductos.model.producto.Producto;

public class Pedido {
    private int id;
    private ArrayList<ItemPedido> items = new ArrayList<>();
    private EstadoPedido estado;

    public Pedido(int id) {
        this.id = id;
        this.estado = EstadoPedido.EN_PROCESO;
    }

    public void agregarItem(Producto producto, int cantidad) {
        if (this.estado != EstadoPedido.EN_PROCESO) {
            System.out.println("No se pueden agregar items a un pedido que no está en proceso.");
            return;
        }
        items.add(new ItemPedido(producto, cantidad));
    }

    public int getId() {
        return id;
    }

    public ArrayList<ItemPedido> getItems() {
        return items;
    }

    public double getTotal() {
        double total = 0;
        for (ItemPedido item : items) {
            total += item.getTotal();
        }
        return total;
    }

    public String getEstado() {
        return estado.getDescripcion();
    }

    public void actualizarEstado(EstadoPedido nuevoEstado) {
        if (this.estado == EstadoPedido.CANCELADO || this.estado == EstadoPedido.ENTREGADO) {
            System.out.println("No se pueden cambiar el estado de un pedido cancelado o entregado.");
            return;
        }
        this.estado = nuevoEstado;
    }
}
