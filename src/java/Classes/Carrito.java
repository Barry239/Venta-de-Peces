package Classes;

public class Carrito {
    private int id_carrito, id_producto, cantidad_carrito;
    private String nombre_producto;
    private float precio_producto;

    public int getId_carrito() {
        return id_carrito;
    }

    public void setId_carrito(int id_carrito) {
        this.id_carrito = id_carrito;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public int getCantidad_carrito() {
        return cantidad_carrito;
    }

    public void setCantidad_carrito(int cantidad_carrito) {
        this.cantidad_carrito = cantidad_carrito;
    }

    public String getNombre_producto() {
        return nombre_producto;
    }

    public void setNombre_producto(String nombre_producto) {
        this.nombre_producto = nombre_producto;
    }

    public float getPrecio_producto() {
        return precio_producto;
    }

    public void setPrecio_producto(float precio_producto) {
        this.precio_producto = precio_producto;
    }
    
}
