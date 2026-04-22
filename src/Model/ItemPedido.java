package Model;

import java.util.ArrayList;

public class ItemPedido {
    private ArrayList<Producto> productos;
    private double total = 0;

    public ItemPedido(Producto producto, int cantidad) {
        this.productos = new ArrayList<>();
        for (int i = 0; i < cantidad; i++) {
            this.productos.add(producto);
            this.total += producto.getPrecio();
        }
    }

    public ArrayList<Producto> getProductos() {
        return productos;
    }

    public double getTotal() {
        return total;
    }

    public int getCantidad() {
        return productos.size();
    }

}
