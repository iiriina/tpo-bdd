package negocio;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Factura {
    private int id;
    private int usuarioId;
    private int pedidoId;
    private int pagoId;
    private String formaDePago;
    private Timestamp fechaHora = new Timestamp(System.currentTimeMillis());
    private String medioPago;
    private String operadorPago;
    private BigDecimal montoPago;
    
    public Factura(int id, int usuarioId, int pedidoId, int pagoId, String formaDePago, Timestamp fechaHora, String medioPago, String operadorPago, BigDecimal montoPago) {
        this.id = id;
        this.usuarioId = usuarioId;
        this.pedidoId = pedidoId;
        this.pagoId = pagoId;
        this.formaDePago = formaDePago;
        //si es null, hay que poner un if y listo
        this.fechaHora = fechaHora;
        this.medioPago = medioPago;
        this.operadorPago = operadorPago;
        this.montoPago = montoPago;
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public int getUsuarioId() {
        return usuarioId;
    }
    
    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }
    
    public int getPedidoId() {
        return pedidoId;
    }
    
    public void setPedidoId(int pedidoId) {
        this.pedidoId = pedidoId;
    }
    
    public int getPagoId() {
        return pagoId;
    }
    
    public void setPagoId(int pagoId) {
        this.pagoId = pagoId;
    }
    
    public String getFormaDePago() {
        return formaDePago;
    }
    
    public void setFormaDePago(String formaDePago) {
        this.formaDePago = formaDePago;
    }
    
    public Timestamp getFechaHora() {
        return fechaHora;
    }
    
    public void setFechaHora(Timestamp fechaHora) {
        this.fechaHora = fechaHora;
    }
    
    public String getMedioPago() {
        return medioPago;
    }
    
    public void setMedioPago(String medioPago) {
        this.medioPago = medioPago;
    }
    
    public String getOperadorPago() {
        return operadorPago;
    }
    
    public void setOperadorPago(String operadorPago) {
        this.operadorPago = operadorPago;
    }
    
    public BigDecimal getMontoPago() {
        return montoPago;
    }
    
    public void setMontoPago(BigDecimal montoPago) {
        this.montoPago = montoPago;
    }
}
