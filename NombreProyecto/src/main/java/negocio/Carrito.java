package negocio;

import java.util.ArrayList;

public class Carrito {

    private ArrayList<Producto> productos;

    //constructor

    public Carrito(){
        productos = new ArrayList<Producto>();
    }

    //setters y getters

    public void setProductos(ArrayList<Producto> listaProductos){
        this.productos = listaProductos;
    }

    public ArrayList<Producto> getProductos(){
        return this.productos;
    }

    //methods

    public void agregarProducto(Producto prod){
        this.productos.add(prod);
    }

    public void eliminarProducto(Producto prod){
        this.productos.remove(prod);
    }

    public void cambiarProducto(Producto prod1, Producto prod2){
        this.productos.remove(prod1);
        this.productos.add(prod2);
    }


}
