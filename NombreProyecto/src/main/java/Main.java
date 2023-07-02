import java.util.ArrayList;
import java.util.Scanner;

import negocio.Producto;

public class Main {
	
	private static void mostrarCatalogo() {
        // esos productos los tengo que traer de la base de datos y la idea es 
		// mostrarlos a todos
		System.out.println("Catálogo de productos:");
        for (int i = 0; i < productos.size(); i++) {
            int numeroProducto = i + 1;
            Producto producto = productos.get(i);
            
            // fijarme si esto quedó igual a lo que está en test asi se ve bien
            System.out.println(numeroProducto + ". " + "Nombre: " + producto.getNombreProducto());
            System.out.println("Descripción: " + producto.getDescripcion());
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
	
	
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        boolean continuar = true;
        while (continuar) {
            System.out.println("1. Iniciar Sesión");
            System.out.println("2. Mostrar catálogo");
            System.out.println("3. Salir");
            System.out.print("Ingrese una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            switch (opcion) {
                case 1:
                    //agregarProducto(scanner);
                    break;
                case 2:
                    mostrarCatalogo();
                    break;
                case 3:
                    continuar = false;
                    break;
                default:
                    System.out.println("Opción inválida");
                    break;
            }
        }

        System.out.println("¡Hasta luego!");
        scanner.close();
    }
       
}