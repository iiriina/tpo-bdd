package view;

import java.io.Serializable;

public class MateriaView implements Serializable {

	private static final long serialVersionUID = -3246920065293361771L;
	private String codigo ;
	private String descripcion;
	private boolean habilitada;
	
	public MateriaView() {}
	
	public MateriaView(String codigo, String descripcion, boolean habilitada) {
		this.codigo = codigo;
		this.descripcion = descripcion;
		this.habilitada = habilitada;
	}

	public String getCodigo() {
		return codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public boolean isHabilitada() {
		return habilitada;
	}
	
	public String toString(){
		return this.codigo + " - " + this.descripcion;
	}
	
}
