package Classes;

public class Compra {
    private String creada_compra, domicilio_compra, nombre_producto;
    private boolean terminada_compra;
    private int cantidad_compra, id_compra;
    private float precio_producto;

    public int getId_compra() {
        return id_compra;
    }

    public void setId_compra(int id_compra) {
        this.id_compra = id_compra;
    }

    public String getCreada_compra() {
        return creada_compra;
    }

    public void setCreada_compra(String creada_compra) {
        this.creada_compra = creada_compra;
    }

    public String getDomicilio_compra() {
        return domicilio_compra;
    }

    public void setDomicilio_compra(String domicilio_compra) {
        this.domicilio_compra = domicilio_compra;
    }

    public String getNombre_producto() {
        return nombre_producto;
    }

    public void setNombre_producto(String nombre_producto) {
        this.nombre_producto = nombre_producto;
    }

    public boolean isTerminada_compra() {
        return terminada_compra;
    }

    public void setTerminada_compra(boolean terminada_compra) {
        this.terminada_compra = terminada_compra;
    }

    public int getCantidad_compra() {
        return cantidad_compra;
    }

    public void setCantidad_compra(int cantidad_compra) {
        this.cantidad_compra = cantidad_compra;
    }

    public float getPrecio_producto() {
        return precio_producto;
    }

    public void setPrecio_producto(float precio_producto) {
        this.precio_producto = precio_producto;
    }
    
}
