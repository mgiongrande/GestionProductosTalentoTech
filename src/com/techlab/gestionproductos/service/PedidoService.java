package com.techlab.gestionproductos.service;

import java.util.ArrayList;
import com.techlab.gestionproductos.exception.NoHayPedidosException;
import com.techlab.gestionproductos.exception.PedidoNoEcontradoException;
import com.techlab.gestionproductos.model.pedido.Pedido;

public class PedidoService {
    private ArrayList<Pedido> pedidos = new ArrayList<>();


    public int GetNextId() {
        return pedidos.size() + 1;
    }
    public void crearPedido(Pedido pedido) {
        pedidos.add(pedido);
    }

    public void listarPedidos() {
        if (pedidos.isEmpty()) 
            throw new NoHayPedidosException();

        for (Pedido pedido : pedidos) {
            System.out.println(
                "Pedido ID: " + pedido.getId() + " con " + pedido.getItems().size() 
                + " items, Total: " + pedido.getTotal()
                + " - Estado: " + pedido.getEstado() 
            );
        }
    }

    public Pedido buscarPedido(int id) {
        for (Pedido pedido : pedidos) {
            if (pedido.getId() == id) {
                return pedido;
            }
        }
        throw new PedidoNoEcontradoException(String.valueOf(id));
    }
}
