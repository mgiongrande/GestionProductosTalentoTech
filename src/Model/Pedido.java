package Model;

import java.util.ArrayList;

public class Pedido {
    private int id;
    private ArrayList<ItemPedido> items = new ArrayList<>();
    private EstadoPedido estado;

    public Pedido(int id) {
        this.id = id;
        this.estado = EstadoPedido.EnProceso;
    }

    public void agregarItem(Producto producto, int cantidad) {
        if (this.estado != EstadoPedido.EnProceso) {
            System.out.println("No se pueden agregar items a un pedido que no está en proceso.");
            return;
        }
        items.add(new ItemPedido(producto, cantidad));
    }

    public void setEstado(EstadoPedido estado) {
        this.estado = estado;
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
        return estado.name();
    }
}
