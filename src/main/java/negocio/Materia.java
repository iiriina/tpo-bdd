package negocio;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import view.MateriaView;

@Entity
public class Materia {
		
	@Id
	private String codigo;
	private String descripcion;
	private boolean habilitada;
	
	public Materia(String codigo, String descripcion) {
		this.codigo = codigo;
		this.descripcion = descripcion;
		this.habilitada = true;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public boolean isHabilitada() {
		return habilitada;
	}

	public void deshabilitar() {
		this.habilitada = false;
	}
	
	public MateriaView toView()
	{
		return new MateriaView(codigo, descripcion, habilitada);
	}

	public boolean soyLaMateria(String codigo) {
		return this.codigo.equals(codigo);
	}
}
