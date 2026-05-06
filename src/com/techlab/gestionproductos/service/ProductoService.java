package com.techlab.gestionproductos.service;

import java.util.ArrayList;
import com.techlab.gestionproductos.exception.NoHayProductosException;
import com.techlab.gestionproductos.exception.ProductoNoEncontradoException;
import com.techlab.gestionproductos.model.producto.Producto;

public class ProductoService {
    private ArrayList<Producto> productos = new ArrayList<>();

    public void agregarProducto(Producto producto) {
        productos.add(producto);
    }

    public int GetNextId() {
        return productos.size() + 1;
    }

    public void listarProductos() {
        if (productos.isEmpty()) 
            throw new NoHayProductosException();
        
        for (Producto producto : productos) {
            System.out.println(producto.listar());
        }
    }

    public void eliminarProducto(int id) {
        var emilinado = productos.removeIf(producto -> producto.getId() == id);

        if (!emilinado) {
            throw new ProductoNoEncontradoException(String.valueOf(id));
        }
    }

    public Producto buscarProducto(int id) {
        for (Producto producto : productos) {
            if (producto.getId() == id) {
                return producto;
            }
        }
        throw new ProductoNoEncontradoException(String.valueOf(id));
    }
}
