package com.techlab.gestionproductos;

import java.util.Scanner;
import com.techlab.gestionproductos.enums.TipoGaseosa;
import com.techlab.gestionproductos.enums.TipoVarietal;
import com.techlab.gestionproductos.exception.NoHayPedidosException;
import com.techlab.gestionproductos.exception.NoHayProductosException;
import com.techlab.gestionproductos.exception.PedidoNoEcontradoException;
import com.techlab.gestionproductos.exception.ProductoNoEncontradoException;
import com.techlab.gestionproductos.model.producto.Gaseosa;
import com.techlab.gestionproductos.model.producto.Vino;
import com.techlab.gestionproductos.service.PedidoService;
import com.techlab.gestionproductos.service.ProductoService;
import com.techlab.gestionproductos.ui.Menu;
import com.techlab.gestionproductos.util.Validador;

public class App {
    public static void main(String[] args) {
        ProductoService productoService = new ProductoService();
        PedidoService pedidoService = new PedidoService();
        Scanner scanner = new Scanner(System.in);
        Menu menu = new Menu(productoService, pedidoService, scanner);
        
        generarProductos(productoService);
        
        int opcion;

        do {
            menu.mostrarMenu();

            System.out.print("Seleccione una opción: ");

            opcion = Validador.leerOpcion(scanner);

            try {
                switch (opcion) {
                    case 1 -> menu.agregarProducto();
                    case 2 -> menu.listarProductos();
                    case 3 -> menu.modificarProducto();
                    case 4 -> menu.eliminarProducto();
                    case 5 -> menu.crearPedido();
                    case 6 -> menu.listarPedidos();
                    case 7 -> menu.detallarPedido();
                    case 8 -> menu.actualizarEstadoPedido();
                    case 9 -> System.out.println("Saliendo del sistema. ¡Hasta luego!");
                    default -> System.out.println("Opción no válida. Por favor, intente nuevamente.");
                }
            } catch (ProductoNoEncontradoException | 
                    PedidoNoEcontradoException | 
                    NoHayProductosException | 
                    NoHayPedidosException e) {
                        System.out.println(e.getMessage());
            } finally {
                if (opcion != 9)
                    menu.mostrarEnterParaContinuar();
            }

        } while (opcion != 9);


        scanner.close();
    }

    private static void generarProductos(ProductoService productoService) {
        productoService.agregarProducto(new Gaseosa(productoService.GetNextId(), "Coca-Cola", 1500, 100,500,TipoGaseosa.ORIGINAL));
        productoService.agregarProducto(new Gaseosa(productoService.GetNextId(), "Coca-Cola", 2400, 80,1500, TipoGaseosa.ZERO));
        productoService.agregarProducto(new Vino(productoService.GetNextId(), "Alma Negra", 33000, 60,2020, TipoVarietal.BLEND));
        productoService.agregarProducto(new Vino(productoService.GetNextId(), "RD Tacuil", 30000, 10,2022, TipoVarietal.BLEND));
        productoService.agregarProducto(new Vino(productoService.GetNextId(), "El Enemigo", 20000, 50,2023, TipoVarietal.CABERNET_FRANC));
    }
}

