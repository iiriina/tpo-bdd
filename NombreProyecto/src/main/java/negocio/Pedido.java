package negocio;

import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.Id;

import controlador.ControllerSistema;

@Entity
public class Pedido {
	
	@Id
    private int nroPedido;
    private ArrayList<Producto> productos;
    
    //cambie para que el usuario sea el cliente
    private Usuario cliente;
    private Double importeTotal;
    private Double descuentos;
    private Double impuestos;
    private Double total;
    private FormaDePago formaDePago;
    private LocalDateTime fechaHora;
    private String empleadoResponsable;
    private boolean facturado;

    //constructor

    public Pedido(int nroPedido, ArrayList<Producto> productos, Usuario cliente, Double importeTotal, Double descuentos,
    Double impuestos, Double total, FormaDePago pago, LocalDateTime fechaHora, String empleado){
        this.nroPedido = nroPedido;
        this.productos = productos;
        this.cliente = cliente;
        this.importeTotal = importeTotal;
        this.descuentos = descuentos;
        this.impuestos = impuestos;
        this.total = total;
        this.formaDePago = pago;
        this.fechaHora = fechaHora;
        this.empleadoResponsable = empleado;
        this.facturado = false;
    }

    enum FormaDePago {
        EFECTIVO,
        TARJETA,
        CUENTA_CORRIENTE
    }

    //setters y getters

    public void setNroPedido(int nroPedido) {
        this.nroPedido = nroPedido;
    }

    public int getNroPedido() {
        return nroPedido;
    }

    public void setProductos(ArrayList<Producto> productos) {
        this.productos = productos;
    }

    public ArrayList<Producto> getProductos() {
        return productos;
    }

    public void setCliente(Usuario cliente) {
        this.cliente = cliente;
    }

    public Usuario getCliente() {
        return cliente;
    }

    public void setImporteTotal(Double importeTotal) {
        this.importeTotal = importeTotal;
    }

    public Double getImporteTotal() {
        return importeTotal;
    }

    public void setDescuentos(Double descuentos) {
        this.descuentos = descuentos;
    }

    public Double getDescuentos() {
        return descuentos;
    }

    public void setImpuestos(Double impuestos) {
        this.impuestos = impuestos;
    }
    
    public Double getImpuestos() {
        return impuestos;
    }
    
    public void setTotal(Double total) {
        this.total = total;
    }
    
    public Double getTotal() {
        return total;
    }

    public void setFormaDePago(FormaDePago formaDePago) {
        this.formaDePago = formaDePago;
    }

    public FormaDePago getFormaDePago() {
        return formaDePago;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setEmpleadoResponsable(String empleadoResponsable) {
        this.empleadoResponsable = empleadoResponsable;
    }

    public String getEmpleadoResponsable() {
        return empleadoResponsable;
    }

    public void setFacturado(boolean facturado) {
        this.facturado = facturado;
    }

    public boolean getFacturado() {
        return facturado;
    }

    //methods

    public void facturar(){
        this.facturado = true;
    }
    
    /*
    public void registrarElPago(FormaDePago medio){
        this.formaDePago = medio;
        int cantPedidos;
		try {
			cantPedidos = ControllerSistema.getListaPedidos().size();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        int nro = cantPedidos + 1;
        
        this.nroPedido = nro;
    } */
}