package negocio;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Producto {

    @Id
    private String idProducto;
    
    private String nombreProducto; 
    
    private String descripcion;
    
    @ElementCollection
    private List<String> fotos = new ArrayList<String>();

    private List<String> comentarios = new ArrayList<String>();

    private List<String> videos = new ArrayList<String>();

    private double precio;
    private int unidadesDisponibles;

	private double descuento;

    public Producto(String idProducto, String nombreProducto, String descripcion, double precio, int unidadesDisponibles, double descuento) {
        this.idProducto = idProducto;
        this.nombreProducto = nombreProducto;
        this.descripcion = descripcion;
        this.precio = precio;
        this.unidadesDisponibles = unidadesDisponibles;
        this.descuento = descuento;
    }

    public String getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(String idProducto) {
        this.idProducto = idProducto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<String> getFotos() {
    	
        return this.fotos;
        
    }

    public void setFotos(List<String> fotos) {
        this.fotos = fotos;
    }

    public List<String> getComentarios() {
        return comentarios;
    }

    public void setComentarios(ArrayList<String> comentarios) {
        this.comentarios = comentarios;
    }

    public List<String> getVideos() {
        return videos;
    }

    public void setVideos(List<String> videos) {
        this.videos = videos;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getUnidadesDisponibles() {
        return unidadesDisponibles;
    }

    public void setUnidadesDisponibles(int unidadesDisponibles) {
        this.unidadesDisponibles = unidadesDisponibles;
    }
    
    //testeado que anda con 1 o muchas fotos, las agrega al array que puede
    // o no, contener fotos ya cargadas dentro.
    public void agregarFotos(List<String> fotos) {
        for (String foto : fotos) {
            this.fotos.add(foto);
        }
    }
    
    public void agregarVideos(List<String> videos) {
        for (String video : videos) {
            this.videos.add(video);
        }
    }
    
    public void agregarComentarios(List<String> comentarios) {
        for (String comentario : comentarios) {
            this.comentarios.add(comentario);
        }
    }
    
    //elimina TODOS los comentarios, fotos o videos segun corresponda
    public void eliminarFotos() {
        this.fotos.clear();
    }
    
    public void eliminarVideos() {
        this.videos.clear();

    }

    public void eliminarComentarios() {
    	this.comentarios.clear();
    }
    
    //borra las fotos que coinciden con las que mandan por parametro.
    //si tengo foto 1 2 y 3 y en el array me mandan foto 1 y 2, 
    // en this.fotos va a quedar la foto 3.
    public void borrarFotos(List<String> fotos) {
    	
        this.getFotos().removeAll(fotos);
    }

    
    public void borrarVideos(List<String> videos) {
    	
        this.getVideos().removeAll(videos);

    }
    
    public void borrarComentarios(List<String> comentarios) {

    	this.getComentarios().removeAll(comentarios);

    }
    
    
	public double getDescuento() {
		return descuento;
	}

	public void setDescuento(double descuento) {
		this.descuento = descuento;
	}

	public String getNombreProducto() {
		return nombreProducto;
	}

	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}
}
