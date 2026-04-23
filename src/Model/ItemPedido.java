package Model;

import java.util.ArrayList;

public class ItemPedido {
    private ArrayList<ProductoPedido> productos;
    private double total = 0;

    public ItemPedido(Producto producto, int cantidad) {
        this.productos = new ArrayList<>();
        for (int i = 0; i < cantidad; i++) {
            this.productos.add(new ProductoPedido(producto));
            this.total += producto.getPrecio();
        }
    }

    public ArrayList<ProductoPedido> getProductos() {
        return productos;
    }

    public double getTotal() {
        return total;
    }

    public int getCantidad() {
        return productos.size();
    }

}
