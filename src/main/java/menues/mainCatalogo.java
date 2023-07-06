package menues;

import negocio.Producto;
import daos.CarritoDAO;
import daos.ProductoDAO;
import negocio.Carrito;
import negocio.Usuario;

import java.util.List;
import java.util.Scanner;

public class mainCatalogo {
    

    public static void buscarProducto(List<Producto> catalogo, Usuario usuario, Scanner scanner){
        
        ProductoDAO productoDAO = ProductoDAO.getInstancia();
        CarritoDAO carritoDAO = CarritoDAO.getInstancia();
        
        System.out.println();
        System.out.print("Ingrese el nombre del producto que desea buscar: ");
        String nombreProducto = scanner.nextLine();
        
        Producto resultadoBusqueda = productoDAO.obtenerPorNombre(nombreProducto);
        
        if(resultadoBusqueda != null){
            System.out.println("Tu búsqueda arrojó estas respuestas: ");
            
            System.out.println(resultadoBusqueda.getNombreProducto());
            System.out.println("Descripción: " + resultadoBusqueda.getDescripcion());
            System.out.println("Fotos: " + resultadoBusqueda.getFotos());
            System.out.println("Videos: " + resultadoBusqueda.getVideos());
            System.out.println("Comentarios: " + resultadoBusqueda.getComentarios());

            if (resultadoBusqueda.getDescuento() != 0) {
                System.out.println("Precio normal: $ " + resultadoBusqueda.getPrecio());
                System.out.println("Precio con descuento: $ " + resultadoBusqueda.getPrecio() * (1 - resultadoBusqueda.getDescuento()));
            } else {
                System.out.println("Precio: $ " + resultadoBusqueda.getPrecio());
            }
            
            System.out.println("Unidades disponibles: " + resultadoBusqueda.getUnidadesDisponibles());
            System.out.println();
            System.out.println("-----------------------");
            
            boolean continuar = true;
        	while (continuar) {
	            System.out.println("1. Añadir producto al carrito");
	            System.out.println("2. Volver al menú principal");
	            System.out.println("Qué desea hacer con el producto encontrado?");
	            int opcion = scanner.nextInt();
	            scanner.nextLine();
	            
	            switch(opcion){
	                case 1:
		                System.out.println("Que cantidad desea agregar de este producto?");
		                int cantidad = scanner.nextInt();
	                	carritoDAO.agregarProductoAlCarrito(usuario.getNombre(), resultadoBusqueda, cantidad);
	                	System.out.println("Producto agregado!");
	                	break;
	                case 2:
	                	continuar = false;
	                	opcionesCatalogo(catalogo,usuario,scanner);
	                	break;
	                default:
	                    System.out.println("Opción inválida");
	                    break;
	            }
            }
            
        } else {
            System.out.println("No encontramos nada en nuestro catálogo que coincida con tu busqueda, lo sentimos :(");
        }
    }
    
    public static void mostrarCatalogo(List<Producto> catalogo){
		// esos productos los tengo que traer de la base de datos y la idea es mostrarlos a todos
        System.out.println();
        System.out.println("Catálogo de productos:");
        for (int i = 0; i < catalogo.size(); i++) {
            System.out.println();
            int numeroProducto = i + 1;
            Producto producto = catalogo.get(i);

            // fijarme si esto quedó igual a lo que está en test asi se ve bien
            System.out.println(numeroProducto + ". " + "Nombre: " + producto.getNombreProducto());
            System.out.println("Descripción: " + producto.getDescripcion());
            System.out.println("Fotos: " + producto.getFotos());
            System.out.println("Videos: " + producto.getVideos());
            System.out.println("Comentarios: " + producto.getComentarios());

            if (producto.getDescuento() != 0) {
                System.out.println("Precio normal: $ " + producto.getPrecio());
                System.out.println("Precio con descuento: $ " + producto.getPrecio() * (1 - producto.getDescuento()));
            } else {
                System.out.println("Precio: $ " + producto.getPrecio());
            }
            System.out.println("Unidades disponibles: " + producto.getUnidadesDisponibles());
            System.out.println();
            System.out.println("-----------------------");
        }
    }

    public static void opcionesCatalogo(List<Producto> catalogo, Usuario usuario, Scanner scanner){
        CarritoDAO carritoDAO = CarritoDAO.getInstancia();
        
        boolean continuar = true;
        while (continuar) {
            System.out.println();
            System.out.println("-----------------------");
            System.out.println("1. Buscar producto");
            System.out.println("2. Añadir producto al carrito");
            System.out.println("3. Ver carrito");
            System.out.println("4. Confirmar carrito");
            System.out.println("5. Ir al menú anterior");
            System.out.println("-----------------------");
            System.out.println();
            System.out.print("Ingrese una opción: ");

            scanner.nextLine();
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            switch (opcion) {
                case 1:
                    buscarProducto(catalogo,usuario,scanner);
                case 2:
                    System.out.println();
                	System.out.println("Qué producto desea agregar? Ingrese su nombre: ");
                	String nombreProducto = scanner.nextLine();
                	System.out.println("Y que cantidad de este producto? ");
                	int cantidad = scanner.nextInt();
                	
                	carritoDAO.agregarProductoAlCarrito(usuario.getNombre(), ProductoDAO.getInstancia().obtenerPorNombre(nombreProducto), cantidad);
                    break;
                case 3:
                    Carrito carritoUsuario = carritoDAO.obtenerCarritoPorUsuario(usuario.getNombre());
                    mainCarrito.mostrarCarrito(carritoUsuario);
                    mainCarrito.opcionesCarrito(carritoUsuario, usuario,scanner);
                    break;
                case 4:
                    //confirmar el carrito
                    break;
                case 5:
                	continuar = false;
                    break;
                default:
                    System.out.println("Opción inválida");
                    break;
            }
        }

        System.out.println("¡Hasta luego!");
    }
}