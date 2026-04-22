package Service;
import java.util.ArrayList;
import Model.Producto;

public class ProductoService {
    private ArrayList<Producto> productos = new ArrayList<>();

    public void agregarProducto(Producto producto) {
        productos.add(producto);
    }

    public int GetNextId() {
        return productos.size() + 1;
    }

    public void listarProductos() {
        if (productos.isEmpty()) {
            System.out.println("No hay productos disponibles.");
            return;
        }
        System.out.println("Lista de Productos:");
        for (Producto producto : productos) {
            System.out.println("ID: " + producto.getId() + ", Nombre: " + producto.getNombre() + ", Precio: " + producto.getPrecio() + ", Cantidad: " + producto.getCantidad());
        }
    }

    public void eliminarProducto(int id) {
        productos.removeIf(producto -> producto.getId() == id);
    }

    public Producto buscarProducto(int id) {
        for (Producto producto : productos) {
            if (producto.getId() == id) {
                return producto;
            }
        }
        return null; 
    }
}
