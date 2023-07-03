package negocio;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ProductoCarrito {
    
    @Id
    private String idProducto;
    
    private String nombre;
    private double precio;
    private int cantidadSeleccionada;
    private double descuento;
    private double precioFinal;
    
	//si cada uno sale 1000 entonces el precio es multiplicar la cantidad por el precio unitario
	//el descuento se aplica ponele si llevas 2 y tenes un 15%, a los 2000, al total de los 2 
	//productos.
	// 1 . Producto 01 cantidadSeleccionada: 2 Precio: $2000 Descuento: 0.15 * 100 % Precio Final: $2000 * (1- 0.15) 
	// 2 . Produco 02 cantidadSeleccionada: 1 
	//Descuento: 0% (no mostrar el precio ni descuento si no tiene descuento,
	//mostrar directamente el precio final)Precio Final: $3000 
    
    // Constructor
    public ProductoCarrito(String idProducto, String nombre, double precio, int cantidadSeleccionada, double descuento) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.precio = precio;
        this.cantidadSeleccionada = cantidadSeleccionada;
        this.descuento = descuento;
        calcularPrecioFinal();
    }

    // Getters y setters

    public String getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(String idProducto) {
        this.idProducto = idProducto;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
        calcularPrecioFinal();
    }

    public int getCantidadSeleccionada() {
        return cantidadSeleccionada;
    }

    public void setCantidadSeleccionada(int cantidadSeleccionada) {
        this.cantidadSeleccionada = cantidadSeleccionada;
        calcularPrecioFinal();
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
        calcularPrecioFinal();
    }

    public double getPrecioFinal() {
        return precioFinal;
    }
    
    
    //chequear si esto está bien 
    private void calcularPrecioFinal() {
        double descuentoAplicado = precio * descuento * cantidadSeleccionada;
        precioFinal = precio * cantidadSeleccionada - descuentoAplicado;
    }

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
