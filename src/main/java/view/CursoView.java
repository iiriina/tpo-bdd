package view;

public class CursoView {

	private int numero;
	private String materia;
	//private String profesor;
	private String dia;
	private String turno; 
	private int vacantes;
	// 	public CursoView(int numero, String materia, String profesor, String dia, String turno, int vacantes) {

	public CursoView(int numero, String materia, String dia, String turno, int vacantes) {
		super();
		this.numero = numero;
		this.materia = materia;
		//this.profesor = profesor;
		this.dia = dia;
		this.turno = turno;
		this.vacantes = vacantes;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getMateria() {
		return materia;
	}

	public void setMateria(String materia) {
		this.materia = materia;
	}

	/* public String getProfesor() {
		return profesor;
	} */

	/* public void setProfesor(String profesor) {
		this.profesor = profesor;
	} */

	public String getDia() {
		return dia;
	}

	public void setDia(String dia) {
		this.dia = dia;
	}

	public String getTurno() {
		return turno;
	}

	public void setTurno(String turno) {
		this.turno = turno;
	}

	public int getVacantes() {
		return vacantes;
	}

	public void setVacantes(int vacantes) {
		this.vacantes = vacantes;
	}
	
	public String toString(){
		return numero + " - " + materia + " " + dia + " " + turno + " " + vacantes;
	}
}
