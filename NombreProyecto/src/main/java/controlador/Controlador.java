package controlador;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import daos.AlumnoDAO;
import daos.AlumnoDAO_REDIS;
import daos.AlumnoDAO_SQL;
import daos.CarritoDAO;
import daos.CursoDAO;
import daos.LogCatalogoDAO;
import daos.MateriaDAO;
import daos.ProductoDAO;
import exceptions.MateriaException;
import negocio.Alumno;
import negocio.Carrito;
import negocio.Curso;
import negocio.Materia;
import negocio.Producto;
import negocio.ProductoCarrito;
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
	
	
	
	
	
	
	/* La nueva aplicacion!!!! 
	   Falta poner que devuelva ProductoView y no los productos directo de la base de datos
	 */
	
	public void agregarProducto(String idProducto, String nombreProducto, String descripcion, double precio, int unidadesDisponibles, List<String> fotos, double descuento) {
	    Producto nuevoProducto = new Producto(idProducto, nombreProducto, descripcion, precio, unidadesDisponibles, descuento);

	    //hay que hacer que almacene directamente un array de fotos, de videos y de comentarios
	    //se los tengo que pasar por parametro
	    nuevoProducto.agregarFotos(fotos);

	    ProductoDAO.getInstancia().guardar(nuevoProducto);
	    //cosas para el log
	    Controlador.getInstancia().crearEntradaLog(nuevoProducto, "", "", "Se ha guardado un nuevo producto");
	}
	
	public Producto getProducto(String idProducto){
        
		Producto productoBuscado = ProductoDAO.getInstancia().obtenerPorId(idProducto);
		return productoBuscado;
		
    }
	
	
	/* traer todos los productos desde la base de datos falta crear la clase producto View*/
	
	public List<Producto> getProductosCatalogo(){
		List<Producto> obtenidos = ProductoDAO.getInstancia().obtenerProductos();

		return obtenidos;
	}
	
	public void eliminarProducto(String idProducto) {
	    Producto productoAEliminar = ProductoDAO.getInstancia().obtenerPorId(idProducto);
	    if (productoAEliminar != null) {
	        ProductoDAO.getInstancia().eliminar(productoAEliminar);
	    }
	    
	    //cosas para el log
	    Controlador.getInstancia().crearEntradaLog(productoAEliminar, "", "", "Se ha eliminado un producto");
	    
	}
	
	//actualizar algun valor del Producto
	
	public void cambiarNombreProducto(String idProducto, String nombre) {
	    Producto producto = ProductoDAO.getInstancia().obtenerPorId(idProducto);
	    String nombreAntiguo = producto.getNombreProducto();
	    if (producto != null) {
	    	producto.setNombreProducto(nombre);
	        ProductoDAO.getInstancia().guardarCambioEnXCampo(producto);
	    }

	    //cosas para el log
	    Controlador.getInstancia().crearEntradaLog(producto, nombreAntiguo, nombre, "Se ha cambiado el nombre de un producto");

	}

	public void cambiarDescripcionProducto(String idProducto, String descripcion) {
	    Producto producto = ProductoDAO.getInstancia().obtenerPorId(idProducto);
	    String descripcionAntigua = producto.getDescripcion();
	    if (producto != null) {
	    	producto.setDescripcion(descripcion);
	        ProductoDAO.getInstancia().guardarCambioEnXCampo(producto);
	    }
	    //cosas para el log
	    Controlador.getInstancia().crearEntradaLog(producto, descripcionAntigua, descripcion, "Se ha cambiado la descripción de un producto");

	}

	public void cambiarPrecioProducto(String idProducto, double precio) {
	    Producto producto = ProductoDAO.getInstancia().obtenerPorId(idProducto);
	    double precioAntiguo = producto.getPrecio();
	    String precioAntiguoStr = Double.toString(precioAntiguo);
	    
	    String precioNuevo = Double.toString(precio);
	    if (producto != null) {
	    	producto.setPrecio(precio);
	        ProductoDAO.getInstancia().guardarCambioEnXCampo(producto);
	    }
	    
	    //cosas para el log
	    Controlador.getInstancia().crearEntradaLog(producto, precioAntiguoStr, precioNuevo, "Se ha cambiado el precio de un producto");

	}
	
	public void cambiarUnidadesDisponiblesProducto(String idProducto, int unidadesDisponibles) {
	    Producto producto = ProductoDAO.getInstancia().obtenerPorId(idProducto);
	    
	    String unidadesDisponiblesAntiguas = String.valueOf(producto.getUnidadesDisponibles());
	    String unidadesDisponiblesNuevas = String.valueOf(unidadesDisponibles);

	    if (producto != null) {
	    	producto.setUnidadesDisponibles(unidadesDisponibles);
	        ProductoDAO.getInstancia().guardarCambioEnXCampo(producto);
	    }
	    
	    //cosas para el log
	    Controlador.getInstancia().crearEntradaLog(producto, unidadesDisponiblesAntiguas, unidadesDisponiblesNuevas, "Se ha cambiado el precio de un producto");

	}
	
	public void cambiarDescuentoProducto(String idProducto, double descuento) {
	    Producto producto = ProductoDAO.getInstancia().obtenerPorId(idProducto);
	    
	    double descuentoAntiguo = producto.getDescuento();
	    String descuentoAntiguoStr = Double.toString(descuentoAntiguo);
	    
	    String descuentoNuevo = Double.toString(descuento);

	    
	    if (producto != null) {
	    	producto.setDescuento(descuento);
	        ProductoDAO.getInstancia().guardarCambioEnXCampo(producto);
	    }	
	    //cosas para el log
	    Controlador.getInstancia().crearEntradaLog(producto, descuentoAntiguoStr, descuentoNuevo, "Se ha cambiado el precio de un producto");

	}
	
	
	//cambiar algo del array de fotos, videos y comentarios
	
	//fotos
	public void agregarFotosProducto(String idProducto, List<String> fotos) {
	    Producto producto = ProductoDAO.getInstancia().obtenerPorId(idProducto);
	    
	    String fotosViejas = producto.getFotosFormatoString();
	    String fotosNuevas = String.join(", ", fotos);
	    
	    if (producto != null) {
	    	producto.agregarFotos(fotos);
	        ProductoDAO.getInstancia().guardarCambioEnXCampo(producto);
	    }	
	    

	    //cosas para el log
	    Controlador.getInstancia().crearEntradaLog(producto, fotosViejas, fotosNuevas, "Se han agregado fotos en un producto");

	}
	
	public void eliminarTodasFotosProducto(String idProducto) {
	    Producto producto = ProductoDAO.getInstancia().obtenerPorId(idProducto);
	    String fotosViejas = producto.getFotosFormatoString();

	    if (producto != null) {
	    	producto.eliminarFotos();
	        ProductoDAO.getInstancia().guardarCambioEnXCampo(producto);
	    }	
	    
	    //cosas para el log
	    Controlador.getInstancia().crearEntradaLog(producto, fotosViejas, "", "Se han eliminado las fotos de un producto");

	}
	
	//borra las fotos que coinciden con las que me pasan en el array
	public void borrarFotosProducto(String idProducto, List<String> fotos) {
	    Producto producto = ProductoDAO.getInstancia().obtenerPorId(idProducto);
	    
	    String fotosViejas = producto.getFotosFormatoString();

	    if (producto != null) {
	    	producto.borrarFotos(fotos);
	        ProductoDAO.getInstancia().guardarCambioEnXCampo(producto);
	    }	
	    
	    String fotosNuevas = producto.getFotosFormatoString();
	    
	    //cosas para el log
	    Controlador.getInstancia().crearEntradaLog(producto, fotosViejas, fotosNuevas, "Se han eliminado algunas fotos de un producto");

	}
	
	//videos
	public void agregarVideosProducto(String idProducto, List<String> videos) {
	    Producto producto = ProductoDAO.getInstancia().obtenerPorId(idProducto);
	    
	    String videosViejos = producto.getVideosFormatoString();
	    String videosNuevos = String.join(", ", videos);
	    
	    if (producto != null) {
	    	producto.agregarVideos(videos);
	        ProductoDAO.getInstancia().guardarCambioEnXCampo(producto);
	    }	
	    //cosas para el log
	    Controlador.getInstancia().crearEntradaLog(producto, videosViejos, videosNuevos, "Se han agregado videos en un producto");

	}
	
	public void eliminarTodosVideosProducto(String idProducto) {
	    Producto producto = ProductoDAO.getInstancia().obtenerPorId(idProducto);
	    String videosViejos = producto.getVideosFormatoString();
	    if (producto != null) {
	    	producto.eliminarVideos();;
	        ProductoDAO.getInstancia().guardarCambioEnXCampo(producto);
	    }	
	    
	    //cosas para el log
	    Controlador.getInstancia().crearEntradaLog(producto, videosViejos, "", "Se han eliminado los videos de un producto");

	}
	
	public void borrarVideosProducto(String idProducto, List<String> videos) {
	    Producto producto = ProductoDAO.getInstancia().obtenerPorId(idProducto);
	    
	    String videosViejos = producto.getVideosFormatoString();

	    
	    if (producto != null) {
	    	producto.borrarVideos(videos);
	        ProductoDAO.getInstancia().guardarCambioEnXCampo(producto);
	    }	
	    
	    String videosNuevos = producto.getVideosFormatoString();

	    //cosas para el log
	    Controlador.getInstancia().crearEntradaLog(producto, videosViejos, videosNuevos, "Se han eliminado algunos videos de un producto");

	}
	
	//comentarios
	
	public void agregarComentariosProducto(String idProducto, List<String> comentarios) {
	    Producto producto = ProductoDAO.getInstancia().obtenerPorId(idProducto);
	    
	    String comentariosViejos = producto.getComentariosFormatoString();
	    String comentariosNuevos = String.join(", ", comentarios);

	    if (producto != null) {
	    	producto.agregarComentarios(comentarios);
	        ProductoDAO.getInstancia().guardarCambioEnXCampo(producto);
	    }	
	    
	    Controlador.getInstancia().crearEntradaLog(producto, comentariosViejos, comentariosNuevos, "Se han agregado comentarios en un producto");

	    
	}
	
	public void eliminarTodosComentariosProducto(String idProducto) {
	    Producto producto = ProductoDAO.getInstancia().obtenerPorId(idProducto);
	    
	    String comentariosViejos = producto.getComentariosFormatoString();

	    if (producto != null) {
	    	producto.eliminarComentarios();
	        ProductoDAO.getInstancia().guardarCambioEnXCampo(producto);
	    }	
	    
	    //cosas para el log
	    Controlador.getInstancia().crearEntradaLog(producto, comentariosViejos, "", "Se han eliminado los comentarios de un producto");

	}
	
	public void borrarComentariosProducto(String idProducto, List<String> comentarios) {
	    Producto producto = ProductoDAO.getInstancia().obtenerPorId(idProducto);
	    
	    String comentariosViejos = producto.getComentariosFormatoString();

	    if (producto != null) {
	    	producto.borrarComentarios(comentarios);
	        ProductoDAO.getInstancia().guardarCambioEnXCampo(producto);
	    }	
	    
	    String comentariosNuevos = producto.getComentariosFormatoString();

	    //cosas para el log
	    Controlador.getInstancia().crearEntradaLog(producto, comentariosViejos, comentariosNuevos, "Se han eliminado algunos comentarios de un producto");

	    
	}
	
	
	
	
	
	
	//log en redis 
	
	
	public void crearEntradaLog(Producto producto, String valorAntiguo, String valorNuevo, String operador) {
		//LogCatalogoDAO.getInstancia.(Producto producto, String valorAntiguo, String valorNuevo, String operador)
			
	    //ProductoDAO.getInstancia().guardar(nuevoProducto);
		
		LogCatalogoDAO.getInstancia().guardarCambio(producto, valorAntiguo, valorNuevo, operador);
		
	}
	
		
	public void obtenerLog() {
		LinkedHashMap<String, Map<String, String>> cambios = LogCatalogoDAO.getInstancia().obtenerCambiosOrdenados();
	    
	    // Iterar sobre los cambios
	    for (Map.Entry<String, Map<String, String>> entry : cambios.entrySet()) {
	        String claveCambio = entry.getKey();
	        Map<String, String> cambio = entry.getValue();
	
	        // Acceder a los campos individuales del cambio
	        String idProducto = cambio.get("idProducto");
	        String nombreProducto = cambio.get("nombreProducto");
	        String descripcion = cambio.get("descripcion");
	        String precio = cambio.get("precio");
	        String unidadesDisponibles = cambio.get("unidadesDisponibles");
	        String descuento = cambio.get("descuento");
	        // Obtener y trabajar con los demás campos del cambio...
	        String valorAntiguo = cambio.get("valorAntiguo");
	        String valorNuevo = cambio.get("valorNuevo");
	        String operador = cambio.get("operador");
	        // Acceder a las fotos, videos y comentarios del cambio...
	        List<String> fotos4 = new ArrayList<>();
	        List<String> videos = new ArrayList<>();
	        List<String> comentarios = new ArrayList<>();
	        for (Map.Entry<String, String> campo : cambio.entrySet()) {
	            String campoKey = campo.getKey();
	            String campoValue = campo.getValue();
	            if (campoKey.startsWith("foto_")) {
	                fotos4.add(campoValue);
	            } else if (campoKey.startsWith("video_")) {
	                videos.add(campoValue);
	            } else if (campoKey.startsWith("comentario_")) {
	                comentarios.add(campoValue);
	            }
	        }
	
	        // Imprimir los campos del cambio
	        System.out.println("Clave del cambio: " + claveCambio);
	        System.out.println("idProducto: " + idProducto);
	        System.out.println("nombreProducto: " + nombreProducto);
	        System.out.println("descripcion: " + descripcion);
	        System.out.println("precio: " + precio);
	        System.out.println("unidadesDisponibles: " + unidadesDisponibles);
	        System.out.println("descuento: " + descuento);
	        System.out.println("Fotos: " + fotos4);
	        System.out.println("Videos: " + videos);
	        System.out.println("Comentarios: " + comentarios);

	        // Imprimir los demás campos del cambio...
	        System.out.println("operador: " + operador);
	        if (!valorAntiguo.equals("")) {
		        System.out.println("valorAntiguo: " + valorAntiguo);
	        }
	        if(!valorNuevo.equals("")) {
	        	System.out.println("valorNuevo: " + valorNuevo);
	        }

	        System.out.println("-------------------------------------");
	    }
    }
	
	//usar solo para borrar todo el contenido del log
	public void borrarContenidoLog() {
		
		LogCatalogoDAO.getInstancia().borrarContenidoLog();
	
	}
	
	
	
	//carrito
	
	
	public int agregarProductoAlCarrito(String idProducto, int cantidadSeleccionada, String nombreUsuario) {
	    // Obtener el producto desde la base de datos 
	    Producto producto = ProductoDAO.getInstancia().obtenerPorId(idProducto);

	    // Validar que el producto exista en la base de datos
	    if (producto == null) {
	        return 2; // 2 indica que el producto no existe
	    }

	    // Validar que la cantidad seleccionada sea menor o igual a la cantidad de stock disponible
	    if (cantidadSeleccionada <= producto.getUnidadesDisponibles()) {
	        // Agregar el producto al carrito
	        CarritoDAO.getInstancia().agregarProductoAlCarrito(nombreUsuario, producto, cantidadSeleccionada);
	        return 0; // 0 indica éxito
	    } else {
	        return 1; // 1 indica que la cantidad seleccionada es mayor al stock disponible
	    }
	}


	public String mostrarCarrito(String nombreUsuario) {
	    // Obtener la lista de productos en el carrito
	    List<ProductoCarrito> productosCarrito = CarritoDAO.getInstancia().obtenerProductosCarrito(nombreUsuario);

	    // Verificar si el carrito está vacío
	    if (productosCarrito.isEmpty()) {
	        System.out.println("El carrito está vacío");
	    } else {
	        // Recorrer la lista y mostrar los datos de cada producto
	        for (ProductoCarrito productoCarrito : productosCarrito) {
		        System.out.println("Número Producto: " + productoCarrito.getIdProducto());
		        System.out.println("Nombre: " + productoCarrito.getNombre());
	            System.out.println("Precio: " + productoCarrito.getPrecio());
	            System.out.println("Cantidad Seleccionada: " + productoCarrito.getCantidadSeleccionada());
	            System.out.println("Descuento: " + productoCarrito.getDescuento());
	            System.out.println("Precio Final: " + productoCarrito.getPrecioFinal());
	            System.out.println("------------------------------------------");
	        }
	    }

	    // Resto de la lógica del controlador

	    return "carrito"; // Retorna la vista del carrito
	}

	
	public void mostrarTodosLosCarritos(List<String> usuarios) {
	    // Obtener la lista de todos los usuarios
		// eh si todavia no hay una lista de usuarios
	    //List<String> usuarios = UsuarioDAO.getInstancia().obtenerTodosLosUsuarios();

	    // Iterar sobre la lista de usuarios y mostrar el carrito de cada uno
	    for (String usuario : usuarios) {
	        Carrito carrito = CarritoDAO.getInstancia().obtenerCarritoPorUsuario(usuario);
	        System.out.println("Usuario: " + usuario);
	        mostrarProductosCarrito(carrito);
	        System.out.println("------------------------------------------");
	    }
	}

	private void mostrarProductosCarrito(Carrito carrito) {
	    // Obtener la lista de productos en el carrito
	    List<ProductoCarrito> productosCarrito = carrito.getProductosCarrito();

	    // Recorrer la lista y mostrar los datos de cada producto
	    for (ProductoCarrito productoCarrito : productosCarrito) {
	        System.out.println("Número Producto: " + productoCarrito.getIdProducto());
	        System.out.println("Nombre: " + productoCarrito.getNombre());
	        System.out.println("Precio: " + productoCarrito.getPrecio());
	        System.out.println("Cantidad Seleccionada: " + productoCarrito.getCantidadSeleccionada());
	        System.out.println("Descuento: " + productoCarrito.getDescuento());
	        System.out.println("Precio Final: " + productoCarrito.getPrecioFinal());
	        System.out.println("------------------------------------------");
	    }
	}

	
	public void eliminarProductoDelCarrito(String nombreUsuario, String idProducto) {
	    CarritoDAO.getInstancia().eliminarProductoDelCarrito(nombreUsuario, idProducto);
	}

	public void modificarCantidadProducto(String nombreUsuario, String idProducto, int nuevaCantidad) {
	    CarritoDAO.getInstancia().modificarCantidadProductoEnCarrito(nombreUsuario, idProducto, nuevaCantidad);
	}

	
}











