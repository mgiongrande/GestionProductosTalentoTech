package com.techlab.gestionproductos.ui;

import java.util.Scanner;
import com.techlab.gestionproductos.enums.EstadoPedido;
import com.techlab.gestionproductos.enums.OpcionMenu;
import com.techlab.gestionproductos.enums.TipoGaseosa;
import com.techlab.gestionproductos.enums.TipoProducto;
import com.techlab.gestionproductos.enums.TipoVarietal;
import com.techlab.gestionproductos.model.pedido.Pedido;
import com.techlab.gestionproductos.model.producto.Gaseosa;
import com.techlab.gestionproductos.model.producto.Vino;
import com.techlab.gestionproductos.service.PedidoService;
import com.techlab.gestionproductos.service.ProductoService;
import com.techlab.gestionproductos.util.Validador;

public class Menu {
    private final ProductoService productoService;
    private final PedidoService pedidoService;
    private final Scanner scanner;

    public Menu(ProductoService productoService, PedidoService pedidoService, Scanner scanner) {
        this.productoService = productoService;
        this.pedidoService = pedidoService;
        this.scanner = scanner;
    }

    public void mostrarMenu() {
        limpiarPantalla();
        
        System.out.println("===============================================================");
        System.out.println("Fabuloso Sistema de Gestión de Productos y Pedidos - Bienvenido");
        System.out.println("===============================================================");
        for (var opcion : OpcionMenu.values()) {
            System.out.println((opcion.ordinal() + 1) + ") " + opcion.getDescripcion());
        }
    }

    public void agregarProducto() {
        limpiarPantalla();
        System.out.println("=== Alta de Productos ===");

        System.out.println("Seleccione el tipo de producto:");
        for (var tipo : TipoProducto.values()) {
            System.out.println((tipo.ordinal() + 1) + ") " + tipo.getDescripcion());
        }

        var tipoProductoOpcion = Validador.leerOpcion(scanner);

        switch (tipoProductoOpcion) {
            case 1 -> mostrarAltaGaseosa();
            case 2 -> mostrarAltaVino();
            default -> System.out.println("Opción no válida. Volviendo al menú principal.");
        }
    }

    public void listarProductos() {
        limpiarPantalla();        
        System.out.println("=== Lista de Productos ===");

        productoService.listarProductos();
    }

    public void modificarProducto() {
            limpiarPantalla();
            System.out.println("=== Modificación de Productos ===");
            
            productoService.listarProductos();
            
            System.out.println("Ingrese el ID del producto a modificar:");
            
            var id = Validador.leerEnteroPositivo(scanner, "ID del producto: ");
            
            var producto = productoService.buscarProducto(id);
            
            System.out.println("Ingrese el nuevo precio del producto:");
            var nuevoPrecio = Validador.leerPrecio(scanner, "Nuevo precio: ");
            
            producto.actualizarPrecio(nuevoPrecio);
            System.out.println("Producto actualizado exitosamente.");
    }

    public void eliminarProducto() {
        limpiarPantalla();
        System.out.println("=== Baja de Productos ===");

        productoService.listarProductos();
        
        System.out.println("Ingrese el ID del producto a eliminar:");
        
        var id = Validador.leerEnteroPositivo(scanner, "ID del producto: ");
        
        productoService.eliminarProducto(id);
        System.out.println("Producto eliminado exitosamente.");
    }

    public void crearPedido() {
        limpiarPantalla();
        System.out.println("=== Alta de Pedidos ===");
        
        var grabar = false;
        var pedido = new Pedido(pedidoService.GetNextId());

        while (!grabar) {
            productoService.listarProductos();
            
            var id = Validador.leerEnteroPositivo(scanner, "ID del producto: ");
            var producto = productoService.buscarProducto(id);
            var cantidad = Validador.leerEnteroPositivo(scanner, "Cantidad: ");
            
            if (!producto.hayStockDisponible(cantidad)) {
                System.out.println("Cantidad insuficiente en stock.");
                cantidad = Validador.leerEnteroPositivo(scanner, "Cantidad: ");
            }

            pedido.agregarItem(producto, cantidad);
            producto.actualizarCantidad(cantidad);

            var respuesta = Validador.leerCaracterSiNo(scanner, "¿Desea agregar otro producto? (s/n): ");
            if (respuesta == 'N') {
                pedidoService.crearPedido(pedido);
                grabar = true;
                System.out.println("Pedido agregado exitosamente con ID " + pedido.getId());
            }
        }
    }

    public void listarPedidos() {
        limpiarPantalla();
        System.out.println("=== Lista de Pedidos ===");
        
        pedidoService.listarPedidos();
    }

    public void detallarPedido() {
        limpiarPantalla();
        System.out.println("=== Detalle de Pedidos ===");
        
        pedidoService.listarPedidos();

        var id = Validador.leerEnteroPositivo(scanner, "Ingrese el ID del pedido a detallar: ");
        var pedido = pedidoService.buscarPedido(id);

        if (pedido == null) {
            System.out.println("Pedido no encontrado.");
            return;
        }

        System.out.println("=== Detalles del Pedido ID " + pedido.getId() + " ===");
        for (var item : pedido.getItems()) {
            System.out.println( "Producto: " + item.getProducto().getNombre() + 
                                ", Cantidad: " + item.getCantidad() + 
                                ", Precio Unitario: " + item.getProducto().getPrecio() + 
                                ", Total: " + item.getTotal());
        }
        System.out.println("Total del Pedido: " + pedido.getTotal());
        System.out.println("Estado del Pedido: " + pedido.getEstado());
    }

    public void actualizarEstadoPedido() {
        limpiarPantalla();
        System.out.println("=== Actualización de Estado de Pedido ===");
        
        pedidoService.listarPedidos();

        var id = Validador.leerEnteroPositivo(scanner, "Ingrese el ID del pedido a actualizar: ");
        var pedido = pedidoService.buscarPedido(id);

        if (pedido == null) {
            System.out.println("Pedido no encontrado.");
            return;
        }

        System.out.println("Seleccione el nuevo estado del pedido:");
        for (var estado : EstadoPedido.values()) {
            System.out.println((estado.ordinal() + 1) + ") " + estado.getDescripcion());
        }
        
        var estadoOption = Validador.leerOpcionEnum(scanner, EstadoPedido.values(), "Opción: ");
        pedido.actualizarEstado(EstadoPedido.values()[estadoOption - 1]);
        
        System.out.println("Estado del pedido actualizado exitosamente.");
    }

    private void mostrarAltaGaseosa() {
        limpiarPantalla();
        System.out.println("=== Agregar Nueva Gaseosa ===");

        var nombre = Validador.leerTexto(scanner, "Ingrese el nombre de la gaseosa: ");
        var precio = Validador.leerPrecio(scanner, "Ingrese el precio de la gaseosa: ");
        var cantidad = Validador.leerEnteroPositivo(scanner, "Ingrese el stock inicial: ");
        var volumen = Validador.leerEnteroPositivo(scanner, "Ingrese el volumen de la gaseosa (en ml): ");

        for (var tipo : TipoGaseosa.values()) {
            System.out.println((tipo.ordinal() + 1) + ") " + tipo.getDescripcion());
        }
        
        var tipoGaseosaOpcion = Validador.leerOpcionEnum(scanner, TipoGaseosa.values(), "Seleccione el tipo de gaseosa: ");

        var gaseosa = new Gaseosa(productoService.GetNextId(), nombre, precio, cantidad, volumen, TipoGaseosa.values()[tipoGaseosaOpcion - 1]);
        productoService.agregarProducto(gaseosa);

        System.out.println("Gaseosa agregada exitosamente con ID " + gaseosa.getId());
    }

    private void mostrarAltaVino() {
        limpiarPantalla();
        System.out.println("=== Agregar Nuevo Vino ===");

        var nombre = Validador.leerTexto(scanner, "Ingrese nombre del vino: ");
        var precio = Validador.leerPrecio(scanner, "Ingrese el precio del vino: ");
        var cantidad = Validador.leerEnteroPositivo(scanner, "Ingrese stock inicial: ");
        var anio = Validador.leerEnteroPositivo(scanner, "Ingrese el año de cosecha: ");

        System.out.println("Seleccione el tipo de varietal:");
        for (var tipo : TipoVarietal.values()) {
            System.out.println((tipo.ordinal() + 1) + ") " + tipo.getDescripcion());
        }

        var tipoVarietalOpcion = Validador.leerOpcionEnum(scanner, TipoVarietal.values(), "Opción: ");
        var tipoVarietal = TipoVarietal.values()[tipoVarietalOpcion - 1];
        var vino = new Vino(productoService.GetNextId(), nombre, precio, cantidad, anio, tipoVarietal);
        
        productoService.agregarProducto(vino);

        System.out.println("Vino agregado exitosamente con ID " + vino.getId());
    }

    private void limpiarPantalla() {
        try {
            var sistemaOperativo = System.getProperty("os.name");
            if (sistemaOperativo.contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (Exception e) {
            System.out.println("Error al limpiar la pantalla: " + e.getMessage());
        }
    }

    public void mostrarEnterParaContinuar() {
        System.out.println("Presione Enter para continuar...");
        scanner.nextLine();
    }
}
