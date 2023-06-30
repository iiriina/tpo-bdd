package negocio;

import javax.persistence.Entity;

@Entity
public class Usuario {

	private String username;

    private String nombre;
    private String direccion;
    private String dni;
    private String categoria;
    private String condicionIva;

    // Constructor
    public Usuario(String username, String nombre, String direccion, String dni, String categoria, String condicionIva){
        
    	this.setUsername(username);
    	this.nombre = nombre;
        this.direccion = direccion;
        this.dni = dni;
        this.categoria = categoria;
        this.condicionIva = condicionIva;
    }

    // Set y get

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

    
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getDireccion() {
        return this.direccion;
    }

    public void setdni(String dni) {
        this.dni = dni;
    }

    public String getdni() {
        return this.dni;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getCategoria() {
        return this.categoria;
    }

    public void setCondicionIva(String condicion) {
        this.condicionIva = condicion;
    }

    public String getCondicionIva() {
        return this.condicionIva;
    }


}