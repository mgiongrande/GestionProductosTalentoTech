package com.techlab.gestionproductos.model.producto;

public abstract class Producto implements Listable {
    private int id;
    private String nombre;
    private double precio;
    private int cantidad;

    public Producto(int id, String nombre, double precio, int cantidad) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
    }
    public int getId() {
        return id;
    }
    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public boolean hayStockDisponible(int cantidadSolicitada) {
        return this.cantidad >= cantidadSolicitada;
    }

    public void actualizarCantidad(int cantidad) {
        if (this.cantidad - cantidad < 0) {
            throw new IllegalArgumentException("No hay suficiente stock para esta operación.");
        }
        this.cantidad = this.cantidad - cantidad;
    }

    public void actualizarPrecio(double nuevoPrecio) {
        this.precio = nuevoPrecio;
    }
}