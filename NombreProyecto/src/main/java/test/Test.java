package test;

import java.util.ArrayList;
import java.util.List;

import controlador.Controlador;
import controlador.ControllerSistema;
import exceptions.MateriaException;
import negocio.Alumno;
import negocio.Producto;
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
//		List<AlumnoView> a = Controlador.getInstancia().getAlumnos();
//		System.out.println(a);

		
		/* ahora lo que voy a hacer es para mostrar si encuentro un alumno con el nombre
		 * que le paso como parametro a la funciòn. devuelve true o false.
		 */
//		boolean variable = Controlador.getInstancia().isAlumnoPorNombre("Cacho");
//		System.out.println(variable);
		
//		boolean variable2 = Controlador.getInstancia().isAlumnoPorNombre("Marta");
//		System.out.println(variable2);
		
		
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

//		MateriaView materia = Controlador.getInstancia().buscarMateria("M002");
//		System.out.println(materia.toString());
		
		/* empiezo a hacer testing de cursos sin profesores */

		/* Controlador.getInstancia().crearCurso("M002","Lunes", "Mañana", 10);
		 * 
		Controlador.getInstancia().crearCurso("M004","Martes", "Mañana", 15);
		Controlador.getInstancia().crearCurso("M001","Martes", "Tarde", 20);
		 */
		
		/* busco a ver si se agrego el primer curso */
//		CursoView curso = Controlador.getInstancia().buscarCurso(1);
//		System.out.println(curso.toString());
		
		
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
		
//		List<AlumnoView> listaDeAlumnosRedis = Controlador.getInstancia().getAlumnosREDIS();
//		System.out.println(listaDeAlumnosRedis);
		
		
		
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

		/* Controlador.getInstancia().agregarAlumnoEnSQL(1145951, "Juan");
		*/
		
//		List<AlumnoView> listaDeAlumnosSql =Controlador.getInstancia().getAlumnosSQL();
//		System.out.println(listaDeAlumnosSql);
		
	

		//vamos a probar agregando fotos con un array. 
		List<String> fotos = new ArrayList<String>();
		fotos.add("foto1.jpg");
		fotos.add("foto2.jpg");
		fotos.add("foto3.jpg");
		//Controlador.getInstancia().agregarProducto("004", "Producto 11", "Secador de pelo con 3994 funcionalidades", 150.00, 15, fotos, 0.15);

		//para ver si anda agregar una sola foto.
		List<String> fotos2 = new ArrayList<String>();
		fotos2.add("foto1.jpg");

		//Controlador.getInstancia().agregarProducto("009", "Producto 13", "Secador de pelo con 3994 funcionalidades", 150.00, 15, fotos, 0.15);
		
		
		Producto producto = Controlador.getInstancia().getProducto("009");
		System.out.println("Se está viendo la foto ahora?: ");
		System.out.println(producto.getFotos());
		System.out.println("ver descuento:" + producto.getDescuento());
        System.out.println("Precio con descuento: " + producto.getPrecio()*(1-producto.getDescuento()));
        
		List<Producto> p = Controlador.getInstancia().getProductosCatalogo();
		
		int n = 0;
		for (Producto productoo : p) {
			System.out.println(n);
			n = n+1;
		    double precioProducto = productoo.getPrecio();
		    System.out.println("Precio del producto: " + precioProducto);
		}
		
		System.out.println("Elementos del array Producto" + p);
		System.out.println("Elementos del array Producto" + p);

		
		//implementar un borrar para que estén cargados bien los productos xd pero cuando
		//sepa que funciona el log de redis.
			
		//Controlador.getInstancia().eliminarProducto("000");
		
		System.out.println("nombre del producto (antiguo): " + producto.getNombreProducto());
		
		Controlador.getInstancia().cambiarNombreProducto("001", "Producto 9 nuevo nombre");
		
		Producto productoActualizado = Controlador.getInstancia().getProducto("001");

		System.out.println("nombre del producto (antiguo): " + productoActualizado.getNombreProducto());
		
		//funciono :D
		
		
		//eliminar todas las fotos anda
		//Controlador.getInstancia().eliminarTodasFotosProducto("006");
		//producto = Controlador.getInstancia().getProducto("006");
		
		//guardar algunas fotos
		//List<String> fotosParaAgregar = new ArrayList<String>();
		//fotosParaAgregar.add("foto4.jpg");
		//fotosParaAgregar.add("foto5.jpg");
		//Controlador.getInstancia().agregarFotosProducto("009", fotosParaAgregar);
		//producto = Controlador.getInstancia().getProducto("009");
		
		//eliminar algunas fotos
		//List<String> fotosParaBorrar = new ArrayList<String>();
		//fotosParaBorrar.add("foto4.jpg");
		//fotosParaBorrar.add("foto5.jpg");
		//borro todas las fotos foto 4 y foto 5 que habia, borra todas las que 
		//sean la misma string asique si hay duplicados los borra, no borra 1 solo de ellos
		//Controlador.getInstancia().borrarFotosProducto("009", fotosParaBorrar);
		//producto = Controlador.getInstancia().getProducto("009");

		
		System.out.println("Nombre prd nuevo: " + producto.getNombreProducto());
        System.out.println("Descripción prd nuevo: " + producto.getDescripcion());
        System.out.println("Fotos: " + producto.getFotos());
        System.out.println("Videos: " + producto.getVideos());
        System.out.println("Comentarios: " + producto.getComentarios());
        
        if(producto.getDescuento() != 0) {
            System.out.println("Precio normal: $ " + producto.getPrecio());
            System.out.println("Precio con descuento: $ " + producto.getPrecio()*(1-producto.getDescuento()));
        }else {
            System.out.println("Precio: $ " + producto.getPrecio());
        }
        System.out.println("Unidades disponibles: " + producto.getUnidadesDisponibles());
        System.out.println("-----------------------");
		
	}
}








