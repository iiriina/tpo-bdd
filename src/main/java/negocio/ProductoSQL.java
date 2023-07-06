package negocio;

import java.math.BigDecimal;

public class ProductoSQL {
    private String idProducto;
    private String nombre;
    private BigDecimal precio;
    private int cantidadSeleccionada;
    private BigDecimal descuento;
    private BigDecimal precioFinal;
    
    public ProductoSQL(String idProducto, String nombre, BigDecimal precio, int cantidadSeleccionada,
            BigDecimal descuento, BigDecimal precioFinal) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.precio = precio;
        this.cantidadSeleccionada = cantidadSeleccionada;
        this.descuento = descuento;
        this.precioFinal = precioFinal;
    }
    
    public String getIdProducto() {
        return idProducto;
    }
    
    public void setIdProducto(String idProducto) {
        this.idProducto = idProducto;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public BigDecimal getPrecio() {
        return precio;
    }
    
    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }
    
    public int getCantidadSeleccionada() {
        return cantidadSeleccionada;
    }
    
    public void setCantidadSeleccionada(int cantidadSeleccionada) {
        this.cantidadSeleccionada = cantidadSeleccionada;
    }
    
    public BigDecimal getDescuento() {
        return descuento;
    }
    
    public void setDescuento(BigDecimal descuento) {
        this.descuento = descuento;
    }
    
    public BigDecimal getPrecioFinal() {
        return precioFinal;
    }
    
    public void setPrecioFinal(BigDecimal precioFinal) {
        this.precioFinal = precioFinal;
    }
}
