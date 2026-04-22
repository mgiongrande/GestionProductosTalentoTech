import java.util.Scanner;
import Model.Pedido;
import Model.Producto;
import Service.PedidoService;
import Service.ProductoService;

public class App {
    private static ProductoService productoService = new ProductoService();
    private static PedidoService pedidoService = new Service.PedidoService();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        GenerarProductos();
        MostrarMenu();
        scanner.close();
    }

    private static void MostrarMenu() {
        System.out.println("Sistema de Gestión de Productos y Pedidos - Bienvenido");
        System.out.println("1) Agregar producto");
        System.out.println("2) Listar productos");
        System.out.println("3) Buscar/Actualizar producto");
        System.out.println("4) Eliminar producto");
        System.out.println("5) Crear pedido");
        System.out.println("6) Listar pedidos");
        System.out.println("7) Salir");
        System.out.println("Ingrese una opción:");

        int opcion = scanner.nextInt();
        System.out.println("Opción seleccionada: " + opcion);

        ProcesarOpcion(opcion);
    }

    private static void ProcesarOpcion(int opcion) {
        switch (opcion) {
            case 1:
                MostrarAltaProducto();
                MostrarMenu();
                break;
            case 2:
                MostrarListadoProductos();
                MostrarMenu();
                break;
            case 3:
                MostrarMenu();
                break;
            case 4:
                MostrarEliminarProducto();
                MostrarMenu();
                break;
            case 5:
                MostrarCrearPedido();
                MostrarMenu();
                break;
            case 6:
                MostrarListadoPedidos();
                MostrarMenu();
                break;
            case 7:
                System.out.println("Saliendo del sistema. ¡Hasta luego!");
                break;
            default:
                System.out.println("Opción no válida. Por favor, intente nuevamente.");
                MostrarMenu();
        }
    }

    private static void MostrarAltaProducto() {
        try {
            System.out.println("Ingrese el nombre del producto:");
            scanner.nextLine(); // consumir el salto de línea pendiente tras nextInt()
            String nombre = scanner.nextLine();

            System.out.println("Ingrese el precio del producto:");
            double precio = scanner.nextDouble();

            System.out.println("Ingrese la cantidad del producto:");
            int cantidad = scanner.nextInt();

            var producto = new Producto(productoService.GetNextId(), nombre, precio, cantidad);

            productoService.agregarProducto(producto);

            System.out.println("Producto agregado exitosamente.");
        } catch (Exception e) {
            System.out.println("Error al agregar el producto: " + e.getMessage());
        }
    }      
    
    private static void MostrarListadoProductos() {
        productoService.listarProductos();
    }

    private static void MostrarEliminarProducto() {
        productoService.listarProductos();
        System.out.println("Ingrese el ID del producto a eliminar:");
        int id = scanner.nextInt();
        productoService.eliminarProducto(id);
        System.out.println("Producto eliminado exitosamente.");
    }

    private static void MostrarCrearPedido() {
        Boolean grabar = false;
        Pedido pedido = new Pedido(pedidoService.GetNextId());

        while (!grabar) {
            productoService.listarProductos();
            System.out.println("Seleccione el ID del producto:");   
            int id = scanner.nextInt();
            var producto = productoService.buscarProducto(id);

            if (producto == null) {
                System.out.println("Producto no encontrado.");
                return;
            }

            System.out.println("Ingrese la cantidad:");
            int cantidad = scanner.nextInt();
            if (cantidad > producto.getCantidad()) {
                System.out.println("Cantidad insuficiente en stock.");
                return;
            }

            pedido.agregarItem(producto, cantidad);
            System.out.println("¿Desea agregar otro producto? (s/n)");
            String respuesta = scanner.next();
            if (!respuesta.equalsIgnoreCase("s")) {
                pedidoService.crearPedido(pedido);
                grabar = true;
            }

        }
    }     

    private static void MostrarListadoPedidos() {
        pedidoService.listarPedidos();
    }
    
    private static void GenerarProductos() {
        productoService.agregarProducto(new Producto(productoService.GetNextId(), "Coca-Cola", 1500, 100));
        productoService.agregarProducto(new Producto(productoService.GetNextId(), "Pepsi", 1400, 80));
        productoService.agregarProducto(new Producto(productoService.GetNextId(), "Fanta", 1300, 60));
        productoService.agregarProducto(new Producto(productoService.GetNextId(), "RD Tacuil", 30000, 10));
    }
}
