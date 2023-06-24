package test;

import java.util.List;

import controlador.Controlador;
import exceptions.MateriaException;
import negocio.Alumno;
import view.AlumnoView;
import view.CursoView;
import view.MateriaView;

public class Test {
	
	public static void main(String[] args) throws MateriaException {
		/**
		Controlador.getInstancia().agregarAlumno(1234,"Luciano");
		Controlador.getInstancia().agregarAlumno(2345,"Cacho");
		Controlador.getInstancia().agregarAlumno(3456,"Coral");
		Controlador.getInstancia().agregarAlumno(4567,"Celia");
		**/
		
		
/** esto es para obtener una lista de alumnos **/
/** tengo que obtener una lista de alumnos view para mostrar, la idea
 * es que yo guardar si guardo alumno cuando lo creo por ejemplo
 * pero el usuario nunca deberia estar viendo mis objetos de negocio
 * que serian Alumno Curso, etc. Siempre tiene que ver las cosas de la 
 * VISTA.  
 **/

		List<AlumnoView> a = Controlador.getInstancia().getAlumnos();
		System.out.println(a);

		
		/* ahora lo que voy a hacer es para mostrar si encuentro un alumno con el nombre
		 * que le paso como parametro a la funciòn. devuelve true o false.
		 */
		boolean variable = Controlador.getInstancia().isAlumnoPorNombre("Cacho");
		System.out.println(variable);
		
		boolean variable2 = Controlador.getInstancia().isAlumnoPorNombre("Marta");
		System.out.println(variable2);
		
		
		/* empiezo a hacer testing de materias */
		/*
		cuando ya las agreguè las comento asi no se agregan dos veces cuando vuelva
		a correr todo este codigo agregando duplicadas las materias
		
		Controlador.getInstancia().agregarMateria("M001", "Materia I");
		Controlador.getInstancia().agregarMateria("M002", "Materia II");
		Controlador.getInstancia().agregarMateria("M003", "Materia III");
		Controlador.getInstancia().agregarMateria("M004", "Materia VI");
		Controlador.getInstancia().agregarMateria("M005", "Materia V");
		Controlador.getInstancia().agregarMateria("M006", "Materia VI");
		*/

		MateriaView materia = Controlador.getInstancia().buscarMateria("M002");
		System.out.println(materia.toString());
		
		/* empiezo a hacer testing de cursos sin profesores */

		/* Controlador.getInstancia().crearCurso("M002","Lunes", "Mañana", 10);
		 * 
		Controlador.getInstancia().crearCurso("M004","Martes", "Mañana", 15);
		Controlador.getInstancia().crearCurso("M001","Martes", "Tarde", 20);
		 */
		
		/* busco a ver si se agrego el primer curso */
		CursoView curso = Controlador.getInstancia().buscarCurso(1);
		System.out.println(curso.toString());
		
		
		/* 
		 * usando los métodos de redis
		 */
		
		/* 
		 * agrego un par de alumnos en Redis (descomentar para cargar los datos
		 * en la base de datos local
		 * de la maquina de cada uno :D) 
		  	Controlador.getInstancia().agregarAlumnoEnRedis(1145945, "Irina");
			Controlador.getInstancia().agregarAlumnoEnRedis(1145946, "Juana");
			Controlador.getInstancia().agregarAlumnoEnRedis(1145947, "Miranda");
		 */
		/*
		List<AlumnoView> listaDeAlumnosRedis = Controlador.getInstancia().getAlumnosREDIS();
		System.out.println(listaDeAlumnosRedis);
		
		*/
		
		/* 
		 * usando los métodos de SQL
		 */
		
		/* 
		 * agrego un par de alumnos en SQL (descomentar para cargar los datos
		 * en la base de datos local
		 * de la maquina de cada uno :D)
		 * 		
		  	Controlador.getInstancia().agregarAlumnoEnSQL(1145948, "Miguel");
			Controlador.getInstancia().agregarAlumnoEnSQL(1145949, "Carlos");
			Controlador.getInstancia().agregarAlumnoEnSQL(1145950, "Roberto");
		 */

		
		
		List<AlumnoView> listaDeAlumnosSql =Controlador.getInstancia().getAlumnosSQL();
		System.out.println(listaDeAlumnosSql);

		
		
		
		
		
		
	}
}








