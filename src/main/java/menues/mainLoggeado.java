package menues;

import java.util.List;
import java.util.Scanner;
import java.time.LocalDateTime;

import daos.CarritoDAO;
import negocio.Producto;
import negocio.Usuario;
import negocio.Carrito;
import daos.UsuarioDAO;
import daos.FacturaDAO;
import negocio.Factura;
import controlador.Controlador;


public class mainLoggeado {
    
    private static void mostrarFacturasPendientes(List<Factura> facturas) {
    System.out.println();
    System.out.println("Facturas con pagos pendientes:");
    
    if (facturas.isEmpty()) {
        System.out.println("No hay facturas pendientes.");
    } else {
        for (Factura factura : facturas) {
            System.out.println("-----------------------");
            System.out.println("ID de factura: " + factura.getId());
            System.out.println("Fecha y hora: " + factura.getFechaHora());
            System.out.println("Monto de pago: " + factura.getMontoPago());
            System.out.println("Forma de pago: " + factura.getFormaDePago());
            System.out.println("-----------------------");
        }
    }
}
    
    
    
    private static void cerrarSesion(Usuario usuario){
        UsuarioDAO usuarioDAO = UsuarioDAO.getInstancia();
        
        LocalDateTime horaCierreSesion = LocalDateTime.now();
        usuarioDAO.actualizarHoraCierreSesion(usuario, horaCierreSesion);
    }

    public static void loggeado(Usuario usuario,Scanner scanner) {
        System.out.println();
        System.out.println("¡BIENVENIDO AL GESTOR DE PEDIDOS!");

        boolean continuar = true;
        while (continuar) {
            System.out.println();
            System.out.println("-----------------------");
            System.out.println("1. Ver catalogo");
            System.out.println("2. Ver carrito");
            System.out.println("3. Ver facturas con pagos pendientes");
            System.out.println("4. Ir al menú anterior");
            System.out.println("-----------------------");
            System.out.println();
            System.out.print("Ingrese una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            switch (opcion) {
                case 1:
                    List<Producto> catalogo = Controlador.getInstancia().getProductosCatalogo();
                    mainCatalogo.mostrarCatalogo(catalogo);
                    
                    //mostrarCatalogo(catalogo); 
                    mainCatalogo.opcionesCatalogo(catalogo,usuario,scanner);
                    break;
                case 2:
                    CarritoDAO carritoDAO = CarritoDAO.getInstancia();
                    Carrito carrito = carritoDAO.obtenerCarritoPorUsuario(usuario.getNombre());
                    mainCarrito.mostrarCarrito(carrito);
                    mainCarrito.opcionesCarrito(carrito,usuario,scanner);
                    break;
                case 3:
					// Mostrar facturas con pagos pendientes
                	//por ahora muestra todas.
                	FacturaDAO facturaDAO = FacturaDAO.getInstancia();
                	List<Factura> facturas = facturaDAO.mostrarFacturasPorUsuario(usuario.getId());
                	mostrarFacturasPendientes(facturas); // Implementa este método según tus necesidades
					
                	break;
                case 4:
                	cerrarSesion(usuario);
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