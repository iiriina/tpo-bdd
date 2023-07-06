package negocio;
import java.math.BigDecimal;
import java.util.ArrayList;

public class Pago {
    private int id;
    private Usuario usuario;
    private String formaPago;
    private BigDecimal monto;
    
    public Pago(int id, Usuario usuario, String formaPago, BigDecimal monto) {
        this.id = id;
        this.usuario = usuario;
        this.formaPago = formaPago;
        this.monto = monto;
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public Usuario getUsuario() {
        return usuario;
    }
    
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    public String getFormaPago() {
        return formaPago;
    }
    
    public void setFormaPago(String formaPago) {
        this.formaPago = formaPago;
    }
    
    public BigDecimal getMonto() {
        return monto;
    }
    
    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }
}
