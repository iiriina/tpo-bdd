package negocio;

import java.util.ArrayList; 
import java.time.LocalDateTime;

public class Usuario {
    private int id;
    private String nombre;
    private String direccion;
    private int dni;
    private String categoria;
    private String condicionIVA;
    private String contraseña;
    private LocalDateTime horaInicio;
    private LocalDateTime horaCierre;
    private ArrayList<Integer> tiemposConexion;
    private ArrayList<Factura> listaFacturas;
    private ArrayList<Pago> listaPagos;
    
        
    public Usuario(int id, String nombre, String direccion, int dni, String categoria, String condicionIVA, String contraseña) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.dni = dni;
        this.categoria = categoria;
        this.condicionIVA = condicionIVA;
        this.contraseña = contraseña;
        this.listaFacturas = new ArrayList<Factura>();
        this.listaPagos = new ArrayList<Pago>();
        this.horaInicio = null;
        this.horaCierre = null;
        this.tiemposConexion = new ArrayList<Integer>();
    }
    
    // Getters y setters
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getDireccion() {
        return direccion;
    }
    
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
    public int getDni() {
        return dni;
    }
    
    public void setDni(int dni) {
        this.dni = dni;
    }
    
    public String getCategoria() {
        return categoria;
    }
    
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    
    public String getCondicionIVA() {
        return condicionIVA;
    }
    
    public void setCondicionIVA(String condicionIVA) {
        this.condicionIVA = condicionIVA;
    }
    
    public String getContraseña() {
        return contraseña;
    }
    
    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
    
	public LocalDateTime getHoraInicio(){
        return horaInicio;
    }
    
    public void setHoraInicio(LocalDateTime horaInicio){
        this.horaInicio = horaInicio;
    }
    
	public LocalDateTime getHoraCierre(){
        return horaCierre;
    }
    
    public void setHoraCierre(LocalDateTime horaCierre){
        this.horaCierre = horaCierre;
    }
    
    public ArrayList<Integer> getTiemposConexion(){
        return tiemposConexion;
    }
    
    public void setTiemposConexion(ArrayList<Integer> tiempos){
        this.tiemposConexion = tiempos;
    }
    
    public ArrayList<Pago> getListaPagos() {
        return listaPagos;
    }

    public void setListaPagos(ArrayList<Pago> listaPagos) {
        this.listaPagos = listaPagos;
    }

    public ArrayList<Factura> getListaFacturas() {
        return listaFacturas;
    }
    
    public void setListaFacturas(ArrayList<Factura> listaFacturas) {
        this.listaFacturas = listaFacturas;
    }
    
    public void calcularCategoria(){
        double promedio = tiemposConexion.stream().mapToInt(Integer::intValue).average().orElse(0.0);
        
        if (promedio > 240) {
        	this.setCategoria("TOP");
	    } else if (promedio > 120) {
	        this.setCategoria("MEDIUM");
	    } else {
	        this.setCategoria("LOW");
    }
    }

}
