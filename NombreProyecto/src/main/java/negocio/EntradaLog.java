package negocio;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class EntradaLog {
	
	@Id
    private LocalDateTime id;
    private Producto producto;
    private String valorAnterior;
    private String nuevoValor;
    private String operador;

    public EntradaLog(Producto producto, String valorAnterior, String nuevoValor, String operador) {
        this.setId(LocalDateTime.now());
        this.setProducto(producto);
        this.setValorAnterior(valorAnterior);
        this.setNuevoValor(nuevoValor);
        this.setOperador(operador);
    }

	public LocalDateTime getId() {
		return id;
	}

	public void setId(LocalDateTime id) {
		this.id = id;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public String getValorAnterior() {
		return valorAnterior;
	}

	public void setValorAnterior(String valorAnterior) {
		this.valorAnterior = valorAnterior;
	}

	public String getNuevoValor() {
		return nuevoValor;
	}

	public void setNuevoValor(String nuevoValor) {
		this.nuevoValor = nuevoValor;
	}

	public String getOperador() {
		return operador;
	}

	public void setOperador(String operador) {
		this.operador = operador;
	}
	
	
}
