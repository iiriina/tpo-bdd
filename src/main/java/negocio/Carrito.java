package negocio;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Carrito {

    @Id
    private String usuario;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "carrito")
    private List<ProductoCarrito> productosCarrito;

    public Carrito() {
        // Constructor vacío requerido por JPA
    }

    public Carrito(String usuario, List<ProductoCarrito> productosCarrito) {
        this.usuario = usuario;
        this.productosCarrito = productosCarrito;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public List<ProductoCarrito> getProductosCarrito() {
        return productosCarrito;
    }
    
    
    public void setProductosCarrito(List<ProductoCarrito> productosCarrito) {
        this.productosCarrito = productosCarrito;
    }
    
    public double getTotalPrecioCarrito() {
        double totalPrecioCarrito = 0.0;
        
        for (ProductoCarrito producto : productosCarrito) {
            totalPrecioCarrito += producto.getPrecioFinal();
        }
        
        return totalPrecioCarrito;
    }
    
    
}
