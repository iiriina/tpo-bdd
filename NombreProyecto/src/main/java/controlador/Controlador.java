package controlador;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import daos.AlumnoDAO;
import daos.AlumnoDAO_REDIS;
import daos.AlumnoDAO_SQL;
import daos.CursoDAO;
import daos.MateriaDAO;
import daos.ProductoDAO;
import exceptions.MateriaException;
import negocio.Alumno;
import negocio.Curso;
import negocio.Materia;
import negocio.Producto;
import negocio.Profesor;
import view.AlumnoView;
import view.CursoView;
import view.MateriaView;

public class Controlador {

	private static Controlador instancia;
	private List<Alumno> alumnos;
	private List<Curso> cursos;
	private List<Materia> materias;
	private List<Profesor> profesores;
	
	private Controlador(){	}
	
	public static Controlador getInstancia(){
		if(instancia == null)
			instancia = new Controlador();
		return instancia;
	}

	
	/* Empezamos con Alumno */
	
	public void agregarAlumno(int legajo, String nombre){
		
		Alumno alumnito = new Alumno(legajo, nombre);
		AlumnoDAO.getInstancia().guardar(alumnito);
	}
	
	
	
	
	// cosas nuevas usando redis!
	
	public void agregarAlumnoEnRedis(int legajo, String nombre) {
		/* se va a agregar al alumno en un hash que tiene como 
		 * clave su legajo y como valor su nombre.
		 */
		Alumno alumnito = new Alumno(legajo, nombre);
		AlumnoDAO_REDIS.getInstancia().guardar(alumnito);
	}
	
	
	public List<AlumnoView> getAlumnosREDIS(){
		List<AlumnoView> resultado = new ArrayList<AlumnoView>();
		List<Alumno> obtenidos = AlumnoDAO_REDIS.getInstancia().obtenerAlumnos();
		for(Alumno x: obtenidos) {
			resultado.add(x.toView());
		}
		return resultado;
	}	
	
	
	
	
	
	
	
	
	
	// cosas nuevas usando SQL!

	
	public void agregarAlumnoEnSQL(int legajo, String nombre) {
		/* se va a agregar al alumno en un hash que tiene como 
		 * clave su legajo y como valor su nombre.
		 */
		Alumno alumnito = new Alumno(legajo, nombre);
		AlumnoDAO_SQL.getInstancia().guardar(alumnito);
	}	
	
	
	public List<AlumnoView> getAlumnosSQL(){
		List<AlumnoView> resultado = new ArrayList<AlumnoView>();
		List<Alumno> obtenidos = AlumnoDAO_SQL.getInstancia().obtenerAlumnosSQL();
		for(Alumno x: obtenidos) {
			resultado.add(x.toView());
		}
		return resultado;
	}	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public AlumnoView recuperarAlumnoPorLegajo(int legajo){
		
		Alumno alumnoConEseLegajo = AlumnoDAO.getInstancia().obtenerPorId(legajo);
		return alumnoConEseLegajo.toView();
	}
	
	public List<AlumnoView> getAlumnos(){
		List<AlumnoView> resultado = new ArrayList<AlumnoView>();
		List<Alumno> obtenidos = AlumnoDAO.getInstancia().obtenerAlumnos();
		for(Alumno x: obtenidos) {
			resultado.add(x.toView());
		}
		return resultado;
	}
	
	public boolean isAlumnoPorNombre(String nombre){
		/* esto lo pongo en AlumnoDAO y acà devuelvo si me dio true o false */
		/* for(Alumno a : alumnos)
			if(a.soyELAlumno(nombre))
				return true;
		*/
		Boolean esElAlumno = AlumnoDAO.getInstancia().esAlumnoPorNombre(nombre);
		return esElAlumno;
	}
	
	/* Empezamos con Materia */
	
	public MateriaView buscarMateria(String codigo){
		
		/* siempre hay que pensar que tenemos que devolver a la persona que busque, no objetos del negocio
		 * si no que objetos de la VISTA. entonces el DAO me envia una Materia del negocio, yo la devuelvo como
		 * MateriaView
		 */
		Materia materiaQueBusco = MateriaDAO.getInstancia().buscarMateria(codigo);
		if (materiaQueBusco != null) {
			return materiaQueBusco.toView();
		}
		return null;
		
	}
	
	
	public void agregarMateria(String codigo, String descripcion) throws MateriaException {
		if(buscarMateria(codigo) == null){
			Materia m = new Materia(codigo, descripcion);
			/* aca no trabajo con views porque lo que estoy haciendo es creando una Materia y
			 * persistiendola mediante agregarla a la base de datos
			 */
			MateriaDAO.getInstancia().agregarMateria(m);
		}
		else
			throw new MateriaException("La Materia " + descripcion + " ya existe");
	}
	

	/* Empezamos con Curso */ 
	
	public void crearCurso(String codigo, String dia, String turno, int maximo){

		/* busco la materia, con el DAO materia me la devuelve */
		Materia materia = MateriaDAO.getInstancia().buscarMateria(codigo);
		
		/* si la materia existe */
		if(materia != null){
			/* creo un curso con ese objeto materia ese dia ese turno y con X maximo */
			Curso curso = new Curso(materia, dia, turno, maximo);
			/* tengo que añadir el curso a la base de datos o sea persistirlo y de eso se encarga el DAO*/
			CursoDAO.getInstancia().crearCurso(curso);
		}
		
		else
			JOptionPane.showMessageDialog(null, "Error en el sistema, comuniquese con el administrador");
	
	}

	public CursoView buscarCurso(int numero){
		
		/* siempre hay que pensar que tenemos que devolver a la persona que busque, no objetos del negocio
		 * si no que objetos de la VISTA. entonces el DAO me envia un Curso del negocio, yo lo devuelvo como
		 * CursoView
		 */
		Curso curso = CursoDAO.getInstancia().buscarCurso(numero);
		if (curso != null) {
			return curso.toView();
		}
		return null;
		
	}
	
	
	
	
	public void agregarProducto(String idProducto, String descripcion, double precio, int unidadesDisponibles, String foto) {
	    Producto nuevoProducto = new Producto(idProducto, descripcion, precio, unidadesDisponibles);
	    nuevoProducto.agregarFoto(foto);
	    nuevoProducto.agregarFoto("foto 2 uwu");
	    nuevoProducto.agregarFoto("foto 3 uwu");

	    ProductoDAO.getInstancia().guardar(nuevoProducto);
	    
	}

	
	/* public AlumnoView recuperarAlumnoPorLegajo(int legajo){
		
		Alumno alumnoConEseLegajo = AlumnoDAO.getInstancia().obtenerPorId(legajo);
		return alumnoConEseLegajo.toView();
	}
	*/
	
	public Producto getProducto(String idProducto){
        
		Producto productoBuscado = ProductoDAO.getInstancia().obtenerPorId(idProducto);
		return productoBuscado;
		
    }
	
}











