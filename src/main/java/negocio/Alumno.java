package negocio;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import view.AlumnoView;
import java.io.Serializable;

@Entity
public class Alumno {
	
	private static final long serialVersionUID = -3896772737211067862L;

	@Id @GeneratedValue
	private int legajo;
	private String nombre;
	private List<Curso> cursos;
	
	public Alumno(int legajo, String nombre){
		
		this.legajo = legajo;
		this.nombre = nombre;
		this.cursos = new ArrayList<Curso>();
		
	} 
	
	public boolean sePuedeInscribir(Materia materia){
		for(Curso c : cursos){
			if(c.getMateria().equals(materia))
				return false;
		}
		return true;
	}
	
	public void inscripto(Curso curso){
		if(!cursos.contains(curso))
			cursos.add(curso);
	}
	
	public boolean soyELAlumno(int legajo){
		return this.legajo == legajo;
	}

	public boolean soyElAlumnoNombre(String nombre){
		return this.nombre.equalsIgnoreCase(nombre);
	}
	
	public int getLegajo() {
		return legajo;
	}

	public String getNombre() {
		return nombre;
	}

	public List<Curso> getCursos() {
		return cursos;
	}
	
	public AlumnoView toView(){
		return new AlumnoView(legajo, nombre);
	}
}

