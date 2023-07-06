package test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.objectdb.o.IXM.a;

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

		//Controlador.getInstancia().agregarProducto("010", "Producto 14", "Trapo de piso", 150.00, 15, fotos2, 0.15);
		
		
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
		
		//Controlador.getInstancia().cambiarNombreProducto("001", "Producto 9 nuevo nombre");
		
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
		
        
        
        //test de redis
        
        //log del catalogo
        //Controlador.getInstancia().crearEntradaLog(productoActualizado, "", "", "se ha guardado un producto");
        //Controlador.getInstancia().crearEntradaLog(productoActualizado, "", "", "se ha guardado otro producto");
        //Controlador.getInstancia().crearEntradaLog(productoActualizado, "", "", "se ha guardado otro producto");
        
        //muestro las entradas del log que hay hasta ahora
        //Controlador.getInstancia().obtenerLog();
        //elimino las entradas del log funcionan
        //Controlador.getInstancia().borrarContenidoLog();
        //Controlador.getInstancia().crearEntradaLog(productoActualizado, "", "", "se ha guardado otro producto");
        //System.out.println("lo que hay adentro del log es esto: ");
        //Controlador.getInstancia().obtenerLog();
        
        //agrego de nuevo algunas entradas al log
        //Controlador.getInstancia().crearEntradaLog(productoActualizado, "", "", "se ha guardado un nuevo producto");
        //Controlador.getInstancia().crearEntradaLog(productoActualizado, "", "", "se ha guardado otro nuevo producto");
        //muestro las entradas del log nuevas
        //Controlador.getInstancia().obtenerLog();
        
        
        //funciona el agregar producto con el log
        //Controlador.getInstancia().eliminarProducto("010"); //si le paso mal aca el string del producto me parece que va a tirar error.
        //Controlador.getInstancia().borrarContenidoLog();
        
        //el log agrega las cosas de manera que se muestra arriba
        //el ultimo agregar producto que se hizo
        //Controlador.getInstancia().obtenerLog();
		//Controlador.getInstancia().agregarProducto("013", "Producto 15", "Trapo de piso 3", 150.00, 15, fotos2, 0.15);
		//Controlador.getInstancia().agregarProducto("014", "Producto 16", "Trapo de piso 4", 150.00, 15, fotos2, 0.15);
        //Controlador.getInstancia().obtenerLog();
        //Controlador.getInstancia().agregarProducto("100", "Toalla de Baño", "Descripcion", 150.00, 15, fotos2, 0.15);
        //Controlador.getInstancia().borrarContenidoLog();
		//Controlador.getInstancia().agregarProducto("038", "Producto 16", "Trapo de piso 16", 150.00, 15, fotos2, 0.15);
		//Controlador.getInstancia().agregarProducto("039", "Producto 17", "Trapo de piso 17", 150.00, 15, fotos2, 0.15);
		//Controlador.getInstancia().agregarProducto("040", "Producto 17", "Trapo de piso 17", 150.00, 15, fotos2, 0.15);
		//Controlador.getInstancia().agregarProducto("041", "Producto 17", "Trapo de piso 17", 150.00, 15, fotos2, 0.15);
		//Controlador.getInstancia().agregarProducto("042", "Producto 17", "Trapo de piso 17", 150.00, 15, fotos2, 0.15);

		//Controlador.getInstancia().agregarProducto("034", "Producto 15", "Trapo de piso 3", 150.00, 15, fotos2, 0.15);
        
        //Controlador.getInstancia().obtenerLog();
        //Controlador.getInstancia().borrarContenidoLog();
        //System.out.println("Se borro el log? : ");
        //Controlador.getInstancia().obtenerLog();
        
        //cambiar nombre en log
        //Controlador.getInstancia().cambiarNombreProducto("034", "Nombre cambiado Producto 15! ! uwu");
        //Controlador.getInstancia().obtenerLog();
        
        //cambiarle precio a un producto
        //Controlador.getInstancia().cambiarPrecioProducto("034", 15.0);
        //Controlador.getInstancia().obtenerLog();
        
        //cambiarle unidades disponibles a un producto
        //Controlador.getInstancia().cambiarUnidadesDisponiblesProducto("034", 13);
        //Controlador.getInstancia().obtenerLog();
        
        //cambiarle el descuento a un producto
        //Controlador.getInstancia().cambiarDescuentoProducto("034", 0.2);
        //Controlador.getInstancia().borrarContenidoLog();
        //Controlador.getInstancia().cambiarNombreProducto("034", "Nombre cambiado Producto 15 uwu");
        //Controlador.getInstancia().cambiarPrecioProducto("034", 16.0);
        //Controlador.getInstancia().cambiarUnidadesDisponiblesProducto("034", 14);
        //Controlador.getInstancia().cambiarDescuentoProducto("034", 0.3);
        //Controlador.getInstancia().obtenerLog();
        
        //agregar fotos en un producto
        
		//List<String> fotosNuevas = new ArrayList<String>();
		//fotosNuevas.add("fotoNueva4.jpg");
		//fotosNuevas.add("fotoNueva5.jpg");
		//fotosNuevas.add("fotoNueva6.jpg");
		
		//Controlador.getInstancia().agregarFotosProducto("034", fotosNuevas);
        //Producto productoParaAgregarFotos = Controlador.getInstancia().getProducto("032");
		//System.out.println("Se está viendo la foto ahora?: ");
		//System.out.println(productoParaAgregarFotos.getFotos());
		//Controlador.getInstancia().obtenerLog();
        
        //agregar videos en un producto 
        
		//List<String> videosNuevos = new ArrayList<String>();
		//videosNuevos.add("videoNuevo1.jpg");
		//videosNuevos.add("videoNuevo2.jpg");
		//videosNuevos.add("videoNuevo4.jpg");

		//videosNuevos.add("videoNuevo3.jpg");
        
        //Controlador.getInstancia().agregarVideosProducto("034", videosNuevos);
        //Controlador.getInstancia().obtenerLog();
        
        //agregar comentarios en un producto
        
		//List<String> comentariosNuevos = new ArrayList<String>();
		//comentariosNuevos.add("comentarioNuevo1.jpg");
		//comentariosNuevos.add("comentarioNuevo2.jpg");
		//comentariosNuevos.add("comentarioNuevo3.jpg");
        
        //Controlador.getInstancia().agregarComentariosProducto("034", comentariosNuevos);
        //Controlador.getInstancia().obtenerLog();
        
        //borrar todas las fotos de un producto
        //Controlador.getInstancia().eliminarTodasFotosProducto("034");
        //Controlador.getInstancia().obtenerLog();
        
        //borrar todos los videos de un producto
        //Controlador.getInstancia().eliminarTodosVideosProducto("034");
        //Controlador.getInstancia().obtenerLog();
        
        //borrar todos los comentarios de un producto
        //Controlador.getInstancia().eliminarTodosComentariosProducto("034");
        //Controlador.getInstancia().obtenerLog();
        
        
        //borrar algunas fotos de un producto
        //List<String> fotosBorrarAlguna = new ArrayList<String>();
        //fotosBorrarAlguna.add("fotoNueva5.jpg");

        //Controlador.getInstancia().borrarFotosProducto("034", fotosBorrarAlguna);
        //Controlador.getInstancia().obtenerLog();
        
        //borrar algunos videos de un producto
        
		//List<String> videosParaBorrar = new ArrayList<String>();
		//videosParaBorrar.add("videoNuevo1.jpg");
		//videosParaBorrar.add("videoNuevo2.jpg");
		
        //Controlador.getInstancia().borrarVideosProducto("034", videosParaBorrar);
        //Controlador.getInstancia().obtenerLog();
        
        //borrar algunos comentarios de un producto
		//List<String> comentariosParaBorrar = new ArrayList<String>();
		//comentariosParaBorrar.add("comentarioNuevo1.jpg");
		//comentariosParaBorrar.add("comentarioNuevo2.jpg");
		
		//Controlador.getInstancia().borrarComentariosProducto("034", comentariosParaBorrar);
		//Controlador.getInstancia().obtenerLog();
        
        
        
        //logica del carrito

        
        //Controlador.getInstancia().agregarProductoAlCarrito("034", 2, "iruchita");
        //Controlador.getInstancia().agregarProductoAlCarrito("033", 3, "iruchita");
        //Controlador.getInstancia().agregarProductoAlCarrito("033", 4, "hades_jose");

        //Controlador.getInstancia().mostrarCarrito("hades_jose");
        
        
        //mostrar los carritos de todos los usuarios
		//List<String> usuarios = new ArrayList<String>();
		//usuarios.add("iruchita");
		//usuarios.add("hades_jose");
        //Controlador.getInstancia().mostrarTodosLosCarritos(usuarios);
        
        //Controlador.getInstancia().mostrarCarrito("iruchita");
        //Controlador.getInstancia().mostrarCarrito("hades_jose");
        
        //a ver si funciona el borrar producto del carrito
        //Controlador.getInstancia().eliminarProductoDelCarrito("iruchita", "033");
        //Controlador.getInstancia().mostrarCarrito("iruchita");
        //Controlador.getInstancia().mostrarCarrito("iri");
        
        
        //Controlador.getInstancia().agregarProductoAlCarrito("006", 2, "iri");
        Controlador.getInstancia().mostrarCarrito("iri");
        //Controlador.getInstancia().agregarProductoAlCarrito("006", 1, "iri");
        
        //Controlador.getInstancia().mostrarCarrito("iri");
        //con el id del producto, poniendo eliminarproducto del carrito "006" anda.
        //Controlador.getInstancia().eliminarProductoDelCarrito("iri", "006");
        //Controlador.getInstancia().eliminarProductoDelCarrito("iruchita", "033");
        //Controlador.getInstancia().mostrarCarrito("hades_jose");
        //modificar cantidad de un productoCarrito
        //Controlador.getInstancia().modificarCantidadProducto("iruchita", "034", 5);
        
        
        
        
        //empezamos con sql, a ver si podemos guardar los productos en la tabla

        
        //los que estan guardados
        //Controlador.getInstancia().agregarProductoAlCarrito("033", 3, "iruchita");

        //Controlador.getInstancia().agregarProductoAlCarrito("031", 2, "iruchita");

        //Controlador.getInstancia().eliminarProductoDelCarrito("iruchita", "034");
        //Controlador.getInstancia().mostrarCarrito("iruchita");
        
        
        
        
        //todo esto es para ver si se agregan al mismo tiempo los productos del carrito
        //a SQL
        
		//Controlador.getInstancia().agregarProducto("059", "Producto 16", "Trapo de piso 16", 150.00, 15, fotos2, 0.15);
		//Controlador.getInstancia().agregarProducto("060", "Producto 16", "Trapo de piso 16", 150.00, 15, fotos2, 0.15);
		//Controlador.getInstancia().agregarProducto("061", "Producto 16", "Trapo de piso 16", 150.00, 15, fotos2, 0.15);

        
        //Controlador.getInstancia().agregarProductoAlCarrito("059", 2, "iruchita6");
        //Controlador.getInstancia().agregarProductoAlCarrito("060", 3, "iruchita6");
        //Controlador.getInstancia().agregarProductoAlCarrito("061", 4, "iruchita6");
        //Controlador.getInstancia().mostrarCarrito("iruchita6");
        
        //le estoy mandando un id de pedido, simplemente puedo guardarlo en una variable
        //del main
        //Controlador.getInstancia().guardarPedido("iruchita6", 562, 0.00);
        //Controlador.getInstancia().mostrarPedido(562);
        
        //a ver si se elimina el pedido con el id este
        //Controlador.getInstancia().eliminarPedido(561);
        //SI FUNCIONOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO
        
        
        //a ver si puedo generar una factura y mostrar los datos:
        int id = 1;
        String nombre = "John Doe";
        String direccion = "123 Main St";
        int dni = 12345678;
        String categoria = "Cliente";
        String condicionIVA = "Aplica";
        String contraseña = "123";

        //Controlador.getInstancia().guardarUsuario(id, nombre, direccion, dni, categoria, condicionIVA, contraseña);
        //Controlador.getInstancia().guardarFactura(222, 2, 562, 1000, "Tarjeta de crédito", "Visa", "OpenAI", new BigDecimal("100.00"));
        
	}



}








