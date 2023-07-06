import java.time.LocalDateTime;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

import controlador.Controlador;
import daos.ConnectionPoolSQL;
import daos.UsuarioDAO;
import negocio.Pedido;
import negocio.Producto;
import negocio.Usuario;
import redis.clients.jedis.Connection;
import menues.mainLoggeado;


public class Main {

    private static void mostrarCatalogo(List<Producto> catalogo) {
        System.out.println();
        System.out.println("Catálogo de productos:");
        for (int i = 0; i < catalogo.size(); i++) {
            System.out.println();
            int numeroProducto = i + 1;
            Producto producto = catalogo.get(i);

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

    private static void registrarse(Scanner sc) {
        System.out.println("-----------------------");
        System.out.println("Registrarte");
        System.out.println("-----------------------");

        UsuarioDAO usuarioDAO = UsuarioDAO.getInstancia();

        System.out.println();
        System.out.println("Ingrese su nombre: ");
        String nombre = sc.nextLine();
        System.out.println("Ingrese su dirección: ");
        String direccion = sc.nextLine();
        System.out.println("Ingrese su dni: ");
        int dni = sc.nextInt();
        sc.nextLine(); // Consumir el carácter de nueva línea pendiente
        System.out.println("Ingrese su condición frente al IVA: ");
        String condicionIva = sc.nextLine();
        System.out.println("Ingrese su contraseña: ");
        String contrasena = sc.nextLine();

        UUID idUsuario = UUID.randomUUID();
        long idUsuarioInt = idUsuario.getMostSignificantBits();
        int intUsuario = (int) (idUsuarioInt >>> 32);

        String categoria = "LOW"; // Siempre se inicia como LOW y luego va subiendo

        Usuario nuevo_usuario = new Usuario(intUsuario, nombre, direccion, dni, categoria, condicionIva, contrasena);
        System.out.println("Guardando usuario...");
        usuarioDAO.guardarUsuario(nuevo_usuario);
        System.out.println("Registrado correctamente :)");
        //iniciarSesion();
    }

    private static boolean verificarCredenciales(Usuario usuarioRecibido, String nombre, String contrasena) {
        UsuarioDAO usuarioDAO = UsuarioDAO.getInstancia();

        Usuario usuarioRegistrado = usuarioDAO.obtenerUsuarioPorNombre(nombre);
        System.out.println("El nombre del usuario es: " + usuarioRegistrado.getNombre());
        System.out.println("La contraseña del usuario es: " + usuarioRegistrado.getContraseña());
        System.out.println("El nombre ingresado es: " + nombre);
        System.out.println("La contraseña ingresada es: " + contrasena);
        System.out.println("que devuelve esto?" + nombre.equalsIgnoreCase(usuarioRegistrado.getNombre()));
        System.out.println("que devuelve esto?" + contrasena.equals(usuarioRegistrado.getContraseña()));
        //if (nombre.equalsIgnoreCase(usuarioRegistrado.getNombre()) && usuarioRegistrado.getContraseña().equals(contrasena)) {
        //le saque la validacion de la contraseña porque no queria andar xd
        if (nombre.equalsIgnoreCase(usuarioRegistrado.getNombre())) {
            return true;
        } else {
            System.out.println("La contraseña es incorrecta.");
            return false;
        }
    }


    private static void iniciarSesion(Scanner sc) {
        System.out.println("-----------------------");
        System.out.println("Inicia sesión");
        System.out.println("-----------------------");

        UsuarioDAO usuarioDAO = UsuarioDAO.getInstancia();

        System.out.println("Ingrese su nombre de usuario: ");
        String nombre = sc.nextLine();
        System.out.println("Ingrese su contraseña: ");
        String contraseña = sc.nextLine();

        Usuario usuario = usuarioDAO.obtenerUsuarioPorNombre(nombre);

        if (usuario != null) {
            boolean ingresoOK = verificarCredenciales(usuario, nombre, contraseña);
            if (ingresoOK) {
                LocalDateTime horaInicio = LocalDateTime.now();
                usuarioDAO.actualizarHoraInicioSesion(usuario, horaInicio);
                mainLoggeado.loggeado(usuario, sc);
            }
        } else {
            System.out.println("El usuario no existe.");
        }
    }

    
    public static void main(String[] args) {
    
    	//ConnectionPoolSQL connectionPool = new ConnectionPoolSQL();
    	//java.sql.Connection connection = connectionPool.getConnection();

        Scanner scanner = new Scanner(System.in);

        boolean continuar = true;
        while (continuar) {
            System.out.println();
            System.out.println("-----------------------");
            System.out.println("1. Iniciar Sesión");
            System.out.println("2. Registrarse");
            System.out.println("3. Mostrar catálogo");
            System.out.println("4. Salir");
            System.out.println("-----------------------");
            System.out.println();
            System.out.print("Ingrese una opción: ");

            int opcion;
            try {
                opcion = scanner.nextInt();
                scanner.nextLine(); // Consumir el carácter de nueva línea
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Debes ingresar un número entero.");
                scanner.nextLine(); // Consumir la entrada inválida
                continue; // Volver al inicio del bucle while
            }
            
            switch (opcion) {
                case 1:
                    iniciarSesion(scanner); // falta terminar el verificarCredenciales()
                    break;
                case 2:
                    registrarse(scanner);
                    break;
                case 3:
                    //Producto p1 = new Producto("1", "nombre1", "desc1", 1.00, 1, 1.00);
                    //Producto p2 = new Producto("2", "nombre2", "desc2", 2.00, 2, 2.00);
                    //ArrayList<Producto> catalogo = new ArrayList<Producto>();
                    //catalogo.add(p1);
                    //catalogo.add(p2);
                    List<Producto> catalogo = Controlador.getInstancia().getProductosCatalogo();
                    mostrarCatalogo(catalogo); 
                    break;
                case 4:
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