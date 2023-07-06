package menues;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;
import java.math.BigDecimal;

import negocio.ProductoCarrito;
import negocio.Carrito;
import negocio.Producto;
import negocio.Usuario;
import negocio.Pedido;
import daos.CarritoDAO;
import daos.PedidoDAO;
import daos.ProductoDAO;

public class mainCarrito {
    
    public static Pedido pasarAPedido(Carrito carrito){
        Double totalCarrito = 0.0;
        
        List<ProductoCarrito> carritoList = carrito.getProductosCarrito();
        for (int i = 0; i < carritoList.size(); i++) {
            ProductoCarrito producto = carritoList.get(i);
            totalCarrito += producto.getPrecio();
        }
        
        BigDecimal total = new BigDecimal(totalCarrito);
        
        UUID idPedido = UUID.randomUUID();
        long idPedidoInt = idPedido.getMostSignificantBits();
        int intPedido = (int) (idPedidoInt >>> 32);
        
        double impuestos = totalCarrito * 0.21;
        BigDecimal impuestosTotal = new BigDecimal(impuestos);
        
        Pedido pedido = new Pedido(intPedido,total,impuestosTotal);
        
        return pedido;
    }

    public static void mostrarCarrito(Carrito carrito) {

        System.out.println();
        System.out.println("Tu carrito:");
        Double totalCarrito = 0.0;
        
        List<ProductoCarrito> carritoList = carrito.getProductosCarrito();
        for (int i = 0; i < carritoList.size(); i++) {
            System.out.println();
            int numeroProducto = i + 1;
            ProductoCarrito producto = carritoList.get(i);
            totalCarrito += producto.getPrecio();

            System.out.println(numeroProducto + ". " + "Nombre: " + producto.getNombre());
            System.out.println("Precio unitario: " + producto.getPrecio());
            System.out.println("Cantidad seleccionada: " + producto.getCantidadSeleccionada());
            System.out.println("Descuento: " + producto.getDescuento());

            if (producto.getDescuento() != 0) {
                System.out.println("Precio normal: $ " + producto.getPrecio());
                System.out.println("Precio con descuento: $ " + producto.getPrecio() * (1 - producto.getDescuento()));
            } else {
                System.out.println("Precio: $ " + producto.getPrecio());
            }
            System.out.println();

            System.out.println("-----------------------");
        }
        System.out.println("TOTAL CARRITO: $" + totalCarrito);
    }

    public static void opcionesCarrito(Carrito carrito, Usuario usuario, Scanner scanner) {
        CarritoDAO carritoDAO = CarritoDAO.getInstancia();


        boolean continuar = true;
        while (continuar) {
            System.out.println();
            System.out.println("-----------------------");
            System.out.println("1. Agregar producto");
            System.out.println("2. Eliminar producto");
            System.out.println("3. Ver carrito");
            System.out.println("4. Comprar carrito");
            System.out.println("5. Ir al menú anterior");
            System.out.println("6. Modificar cantidad producto");
            System.out.println("-----------------------");
            System.out.println();
            System.out.print("Ingrese una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            switch (opcion) {
                case 1:
                	System.out.println();
                	System.out.println("Qué producto desea agregar? Ingrese su nombre: ");
                	String nombreProducto = scanner.nextLine();
                	System.out.println("Y que cantidad de este producto? ");
                	int cantidad = scanner.nextInt();
                	
                	carritoDAO.agregarProductoAlCarrito(usuario.getNombre(), ProductoDAO.getInstancia().obtenerPorNombre(nombreProducto), cantidad);
                    break;
                case 2:
                	System.out.println("Qué producto desea eliminar? Ingrese su nombre: ");
                	String nombreProductoElegido = scanner.nextLine();
                	ProductoDAO productoDAO = ProductoDAO.getInstancia();
                	Producto productoAEliminar = productoDAO.obtenerPorNombre(nombreProductoElegido);
                	carritoDAO.eliminarProductoDelCarrito(usuario.getNombre(), productoAEliminar.getIdProducto());
                	break;
                case 3:
                    Carrito carritoUsuario = carritoDAO.obtenerCarritoPorUsuario(usuario.getNombre());

                    mostrarCarrito(carritoUsuario);
                    break;
                case 4:
                	PedidoDAO pedidoDAO = PedidoDAO.getInstancia();
                	Pedido pedido = pasarAPedido(carrito);
                    System.out.println("print mainCarrito opcionesCarrito "+pedido);
                    System.out.println("print mainCarrito opcionesCarrito "+pedido.getId());
                    carritoUsuario = carritoDAO.obtenerCarritoPorUsuario(usuario.getNombre());
                	pedidoDAO.guardarPedido(pedido, carritoUsuario);
                	pedidoDAO.mostrarPedido(pedido.getId());
                	break;
                case 5:
                    continuar = false;
                    break;
               //esto lo agregue para modificar cantidad de X producto en el carrito
                case 6:
                	System.out.println("A que producto le interesa cambiarle la cantidad? Ingrese su nombre: ");
                	nombreProductoElegido = scanner.nextLine();
                	System.out.println("Ingrese la nueva cantidad: ");
                	cantidad = scanner.nextInt();
                	productoDAO = ProductoDAO.getInstancia();
                	Producto productoAModificar = productoDAO.obtenerPorNombre(nombreProductoElegido);
                	String idProductoAmodificar = productoAModificar.getIdProducto();
                	carritoDAO.modificarCantidadProductoEnCarrito(usuario.getNombre(), idProductoAmodificar, cantidad);
                    break;

                default:
                    System.out.println("Opción inválida");
                    break;
            }
        }
    }

}
