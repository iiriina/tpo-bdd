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

    private String descripcion;
    
    @ElementCollection
    private List<String> fotos = new ArrayList<String>();

    private List<String> comentarios;

    private List<String> videos;

    private double precio;
    private int unidadesDisponibles;

    public Producto(String idProducto, String descripcion, double precio, int unidadesDisponibles) {
        this.idProducto = idProducto;
        this.descripcion = descripcion;
        this.comentarios = new ArrayList<>();
        this.videos = new ArrayList<>();
        this.precio = precio;
        this.unidadesDisponibles = unidadesDisponibles;
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
    	
        System.out.println(this.fotos);
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

    public void agregarFoto(String foto) {

        fotos.add(foto);
        System.out.println("se agregan las fotos ?: ");
        System.out.println(this.fotos);
    }
    /*
    public void eliminarFoto(Foto foto) {
        if (fotos != null) {
            fotos.remove(foto);
        }
    }
*/
    /* public String getFotosPretty() {
        if (fotos == null || fotos.isEmpty()) {
            return "No hay fotos disponibles";
        } else {
            List<String> fotoUrls = new ArrayList<>();
            for (Foto foto : fotos) {
                fotoUrls.add(foto.getUrl());
            }
            return String.join(", ", fotoUrls);
        }
    }*/
}
