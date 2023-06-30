package controlador;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import daos.AlumnoDAO;
import daos.ConnectionPool;
import daos.ProductoDAO;
import negocio.Alumno;
import negocio.EntradaLog;
import negocio.Pedido;
import negocio.Producto;
import negocio.Usuario;
import view.AlumnoView;

//esto seria como un main con las funciones digamos
public class ControllerSistema {

    private static ControllerSistema instancia;
    private ArrayList<Pedido> listaPedidos;
    private ArrayList<Producto> catalogo;
    //private ArrayList<Producto> catalogoViejo;
    private ArrayList<EntradaLog> log;
    private ArrayList<Usuario> usuarios;

    public ControllerSistema() {
        listaPedidos = new ArrayList<Pedido>();
        catalogo = new ArrayList<Producto>();
        //catalogoViejo = new ArrayList<Producto>();
        usuarios = new ArrayList<Usuario>();
        log = new ArrayList<EntradaLog>();
    }

	public static ControllerSistema getInstancia(){
		if(instancia == null)
			instancia = new ControllerSistema();
		return instancia;
	}
    
    //setters
    public void setListaPedidos(ArrayList<Pedido> listaPedidos){
        this.listaPedidos = listaPedidos;
    }

    public void setCatalogo(ArrayList<Producto> catalogo){
        this.catalogo = catalogo;
    }
    /*
    public void setCatalogoViejo(ArrayList<Producto> catalogoViejo){
        this.catalogoViejo = catalogoViejo;
    }
	*/
    public void setListaUsuarios(ArrayList<Usuario> usuarios){
        this.usuarios = usuarios;
    }


    //getters
    public ArrayList<Pedido> getListaPedidos(){
        return this.listaPedidos;
    }

    public ArrayList<Producto> getCatalogo(){
        return this.catalogo;
    }

    /* public ArrayList<Producto> getCatalogoViejo(){
        return this.catalogoViejo;
    } */

    // hay que retornar el log del catalogo realmente
    
    
    
    public ArrayList<Usuario> getListaUsuarios(){
        return this.usuarios;
    }

	public ArrayList<EntradaLog> getLog() {
		return log;
	}

	public void setLog(ArrayList<EntradaLog> log) {
		this.log = log;
	}
	
	public void agregarEntradaLog(EntradaLog entrada) {
		this.log.add(entrada);
	}
	
	//crea un nuevo usuario y lo guarda en la lista de usuarios
	public void crearNuevoUsuario(String username, String nombre, String direccion, String dni, String categoria, String condicionIva) {
		
		Usuario nuevoUsuario = new Usuario(username, nombre, direccion, dni, categoria, condicionIva);
		this.usuarios.add(nuevoUsuario);
		
	}
	
	
	/* 
	 * public void agregarAlumno(int legajo, String nombre){
		
		Alumno alumnito = new Alumno(legajo, nombre);
		AlumnoDAO.getInstancia().guardar(alumnito);
	}
	 */
	
	
	


	/* public AlumnoView recuperarAlumnoPorLegajo(int legajo){
		
		Alumno alumnoConEseLegajo = AlumnoDAO.getInstancia().obtenerPorId(legajo);
		return alumnoConEseLegajo.toView();
	}
	*/

	/*
    public void agregarVideoProducto(Producto prod, String video){
    	
    	//valor anterior es el array de videos anteriores, valor actual
    	// es el valor nuevo de videos.
    	
    	String videosAnteriores = prod.getVideosPretty();

        prod.agregarVideo(video);

        EntradaLog entrada = new EntradaLog(prod, videosAnteriores, prod.getVideosPretty(), "Se agrega un video al producto");
        
        this.agregarEntradaLog(entrada);
    	
    }
    
    public void agregarFotoProducto(Producto prod, String foto){
    	
    	String fotosAnteriores = prod.getFotosPretty();

    	prod.agregarFoto(foto);
    	
        EntradaLog entrada = new EntradaLog(prod, fotosAnteriores, prod.getFotosPretty(), "Se agrega una foto del producto");
        
        this.agregarEntradaLog(entrada);
    	
    }
	
    public void quitarVideoProducto(Producto prod, String video){
    	
    	String videosAnteriores = prod.getVideosPretty();
        prod.eliminarVideo(video);
        EntradaLog entrada = new EntradaLog(prod, videosAnteriores, prod.getVideosPretty(), "Se quita un video al producto");
        this.agregarEntradaLog(entrada);
    
    }
    
    public void quitarFotoProducto(Producto prod, String foto){
    	
    	String fotosAnteriores = prod.getFotosPretty();

    	prod.eliminarFoto(foto);

    	EntradaLog entrada = new EntradaLog(prod, fotosAnteriores, prod.getFotosPretty(), "Se quita una foto al producto");

        this.agregarEntradaLog(entrada);
    	
    }
    
    public void cambiarPrecio(Producto prod, double precio){
        String precioAnterior = String.valueOf(prod.getPrecio());
        String precioNuevo = String.valueOf(precio);
        
        prod.setPrecio(precio);
        
        EntradaLog entrada = new EntradaLog(prod, precioAnterior, precioNuevo, "Se modifica el precio del producto");
        
        this.agregarEntradaLog(entrada);
    }
    
    public void agregarComentarioProducto(Producto prod, String comentario){
    	
    	String comentariosAnteriores = prod.getComentariosPretty();

    	prod.agregarComentario(comentario);

    	EntradaLog entrada = new EntradaLog(prod, comentariosAnteriores, prod.getComentariosPretty(), "Se agrega una comentario al producto");

        this.agregarEntradaLog(entrada);
    	
    }

    public void quitarComentarioProducto(Producto prod, String comentario){
    	
    	String comentariosAnteriores = prod.getComentariosPretty();

    	prod.eliminarComentario(comentario);

    	EntradaLog entrada = new EntradaLog(prod, comentariosAnteriores, prod.getComentariosPretty(), "Se quita un comentario al producto");

        this.agregarEntradaLog(entrada);
    }
	*/
	public void agregarUsuario(Usuario usuario){
        this.usuarios.add(usuario);
    }
    
    public void quitarUsuario(Usuario usuario){
        this.usuarios.remove(usuario);
    }
    
    public void quitarProducto(Producto prod) { 
        this.catalogo.remove(prod);
        
        EntradaLog entrada = new EntradaLog(prod, null, null, "Se elimina un producto al catalogo");
        this.agregarEntradaLog(entrada);
    }
    

	
}