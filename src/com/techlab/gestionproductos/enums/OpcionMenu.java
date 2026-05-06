package com.techlab.gestionproductos.enums;

public enum OpcionMenu {
    AGREGAR_PRODUCTO("Agregar Producto"),
    LISTAR_PRODUCTOS("Listar Productos"),
    BUSCAR_ACTUALIZAR_PRODUCTO("Actualizar Producto"),
    ELIMINAR_PRODUCTO("Eliminar Producto"),
    AGREGAR_PEDIDO("Crear Pedido"),
    LISTAR_PEDIDOS("Listar Pedidos"),
    DETALLE_PEDIDO("Detalle de Pedido"),
    CAMBIAR_ESTADO_PEDIDO("Cambiar Estado de Pedido"),
    SALIR("Salir");

    private String descripcion;

    OpcionMenu(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return this.descripcion;
    }
}
