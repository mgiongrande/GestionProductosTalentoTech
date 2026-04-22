package Service;
import java.util.ArrayList;
import Model.Pedido;

public class PedidoService {
    private ArrayList<Pedido> pedidos = new ArrayList<>();


    public int GetNextId() {
        return pedidos.size() + 1;
    }
    public void crearPedido(Pedido pedido) {
        pedidos.add(pedido);
    }

    public void listarPedidos() {
        if (pedidos.isEmpty()) {
            System.out.println("No hay pedidos disponibles.");
            return;
        }
        System.out.println("Lista de Pedidos:");
        for (Pedido pedido : pedidos) {
            System.out.println(
                "Pedido ID: " + pedido.getId() + " con " + pedido.getItems().size() 
                + " items, Total: " + pedido.getTotal()
                + " - Estado: " + pedido.getEstado() // Muestra el nombre del primer producto del pedido
            );
        }
    }
}
