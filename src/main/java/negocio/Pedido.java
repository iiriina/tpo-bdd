package negocio;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private int id;
    private BigDecimal importe;
    private BigDecimal impuestos;
    private List<ProductoSQL> productosPedido;

    public Pedido(int id, BigDecimal importe, BigDecimal impuestos) {
        this.id = id;
        this.importe = importe;
        this.impuestos = impuestos;
        this.productosPedido = new ArrayList<>();
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public BigDecimal getImporte() {
        return importe;
    }
    
    public void setImporte(BigDecimal importe) {
        this.importe = importe;
    }
    
    public BigDecimal getImpuestos() {
        return impuestos;
    }
    
    public void setImpuestos(BigDecimal impuestos) {
        this.impuestos = impuestos;
    }
    
    public List<ProductoSQL> getProductosPedido() {
        return productosPedido;
    }
    
    public void agregarProductoPedido(ProductoSQL productoPedido) {
        productosPedido.add(productoPedido);
    }
}
